package com.academy.cic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.academy.cic.model.Course;
import com.academy.cic.model.Registration;
import com.academy.cic.model.Student;
import com.academy.cic.model.StudentNumCourses;
import com.academy.cic.util.JdbcUtil;

public class Dao {
	
	public void insertStudent(Student student) throws SQLException {
    	String query = "INSERT INTO STUDENT (ID, FIRST_NAME, LAST_NAME, AGE) VALUES (?,?,?,?)";
    	
    	Connection conn = null;
        try {
            conn = JdbcUtil.getConnection();

            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setInt(1, student.getId());
            pstm.setString(2, student.getName());
            pstm.setString(3, student.getSurname());
            pstm.setInt(4, student.getAge());
            pstm.executeUpdate();
            pstm.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	JdbcUtil.closeConnection(conn);
        }
    }
	
	public void insertCourse(Course course) throws SQLException {
    	String query = "INSERT INTO COURSE (ID, NAME) VALUES (?,?)";
    	
    	Connection conn = null;
        try {
            conn = JdbcUtil.getConnection();

            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setInt(1, course.getId());
            pstm.setString(2, course.getName());
            pstm.executeUpdate();
            pstm.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	JdbcUtil.closeConnection(conn);
        }
    }
	
	public void registryStudentCourse(Registration courseRegistration) throws SQLException {
    	String query = "INSERT INTO REGISTRATION (ID, STUDENT_ID, COURSE_ID) VALUES (?,?,?)";
    	
    	Connection conn = null;
        try {
            conn = JdbcUtil.getConnection();

            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setInt(1, courseRegistration.getId());
            pstm.setInt(2, courseRegistration.getStudentId());
            pstm.setInt(3, courseRegistration.getCourseId());
            pstm.executeUpdate();
            pstm.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	JdbcUtil.closeConnection(conn);
        }
    }

    public Student selectByNameSurname(String name, String surname) throws SQLException {
    	String query = "SELECT ID, FIRST_NAME, LAST_NAME, AGE FROM STUDENT WHERE FIRST_NAME = ? AND LAST_NAME = ?";
    	
    	Connection conn = null;
        Student student = null;
        try {
        	conn = JdbcUtil.getConnection();

        	PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2, surname);

            ResultSet rs = stmt.executeQuery();

            student = new Student();
            if (rs.next()) {
            	student.setId(rs.getInt("ID"));
            	student.setName(rs.getString("FIRST_NAME"));
            	student.setSurname(rs.getString("LAST_NAME"));
            	student.setAge(rs.getInt("AGE"));
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	JdbcUtil.closeConnection(conn);
        }
        return student;
    }
    
	public void updateCourseGradeById(int registrationId, int grade) throws SQLException {
    	String query = "UPDATE REGISTRATION SET GRADE = ? WHERE ID = ?";
    	
    	Connection conn = null;
    	try {
            conn = JdbcUtil.getConnection();

            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setInt(1, registrationId);
            pstm.setInt(2, grade);
            pstm.executeUpdate();
            pstm.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	JdbcUtil.closeConnection(conn);
        }
    }
	
    public List<Course> findStudentCourses(int id) throws SQLException {
    	String query = "SELECT C.ID, C.NAME FROM REGISTRATION R, COURSE C WHERE R.COURSE_ID = C.ID AND R.STUDENT_ID = ?";
    	
    	Connection conn = null;
    	List<Course> courses = null;
        try {
        	conn = JdbcUtil.getConnection();

        	PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            courses = new ArrayList<Course>();
            while (rs.next()) {
            	Course course = new Course();
            	course.setId(rs.getInt("ID"));
            	course.setName(rs.getString("NAME"));
            	courses.add(course);
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	JdbcUtil.closeConnection(conn);
        }
        return courses;
    }
    
    public List<StudentNumCourses> findStudentIdNumCourses() throws SQLException {
    	String query = "SELECT STUDENT_ID, COUNT(*) AS COURSE_COUNT FROM REGISTRATION GROUP BY STUDENT_ID";
    	
    	Connection conn = null;
    	List<StudentNumCourses> studentCountList = null;
    	
        try {
        	conn = JdbcUtil.getConnection();
        	PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            studentCountList = new ArrayList<StudentNumCourses>();
            while (rs.next()) {
            	StudentNumCourses studentCount = new StudentNumCourses();
            	Student student = new Student();
            	student.setId(rs.getInt("STUDENT_ID"));
            	studentCount.setStudent(student);
            	studentCount.setCourseCount(rs.getInt("COURSE_COUNT"));
            	studentCountList.add(studentCount);
            }

            stmt.close();
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	JdbcUtil.closeConnection(conn);
        }
        return studentCountList;
    }
    
    public List<StudentNumCourses> findStudentDataNumCourses() throws SQLException {
    	String query = "SELECT S.ID, S.FIRST_NAME, S.LAST_NAME, S.AGE, COUNT(*) AS COURSE_COUNT "
    			+ "FROM REGISTRATION R, STUDENT S WHERE R.STUDENT_ID=S.ID GROUP BY R.STUDENT_ID";
   
    	Connection conn = null;
    	List<StudentNumCourses> studentCountList = null;
    	
        try {
        	conn = JdbcUtil.getConnection();
        	PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            studentCountList = new ArrayList<StudentNumCourses>();
            while (rs.next()) {
            	StudentNumCourses studentCount = new StudentNumCourses();
            	Student student = new Student();
            	student.setId(rs.getInt("ID"));
            	student.setName(rs.getString("FIRST_NAME"));
            	student.setSurname(rs.getString("LAST_NAME"));
            	student.setAge(rs.getInt("AGE"));
            	studentCount.setStudent(student);
            	studentCount.setCourseCount(rs.getInt("COURSE_COUNT"));
            	studentCountList.add(studentCount);
            }

            stmt.close();
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	JdbcUtil.closeConnection(conn);
        }
        return studentCountList;
    }

}
