package servlet;

import model.Course;
import service.CourseService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
  
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.html");
            return;
        }
        
        String username = (String) session.getAttribute("username");
        request.setAttribute("username", username);
        
        String successMessage = request.getParameter("success");
        if (successMessage != null) {
            request.setAttribute("successMessage", "Successfully enrolled in the course!");
        }
        
        List<Course> availableCourses = CourseService.getAllCourses();
        request.setAttribute("availableCourses", availableCourses);
        
        @SuppressWarnings("unchecked")
        List<Course> enrolledCourses = (List<Course>) session.getAttribute("enrolledCourses");
        if (enrolledCourses == null) {
            enrolledCourses = new ArrayList<>();
            session.setAttribute("enrolledCourses", enrolledCourses);
        }
        request.setAttribute("enrolledCourses", enrolledCourses);
        
        request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
    }
}