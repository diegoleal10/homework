This is a integration test server that we use to do local smoke testing, it is not part of production code it is merely used as a
convenience since developer laptops are not allowed to access outside services like NextGate.

This is only meant to be deployed to a local running Fabric, please do not put this in production and do not add it as a dependency
to other test projects. If you need NextGate services in your unit test, use one of the stubs already provided or simply re-implement
the interface for your specific needs.