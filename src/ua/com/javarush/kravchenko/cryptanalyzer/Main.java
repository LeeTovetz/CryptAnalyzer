package ua.com.javarush.kravchenko.cryptanalyzer;

public class Main {
    public static void main(String[] args) {
        CesarCipher cesarCipher = new CesarCipher();

        String word = "эюя";
        String enCripted = cesarCipher.encrypt(word, 4);
        System.out.println(enCripted);

        String deEncripted = cesarCipher.deEncrypt(enCripted, 4);
        System.out.println(deEncripted);
    }
}
