package com.manjosh.client;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.manjosh.entities.Employee;
import com.manjosh.util.HibernateUtil;

public class ClientTest {

	public static void main(String[] args) {
			
			try(Session session= HibernateUtil.getSessionFactory().openSession()){
//				int id =createEmployee(session);
//				getEmployee(session,2);
//				updateEmployee(session,1);
				deleteEmployeeById(session,1);
				
			}catch(HibernateException e) {
				e.printStackTrace();
			}

	}
	
	private static void deleteEmployeeById(Session session, int i) {
		Employee emp = getEmployee(session,i);
		if(emp!=null) {
			session.beginTransaction();
			session.delete(emp);
			session.getTransaction().commit();
		}
	}

	private static void updateEmployee(Session session, int id) {
		Employee emp = getEmployee(session,id);
		if(emp!=null) {
			emp.setSalary(5000.00);
			session.beginTransaction();
			session.saveOrUpdate(emp);
			session.getTransaction().commit();
		}
		
	}

	private static Employee getEmployee(Session session, int id) {
		Employee emp = session.get(Employee.class, id);
		if(emp != null)
			System.out.println(emp);
		else
			System.out.printf("Employee does not exist witht the id :%d",id);
		return emp;
	}

	private static int createEmployee(Session session) {
		session.beginTransaction();
		Employee emp = getEmployee();
		Integer id =(Integer)session.save(emp);
		session.getTransaction().commit();
		System.out.println("Emp id is : " + id);
		return id;
	}
	
	private static Employee getEmployee() {
		Employee emp = new Employee();
		emp.setEmpName("manjosh");
		emp.setDoj(new Date());
		emp.setEmail("manjosh.ramesh@cgi.com");
		emp.setSalary(1000.00);
		return emp;
	}

}
