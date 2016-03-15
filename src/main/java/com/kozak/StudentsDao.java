package com.kozak;

public interface StudentsDao {
	void addStudent(Student student);
	Student getStudentById(int id);
}
