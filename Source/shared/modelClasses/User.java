package shared.modelClasses;
import com.thoughtworks.xstream.annotations.XStreamAlias;



/**
 * Each user element contains the information for a single user of the system.
 * @author isai
 *
 */
@XStreamAlias("user")
public class User {

	
	
	@XStreamAlias("username")
	private String username;
	@XStreamAlias("password")
	private String password;
	@XStreamAlias("firstname")
	private String firstname;
	@XStreamAlias("lastname")
	private String lastname;
	@XStreamAlias("email")
	private String email;
	@XStreamAlias("indexedrecords")
	private int indexedrecords;
	private int id;
	private int currimageid;
	
	
	
	/**
	 * Constructor that takes parameters
	 * @param username
	 * @param password
	 * @param firstname
	 * @param lastname
	 * @param email
	 * @param indexedrecords
	 * @param id
	 * @param currimageid
	 */
	public User(String username, String password, String firstname,
			String lastname, String email, int indexedrecords, int id,
			int currimageid) {
		super();
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.indexedrecords = indexedrecords;
		this.id = id;
		this.currimageid = currimageid;
	}
	
	
	
	public User() {
		this.username = new String();
		this.password = new String();
		this.firstname = new String();
		this.lastname = new String();
		this.email = new String();
		this.indexedrecords = 0;
		this.id = 0;
		this.currimageid = 0;
	}


	
	public User(User user) {
		this.username = new String(user.getUsername());
		this.password = new String(user.getPassword());
		this.firstname = new String(user.getFirstname());
		this.lastname = new String(user.getLastname());
		this.email = new String(user.getEmail());
		this.indexedrecords = user.getIndexedrecords();
		this.id = user.getId();
		this.currimageid = user.getCurrimageid();
	}

	
	
	/**
	 * 
	 * @return username
	 */
	public String getUsername() {
		return username;
	}
	
	
	
	/**
	 * 
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
	/**
	 * 
	 * @return password
	 */
	public String getPassword() {
		return password;
	}
	
	
	
	/**
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	/**
	 * 
	 * @return firstname
	 */
	public String getFirstname() {
		return firstname;
	}
	
	
	
	/**
	 * 
	 * @param firstname
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	
	
	/**
	 * 
	 * @return lastname
	 */
	public String getLastname() {
		return lastname;
	}
	
	
	
	/**
	 * 
	 * @param lastname
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	
	
	/**
	 * 
	 * @return email
	 */
	public String getEmail() {
		return email;
	}
	
	
	
	/**
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	/**
	 * 
	 * @return indexedrecords
	 */
	public int getIndexedrecords() {
		return indexedrecords;
	}
	
	
	
	/**
	 * 
	 * @param indexedrecords
	 */
	public void setIndexedrecords(int indexedrecs) {
			indexedrecords = indexedrecs;
	}
	
	
	
	/**
	 * 
	 * @return id
	 */
	public int getId() {
		return id;
	}
	
	
	
	/**
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	
	
	/**
	 * 
	 * @return currimageid
	 */
	public int getCurrimageid() {
		return currimageid;
	}
	
	
	
	/**
	 * 
	 * @param currimageid
	 */
	public void setCurrimageid(int currimageid) {
		this.currimageid = currimageid;
	}
	
	
	
	@Override
	public String toString() {
		return "<user>\n" +
				"<username>" + username + "</username>\n" +
				"<password>" + password + "</password>\n" +
				"<firstname>" + firstname + "</firstname>\n" +
				"<lastname>" + lastname + "</lastname>\n" +
				"<email>" + email + "</email>\n" +
				"<indexedrecords>" + indexedrecords + "</indexedrecords>\n" +
				"<id>" + id + "</id>\n" +
				"<currimageid>" + currimageid + "</currimageid>\n" +
				"</user>\n";
	}
}
