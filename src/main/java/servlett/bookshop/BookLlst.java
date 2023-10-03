package servlett.bookshop;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;;

@WebServlet("/booklist")
public class BookLlst extends HttpServlet
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
		
		
		try(
			Connection con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT id,bookname,bookedition,bookprice FROM bookshop_servlet");
				)
		{
			ResultSet rs = ps.executeQuery();
			

			out.print("<table border='1' align='center'>");
			out.print("<tr>");
			out.print("<th>Book ID</th>");
			out.print("<th>Book Name</th>");
			out.print("<th>Book Edition</th>");
			out.print("<th>Book Price</th>");
			out.print("<th>Edit</th>");
			out.print("<th>Delete</th>");
			out.print("</tr>");
			while(rs.next()) 
			{ 
				out.print("<tr>");
				out.print("<td>"+rs.getInt("id")+"</td>");
				out.print("<td>"+rs.getString("bookname")+"</td>");
				out.print("<td>"+rs.getString("bookedition")+"</td>");
				out.print("<td>"+rs.getFloat("bookprice")+"</td>");

				out.print("<td><a href='editScreen?id="+rs.getInt("id")+"'>edit</a></td>");
				out.print("<td><a href='deleteurl?id="+rs.getInt("id")+"'>delete</a></td>");
				out.print("</tr>");
			}
			out.print("</table>");
			 
			
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
