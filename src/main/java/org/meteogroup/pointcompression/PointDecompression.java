package org.meteogroup.pointcompression;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class PointDecompression {
  private PointDecompression() {
  }

  public static List<Point> decompressPoints(String compressedValue) {
    List<Point> latLon = new ArrayList<>();
    List<List<Integer>> pointsArray = new ArrayList<>();
    List<Integer> point = new ArrayList<>();
    double lastLat = 0, lastLon = 0;

    for (int i = 0; i < compressedValue.length(); i++) {
      int num = Constants.SAFE_CHARACTERS.indexOf(compressedValue.charAt(i));
      if (num < Constants.DIVIDER) {
        point.add(num);
        pointsArray.add(point);
        point = new ArrayList<>();
      } else {
        num -= Constants.DIVIDER;
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
          result = result * Constants.DIVIDER + num;
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
      lat /= Constants.MULTIPLIER;
      lon /= Constants.MULTIPLIER;

      latLon.add(new Point(lat, lon));
    }

    return latLon;
  }
}