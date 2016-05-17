This is a integration test server that we use to do local smoke testing, it is not part of production code it is merely used as a
convenience since developer laptops are not allowed to access outside services like NextGate.

This is only meant to be deployed to a local running Fabric, please do not put this in production and do not add it as a dependency
to other test projects. If you need NextGate services in your unit test, use one of the stubs already provided or simply re-implement
the interface for your specific needs.

For logging:::

In Spring XML:
<camel:to uri="log:inbound?showAll=true&amp;level=INFO&amp;multiline=true">

In Java:
private static sl4jlogger Logger;
Logger.INFO("");

Look at log4j.properties:
log4j.logger.org.springframework=INFO


This project depends on the local artifact osgi:install mvn:com.customer.app/artifacts/1.0-SNAPSHOT  (compile the core project to get this jar)

% bin/karaf

>osgi:install mvn:com.customer.app/integration-test-server/1.0-SNAPSHOT
>osgi:install mvn:com.customer.app/artifacts/1.0-SNAPSHOT
>osgi:start [bundle-id for integration-test-server]
>osgi:start [bundle-id for artifacts]
>osgi:list
