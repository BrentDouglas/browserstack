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
public class Error extends BaseImpl {
    private String field;
    private String code;

    @JsonProperty
    public String getField() {
        return field;
    }

    public Error setField(final String field) {
        this.field = field;
        return this;
    }

    @JsonProperty
    public String getCode() {
        return code;
    }

    public Error setCode(final String code) {
        this.code = code;
        return this;
    }
}
