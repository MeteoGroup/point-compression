package org.meteogroup.pointcompression;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PointCompression {

  private Logger logger = LoggerFactory.getLogger(PointCompression.class);

  public String compressAsString(List<Point> points) {
    long latitude = 0;
    long longitude = 0;
    StringBuilder result = new StringBuilder();
    try {
      for (Point point : points) {
        long newLat = Math.round(point.getLat() * PointConstants.MULTIPLIER);
        long newLon = Math.round(point.getLon() * PointConstants.MULTIPLIER);
        long dy = newLat - latitude;
        long dx = newLon - longitude;
        latitude = newLat;
        longitude = newLon;
        dy = (dy << 1) ^ (dy >> 31);
        dx = (dx << 1) ^ (dx >> 31);
        long index = ((dy + dx) * (dy + dx + 1) / 2) + dy;
        while (index > 0) {
          int rem = (int) (index & 31);
          index = (index - rem) / PointConstants.DIVIDER;
          if (index > 0) {
            rem += PointConstants.DIVIDER;
          }
          result.append(PointConstants.SAFE_CHARACTERS.charAt(rem));
        }
      }

    } catch (Exception ex) {
      logger.error("Can't compress set: " + points + " of lat/lon to compressed string", ex.getMessage());
    }
    return result.toString();
  }
}
