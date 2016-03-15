package com.kozak;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class JdbcTestApplication {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("SpringBeans.xml");
		WorkWithStudents worker = (WorkWithStudents) context.getBean("worker");
		Student student = worker.getStudentById(1);
		System.out.println(student);
		student = worker.getStudentById(1);
		System.out.println(student);

	}
}
