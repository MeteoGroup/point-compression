package org.meteogroup.pointcompression;

import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PointCompressionTest extends BasePointTest {

  @Test(dataProvider = "arrayOfPoints")
  public void testCorrectCompressionOfPointsToString(List<Point> points, String compressedValue) throws Exception {
    String encodedPoints = new PointCompression().compressAsString(points);
    assertThat(encodedPoints).isEqualTo(compressedValue);
  }

}