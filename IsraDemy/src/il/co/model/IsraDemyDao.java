package il.co.model;

import java.util.List;

import tables.Files;
import tables.Messages;
import tables.Users;

public interface IsraDemyDao {
	public void addUser(Users u);
	public void deleteUser(Object o);
	public void updateUser(Object item);
	public Users getUser(String name);
	public void addFile(Object item);
	public void deleteFile(int id);
	public void updateFile(Object item);
	public Object getFile(Object id);
	public List <Files>getFiles(String user);
	public List <Files>getCollegeFiles(String user);
	public void addMessage(Messages msg);
	public List<Messages> getMessages();
}
