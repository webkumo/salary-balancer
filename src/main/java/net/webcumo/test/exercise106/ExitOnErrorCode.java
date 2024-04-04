package net.webcumo.test.exercise106;

public class ExitOnErrorCode implements ErrorCodeListener {
    @Override
    public void registerErrorCode(int code) {
        System.exit(code);
    }
}
