package org.meteogroup.pointcompression;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PointDecompression {

  private Logger logger = LoggerFactory.getLogger(PointDecompression.class);

  public List<Point> decompressPoints(String compressedValue) {
    List<Point> latLon = new ArrayList<>();
    List<List<Integer>> pointsArray = new ArrayList<>();
    List<Integer> point = new ArrayList<>();
    double lastLat = 0, lastLon = 0;
    try {
      for (int i = 0; i < compressedValue.length(); i++) {
        int num = PointConstants.SAFE_CHARACTERS.indexOf(compressedValue.charAt(i));
        if (num < PointConstants.DIVIDER) {
          point.add(num);
          pointsArray.add(point);
          point = new ArrayList<>();
        } else {
          num -= PointConstants.DIVIDER;
          point.add(num);
        }
      }
      for (List<Integer> list : pointsArray) {
        long result = 0;
        Collections.reverse(list);
        for (Integer num : list) {
          if (result == 0) {
            result = num;
          } else {
            result = result * PointConstants.DIVIDER + num;
          }
        }
        long dIag = (long) ((Math.sqrt(8 * result + 5) - 1) / 2);
        double latY = result - (dIag * (dIag + 1) / 2);
        double lonX = dIag - latY;
        if (latY % 2 == 1) {
          latY = (latY + 1) * -1;
        }
        if (lonX % 2 == 1) {
          lonX = (lonX + 1) * -1;
        }
        latY /= 2;
        lonX /= 2;
        double lat = latY + lastLat;
        double lon = lonX + lastLon;
        lastLat = lat;
        lastLon = lon;
        lat /= PointConstants.MULTIPLIER;
        lon /= PointConstants.MULTIPLIER;

        latLon.add(new Point((float) lat, (float) lon));

      }
    } catch (Exception ex) {
      logger.error("Can't decompress compressedValue: " + compressedValue + " to set of lat/lon", ex.getMessage());
    }
    return latLon;
  }

}
