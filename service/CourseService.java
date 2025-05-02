package service;

import model.Course;
import java.util.ArrayList;
import java.util.List;

public class CourseService {
    private static final List<Course> availableCourses = new ArrayList<>();
    
    static {
        // Populate with some sample courses
        availableCourses.add(new Course(101, "Java Programming", "Prof. Johnson"));
        availableCourses.add(new Course(102, "Web Development", "Dr. Smith"));
        availableCourses.add(new Course(103, "Database Systems", "Prof. Williams"));
        availableCourses.add(new Course(104, "Computer Networks", "Dr. Brown"));
        availableCourses.add(new Course(105, "Data Structures", "Prof. Davis"));
    }
    
    public static List<Course> getAllCourses() {
        return new ArrayList<>(availableCourses);
    }
    
    public static Course getCourseById(int courseId) {
        for (Course course : availableCourses) {
            if (course.getCourseId() == courseId) {
                return course;
            }
        }
        return null;
    }
}