package EX1;

import java.security.*;
import javax.crypto.*;
import java.io.*;
import javax.crypto.KeyGenerator;

class CryptageFile {

    private static byte[] getEncrypte(String keyS, Key key){
        //Partie création clés
        try {
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return cipher.doFinal(keyS.getBytes());
        }
        catch(Exception ex){
            System.out.println("la génération de la clés a échoué.");
            return null;
        }

    }

    private static byte[] getDecrypt(byte[] messageEncrypt, Key key){
        try {
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            return cipher.doFinal(messageEncrypt);
        }
        catch(Exception ex){
            System.out.println("la génération de la clés a échoué.");
            return null;
        }

    }

    private static String lireFichier(String File){
        String FileName ="";
        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(File));
            StringWriter out = new StringWriter();
            int bb;
            while ((bb=in.read()) != -1)
                out.write(bb);
            out.flush();
            out.close();
            in.close();
            FileName = out.toString();
        }
        catch (IOException ie)
        {
            ie.printStackTrace();
        }
        return FileName;
    }

    private static void ecrireDansFichier(String File, String text){
        try{

            File fileOut=new File(File); // définir l'arborescence
            fileOut.createNewFile();
            FileWriter fileOutW=new FileWriter(fileOut);
            fileOutW.write(text);  // écrire une ligne dans le fichier resultat.txt
            fileOutW.close(); // fermer le fichier à la fin des traitements
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public static String toHexa(byte[] b){ // convert byte[] to hexadecimal
        String str ="";
        for(int i = 0; i < b.length; i ++){
            str += String.format("%02x",b[i]);
        }
        return str.toLowerCase();
    }

    public static byte[] hexStringToByteArray(String s) { // convert hexadecimal to byte[]
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }


    public static void main(String[] args){
        try{
            KeyGenerator keyGen = KeyGenerator.getInstance("DES");
            keyGen.init(56);
            Key key = keyGen.generateKey();

            String FileNameClear = lireFichier(args[0]);

            byte[] crypter = getEncrypte(FileNameClear, key);
            String HexCrypterS = toHexa(crypter);
            ecrireDansFichier("resultat.txt",HexCrypterS);

            String FileCryptHex = lireFichier("resultat.txt");
            byte[] crypterR = hexStringToByteArray(FileCryptHex);

            byte[] resultat = getDecrypt(crypterR, key);
            String resultatS = new String(resultat, "UTF8");
            ecrireDansFichier("resultat2.txt",resultatS);
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }
}
