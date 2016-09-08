
**Point compression and decompression**
=========================================

Java tool for compress and decompress list of points by bing point compression algorithm
[https://msdn.microsoft.com/en-us/library/jj158958.aspx]()

##### License

[![License](https://img.shields.io/:license-Apache%202.0-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0)

## Using

In order to use, simply add these lines to your project's **pom.xml**


```xml
 <dependency>
      <groupId>org.meteogroup</groupId>
      <artifactId>point-compression</artifactId>
      <version>1.0-SNAPSHOT</version>
      <exclusions>
        <exclusion>
          <groupId>org.slf4j</groupId>
          <artifactId>slf4j-api</artifactId>
        </exclusion>
        <exclusion>
          <groupId>org.slf4j</groupId>
          <artifactId>slf4j-log4j12</artifactId>
        </exclusion>
      </exclusions>
    </dependency>
```