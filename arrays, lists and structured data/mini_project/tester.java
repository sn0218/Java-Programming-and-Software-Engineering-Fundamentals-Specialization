
/**
 * Write a description of tester here.
 * 
 * @author Samuel Ng 
 * @version 1.0.0
 */
import edu.duke.*;

public class tester {
    public void ccTester () {
        /*
        int dkey = 5;
        CaesarCipher cc = new CaesarCipher(dkey);
        FileResource fr = new FileResource();
        String originalMsg = fr.asString();
        
        System.out.println("Test 1:");
        System.out.println("Original: ");
        System.out.println(originalMsg);
        
        String encrypted = cc.encrypt(originalMsg);
        System.out.println("Encrypted message with key " + dkey + ": ");
        System.out.println(encrypted);
        
        String decrypted = cc.decrypt(encrypted);
        System.out.println("Decrypted message (knowing the key): ");
        System.out.println(decrypted);
        
        System.out.println();
        System.out.println("Test 2:");
        CaesarCipher cc2 = new CaesarCipher(dkey);
        FileResource fr2 = new FileResource();
        String originalMsg2 = fr2.asString();
        
        System.out.println("Original: ");
        System.out.println(originalMsg2);
        
        CaesarCracker cracker = new CaesarCracker();
        String decryptedAuto = cracker.decrypt(originalMsg2);
        System.out.println("Decrypted message (cracker): ");
        System.out.println(decryptedAuto);
        
        // test cracker for portuguese
        System.out.println("Test 3:");
        CaesarCipher cc3 = new CaesarCipher(dkey);
        FileResource fr3 = new FileResource();
        String originalMsg3 = fr3.asString();
        
        System.out.println("Original: ");
        System.out.println(originalMsg3);
        
        CaesarCracker cracker2 = new CaesarCracker('a');
        String decryptedAuto2 = cracker2.decrypt(originalMsg3);
        System.out.println("Decrypted message (cracker): ");
        System.out.println(decryptedAuto2);
        */
        
        // test for Vigenere
        System.out.println("Test 4:");
        int[] romeKey = {17, 14, 12, 4};
        
        FileResource fr4 = new FileResource();
        String originalMsg4 = fr4.asString();
        
        VigenereCipher vc = new VigenereCipher(romeKey);
        String encryptedVC = vc.encrypt(originalMsg4);
        
        System.out.println("Encrypted message with VC: ");
        System.out.println(encryptedVC);
        
        String decryptedVC = vc.decrypt(encryptedVC);
        System.out.println("Decrypted message (VC)(knowing the key): ");
        System.out.println(decryptedVC);
        
        
    }
    
    

}
