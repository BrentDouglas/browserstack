package io.machinecode.browserstack.test;

import io.machinecode.browserstack.Browser;
import io.machinecode.browserstack.BrowserStack;
import io.machinecode.browserstack.BrowserStackSync;
import io.machinecode.browserstack.Status;
import io.machinecode.browserstack.WorkerStatus;
import io.machinecode.browserstack.WorkerSync;
import junit.framework.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * @author Brent Douglas <brent.n.douglas@gmail.com>
 */
public class BrowserStackSyncTest {

    public static final String GOOGLE = "http://www.google.com";
    private static BrowserStackSync<WorkerSync> sync;

    @BeforeClass
    public static void beforeClass() {
        final String username = System.getenv("BROWSERSTACK_USERNAME");
        final String password = System.getenv("BROWSERSTACK_PASSWORD");
        sync = BrowserStack.sync(username, password);
    }

    @Test
    public void testBrowserStackStatus() throws IOException {
        final Status status = sync.status();
        Assert.assertNotNull(status);
    }

    @Test
    public void testBrowserStackBrowsers() throws IOException {
        final List<Browser> browsers = sync.browsers();
        Assert.assertNotNull(browsers);
        Assert.assertNotSame(browsers.size(), 0);
    }

    @Test
    public void testBrowserStackWorker() throws IOException {
        final List<Browser> browsers = sync.browsers();
        Assert.assertNotNull(browsers);
        Assert.assertNotSame(browsers.size(), 0);
        final WorkerSync worker = sync.worker(browsers.get(0), 5, GOOGLE);
        try {
            Assert.assertNotNull(worker);
            final WorkerStatus status = worker.status();
            Assert.assertNotNull(status);
        } finally {
            if (worker != null) {
                worker.delete();
            }
        }
    }
}
