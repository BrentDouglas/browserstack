package io.machinecode.browserstack;

import io.machinecode.browserstack.http.Http;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;

import java.io.IOException;
import java.util.concurrent.Future;

import static org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE;

/**
 * @author Brent Douglas <brent.n.douglas@gmail.com>
 */
@JsonAutoDetect(creatorVisibility = NONE,
        fieldVisibility = NONE,
        getterVisibility = NONE,
        isGetterVisibility = NONE,
        setterVisibility = NONE)
public class Worker extends BaseImpl implements WorkerAsync {

    private String id;

    @JsonProperty
    @Override
    public String getId() {
        return id;
    }

    public Worker setId(final String id) {
        this.id = id;
        return this;
    }

    @Override
    public WorkerStatus status() throws IOException {
        return browserStack.run(mapper, browserStack.request(Http.GET, "worker/" + id), WorkerStatus.class);
    }

    @Override
    public Future<WorkerStatus> statusAsync() throws IOException {
        return browserStack.runAsync(mapper, browserStack.request(Http.GET, "worker/" + id), WorkerStatus.class);
    }

    @Override
    public void delete() throws IOException {
        browserStack.run(mapper, browserStack.request(Http.DELETE, "worker/" + id), null);
    }

    @Override
    public void deleteAsync() throws IOException {
        browserStack.runAsync(mapper, browserStack.request(Http.DELETE, "worker/" + id), null);
    }
}
