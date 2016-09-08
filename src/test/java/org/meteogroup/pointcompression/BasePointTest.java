package org.meteogroup.pointcompression;

import org.meteogroup.pointcompression.Point;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class BasePointTest {

  @DataProvider
  public Object[][] arrayOfPoints() {
    return new Object[][] {
        {new ArrayList<>(Arrays.asList(new Point(52.1161994934082f, 13.787839889526367f), new Point(39.94520950317383f, 15.137619972229004f))),
         "s9t5rilgvC6y0rgktqD"},
        {new ArrayList<>(Arrays.asList(new Point(83.45456f, 57.75121f), new Point(12.77555f, 91.15116f)))
            , "j7-wuki2qLh17tv3phlG"}
    };
  }
}
