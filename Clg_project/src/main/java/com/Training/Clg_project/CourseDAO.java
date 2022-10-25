package com.Training.Clg_project;


import java.sql.Date;
import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CourseDAO {
		SessionFactory sessionFactory;
	
	public String generateCourseId() {
		sessionFactory=SessionHelper.getConnection();
		Session session;
		session=sessionFactory.openSession();
		Criteria cr=session.createCriteria(CourseList.class);
		List<CourseList>courselist=cr.list();
		session.close();
		
		if (courselist.size()==0) {
			return "C001";
		} else {
		String courseIds=courselist.get(courselist.size()-1).getCourseno();
		int ids = Integer.parseInt(courseIds.substring(1));
		ids++;
		String rid=String.format("C%03d",++ids);
		return rid;
		}
	}
	public String Addcourse(CourseList course) {
		String courseID=generateCourseId();
		course.setCourseno(courseID);
		if (sessionFactory==null) {
			sessionFactory=SessionHelper.getConnection();
		}
		Session session;
		session=sessionFactory.openSession();
		Transaction trans=session.beginTransaction();
		session.save(course);
		trans.commit();
		session.close();
		return "***----Add Course----***";
		
	}
	public Date convertDate(java.util.Date dt) {
		java.sql.Date sqlDate=new java.sql.Date(dt.getTime());
		return sqlDate;
	}
	
	public String Addsubject(Subjects subjects) {
		sessionFactory=SessionHelper.getConnection();
		Session session;
		session=sessionFactory.openSession();
		Transaction trans=session.beginTransaction();
		session.save(subjects);
		trans.commit();
		session.close();
		return "***----Add Subject----***";
		
	}
	
	
}
