package org.meteogroup.pointcompression;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PointIntegrationTest {
  @DataProvider
  public Object[][] samples() {
    return new Object[][] {
        {"s9t5rilgvC6y0rgktqD"}, {"j7-wuki2qLh17tv3phlG"}, {"vx1vilihnM6hR7mEl2Q"}
    };
  }

  @Test(dataProvider = "samples")
  public void testDecompressCompress(String sample) {
    Assert.assertEquals(
        PointCompression.compressAsString(PointDecompression.decompressPoints(sample)),
        sample);
  }
}