import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Base64;

public class Generate_Random_String {

	static SecureRandom srand = new SecureRandom();
	static byte[] buffer = new byte[16];
    
	public static void generateRandonString2() {
	    
		srand.nextBytes(buffer);
	        String str1 = Base64.getUrlEncoder().encodeToString(buffer);
	        System.out.println("BASE64 str1 "+str1);
	        System.out.println("BASE64 str1 "+str1.length());
	    
	}
	
	public static void generateRandonString1() {
		
		SecureRandom random = new SecureRandom();
		
		String str1 =  new BigInteger(130, random).toString(32);
		System.out.println("str1 32 "+str1);
		
		str1 = new BigInteger(130, random).toString(10);
		System.out.println("str1 10 "+str1);
		
		str1 = new BigInteger(130, random).toString(8);
		System.out.println("str1 8 "+str1);
		
		str1 = new BigInteger(130, random).toString(16);
		System.out.println("str1 16 "+str1);
		
		str1 = new BigInteger(130, random).toString(28);
		System.out.println("str1 28 "+str1);
		
		str1 = new BigInteger(130, random).toString(64);
		System.out.println("str1 64 "+str1);
		
		str1 = new BigInteger(130, random).toString();
		System.out.println("str1 "+str1);
		
		str1 = new BigInteger(130, random).toString(2);
		System.out.println("str1 2 "+str1);
		
		str1 = new BigInteger(130, random).toString(12);
		System.out.println("str1 12 "+str1);
		 
		 
	}
	public static void main(String[] args) {
		generateRandonString1();
		generateRandonString2();
		generateRandonString2();
		generateRandonString2();
		generateRandonString2();
		generateRandonString2();
		generateRandonString2();
	}

}
