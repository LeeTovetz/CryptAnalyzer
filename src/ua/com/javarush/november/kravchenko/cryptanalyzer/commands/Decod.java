package ua.com.javarush.november.kravchenko.cryptanalyzer.commands;

import ua.com.javarush.november.kravchenko.cryptanalyzer.data.Message;

import java.io.*;

import static ua.com.javarush.november.kravchenko.cryptanalyzer.data.CryptAlphabet.ALPHABET;
import static ua.com.javarush.november.kravchenko.cryptanalyzer.data.CryptAlphabet.ALPHABET_SIZE;
import static ua.com.javarush.november.kravchenko.cryptanalyzer.data.MessageConsole.ERROR_READ;
import static ua.com.javarush.november.kravchenko.cryptanalyzer.data.MessageConsole.FILE_NOT_FOUND;

public class Decod {
    public void startDecryption(String inputFilePath, String outputFilePath, int encryptionKey) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))
        ) {
            while (reader.ready()) {
                char characterFromFile = (char) reader.read();
                int indexCharacterFromAlphabet = ALPHABET.indexOf(characterFromFile);

                if (indexCharacterFromAlphabet == -1) {
                    writer.write(characterFromFile);
                } else {
                    int indexForDecryption = indexCharacterFromAlphabet - encryptionKey;
                    if (indexForDecryption < 0) {
                        indexForDecryption = ALPHABET_SIZE + indexForDecryption;
                    }
                    writer.write(ALPHABET.get(indexForDecryption));
                }
            }
            writer.flush();
        } catch (FileNotFoundException e) {
            Message.print(FILE_NOT_FOUND);
        } catch (IOException e) {
            Message.print(ERROR_READ);
        }
    }
}
