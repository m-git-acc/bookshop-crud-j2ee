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

@WebServlet("/editScreen")
public class EditScreen extends HttpServlet 
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
		
		
		try(
			Connection con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT id,bookname,bookedition,bookprice FROM bookshop_servlet WHERE id=?");
				)
		{
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
			{
				out.print("<form action='editurl?id="+id+"' method='post'>");
	
				out.print("<table align='center'>");
	
				out.print("<tr>");
				out.print("<td>Book Name</td>");
				out.print("<td><input type='text' name='bookName' value='"+rs.getString("bookname")+"'</td>");
				out.print("</tr>");
				
				out.print("<tr>");
				out.print("<td>Book Edition</td>");
				out.print("<td><input type='text' name='bookEdition' value='"+rs.getString("bookedition")+"'</td>");
				out.print("</tr>");
				
				out.print("<tr>");
				out.print("<td>Book Price</td>");
				out.print("<td><input type='text' name='bookPrice' value='"+rs.getFloat("bookprice")+"'</td>");
				out.print("</tr>");
				
	
				out.print("</tr>");
				out.print("<td><input type='submit' value='Edit'></td>");
				out.print("<td><input type='reset' value='Cancel'></td>");
				out.print("</tr>");
				
				out.print("</table>");
				out.print("</form>");
			}
			else
			{
				out.print("NO DATA FOUND");
				RequestDispatcher rd = req.getRequestDispatcher("home.html");
				rd.include(req, res);
			}	
			rs.close();
		}
		catch(Exception e)
		{
			out.print(e);
			out.print("<h1>error :- "+e.getMessage()+"</h1>");
		}	
		
		out.print("<br>");
		out.print("<a href='home.html'>Home</a>");
		
	}

}
