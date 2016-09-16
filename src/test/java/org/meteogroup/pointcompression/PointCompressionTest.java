package org.meteogroup.pointcompression;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class PointCompressionTest {

  @Test(dataProvider = "samples", dataProviderClass = SampleDataProvider.class)
  public void testCorrectCompressionOfPointsToString(List<Point> points, String compressedValue) {
    String encodedPoints = PointCompression.compressAsString(points);

    Assert.assertEquals(encodedPoints, compressedValue);
  }
}