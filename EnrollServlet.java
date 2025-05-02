package servlet;

import model.Course;
import service.CourseService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/enroll")
public class EnrollServlet extends HttpServlet {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.html");
            return;
        }
        
        String courseIdParam = request.getParameter("courseId");
        
        if (courseIdParam != null && !courseIdParam.isEmpty()) {
            try {
                int courseId = Integer.parseInt(courseIdParam);
                Course course = CourseService.getCourseById(courseId);
                
                if (course != null) {
                    @SuppressWarnings("unchecked")
                    List<Course> enrolledCourses = (List<Course>) session.getAttribute("enrolledCourses");
                    
                    if (enrolledCourses == null) {
                        enrolledCourses = new ArrayList<>();
                        session.setAttribute("enrolledCourses", enrolledCourses);
                    }
                    
                    boolean alreadyEnrolled = false;
                    for (Course enrolledCourse : enrolledCourses) {
                        if (enrolledCourse.getCourseId() == courseId) {
                            alreadyEnrolled = true;
                            break;
                        }
                    }
                    
                    if (!alreadyEnrolled) {
                        enrolledCourses.add(course);
                        session.setAttribute("enrolledCourses", enrolledCourses);
                    }
                    
                    response.sendRedirect("dashboard?success=true");
                } else {
                    response.sendRedirect("dashboard?error=course-not-found");
                }
            } catch (NumberFormatException e) {
                response.sendRedirect("dashboard?error=invalid-id");
            }
        } else {
            response.sendRedirect("dashboard?error=no-id");
        }
    }
}