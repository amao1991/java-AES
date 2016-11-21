import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptMethod {
	/**
	 * Encrypt
	 * Checks if the AES key length is allowed on the current system and encrypt it.
	 * On most systems 128 bit is the highest allowed key length.
	 * @param the string which wants to encrypt(plaintext), the secret key 
	 * @return the ciphertext
	 */
	public static String Encrypt(String pText, SecretKey sKey, IvParameterSpec iv) throws Exception {
		
		// 測試區
//		SecretKeySpec sKeySpec = new SecretKeySpec("0102030405060708".getBytes(), "AES");
//		Cipher cipher = Cipher.getInstance("AES/OFB/NoPadding");
//		IvParameterSpec vv = new IvParameterSpec("0102030405060708".getBytes());
//		cipher.init(Cipher.ENCRYPT_MODE, sKeySpec, vv);
		
		// true secret key
		SecretKeySpec sKeySpec = new SecretKeySpec(sKey.getEncoded(), "AES");
		// encryption mode
		Cipher cipher = Cipher.getInstance("AES/OFB/NoPadding");
		// Initial vector
		cipher.init(Cipher.ENCRYPT_MODE, sKeySpec, iv);
		
		// encrypt
		byte[] encrypted = cipher.doFinal(pText.getBytes());
		
		for (int i = 0; i < encrypted.length; i++) {
	        System.out.print(encrypted[i] + " ");
	      }
	      System.out.println();
	      for (int i = 0; i < encrypted.length; i++) {
	        int postive = encrypted[i] & 0xff;
	        System.out.print(postive + " ");
	      }
	      System.out.println();

		return byte2hex(encrypted);
	}

	// decrypt
	/**
	 * Checks if the AES key length is allowed on the current system and decrypt it.
	 * On most systems 128 bit is the highest allowed key length.
	 * @param the string which wants to decrypt(ciphertext), the secret key 
	 * @return the plaintext
	 */
	public static String Decrypt(String cText, SecretKey sKey, IvParameterSpec iv) throws Exception {
		try {
			SecretKeySpec sKeySpec = new SecretKeySpec(sKey.getEncoded(), "AES");
			Cipher cipher = Cipher.getInstance("AES/OFB/NoPadding");
			cipher.init(Cipher.DECRYPT_MODE, sKeySpec, iv);
			byte[] encrypted1 = hex2byte(cText);
			try {
				byte[] original = cipher.doFinal(encrypted1);
				String originalString = new String(original);
				return originalString;
			}
			catch (Exception e) {
				System.out.println(e.toString());
				return null;
			}
		}
		catch (Exception ex) {
			System.out.println(ex.toString());
			return null;
		}
	}

	public static byte[] hex2byte(String strhex) {
        if (strhex == null) {
            return null;
        }
        int l = strhex.length();
        if (l % 2 == 1) {
            return null;
        }
        byte[] b = new byte[l / 2];
        for (int i = 0; i != l / 2; i++) {
            b[i] = (byte) Integer.parseInt(strhex.substring(i * 2, i * 2 + 2),
                    16);
        }
        return b;
    }

    public static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF)); // 轉回十六進位
//            System.out.print(stmp + " ");
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        
        return hs.toUpperCase();
    }
    
    public static String EncryptToASCII(String pText) throws Exception, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
    	SecretKeySpec sKeySpec = new SecretKeySpec("0102030405060708".getBytes("ASCII"), "AES");
		Cipher cipher = Cipher.getInstance("AES/OFB/NoPadding");
		IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes("ASCII"));
		cipher.init(Cipher.ENCRYPT_MODE, sKeySpec, iv);
		
		String ascii = new String(cipher.doFinal(pText.getBytes("ASCII")));
		
		return ascii;
    }
}
