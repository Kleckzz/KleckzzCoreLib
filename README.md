# KleckzzCoreLib
Main library file to centralize main functions for multiple plugins which will allow better maintaining capabilities.

# Why is it public?
We will share our work.

# Pre Alpha
This is not a final product!

## Maven
[Look here](https://repo.kleckzz.de/#/snapshots/de/kleckzz/coresystem/lib)
```
<!-- CoreSystem -->
<repositories>
    <repository>
        <id>coresystem-waterfall</id>
        <url>https://repo.kleckzz.de/snapshots</url>
    </repository>
</repositories>
    
<dependencies>
    <!-- CoreSystem -->
    <dependency>
        <groupId>de.kleckzz.coresystem.lib</groupId>
        <artifactId>coresystem-waterfall</artifactId>
        <version>1.0-SNAPSHOT</version>
        <scope>provided</scope>
    </dependency>
</dependencies>
```

## Info
````
<scope>provided</scope>
Make available into class path, don't add this dependency into final jar if it is normal jar; but add this jar into jar if final jar is a single jar (for example, executable jar)
<scope>compile</scope>
Dependency will be available at run time environment so don't add this dependency in any case; even not in single jar (i.e. executable jar etc)
````
