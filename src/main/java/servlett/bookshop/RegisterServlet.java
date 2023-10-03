package servlett.bookshop;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/register")
public class RegisterServlet extends HttpServlet 
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		doGet(req,res);
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();

		String bookName = req.getParameter("bookName");
		String bookEdition = req.getParameter("bookEdition");
		float bookPrice = Float.parseFloat(req.getParameter("bookPrice"));
		
		
		try(
			Connection con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement("INSERT INTO bookshop_servlet(bookname,bookedition,bookprice) VALUES (?,?,?)");
				)
		{
			ps.setString(1, bookName);
			ps.setString(2, bookEdition);
			ps.setFloat(3, bookPrice);
			
			int count = ps.executeUpdate();
			
			if(count==1)
			{
				out.print("<h2>Record Is Registered Successfully.</h2>");
			}
			else
			{
				out.print("<h2>Record Is not Registered.</h2>");
			}
			out.print("<a href='home.html'>Home</a>");
			out.print("<br>");
			out.print("<a href='booklist'>Book List</a>");
			
		}
		catch(Exception e)
		{
			out.print(e);
			out.print("<h1>error :- "+e.getMessage()+"</h1>");
		}	
		
	}

}
