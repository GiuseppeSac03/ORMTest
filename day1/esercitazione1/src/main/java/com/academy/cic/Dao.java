package com.academy.cic;
<<<<<<< HEAD
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.academy.cic.util.*;
import com.academy.cic.model.*;

public class Dao {
	
	public void  insertStudent(Student student) throws SQLException {
		String query = "INSERT INTO STUDENT (id, first_name, last_name, age) VALUES (?, ?, ?, ?)";
    	Connection conn = null;
    	PreparedStatement pstm = null;
        try {
            conn = JdbcUtil.getConnection();

            pstm = conn.prepareStatement(query);
            pstm.setInt(1, student.getId());
            pstm.setString(2, student.getFirst_name());
            pstm.setString(3, student.getLast_name());
            pstm.setInt(4, student.getAge());
            pstm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	JdbcUtil.closePreparedStatement(pstm);
        	JdbcUtil.closeConnection(conn);
        	
        }
		
	}
	
	public void insertCourse(Course course) throws SQLException {
		String query = "INSERT INTO COURSE (id, name) VALUES  (?, ?)";
		Connection conn = null;
    	PreparedStatement pstm = null;
        try {
            conn = JdbcUtil.getConnection();

            pstm = conn.prepareStatement(query);
            
            pstm.setInt(1, course.getId());
            pstm.setString(2, course.getName());
            pstm.executeUpdate();
            

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	JdbcUtil.closePreparedStatement(pstm);
        	JdbcUtil.closeConnection(conn);
        }
		
		
	}
	
	public void registryStudentCourse(Registration registration) throws SQLException {
		String query = "INSERT INTO REGISTRATION (id, student_id, course_id, grade) VALUES  (?, ?, ?, ?)";
		Connection conn = null;
    	PreparedStatement pstm = null;
        try {
            conn = JdbcUtil.getConnection();

            pstm = conn.prepareStatement(query);
            pstm.setInt(1, registration.getId());
            pstm.setInt(2, registration.getStudent_id());
            pstm.setInt(3, registration.getCourse_id());
            pstm.setInt(4, registration.getGrade());
            pstm.executeUpdate();
            

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	JdbcUtil.closeConnection(conn);
        	JdbcUtil.closePreparedStatement(pstm);
        }
		
	}
	
    public Student selectByNameSurname(String name, String surname) throws SQLException {
    	String query = "SELECT * "
        		+ "FROM student WHERE first_name = ? AND last_name = ? ";
    	Connection conn = null;
    	PreparedStatement stmt = null;
        Student student = null;
        try {
        	conn = JdbcUtil.getConnection();

            stmt = conn.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2, surname);

            ResultSet rs = stmt.executeQuery();

            student = new Student();
            if (rs.next()) {
                student.setId(rs.getInt("id"));
                student.setFirst_name(rs.getString("first_name"));
                student.setLast_name(rs.getString("last_name"));
                student.setAge(rs.getInt("age"));
                System.out.println(student.toString());

            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	JdbcUtil.closePreparedStatement(stmt);
        	JdbcUtil.closeConnection(conn);
        }
        return student;
    }
    
    public void updateCourseGradeById(int registrationId, int grade) throws SQLException {
    	String query = "UPDATE Registration SET grade = ? WHERE id = ?";
    	Connection conn = null;
    	PreparedStatement stmt = null;
        try {
            conn = JdbcUtil.getConnection();

            stmt = conn.prepareStatement(query);
            stmt.setInt(1, grade);
            stmt.setInt(2, registrationId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	JdbcUtil.closePreparedStatement(stmt);
        	JdbcUtil.closeConnection(conn);
        }
    }
    
    public List<Course> findStudentCourses(int id) throws SQLException {
    	String query = "SELECT course.id, course.name, student.first_name, student.last_name, registration.course_id "
        		+ "FROM student, Registration, Course WHERE student.id = registration.student_id and student.id = ?; ";
    	Connection conn = null;
    	PreparedStatement pstm = null;
        List<Course> courseList = new ArrayList<Course>();
        Course course = null;
        
        try {
        	conn = JdbcUtil.getConnection();

            pstm = conn.prepareStatement(query);
            pstm.setInt(1, id);

            ResultSet rs = pstm.executeQuery();

           
          
            while (rs.next()) {
            	course = new Course();
            	course.setId(rs.getInt("id"));
            	course.setName(rs.getString("name"));
            	courseList.add(course);
            	System.out.println(course.toString());

            }

            rs.close();
            pstm.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	JdbcUtil.closeConnection(conn);
        	JdbcUtil.closePreparedStatement(pstm);
        }
        return courseList;
    	
    }
    
    
    // implementare un metodo che restituisce per ogni studente il numero di corsi a cui è iscritto

    public void corsiPerStudente() throws SQLException {
    	String query = "SELECT student_id, count(student_id) FROM REGISTRATION GROUP BY student_id;";
    	Connection conn = null;
    	PreparedStatement pstm = null;
    	

        try {
        	conn = JdbcUtil.getConnection();

            pstm = conn.prepareStatement(query);
            ResultSet rs = pstm.executeQuery();

           
          
            while (rs.next()) {
                int studentId = rs.getInt(1);
                int numCourses = rs.getInt(2);
                System.out.printf("Studente %d: %d corsi\n", studentId, numCourses);
            }

            rs.close();
            pstm.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	
        	JdbcUtil.closeConnection(conn);
        	JdbcUtil.closePreparedStatement(pstm);
        }
    	
    	
    }
    
	
	

}
=======



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.academy.cic.model.Course;
import com.academy.cic.model.CourseRegistration;
import com.academy.cic.model.Student;
import com.academy.cic.util.JdbcUtil;


public class Dao {

