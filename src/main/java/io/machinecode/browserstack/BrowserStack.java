package io.machinecode.browserstack;

import io.machinecode.browserstack.http.Http;
import io.machinecode.browserstack.http.Request;
import io.machinecode.browserstack.http.Response;
import org.apache.commons.codec.binary.Base64;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.PropertyNamingStrategy;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Java language bindings for BrowserStack API v3
 *
 * @see <a href="https://github.com/browserstack/api">API docs on github</a>
 *
 * @author Brent Douglas <brent.n.douglas@gmail.com>
 */
public class BrowserStack<T extends WorkerSync> implements BrowserStackAsync<T> {

    static final String URL = "http://api.browserstack.com/3/";
    public static final TypeReference<List<Browser>> BROWSER_LIST = new TypeReference<List<Browser>>() {
    };

    private final ObjectMapper mapper;
    private final String credentials;
    private final ExecutorService executor;
    private final Class<T> workerType;

    private BrowserStack(final String username, final String password, final ExecutorService executor, final Class<T> workerType) {
        if (username == null || password == null) {
            throw new IllegalArgumentException("BrowserStack username and password must be provided.");
        }
        this.credentials = "Basic " + new Base64(0, new byte[0]).encodeToString((username + ":" + password).getBytes());
        this.mapper = new ObjectMapper()
                .setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
        this.executor = executor;
        this.workerType = workerType;
    }

    /**
     * Calls the BrowserStack API synchronously
     *
     * @param username BrowserStack username
     * @param password BrowserStack password
     * @return
     */
    public static BrowserStackSync<WorkerSync> sync(final String username, final String password) {
        return new BrowserStack<WorkerSync>(username, password, null, WorkerSync.class);
    }

    /**
     * Calls the BrowserStack API asynchronously. Uses a single thread to execute calls.
     *
     * @param username BrowserStack username
     * @param password BrowserStack password
     * @return
     */
    public static BrowserStackAsync<WorkerAsync> async(final String username, final String password) {
        return new BrowserStack<WorkerAsync>(username, password, Executors.newSingleThreadExecutor(), WorkerAsync.class);
    }

    /**
     * Calls the BrowserStack API asynchronously. Uses the provided {@link ExecutorService} to execute calls.
     *
     * @param username BrowserStack username
     * @param password BrowserStack password
     * @param executor To execute asynchronous calls.
     * @return
     */
    public static BrowserStackAsync<WorkerAsync> async(final String username, final String password, final ExecutorService executor) {
        if (executor == null) {
            throw new IllegalArgumentException("ExecutorService must be provided.");
        }
        return new BrowserStack<WorkerAsync>(username, password, executor, WorkerAsync.class);
    }

    // Synchronous

    @Override
    public List<Browser> browsers() throws IOException {
        return runCollection(mapper, request(Http.GET, "browsers?flat=true"), BROWSER_LIST);
    }

    @Override
    public T worker(final Browser browser, final int timeout, final String url) throws IOException {
        return (T) run(mapper, workerRequest(browser, timeout, url), Worker.class);
        //return run(mapper, workerRequest(browser, timeout, url), workerType, Worker.class); //TODO Won't compile w/ oracle jdk 7u21
    }

    @Override
    public T worker(final Browser browser, final String url) throws IOException {
        return worker(browser, -1, url);
    }

    @Override
    public Status status() throws IOException {
        return run(mapper, request(Http.GET, "status"), Status.class);
    }

    //Asynchronous

    @Override
    public Future<List<Browser>> browsersAsync() throws IOException {
        return runCollectionAsync(mapper, request(Http.GET, "browsers?flat=true"), BROWSER_LIST);
    }

    @Override
    public Future<T> workerAsync(final Browser browser, final int timeout, final String url) throws IOException {
        return (Future<T>) runAsync(mapper, workerRequest(browser, timeout, url), Worker.class);
        //return runAsync(mapper, workerRequest(browser, timeout, url), workerType, Worker.class); //TODO Won't compile w/ oracle jdk 7u21
    }

    @Override
    public Future<T> workerAsync(final Browser browser, final String url) throws IOException {
        return workerAsync(browser, -1, url);
    }

