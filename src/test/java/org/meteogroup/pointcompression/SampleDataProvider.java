package org.meteogroup.pointcompression;

import org.testng.annotations.DataProvider;

import java.util.Arrays;

public class SampleDataProvider {
  @DataProvider(name = "samples")
  public static Object[][] samples() {
    return new Object[][] {
        {
            Arrays.asList(new Point(52.1162, 13.78784), new Point(39.94521, 15.13762)),
            "s9t5rilgvC6y0rgktqD"
        },
        {
            Arrays.asList(new Point(83.45456, 57.75121), new Point(12.77555, 91.15116)),
            "j7-wuki2qLh17tv3phlG"
        }
    };
  }
}