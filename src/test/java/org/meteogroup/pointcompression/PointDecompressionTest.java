package org.meteogroup.pointcompression;

import org.assertj.core.groups.Tuple;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PointDecompressionTest extends BasePointTest {

  @Test(dataProvider = "arrayOfPoints")
  public void testCorrectDecompressionOfPoints(List<Point> points, String compressedValue) throws Exception {
    List<Point> resultListOfPoints = new PointDecompression().decompressPoints(compressedValue);
    for (Point expectedPoint : points) {
      assertThat(resultListOfPoints).extracting("lat", "lon").contains(new Tuple(expectedPoint.getLat(), expectedPoint
          .getLon()));
    }
  }

}