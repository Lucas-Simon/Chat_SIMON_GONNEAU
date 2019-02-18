package EX2;

import javax.crypto.*;
import java.security.*;
import java.util.Scanner;

public class Emetteur {

    private static Key keyGenerationDES() {
        KeyGenerator keyGen;
        Key key = null;
        try {
            keyGen = KeyGenerator.getInstance("DES");
            keyGen.init(56);
            key = keyGen.generateKey();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return key;
    }


    private static String Scan() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Entrez une phrase");
        return sc.nextLine();
    }

    public static void main(String[] args) {
        String message = Scan();
        Key keyDES = keyGenerationDES();
        Key publicKey = null;


    }

}
