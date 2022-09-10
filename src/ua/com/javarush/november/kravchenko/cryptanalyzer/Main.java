package ua.com.javarush.november.kravchenko.cryptanalyzer;

public class Main {
    public static void main(String[] args) {
        CesarCipher cesarCipher = new CesarCipher();

        String word = "привет мир";
        String enCripted = cesarCipher.encrypt(word, 1);
        System.out.println(enCripted);

        String deEncripted = cesarCipher.deEncrypt(enCripted, 1);
        System.out.println(deEncripted);
    }
}
