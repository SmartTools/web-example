#SmartActors Example
##Backend side

Actors are here.

##Add dependency

Go to SmartActors project and run `mvn install::install-file -Dfile=target/smartactors-1.0-SNAPSHOT.jar -DgroupId=info.smart_tools -DartifactId=smartactors -Dversion=1.0-SNAPSHOT -Dpackaging=jar`. This command compile project and save jar file in `~/.m2/repository` directory.

In your maven brand new application add dependency.

```
<dependency>
    <groupId>info.smart_tools</groupId>
    <artifactId>smartactors</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```
