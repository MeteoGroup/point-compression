package org.meteogroup.pointcompression;

public final class Point {

  private final double lat;
  private final double lon;

  public Point(double lat, double lon) {
    this.lat = lat;
    this.lon = lon;
  }

  public double getLat() {
    return lat;
  }

  public double getLon() {
    return lon;
  }

  @Override
  public String toString() {
    return "Point{" +
           "lat=" + lat +
           ", lon=" + lon +
           '}';
  }
}