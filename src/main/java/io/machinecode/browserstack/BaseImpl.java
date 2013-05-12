package io.machinecode.browserstack;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * @author Brent Douglas <brent.n.douglas@gmail.com>
 */
public class BaseImpl implements Base {
    protected ObjectMapper mapper;
    protected BrowserStack<?> browserStack;

    // Only public so BrowserStack can set them

    @Override
    public BaseImpl setMapper(final ObjectMapper mapper) {
        this.mapper = mapper;
        return this;
    }

    @Override
    public BaseImpl setBrowserStack(final BrowserStack<?> browserStack) {
        this.browserStack = browserStack;
        return this;
    }
}
