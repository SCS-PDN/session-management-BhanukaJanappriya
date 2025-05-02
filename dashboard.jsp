<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Course, java.util.List, java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
    <title>Course Registration - Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background-color: #f4f4f4;
        }
        .container {
            width: 80%;
            margin: 0 auto;
            background-color: white;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1, h2 {
            color: #333;
        }
        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
        }
        .logout-btn {
            background-color: #f44336;
            color: white;
            border: none;
            padding: 10px 15px;
            border-radius: 4px;
            cursor: pointer;
            text-decoration: none;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        th, td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
        }
        .enroll-btn {
            background-color: #4CAF50;
            color: white;
            padding: 8px 12px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            text-decoration: none;
        }
        .success-message {
            background-color: #dff0d8;
            color: #3c763d;
            padding: 10px;
            margin-bottom: 20px;
            border-radius: 4px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>Welcome, <%= request.getAttribute("username") %>!</h1>
            <a href="logout" class="logout-btn">Logout</a>
        </div>
        
        <% if(request.getAttribute("successMessage") != null) { %>
            <div class="success-message">
                <%= request.getAttribute("successMessage") %>
            </div>
        <% } %>
        
        <h2>Your Enrolled Courses</h2>
        <% 
        @SuppressWarnings("unchecked")
        List<Course> enrolledCourses = (List<Course>) request.getAttribute("enrolledCourses");
        if (enrolledCourses != null && !enrolledCourses.isEmpty()) {
        %>
            <table>
                <tr>
                    <th>Course ID</th>
                    <th>Course Name</th>
                    <th>Instructor</th>
                </tr>
                <% for (Course course : enrolledCourses) { %>
                <tr>
                    <td><%= course.getCourseId() %></td>
                    <td><%= course.getCourseName() %></td>
                    <td><%= course.getInstructor() %></td>
                </tr>
                <% } %>
            </table>
        <% } else { %>
            <p>You are not enrolled in any courses yet.</p>
        <% } %>
        
        <h2>Available Courses</h2>
        <% 
        @SuppressWarnings("unchecked")
        List<Course> availableCourses = (List<Course>) request.getAttribute("availableCourses");
        if (availableCourses != null && !availableCourses.isEmpty()) {
        %>
            <table>
                <tr>
                    <th>Course ID</th>
                    <th>Course Name</th>
                    <th>Instructor</th>
                    <th>Action</th>
                </tr>
                <% for (Course course : availableCourses) { %>
                <tr>
                    <td><%= course.getCourseId() %></td>
                    <td><%= course.getCourseName() %></td>
                    <td><%= course.getInstructor() %></td>
                    <td>
                        <a href="enroll?courseId=<%= course.getCourseId() %>" class="enroll-btn">Enroll</a>
                    </td>
                </tr>
                <% } %>
            </table>
        <% } else { %>
            <p>No courses available at the moment.</p>
        <% } %>
    </div>
</body>
</html>