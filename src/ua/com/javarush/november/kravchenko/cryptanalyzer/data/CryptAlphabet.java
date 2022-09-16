package ua.com.javarush.november.kravchenko.cryptanalyzer.data;

import java.util.Arrays;
import java.util.List;

public class CryptAlphabet {

    private CryptAlphabet() {
    }

    public static final List<Character> ALPHABET = Arrays.asList('а', 'б', 'в',
            'г', 'д', 'е', 'ж', 'з', 'и', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у',
            'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'я', '.', ',', '«', '»',
            ':', '!', '?', ' ');

    public static final int ALPHABET_SIZE = ALPHABET.size();

    public static final String DOUBLE_SLASH = "\\";
    public static final String DASH = " - ";
}
