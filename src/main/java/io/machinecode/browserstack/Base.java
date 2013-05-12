package io.machinecode.browserstack;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * @author Brent Douglas <brent.n.douglas@gmail.com>
 */
public interface Base {

    Base setMapper(final ObjectMapper mapper);

    Base setBrowserStack(final BrowserStack<?> browserStack);
}
