package io.machinecode.browserstack;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

import static org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE;

/**
 * @author Brent Douglas <brent.n.douglas@gmail.com>
 */
@JsonAutoDetect(creatorVisibility = NONE,
        fieldVisibility = NONE,
        getterVisibility = NONE,
        isGetterVisibility = NONE,
        setterVisibility = NONE)
public class Failure extends BaseImpl {

    private String field;
    private List<Error> errors;

    @JsonProperty
    public String getField() {
        return field;
    }

    public Failure setField(final String field) {
        this.field = field;
        return this;
    }

    @JsonProperty
    public List<Error> getErrors() {
        return errors;
    }

    public Failure setErrors(final List<Error> errors) {
        this.errors = errors;
        return this;
    }
}
