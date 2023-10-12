package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.User;
import dao.DBOperations;

public class RegisterServlet extends HttpServlet
{
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		res.setContentType("text/html");
		User user = new User();
		user.setUsername(req.getParameter("username"));
		user.setPassword(req.getParameter("password"));
		user.setFirstname(req.getParameter("firstname"));
		user.setLastname(req.getParameter("lastname"));
		user.setEmail(req.getParameter("email"));
		PrintWriter out = res.getWriter();
		
		
		
		if(!DBOperations.insert(user))
		{
			out.print("<h3>Successfully registered as my friend!!!</h3>");
			out.print("<h3>Login to continue....</h3>");
			req.getRequestDispatcher("home.html").include(req, res);
		}
		else
		{
			out.print("Fake Friend Spotted!!! Try Registering Again....");
			req.getRequestDispatcher("home.html").include(req, res);
			}
		
		
	}

}
