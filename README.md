
**Point compression and decompression**
=========================================

Java tool for compress and decompress list of points by bing point compression algorithm
[Point Compression Algorithm](https://msdn.microsoft.com/en-us/library/jj158958.aspx)

##### License

[![License](https://img.shields.io/:license-Apache%202.0-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0)

##### Build Status

[![Build Status](https://travis-ci.org/MeteoGroup/point-compression.svg?branch=master)](https://travis-ci.org/MeteoGroup/point-compression)

## About algorithm

When the number of points (latitude and longitude pairs) becomes too large, the length of the URL request may exceed limits 
imposed by clients, proxies, or the server. To reduce the size of the request or when you cannot use the HTTP POST method, 
you can implement the compression algorithm described below to get a compressed string that you can use instead of 
the lengthy points list. The points parameter in the Elevation API URLs supports these compressed strings and automatically returns 
uncompressed elevation data. There is no need for a decompression algorithm. This algorithm is best for 400 points or less. 
If you are requesting elevation data for more than 400 points, make an HTTP POST request. The following example shows the difference 
in size between a list of points and the equivalent compressed string

**Note:** Due to the algorithm lat/lon rounding the close enough points (distance ~ 0.009076 km) could be dropped. This can be influenced through changing of the _Multiplier_ constant (there is a direct correlation between the how big the multiplier is and how accurate is the rounding) The obvious drawback is: The bigger the _Multiplier_ - the bigger the size of the compressed points final payload, but it grows slowly and linearly.

**Original Values:**

points=35.894309002906084,-110.72522000409663,35.893930979073048,-110.72577999904752,35.893744984641671,-110.72606003843248,35.893366960808635,-110.72661500424147

**Equivalent Compressed Value:**

points=vx1vilihnM6hR7mEl2Q


## Using

In order to use, simply add these lines to your project's **pom.xml**


```xml
 <dependency>
       <groupId>org.meteogroup</groupId>
       <artifactId>point-compression</artifactId>
       <version>1.0</version>
  </dependency>
```

##### Example of Point Decompression service 

```java
    //String locations = ...
    List<Point> points = new PointDecompression().decompressPoints(locations);    
```

##### Example of Point Compression service 

```java
    //List<Point> points =  ...
    String locations = new PointCompression().compressAsString(points);    
```