	public void insertStudent(Student student) throws SQLException {
		
		String query = "INSERT INTO STUDENT (ID, FIRST_NAME, LAST_NAME, AGE) VALUES  (?, ?, ?, ?)";
    	Connection conn = null;
    	PreparedStatement pstm = null;
    	
        try {
        	
            conn = JdbcUtil.getConnection();

            pstm = conn.prepareStatement(query);
            pstm.setInt(1, student.getId());
            pstm.setString(2, student.getFirst_name());
            pstm.setString(3, student.getLast_name());
            pstm.setInt(4, student.getAge());
            pstm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	JdbcUtil.closeConnection(conn);
        	JdbcUtil.closePreparedStatement(pstm);
        }
	}
	
	
	public void insertCourse(Course course) throws SQLException {
		
		String query = "INSERT INTO COURSE (ID, NAME) VALUES (?, ?)";
    	Connection conn = null;
    	PreparedStatement pstm = null;
    	
        try {
        	
            conn = JdbcUtil.getConnection();

            pstm = conn.prepareStatement(query);
            pstm.setInt(1, course.getId());
            pstm.setString(2, course.getName());
            pstm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	JdbcUtil.closeConnection(conn);
        	JdbcUtil.closePreparedStatement(pstm);
        }
	}
	
	
	public void registryStudentCourse(CourseRegistration registration) throws SQLException {
		
		String query = "INSERT INTO REGISTRATION (ID, STUDENT_ID, COURSE_ID, GRADE) VALUES (?, ?, ?, ?)";
    	Connection conn = null;
    	PreparedStatement pstm = null;
    	
        try {
        	
            conn = JdbcUtil.getConnection();

            pstm = conn.prepareStatement(query);
            pstm.setInt(1, registration.getId());
            pstm.setInt(2, registration.getStudentId());
            pstm.setInt(3, registration.getCourseId());
            pstm.setInt(4, registration.getGrade());
            pstm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	JdbcUtil.closeConnection(conn);
        	JdbcUtil.closePreparedStatement(pstm);
        }
	}
	
	
	public Student selectByNameSurname(String name, String surname) throws SQLException {
		
		String query = "SELECT STUDENT.* FROM STUDENT WHERE STUDENT.FIRST_NAME = ? AND STUDENT.LAST_NAME = ?";
    	Connection conn = null;
    	PreparedStatement pstm = null;
    	
    	Student student = null;
    	ResultSet rs = null;
        try {
        	
            conn = JdbcUtil.getConnection();

            pstm = conn.prepareStatement(query);
            pstm.setString(1, name);
            pstm.setString(2, surname);
            
            rs = pstm.executeQuery(); 

            student = new Student();
            if (rs.next()) {
            	student.setId(rs.getInt("ID"));
            	student.setFirst_name(rs.getString("FIRST_NAME"));
            	student.setFirst_name(rs.getString("LAST_NAME"));
            	student.setAge(rs.getInt("AGE"));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	JdbcUtil.closeConnection(conn);
        	JdbcUtil.closePreparedStatement(pstm);
        	JdbcUtil.closeResultSet(rs);
        }
        return student;
	}
	
	public void updateCourseGradeById(int registrationId, int grade) throws SQLException {
		
		String query = "UPDATE REGISTRATION SET GRADE = ? WHERE ID = ?";
    	Connection conn = null;
    	PreparedStatement pstm = null;
    	
        try {
        	
            conn = JdbcUtil.getConnection();

            pstm = conn.prepareStatement(query);
            pstm.setInt(1, grade);
            pstm.setInt(2, registrationId);
            pstm.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	JdbcUtil.closeConnection(conn);
        	JdbcUtil.closePreparedStatement(pstm);
        }
	}
	
	public List<Course> findStudentCourses(int id) throws SQLException {
		
		String query = "SELECT DISTINCT COURSE.* FROM COURSE, REGISTRATION "
				+ "WHERE REGISTRATION.COURSE_ID = COURSE.ID "
				+ "AND REGISTRATION.STUDENT_ID = ?";
    	Connection conn = null;
    	PreparedStatement pstm = null;
    	
    	List<Course> studentCourses = new LinkedList<Course>();
    	ResultSet rs = null;
    	
        try {
        	
            conn = JdbcUtil.getConnection();

            pstm = conn.prepareStatement(query);
            pstm.setInt(1, id);
            
            rs = pstm.executeQuery();

            while (rs.next()) {
            	studentCourses.add(new Course(rs.getInt("ID"), rs.getString("NAME")));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	JdbcUtil.closeConnection(conn);
        	JdbcUtil.closePreparedStatement(pstm);
        	JdbcUtil.closeResultSet(rs);
        }
        return studentCourses;
	}
	
	//inizializzo un metodo che mi restituisca per ogni studente il numero di corsi a 
	//cui è iscritto . 
	public int getCourseCountByStudentId(int studentId) throws SQLException {
	    String query = "SELECT COUNT(*) FROM REGISTRATION WHERE STUDENT_ID = ?";
	    Connection conn = null;
	    PreparedStatement pstm = null;
	    ResultSet rs = null;
	    int courseCount = 0;
	    try {
	        conn = JdbcUtil.getConnection();
	        pstm = conn.prepareStatement(query);
	        pstm.setInt(1, studentId);
	        rs = pstm.executeQuery();
	        if (rs.next()) {
	            courseCount = rs.getInt(1);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        JdbcUtil.closeConnection(conn);
	        JdbcUtil.closePreparedStatement(pstm);
	        JdbcUtil.closeResultSet(rs);
	    }
	    return courseCount;
	}

}
>>>>>>> refs/remotes/origin/main
