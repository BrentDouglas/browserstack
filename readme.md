# Java bindings for BrowserStack API v3

## Getting the jar

You can get the jar by building this repo (check it out and then run 
`mvn install`). It is also available from the [machinecode.io nexus
repository](http://repository.machinecode.io) (see `pom.xml` for details).

## Usage

Call BrowserStack.sync(...) or BrowserStack.async(...) depending on what
you require.

## Tests

To run the (minimal) test suite you will need to set two environment
variables `BROWSERSTACK_USERNAME` and `BROWSERSTACK_PASSWORD`. Run them
with `mvn test`.
