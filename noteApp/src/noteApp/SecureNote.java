package noteApp;

import java.security.NoSuchAlgorithmException;

public class SecureNote extends Note {
	
	private String passwordHash;
	
	public void setPasswordHash(String password) throws NoSuchAlgorithmException {
		this.passwordHash = Crypto.encryptPassword(password);
	}
	
	public String getPasswordHash() {
		return this.passwordHash;
	}

}
