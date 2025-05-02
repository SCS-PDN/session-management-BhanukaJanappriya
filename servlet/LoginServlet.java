package servlet;

import service.UserService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // Authenticate user
        if (UserService.authenticate(username, password)) {
            // Create session
            HttpSession session = request.getSession(true);
            session.setAttribute("username", username);
            
            // Set cookie
            Cookie userCookie = new Cookie("username", username);
            userCookie.setMaxAge(30 * 60); // Cookie expires in 30 minutes
            response.addCookie(userCookie);
            
            // Redirect to dashboard
            response.sendRedirect("dashboard");
        } else {
            // Authentication failed, redirect back to login with error
            response.sendRedirect("login.jsp?error=true");
        }
    }
}