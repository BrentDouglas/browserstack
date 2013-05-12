package io.machinecode.browserstack.test;

import io.machinecode.browserstack.Browser;
import io.machinecode.browserstack.BrowserStack;
import io.machinecode.browserstack.BrowserStackAsync;
import io.machinecode.browserstack.Status;
import io.machinecode.browserstack.WorkerAsync;
import io.machinecode.browserstack.WorkerStatus;
import junit.framework.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author Brent Douglas <brent.n.douglas@gmail.com>
 */
public class BrowserStackAsyncTest {

    public static final String GOOGLE = "http://www.google.com";
    private static BrowserStackAsync<WorkerAsync> async;

    @BeforeClass
    public static void beforeClass() {
        final String username = System.getenv("BROWSERSTACK_USERNAME");
        final String password = System.getenv("BROWSERSTACK_PASSWORD");
        async = BrowserStack.async(username, password);
    }

    @Test
    public void testBrowserStackSyncStatus() throws IOException, ExecutionException, InterruptedException {
        final Status status = async.statusAsync().get();
        Assert.assertNotNull(status);
    }

    @Test
    public void testBrowserStackBrowsers() throws IOException, ExecutionException, InterruptedException {
        final List<Browser> browsers = async.browsersAsync().get();
        Assert.assertNotNull(browsers);
        Assert.assertNotSame(browsers.size(), 0);
    }

    @Test
    public void testBrowserStackWorker() throws IOException, ExecutionException, InterruptedException {
        final List<Browser> browsers = async.browsersAsync().get();
        Assert.assertNotNull(browsers);
        Assert.assertNotSame(browsers.size(), 0);
        final WorkerAsync worker = async.workerAsync(browsers.get(0), 5, GOOGLE).get();
        try {
            Assert.assertNotNull(worker);
            final WorkerStatus status = worker.statusAsync().get();
            Assert.assertNotNull(status);
        } finally {
            if (worker != null) {
                worker.delete();
            }
        }
    }
}
