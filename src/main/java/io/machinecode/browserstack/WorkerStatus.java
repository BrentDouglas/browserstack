package io.machinecode.browserstack;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;

import static org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE;

/**
 * @author Brent Douglas <brent.n.douglas@gmail.com>
 */
@JsonAutoDetect(creatorVisibility = NONE,
        fieldVisibility = NONE,
        getterVisibility = NONE,
        isGetterVisibility = NONE,
        setterVisibility = NONE)
public class WorkerStatus extends BaseImpl {
    private String id;
    private String status;
    private String browser;
    private String browserVersion;
    private String os;
    private String osVersion;

    @JsonProperty
    public String getId() {
        return id;
    }

    public WorkerStatus setId(final String id) {
        this.id = id;
        return this;
    }

    @JsonProperty
    public String getStatus() {
        return status;
    }

    public WorkerStatus setStatus(final String status) {
        this.status = status;
        return this;
    }

    @JsonProperty
    public String getBrowser() {
        return browser;
    }

    public WorkerStatus setBrowser(final String browser) {
        this.browser = browser;
        return this;
    }

    @JsonProperty
    public String getBrowserVersion() {
        return browserVersion;
    }

    public WorkerStatus setBrowserVersion(final String browserVersion) {
        this.browserVersion = browserVersion;
        return this;
    }

    @JsonProperty
    public String getOs() {
        return os;
    }

    public WorkerStatus setOs(final String os) {
        this.os = os;
        return this;
    }

    @JsonProperty
    public String getOsVersion() {
        return osVersion;
    }

    public WorkerStatus setOsVersion(final String osVersion) {
        this.osVersion = osVersion;
        return this;
    }
}
