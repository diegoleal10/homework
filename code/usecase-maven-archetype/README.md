# Fuse Use Case Archetype

## Description

The Fuse Use Case Archetype is a maven archetype allowing to generate a maven project structure
to develop a use case.

You can download project from Git and build it using this maven command `mvn install`

## How to use it

When the archetype has been compiled and deployed within your maven local repo, you can use as such

- Execute the following command in a shell environment

```
mvn archetype:generate -DarchetypeGroupId=org.fuse.archetypes -DarchetypeArtifactId=usecase-archetype -DarchetypeVersion=1.0 -DgroupId=org.fuse.usecase -DartifactId=project -Dversion=1.0
```

- Go to the `project` directory and run a `mvn clean install` to test it.
- Develop your use case !