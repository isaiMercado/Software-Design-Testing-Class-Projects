package shared.modelClasses;

import java.util.ArrayList;

import server.dataBase.DataBaseConnector;
import server.javaHTTPserver.HTTPserver;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;



/**
 * Users is a collection of User objects
 * @author isai
 *
 */
@XStreamAlias("users")
public class Users {

	
	
	@XStreamImplicit(itemFieldName="user")
	private ArrayList<User> users;
	
	
	
	/**
	 * Constructor that takes parameters
	 * @param users
	 */
	public Users(ArrayList<User> users) {
		super();
		this.users = users;
	}

	
	
	public Users() {
		this.users = new ArrayList<User>();
	}

	

	/**
	 * 
	 * @return users
	 */
	public ArrayList<User> getUsers() {
		return users;
	}

	
	
	/**
	 * 
	 * @param users
	 */
	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

	
	
	@Override
	public String toString() {
		return "<users>\n" + 
				users + 
				"</users>\n";
	}

	
	
	public void importUsersFromDB(DataBaseConnector db) {
		try {
			db.startTransaction();
			Users temp = db.getUserDAO().getAllRows();
			users.addAll(temp.getUsers());
			db.endTransaction(true);
		} catch(Exception e) {
			e.printStackTrace();
			db.endTransaction(false);
		}
	}
	
	

	public User validateUser(String username, String password) {
		User output = null;
		for(User user : users) {
			if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
				output = new User();
				output = user;
			}
		}
		return output;
	}

	
	
	public void updateCurrImage(int userId, int imageId) {
		try {
			HTTPserver.getDatabasePersistent().startTransaction();
			for(User user : users) {
				if (user.getId() == userId) {
					user.setCurrimageid(imageId);
					HTTPserver.getDatabasePersistent().getUserDAO().editRow(user.getId(), user);
				}
			}
			HTTPserver.getDatabasePersistent().endTransaction(true);
		} catch(Exception e) {
			e.printStackTrace();
			HTTPserver.getDatabasePersistent().endTransaction(false);
		}
	}
	
	
	
	public void updateRecordsIndexed(int userId, int indexedrecs) {
		try {
			HTTPserver.getDatabasePersistent().startTransaction();
			for(User user : users) {
				if(user.getId() == userId) {
					user.setIndexedrecords(indexedrecs);
					HTTPserver.getDatabasePersistent().getUserDAO().editRow(user.getId(), user);
				}
			}
			HTTPserver.getDatabasePersistent().endTransaction(true);
		} catch(Exception e) {
			e.printStackTrace();
			HTTPserver.getDatabasePersistent().endTransaction(false);
		}
	}
}
