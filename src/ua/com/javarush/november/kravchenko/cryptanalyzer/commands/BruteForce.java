package ua.com.javarush.november.kravchenko.cryptanalyzer.commands;

import ua.com.javarush.november.kravchenko.cryptanalyzer.data.Message;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import static ua.com.javarush.november.kravchenko.cryptanalyzer.data.CryptAlphabet.ALPHABET;
import static ua.com.javarush.november.kravchenko.cryptanalyzer.data.CryptAlphabet.ALPHABET_SIZE;
import static ua.com.javarush.november.kravchenko.cryptanalyzer.data.MessageConsole.ERROR_READ;
import static ua.com.javarush.november.kravchenko.cryptanalyzer.data.MessageConsole.FILE_NOT_FOUND;

public class BruteForce {
    public static int calculatingEncryptionKey(String inputFilePath) {
        int encryptionKey = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
            Map<Character, Integer> mapCharacters = new TreeMap<>();

            while (reader.ready()) {
                char characterFromFile = (char) reader.read();
                if (mapCharacters.containsKey(characterFromFile)) {
                    int charsCounter = mapCharacters.get(characterFromFile);
                    charsCounter++;
                    mapCharacters.put(characterFromFile, charsCounter);
                } else {
                    mapCharacters.put(characterFromFile, 1);
                }
            }

            int maxCharacterRepetition = Collections.max(mapCharacters.values());
            char maxRepeatedCharacter = ' ';

            for (Map.Entry<Character, Integer> pair : mapCharacters.entrySet()) {
                if (pair.getValue() == maxCharacterRepetition) {
                    maxRepeatedCharacter = pair.getKey();
                    break;
                }
            }

            reader = new BufferedReader(new FileReader(inputFilePath));

            while (reader.ready()) {
                char characterFromFile = (char) reader.read();
                if (characterFromFile == maxRepeatedCharacter) {
                    int indexCharacterFromAlphabet = ALPHABET.indexOf(characterFromFile);
                    int indexOfSpace = ALPHABET.indexOf(' ');
                    encryptionKey = ALPHABET_SIZE - (indexOfSpace - indexCharacterFromAlphabet);
                    break;
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            Message.print(FILE_NOT_FOUND);
        } catch (IOException e) {
            Message.print(ERROR_READ);
        }
        return encryptionKey;
    }
}
