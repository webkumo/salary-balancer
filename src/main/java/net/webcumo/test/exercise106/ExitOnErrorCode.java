package net.webcumo.test.exercise106;

public class ExitOnErrorCode implements ErrorCodeListener {
    @Override
    public void registerErrorCode(int code) {
        //todo it is impossible to test `System.exit` calls since java 17.
        System.exit(code);
    }
}
