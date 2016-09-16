package org.meteogroup.pointcompression;

import java.util.List;

public final class PointCompression {
  private PointCompression() {
  }

  public static String compressAsString(List<Point> points) {
    long latitude = 0;
    long longitude = 0;
    StringBuilder result = new StringBuilder();
    for (Point point : points) {
      long newLat = Math.round(point.getLat() * Constants.MULTIPLIER);
      long newLon = Math.round(point.getLon() * Constants.MULTIPLIER);
      long dy = newLat - latitude;
      long dx = newLon - longitude;
      latitude = newLat;
      longitude = newLon;
      dy = (dy << 1) ^ (dy >> 31);
      dx = (dx << 1) ^ (dx >> 31);
      long index = ((dy + dx) * (dy + dx + 1) / 2) + dy;
      while (index > 0) {
        int rem = (int) (index & 31);
        index = (index - rem) / Constants.DIVIDER;
        if (index > 0) {
          rem += Constants.DIVIDER;
        }

        result.append(Constants.SAFE_CHARACTERS.charAt(rem));
      }
    }

    return result.toString();
  }
}