import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

// AES algorithm

public class AES {

	private static String hexToASCII(String hexValue) {
	    StringBuilder output = new StringBuilder("");
	    for (int i = 0; i < hexValue.length(); i += 2) {
	        String str = hexValue.substring(i, i + 2);
	        output.append((char) Integer.parseInt(str, 16));
	    }
	    return output.toString();
	}

	public static void main(String[] args) throws Exception{
		// AES-128-CBC need 16bits key

//		String inputfile = "D:/Document/test.ftm";
		String inputfile = "D:/Document/plaintext.ftm";
		String outputfile = "D:/Document/EncryptData.ftm";
		String line = null;
		
		try {
			
			long startTime = System.currentTimeMillis();
			
			FileReader fileReader = new FileReader(inputfile);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			FileWriter fileWriter = new FileWriter(outputfile);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			
			SecretKey key = Generate.key();
			IvParameterSpec iv = new IvParameterSpec(Generate.iv());
			
			while ((line = bufferedReader.readLine()) != null) {
				
				String enString = EncryptMethod.Encrypt(line, key, iv);
//				String enString = EncryptMethod.EncryptToASCII(line);
//				String ascii = hexToASCII(enString);
				System.out.println(enString);
//				System.out.println(ascii);
				
				bufferedWriter.write(enString);
				bufferedWriter.newLine();
			}
			
			long endTime = System.currentTimeMillis();
			
			System.out.println(endTime - startTime);
						
			bufferedReader.close();
			bufferedWriter.close();
		}
		catch (FileNotFoundException ex){
			System.out.println("Unable to open file" + inputfile);
		}
		catch (IOException ex){
			System.out.println("Error reading file" + inputfile);
		}
	}
}