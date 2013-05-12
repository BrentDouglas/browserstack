package io.machinecode.browserstack.http;

import java.util.List;

/**
 * @author Brent Douglas <brent.n.douglas@gmail.com>
 */
public interface Response {

    int code();

    byte[] data();

    List<String> header(final String header);
}
