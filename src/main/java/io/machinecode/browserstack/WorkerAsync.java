package io.machinecode.browserstack;

import java.io.IOException;
import java.util.concurrent.Future;

/**
 * @author Brent Douglas <brent.n.douglas@gmail.com>
 */
public interface WorkerAsync extends WorkerSync {

    Future<WorkerStatus> statusAsync() throws IOException;

    void deleteAsync() throws IOException;
}
