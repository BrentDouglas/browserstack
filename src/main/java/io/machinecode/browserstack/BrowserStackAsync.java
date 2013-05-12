package io.machinecode.browserstack;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Future;

/**
 * Can make Asynchronous calls to the BrowserStack API
 *
 * @author Brent Douglas <brent.n.douglas@gmail.com>
 */
public interface BrowserStackAsync<T extends WorkerSync> extends BrowserStackSync<T> {

    Future<List<Browser>> browsersAsync() throws IOException;

    Future<T> workerAsync(final Browser browser, final int timeout, final String url) throws IOException;

    Future<T> workerAsync(final Browser browser, final String url) throws IOException;

    Future<Status> statusAsync() throws IOException;
}
