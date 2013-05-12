package io.machinecode.browserstack;

import io.machinecode.browserstack.http.Parameters;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Map;

import static org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE;

/**
 * @author Brent Douglas <brent.n.douglas@gmail.com>
 */
@JsonAutoDetect(creatorVisibility = NONE,
        fieldVisibility = NONE,
        getterVisibility = NONE,
        isGetterVisibility = NONE,
        setterVisibility = NONE)
public class Browser extends BaseImpl implements Parameters {
    private String device;
    private String osVersion;
    private String os;
    private String browserVersion;
    private String browser;

    @JsonProperty
    public String getDevice() {
        return device;
    }

    public Browser setDevice(final String device) {
        this.device = device;
        return this;
    }

    @JsonProperty
    public String getOsVersion() {
        return osVersion;
    }

    public Browser setOsVersion(final String osVersion) {
        this.osVersion = osVersion;
        return this;
    }

    @JsonProperty
    public String getOs() {
        return os;
    }

    public Browser setOs(final String os) {
        this.os = os;
        return this;
    }

    @JsonProperty
    public String getBrowserVersion() {
        return browserVersion;
    }

    public Browser setBrowserVersion(final String browserVersion) {
        this.browserVersion = browserVersion;
        return this;
    }

    @JsonProperty
    public String getBrowser() {
        return browser;
    }

    public Browser setBrowser(final String browser) {
        this.browser = browser;
        return this;
    }

    @Override
    public Map<String, String> parameters() {
        return (Map<String, String>) mapper.convertValue(this, Map.class);
    }
}
