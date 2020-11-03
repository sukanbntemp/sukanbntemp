package WinBuilder;

public class Users {

	private static String userID;
	private String username;
	private String email;
	private String password;

	public Users(String userID, String username, String email, String password) {
		this.userID = userID;
		this.username = username;
		this.email = email;
		this.setPassword(password);
	}
	
	public static String getUserID() {
		return userID;
	}

	public static void setUserID(String string) {
		userID = string;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
