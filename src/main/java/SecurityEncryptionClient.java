package main.java;

import com.yapstone.core.security.client.SecurityClient;
import com.yapstone.core.security.exceptions.SecurityException;
import com.yapstone.core.security.util.Configuration;

import java.io.FileInputStream;
import java.util.Properties;

public class SecurityEncryptionClient {

	private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(SecurityEncryptionClient.class);

    static String PROPERTY_PATH = "src/main/resources/config/provenirsecurity.properties";
	private static SecurityClient sc = null;

	static {
		//Initilaize security client
		sc = new SecurityClient(loadProperties());
	}

	public String encrypt(String data) throws SecurityException{
		return sc.encrypt(data);
	}

	public String decrypt(String data) throws SecurityException{
		return sc.decrypt(data);
	}

	public String encryptText(String data) throws SecurityException{
		return sc.encryptText(data);
	}

	public String decryptText(String data) throws SecurityException{
		return sc.decryptText(data);
	}

	public String[] encryptText(String[] data) throws SecurityException{
		return sc.encryptText(data);
	}

	public String[] decryptText(String[] data) throws SecurityException{
		return sc.decryptText(data);
	}


	public static Properties loadProperties() {
		Properties props = new Properties();
		try {
			props.load(new FileInputStream(PROPERTY_PATH));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		Configuration.decryptValues(props);
		return props;
	}

	public static void main(String args[]){
		try {
			String enc = sc.encryptText("Ptest");
			System.out.println("Encrypted text is: " + enc);
			String decrypted = sc.decryptText(enc);
			System.out.println("Decrypted is: " + decrypted);
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}
}
