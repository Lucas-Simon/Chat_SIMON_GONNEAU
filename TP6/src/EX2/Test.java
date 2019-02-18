package EX2;

import javax.crypto.*;
import java.security.*;

public class Test {

    private static byte[] encryptKey(Key key1, Key key2) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE,key2);
            byte[] key1Byte = cipher.doFinal(key1.getEncoded());
            return key1Byte;
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
            System.out.println("Erreur de l'encryptage de la clé");
            return null;
        }
    }

    private static byte[] decryptKey(byte[] key1, Key key2) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE,key2);
            byte[] key1Byte = cipher.doFinal(key1);
            return key1Byte;
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
            System.out.println("Erreur de l'encryptage de la clé");
            return null;
        }
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

    public static void main(String[] args) {
        Key keyDES = keyGenerationDES();
        Key[] keysRAS = keyGenerationRAS();

        Key publicKeyRAS = keysRAS[0];
        Key privateKeyRAS = keysRAS[1];

        byte[] keyEncrypt = encryptKey(keyDES,publicKeyRAS);
        byte[] keyDecoded = decryptKey(keyEncrypt,privateKeyRAS);

        System.out.println(keyDES.getEncoded().equals(keyDecoded));


    }
}
