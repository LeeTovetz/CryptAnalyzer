package ua.com.javarush.november.kravchenko.cryptanalyzer.consol;

import ua.com.javarush.november.kravchenko.cryptanalyzer.commands.BruteForce;
import ua.com.javarush.november.kravchenko.cryptanalyzer.commands.Decod;
import ua.com.javarush.november.kravchenko.cryptanalyzer.commands.Encod;
import ua.com.javarush.november.kravchenko.cryptanalyzer.data.Message;

import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Scanner;

import static ua.com.javarush.november.kravchenko.cryptanalyzer.data.CryptAlphabet.ALPHABET_SIZE;
import static ua.com.javarush.november.kravchenko.cryptanalyzer.data.MessageConsole.*;
import static ua.com.javarush.november.kravchenko.cryptanalyzer.data.ConslVersion.*;
import static ua.com.javarush.november.kravchenko.cryptanalyzer.data.CryptAlphabet.DASH;
import static ua.com.javarush.november.kravchenko.cryptanalyzer.data.CryptAlphabet.DOUBLE_SLASH;

public class ConslMenu {
    private final Scanner console = new Scanner(System.in);

    public void start() {
        Message.print(GREETINGS);
        boolean isWorking = true;

        while (isWorking) {
            for (Operation operation : Operation.values()) {
                Message.print(operation.getOperationToken() + DASH + operation.getDescription());
            }
            Message.printLine(ENTER_OPTIONS);
            String selectedOption = console.nextLine();

            try {
                boolean withBruteForce = true;
                switch (Operation.getOperationByToken(selectedOption)) {
                    case EXIT:
                        isWorking = processExit();
                        break;
                    case ENCODER:
                        processEncryption();
                        break;
                    case DECODER:
                        processDecryption(!withBruteForce);
                        break;
                    case BRUTEFORCE:
                        processDecryption(withBruteForce);
                        break;
                }
            } catch (IllegalArgumentException e) {
                Message.print(e.getMessage());
            }
        }
    }

    private boolean processExit() {
        Message.print(EXIT);
        console.close();
        return false;
    }

    private void processEncryption() {
        String inputFilePath = enterInputFilePath();
        String outputFilePath = enterOutputFilePath();
        int encryptionKey = enterEncryptionKey();

        try {
            new Encod().startEncryption(inputFilePath, outputFilePath, encryptionKey);
            Message.print(FILE_ENCRYPTED + outputFilePath);
        } catch (Exception e) {
            Message.print(e.getMessage());
        }
    }

    private void processDecryption(boolean withBruteForce) {
        String inputFilePath = enterInputFilePath();
        String outputFilePath = enterOutputFilePath();
        int encryptionKey;

        if (!withBruteForce) {
            encryptionKey = enterEncryptionKey();
        } else {
            encryptionKey = BruteForce.calculatingEncryptionKey(inputFilePath);
        }

        try {
            new Decod().startDecryption(inputFilePath, outputFilePath, encryptionKey);
            Message.print(FILE_DECRYPTED + outputFilePath);
        } catch (Exception e) {
            Message.print(e.getMessage());
        }
    }

    private String enterInputFilePath() {
        boolean isWorking = true;
        String inputFilePath = "";

        while (isWorking) {
            Message.printLine(ENTER_INPUT_FILE);
            inputFilePath = console.nextLine();

            try {
                if (!Files.isRegularFile(Path.of(inputFilePath))) {
                    Message.print(FILE_NOT_FOUND);
                } else {
                    isWorking = false;
                }
            } catch (InvalidPathException e) {
                Message.print(FILE_NOT_FOUND);
            }
        }
        return inputFilePath;
    }

    private String enterOutputFilePath() {
        boolean isWorking = true;
        String outputFilePath = "";

        while (isWorking) {
            Message.printLine(ENTER_OUTPUT_FOLDER);
            Path folderPath = Path.of(console.nextLine());

            try {
                if (Files.exists(folderPath) && Files.isDirectory(folderPath)) {
                    Message.printLine(ENTER_OUTPUT_FILE_NAME);
                    String fileName = console.nextLine() + ".txt";
                    outputFilePath = folderPath + DOUBLE_SLASH + fileName;
                    isWorking = false;
                } else {
                    Message.print(DIRECTORY_NOT_FOUND);
                }
            } catch (InvalidPathException e) {
                Message.print(DIRECTORY_NOT_FOUND);
            }
        }
        return outputFilePath;
    }

    private int enterEncryptionKey() {
        boolean isWorking = true;
        int encryptionKey = 0;

        while (isWorking) {
            Message.printLine(ENTER_ENCRYPTION_KEY);
            try {
                encryptionKey = Integer.parseInt(console.nextLine());
                if (encryptionKey <= 0 || encryptionKey >= ALPHABET_SIZE) {
                    System.out.printf("* Key should be between 0 and %d *\n", ALPHABET_SIZE);
                } else {
                    isWorking = false;
                }
            } catch (NumberFormatException e) {
                Message.print(INVALID_KEY);
            }
        }
        return encryptionKey;
    }
}
