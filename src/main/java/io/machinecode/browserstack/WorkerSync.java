package io.machinecode.browserstack;

import java.io.IOException;

/**
 * @author Brent Douglas <brent.n.douglas@gmail.com>
 */
public interface WorkerSync extends Base {

    String getId();

    WorkerStatus status() throws IOException;

    void delete() throws IOException;
}
