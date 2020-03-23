package ca.gbc.library.include;

public class Authentication {
	
	public boolean adminAuth(String username, String password) {
		if(username != null && username.length()>0) {
			if(username.equals("admin@georgebrown.ca") && password.equals("admin123"))
				return true;
			else
				return false;
		}else
			return false;
	}
	
	public boolean librarianAuth(String username, String password) {
		if(username != null && username.length()>0) {
			if(username.equals("librarian@georgebrown.ca") && password.equals("librarian123"))
				return true;
			else
				return false;
		}else
			return false;
	}
}
