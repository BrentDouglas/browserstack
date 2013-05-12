package io.machinecode.browserstack;

import java.io.IOException;
import java.util.List;

/**
 * Can make Synchronous calls to the BrowserStack API
 *
 * @author Brent Douglas <brent.n.douglas@gmail.com>
 */
public interface BrowserStackSync<T extends WorkerSync> {

    List<Browser> browsers() throws IOException;

    T worker(final Browser browser, final int timeout, final String url) throws IOException;

    T worker(final Browser browser, final String url) throws IOException;

    Status status() throws IOException;
}
