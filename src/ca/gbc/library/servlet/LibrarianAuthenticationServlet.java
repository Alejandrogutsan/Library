package ca.gbc.library.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ca.gbc.library.beans.User;
import ca.gbc.library.include.Authentication;

@WebServlet("/LibrarianAuthenticate")
public class LibrarianAuthenticationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LibrarianAuthenticationServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = new User();
		Authentication auth = new Authentication();
		
		boolean authCheck = auth.librarianAuth(request.getParameter("librarianEmail"), request.getParameter("librarianPassword"));
		
		if (authCheck) {
			user.setEmail(request.getParameter("librarianEmail"));
			user.setPassword(request.getParameter("librarianPassword"));
			user.setRole("librarian");
			request.setAttribute("librarianUser", user);
			log("User " + user.getEmail() + 
					" from " + getServletContext().getInitParameter("School") +
					" in " + getServletContext().getInitParameter("Locale") +
					" has successfully logged in");
			request.getRequestDispatcher("librarianHome.html").forward(request, response);
		}
		else
		{
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.print("<h2>Invalid Credentials Provided!</h2>");
			request.getRequestDispatcher("index.html").include(request, response);
		}
	}

}
