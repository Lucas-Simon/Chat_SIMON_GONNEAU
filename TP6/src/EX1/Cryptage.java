package EX1;

import javax.crypto.*;
import java.security.*;
import java.util.Scanner;

public class Cryptage {

    private static byte[] encrypt(String str, Key key, String type) {
        byte[] message = str.getBytes();
        try {
            Cipher cipher = Cipher.getInstance(type);
            cipher.init(Cipher.ENCRYPT_MODE,key);
            message = cipher.doFinal(message);
            return message;
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
            System.out.println("Erreur de l'encryptage de la clé");
            return null;
        }
    }

    private static String decrypt(byte[] b, Key key,String type) {
        byte[] message = null;
        try {
            Cipher cipher = Cipher.getInstance(type);
            cipher.init(Cipher.DECRYPT_MODE,key);
            message = cipher.doFinal(b);
            return new String(message);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
            System.out.println("Erreur de l'encryptage de la clé");
            return null;
        }
    }

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
//        Key key = keyGenerationDES();
//        String str = Scan();
//        byte[] messageEncrypt = encrypt(str, key, "DES");
//        System.out.println(messageEncrypt);
//        String messageDecrypt = decrypt(messageEncrypt,key, "DES");
//        System.out.println(messageDecrypt);

        String str = Scan();
        Key[] keys = keyGenerationRAS();
        byte[] messageEncrypt = encrypt(str, keys[0],"RSA");
        System.out.println(messageEncrypt);
        String messageDecrypt = decrypt(messageEncrypt,keys[1],"RSA");
        System.out.println(messageDecrypt);



    }
}
