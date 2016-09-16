package org.meteogroup.pointcompression;

import org.assertj.core.groups.Tuple;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PointDecompressionTest {

  @Test(dataProvider = "samples", dataProviderClass = SampleDataProvider.class)
  public void testCorrectCompressionOfPointsToString(List<Point> points, String compressedValue) {
    String encodedPoints = PointCompression.compressAsString(points);

    Assert.assertEquals(encodedPoints, compressedValue);
  }

  @Test(dataProvider = "samples", dataProviderClass = SampleDataProvider.class)
  public void testCorrectDecompressionOfPoints(List<Point> points, String compressedValue) {
    List<Point> resultListOfPoints = PointDecompression.decompressPoints(compressedValue);

    for (Point expectedPoint : points) {
      assertThat(resultListOfPoints).extracting("lat", "lon").contains(new Tuple(expectedPoint.getLat(), expectedPoint
          .getLon()));
    }
  }
}