package ua.com.javarush.kravchenko.cryptoanalyzer;

public class CesarCipher {

    private static final String ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя" + "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    public String encrypt(String string, int key) {
        StringBuilder stringBuilder = new StringBuilder();
        for (char aChar : string.toCharArray()) {
            int indexAlphabet = ALPHABET.indexOf(aChar);
            int newIndexAlphabet;
            char charAt = 0;
            if (indexAlphabet >= 0) {
                if (key > 0) {
                    newIndexAlphabet = (indexAlphabet + key) % (ALPHABET.length() / 2);
                } else {
                    int newKey = key % ALPHABET.length() / 2;
                    newIndexAlphabet = (indexAlphabet + (ALPHABET.length() / 2) + newKey) % ALPHABET.length();
                }
                charAt = ALPHABET.charAt(newIndexAlphabet);
            }
            stringBuilder.append(charAt);
        }
        return stringBuilder.toString();
    }

    public String deEncrypt(String string, int key) {
        return encrypt(string, -key);
    }
}
