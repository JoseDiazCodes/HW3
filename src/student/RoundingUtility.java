package student;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Utility class for rounding operations used in financial calculations to two decimal places.
 */
public class RoundingUtility {
  /**
   * Rounds a double value to two decimal places.
   * This method uses the "round half up" strategy: if the digit to the right of
   * the decimal place is 5 or more, round up; otherwise round down.
   *
   * @param value The double value to be rounded.
   * @return The input value rounded to two decimal places.
   */

  public static double roundToTwoDecimalPlaces(double value) {
    return BigDecimal.valueOf(value)
            .setScale(2, RoundingMode.HALF_UP)
            .doubleValue();
  }
}