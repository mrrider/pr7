package com.kozak;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;

import com.googlecode.ehcache.annotations.Cacheable;

public class WorkWithStudents {
	@Autowired
	private StudentsDao studentsDao;
	private static final String SQL_SELECT_STUDENT_BY_ID = "select id, pib, course from students where id = ?";

	@Autowired
	private SimpleJdbcTemplate jdbcTemplate;

	
	public void saveStudentToDb(Student student) {
		if ((student != null) && (student.getPib() != null) && (!"".equals(student.getPib()))
				&& (student.getCourse() > 0)) {
			studentsDao.addStudent(student);
			System.out.println("Student have been saved " + student);
		}
	}

	@Cacheable(cacheName = "studentsCache")
	public Student getStudentById(int id) {
		return jdbcTemplate.queryForObject(SQL_SELECT_STUDENT_BY_ID, new ParameterizedRowMapper<Student>() {
			public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
				Student student = new Student();
				student.setStudentId(rs.getInt(1));
				student.setPib(rs.getString(2));
				student.setCourse(rs.getInt(3));
				return student;
			}
		}, id);
	}

}
