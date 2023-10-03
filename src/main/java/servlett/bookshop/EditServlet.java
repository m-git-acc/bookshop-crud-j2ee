package servlett.bookshop;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/editurl")
public class EditServlet extends HttpServlet
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
		
		int id = Integer.parseInt(req.getParameter("id"));


		String bookName = req.getParameter("bookName");
		String bookEdition = req.getParameter("bookEdition");
		float bookPrice = Float.parseFloat(req.getParameter("bookPrice"));
		
		
		try(
			Connection con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement("UPDATE bookshop_servlet SET bookname=?,bookedition=?,bookprice=? WHERE id=?");
				)
		{
			ps.setString(1, bookName);
			ps.setString(2, bookEdition);
			ps.setFloat(3, bookPrice);
			ps.setFloat(4, id);
			
			int n = ps.executeUpdate();
			
			if(n==1)
			{
				out.print("<h2>Record is Edited Successfully</h2>");
			}
			else 
			{
				out.print("<h2>Record is not Edited.</h2>");				
			}
			
		}
		catch(Exception e)
		{
			out.print(e);
			out.print("<h1>error :- "+e.getMessage()+"</h1>");
		}	
		out.print("<br>");
		out.print("<a href='home.html'>Home</a>");
		out.print("<br>");
		out.print("<a href='booklist'>BookList</a>");
		
	}
}
