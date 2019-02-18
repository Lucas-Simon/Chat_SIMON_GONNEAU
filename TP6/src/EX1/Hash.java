package EX1;

import java.security.*;


public class Hash {

    public static byte[] encryptMD5(String mdp) {
        byte[] code = null;
        try {
            String str = mdp;
            byte[] data = str.getBytes();
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(data);
            code = md.digest();
        } catch (Exception e) {

        }
        return code;
    }

    public static byte[] encryptSHA(String mdp) {
        byte[] code = null;
        try {
            String str = mdp;
            byte[] data = str.getBytes();
            MessageDigest md = MessageDigest.getInstance("SHA");
            md.update(data);
            code = md.digest();
        } catch (Exception e) {

        }
        return code;
    }

    public static String toString(byte[] b) {
        String str = "";
        for (int i = 0; i < b.length; i++) {
            str += String.format("%02x", b[i]);
        }
        return str.toLowerCase();
    }

    public static String BruteForceMD5(String mdp) {
        byte[] b;
        String test = "";

        for (char i : "abcdefghijklmnopqrstuvwxyz".toCharArray()) {
            for (char j : "abcdefghijklmnopqrstuvwxyz".toCharArray()) {
                for (char k : "abcdefghijklmnopqrstuvwxyz".toCharArray()) {
                    for (char l : "abcdefghijklmnopqrstuvwxyz".toCharArray()) {
                        b = encryptMD5(String.valueOf(i) + String.valueOf(j) + String.valueOf(k) + String.valueOf(l));
                        test = toString(b);
                        if (mdp.equals(test)) {
                            return (String.valueOf(i) + String.valueOf(j) + String.valueOf(k) + String.valueOf(l));
                        }
                    }
                }
            }
        }
        return ("Je n'ai pas trouvé le mot de passe");
    }

    public static String BruteForceSHA(String mdp) {
        byte[] b;
        String test = "";

        for (char i : "abcdefghijklmnopqrstuvwxyz".toCharArray()) {
            for (char j : "abcdefghijklmnopqrstuvwxyz".toCharArray()) {
                for (char k : "abcdefghijklmnopqrstuvwxyz".toCharArray()) {
                    for (char l : "abcdefghijklmnopqrstuvwxyz".toCharArray()) {
                        b = encryptSHA(String.valueOf(i) + String.valueOf(j) + String.valueOf(k) + String.valueOf(l));
                        test = toString(b);
                        if (mdp.equals(test)) {
                            return (String.valueOf(i) + String.valueOf(j) + String.valueOf(k) + String.valueOf(l));
                        }
                    }
                }
            }
        }
        return ("Je n'ai pas trouvé le mot de passe");
    }

    private static void Cryptage(byte[] data, String type) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(type); // sélectionne SHA OU MD5
        md.update(data); // sélectionne la chaine à coder
        // data est une chaine de caractères qui contient le texte en clair
        byte[] code = md.digest(); // code la chaine (data) et met le résultat dans code
        // affichage du résultat
        System.out.println("\nResultat du cryptage (" + code.length + " octets) en " + type);
        String message = toString(code);
        System.out.println(message);

    }

    public static void main(String[] args) {

        try {
            //--------------Première partie-----------------
            String str = "a";
            byte[] data = str.getBytes();
            Cryptage(data, "MD5");
            Cryptage(data, "SHA");

            //--------------Deuxième partie-----------------
            java.util.Date uDate = new java.util.Date(System.currentTimeMillis()); //Relever l'heure avant le debut du progamme (en milliseconde)

            //----Premier mot de passe -----
            String resultatMD5 = BruteForceMD5("e2fc714c4727ee9395f324cd2e7f331f");
            System.out.println("\n");
            System.out.println("Résultat mdp MD5 : ");
            System.out.println(resultatMD5);

            //----Deuxième mot de passe -----
            String resultatSHA = BruteForceSHA("81fe8bfe87576c3ecb22426f8e57847382917acf");
            System.out.println("Résultat mdp SHA : ");
            System.out.println(resultatSHA);

         } catch (Exception e) {

        }
    }
}


