package com.emc.dpc.resources.student.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.emc.dpc.resources.school.domain.Dept;
import com.emc.dpc.resources.student.domain.Student;

@Repository
public class StudentDAOImpl implements StudentDAO{

	@Autowired
	private SessionFactory sessionFactory;

	
	@Override
	public Student getStudent(int rollNo) {

		String query = "from Student where rollNo = "+rollNo;
		Session session = sessionFactory.getCurrentSession();
		List<Student> students = session.createQuery(query).list();
		if(students != null && students.size()==1)
		{
			return students.get(0);
		}
		return null;
	}

	@Override
	public List<Student> getStudents() {
		String query = "from Student";
		Session session = sessionFactory.getCurrentSession();
		List<Student> students = session.createQuery(query).list();
		return students;
	}

	@Override
	public void enrollStudent(Student student) {
		Session session = sessionFactory.getCurrentSession();
		session.save(student);
	}

	@Override
	public void enrollStudents(List<Student> students) {
		Session session = sessionFactory.getCurrentSession();

		for (Student student : students) {
			session.save(student);
		}
	}
	
	@Override
	public void updateStudent(Student student)
	{
		Session session = sessionFactory.getCurrentSession();
		session.update(student);
	}

	@Override
	public List<Student> getStudents(Dept dept) {

		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from Student where dept ="+dept.ordinal()).list();
	}

	@Override
	public List<Student> getStudents(Dept dept, int rollNo) {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from Student where rollNo="+rollNo+" and dept ="+dept.ordinal()).list();
	}
}
