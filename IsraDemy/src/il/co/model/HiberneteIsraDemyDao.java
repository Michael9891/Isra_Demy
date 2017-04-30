package il.co.model;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import tables.Files;
import tables.Messages;
import tables.Users;

// dataBase queres class (sheiltot)
public class HiberneteIsraDemyDao implements IsraDemyDao {
	private SessionFactory factory;
	private Session session;
	
	public HiberneteIsraDemyDao(){
		factory = Factory.getFactory();
	}

	@Override
	public void addUser(Users u) {
		session = factory.openSession();
		try{
		session.beginTransaction();
			session.save(u);
		session.getTransaction().commit();
		}catch(HibernateException e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally {
			if(session != null) { session.close(); };
		}
	}

	@Override
	public void deleteUser(Object o) {
		session = factory.openSession();
		try{
		session.beginTransaction();
			session.delete(o);
		session.getTransaction().commit();
		}catch(HibernateException e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally {
			if(session != null) { session.close(); };
		}
		
	}

	@Override
	public void updateUser(Object item) {
		session = factory.openSession();
		try{
		session.beginTransaction();
			session.saveOrUpdate(item);
		session.getTransaction().commit();
		}catch(HibernateException e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally {
			if(session != null) { session.close(); };
		}
		
	}

	@Override
	public Users getUser(String name) {
		Users u = new Users();
		session = factory.openSession();
		try{
		session.beginTransaction();
		//the next 3 lines get userName details with SQL injection protection!
		Query query = session.createQuery("from Users where userName= ?");
		query.setString(0, name);
		u = (Users)query.uniqueResult();
		session.getTransaction().commit();
		}catch(HibernateException e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally {
			if(session != null) { session.close(); };
		}
		return u;
	}

	@Override
	public void addFile(Object item) {
		session = factory.openSession();
		session.beginTransaction();
			session.save(item);
		session.getTransaction().commit();
		session.close();
		
	}

	@Override
	public void deleteFile(int id) {
		session = factory.openSession();
		session.beginTransaction();
		//the next 2 lines get userName details with SQL injection protection!
		Query query = session.createQuery("delete from Files where fileId = ?");
		query.setInteger(0, id);
		query.executeUpdate();
		System.out.println("deletttttteeeeee");
		session.getTransaction().commit();
		session.close();
		
	}

	@Override
	public void updateFile(Object item) {
		session = factory.openSession();
		session.beginTransaction();
			session.saveOrUpdate(item);
		session.getTransaction().commit();
		session.close();
		
	}

	@Override
	public Object getFile(Object id) {
		session = factory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Files where fileId = ?");
		query.setInteger(0, (int) id);
		Files file = (Files) query.uniqueResult();
		session.getTransaction().commit();
		session.close();
		return file;
	}

	@Override
	public List<Files> getFiles(String user) {
		session = factory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Files where userName = ?");
		query.setString(0, user);
        @SuppressWarnings("unchecked")
	    List<Files> files = query.list();
		session.getTransaction().commit();
		session.close();
		return (List<Files>)files;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Files> getCollegeFiles(String college) {
		session = factory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Files where collegeName = ?");
		query.setString(0, college);
        List<Files> files = null;
	    files = query.list();
		session.getTransaction().commit();
		session.close();
		return (List<Files>)files;
	}

	@Override
	public void addMessage(Messages msg) {
		session = factory.openSession();
		session.beginTransaction();
			session.save(msg);
		session.getTransaction().commit();
		session.close();
		
	}

	@Override
	public List<Messages> getMessages() {
		session = factory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Messages");
		@SuppressWarnings("unchecked")
		List<Messages> msg = (List<Messages>)query.list();
		session.getTransaction().commit();
		session.close();
		return msg;
		
	}


	

}