    @Override
    public Future<Status> statusAsync() throws IOException {
        return runAsync(mapper, request(Http.GET, "status"), Status.class);
    }

    // Internal

    private Request workerRequest(final Browser browser, final int timeout, final String url) throws IOException {
        final Request request = request(Http.POST, "worker", browser.parameters());
        if (timeout >= 3600) {
            request.data("timeout", Integer.toString(3600));
        } else if (timeout > 0) {
            request.data("timeout", Integer.toString(timeout));
        }
        request.data("url", url);
        return request;
    }

    Request request(final String method, final String url, final Map<String, String> parameters) throws IOException {
        return Http.request(method, URL + url)
                .data(parameters)
                .cookie("Authorization", credentials);
    }

    Request request(final String method, final String url) throws IOException {
        return Http.request(method, URL + url)
                .cookie("Authorization", credentials);
    }

    <X extends Base> X run(final ObjectMapper mapper, final Request request, final Class<X> clazz) throws IOException {
        final Response response = request
                .send();
        if (response.code() == 200) {
            if (clazz == null) {
                return null;
            }
            final X that = mapper.readValue(response.data(), clazz);
            that.setMapper(mapper)
                    .setBrowserStack(this);
            return that;
        } else if (response.code() == 422) {
            throw new ResponseException(mapper.readValue(response.data(), Failure.class));
        } else {
            throw new ResponseException(response.code());
        }
    }

    <X extends Base> Future<X> runAsync(final ObjectMapper mapper, final Request request, final Class<X> clazz) throws IOException {
        if (this.executor == null) {
            throw new IllegalStateException("Asynchronous calls not supported from BrowserStack instances created with BrowserStack#sync");
        }
        return executor.submit(new Callable<X>() {
            @Override
            public X call() throws Exception {
                return BrowserStack.this.run(mapper, request, clazz);
            }
        });
    }

    <X extends Base, Y extends X> X run(final ObjectMapper mapper, final Request request, final Class<X> interfaz, final Class<Y> clazz) throws IOException {
        final Response response = request
                .send();
        if (response.code() == 200) {
            if (interfaz == null) {
                return null;
            }
            final Y that = mapper.readValue(response.data(), clazz);
            that.setMapper(mapper)
                    .setBrowserStack(this);
            return that;
        } else if (response.code() == 422) {
            throw new ResponseException(mapper.readValue(response.data(), Failure.class));
        } else {
            throw new ResponseException(response.code());
        }
    }

    <X extends Base, Y extends X> Future<X> runAsync(final ObjectMapper mapper, final Request request, final Class<X> interfaz, final Class<Y> clazz) throws IOException {
        if (this.executor == null) {
            throw new IllegalStateException("Asynchronous calls not supported from BrowserStack instances created with BrowserStack#sync");
        }
        return executor.submit(new Callable<X>() {
            @Override
            public X call() throws Exception {
                return BrowserStack.this.run(mapper, request, interfaz, clazz);
            }
        });
    }

    <X extends Base, Y extends Collection<X>> Y runCollection(final ObjectMapper mapper, final Request request, final TypeReference<Y> clazz) throws IOException {
        final Response response = request
                .send();
        if (response.code() == 200) {
            if (clazz == null) {
                return null;
            }
            final Y collection = mapper.readValue(response.data(), clazz);
            for (final X that : collection) {
                that.setMapper(mapper)
                        .setBrowserStack(this);
            }
            return collection;
        } else if (response.code() == 422) {
            throw new ResponseException(mapper.readValue(response.data(), Failure.class));
        } else {
            throw new ResponseException(response.code());
        }
    }

    <X extends Base, Y extends Collection<X>> Future<Y> runCollectionAsync(final ObjectMapper mapper, final Request request, final TypeReference<Y> clazz) throws IOException {
        if (this.executor == null) {
            throw new IllegalStateException("Asynchronous calls not supported from BrowserStack instances created with BrowserStack#sync");
        }
        return executor.submit(new Callable<Y>() {
            @Override
            public Y call() throws Exception {
                return BrowserStack.this.runCollection(mapper, request, clazz);
            }
        });
    }
}
