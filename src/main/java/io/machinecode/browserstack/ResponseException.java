package io.machinecode.browserstack;

/**
 * @author Brent Douglas <brent.n.douglas@gmail.com>
 */
public class ResponseException extends RuntimeException {

    public ResponseException(final int code) {
        super("Received response code " + code);
    }

    public ResponseException(final Failure failure) {
        super("Received response code 403: " + failure.toString());
    }
}
