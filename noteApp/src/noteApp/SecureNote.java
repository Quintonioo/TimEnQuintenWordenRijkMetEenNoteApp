package noteApp;

import java.security.NoSuchAlgorithmException;

public class SecureNote extends Note {

	private String passwordHash;

	public SecureNote(String name, String text, String password) throws NoSuchAlgorithmException {
		super(name, text);
		setPasswordHash(password);
	}
	
	public void setPasswordHash(String password) throws NoSuchAlgorithmException {
		this.passwordHash = Crypto.encryptPassword(password);
	}
	
	public String getPasswordHash() {
		return this.passwordHash;
	}

}
