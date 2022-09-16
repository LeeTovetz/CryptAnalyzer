package ua.com.javarush.november.kravchenko.cryptanalyzer.consol;

import static ua.com.javarush.november.kravchenko.cryptanalyzer.data.MessageConsole.ILLEGAL_OPERATION;

public enum Operation {
    EXIT("0", "Exit"),
    ENCODER("1", "Encoder"),
    DECODER("2", "Decoder"),
    BRUTEFORCE("3", "Decoder with brute force");

    private final String operationToken;
    private final String description;

    Operation(String operationToken, String description) {
        this.operationToken = operationToken;
        this.description = description;
    }

    public String getOperationToken() {
        return operationToken;
    }

    public String getDescription() {
        return description;
    }

    public static Operation getOperationByToken(String token) {
        for (Operation operation : values()) {
            if (operation.getOperationToken().equals(token)) {
                return operation;
            }
        }
        throw new IllegalArgumentException(ILLEGAL_OPERATION);
    }
}
