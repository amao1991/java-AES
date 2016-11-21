import java.security.SecureRandom;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class Generate {
	
	public static SecretKey key () throws Exception{
		KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		keyGen.init(256);
		
		return keyGen.generateKey();
	}
	
	
	public static byte[] iv (){
		SecureRandom random = new SecureRandom();
		byte[] randBytes = new byte[16];
		random.nextBytes(randBytes);
		//IvParameterSpec iv = new IvParameterSpec(randBytes);
		
		return randBytes;
	}
}
