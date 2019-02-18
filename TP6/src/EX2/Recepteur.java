package EX2;

import java.security.*;
import java.util.Scanner;

public class Recepteur {

    private static Key[] keyGenerationRAS() {
        KeyPairGenerator keyGen;
        PrivateKey keyPrivate = null;
        PublicKey keyPublic = null;
        Key[] keys = null;
        try {
            keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(1020);
            KeyPair keyPair = keyGen.genKeyPair();
            keyPrivate = keyPair.getPrivate();
            keyPublic = keyPair.getPublic();
            keys = new Key[]{keyPublic, keyPrivate};
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return keys;
    }

    private static String Scan() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Entrez une phrase");
        return sc.nextLine();
    }

    public static void main(String[] args) {
        String message = Scan();
        Key[] keys = keyGenerationRAS();
        Key pubicKey = keys[0];
        Key privateKey = keys[1];
        System.out.println(privateKey);


    }
}
