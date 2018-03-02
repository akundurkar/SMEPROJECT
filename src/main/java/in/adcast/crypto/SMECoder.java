package in.adcast.crypto;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class SMECoder {

	private static final String ALGORITHM = "AES";
	private static final byte[] keyValue = "ADBSJHJS12547896".getBytes();

	public static void main(String args[]) {
		String encriptValue = encrypt("dude5-jashkjahksjh-kajdkjdkjadkjl");
		System.out.println(decrypt(encriptValue));

	}


	public static String encrypt(String valueToEnc)  
	{
		Cipher c = null;
		String encryptedValue = null;//use to get encrypt value and return to calling fun
		Key key=null;
		try
		{
			key = generateKey();		
			c = Cipher.getInstance(ALGORITHM);
			c.init(Cipher.ENCRYPT_MODE, key);
			System.out.println("valueToEnc.getBytes().length "+valueToEnc.getBytes().length);
			byte[] encValue = c.doFinal(valueToEnc.getBytes());
			System.out.println("encValue length" + encValue.length);
			byte[] encryptedByteValue = new Base64().encode(encValue);
			encryptedValue = new String(encryptedByteValue);//encryptedByteValue.toString();
			System.out.println("encryptedValue " + encryptedValue);
		}catch (NoSuchAlgorithmException | NoSuchPaddingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (InvalidKeyException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return encryptedValue;
	}

	public static String decrypt(String encryptedValue) {
		Key key = null;
		Cipher c = null;
		byte[] enctVal=null;
		try{

			key= generateKey();
			c = Cipher.getInstance(ALGORITHM);
			c.init(Cipher.DECRYPT_MODE, key);
			enctVal = c.doFinal(new Base64().decode(encryptedValue.getBytes()));

		}catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		System.out.println("enctVal length " + enctVal.length);

		byte[] decordedValue = enctVal;

		return new String(decordedValue);//decordedValue.toString();
	}

	private static Key generateKey() throws Exception {
		Key key = new SecretKeySpec(keyValue, ALGORITHM);
		return key;
	}

}