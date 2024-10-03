package student;

/**
 * Represents an hourly employee in the system.
 * This class extends AbstractEmployeeClass and implements specific behavior for hourly workers.
 */
public class HourlyEmployee extends AbstractEmployeeClass {
  private double hourlySalary;
  private final double normalHours;
  private double specialHours;
  private boolean useSpecialHours;

  /**
   * Constructs a new HourlyEmployee with the specified details.
   *
   * @param name The name of the employee.
   * @param id The unique identifier of the employee.
   * @param hourlySalary The hourly rate of pay for the employee.
   * @param normalHours The normal number of hours worked per week.
   * @throws IllegalArgumentException If any of the input parameters are invalid.
   */
  public HourlyEmployee(String name, String id, double hourlySalary, double normalHours)
          throws IllegalArgumentException {
    super(name, id);
    if (hourlySalary > HourlyEmployeeEnum.MAX_HOURLY_RATE.getValue()
            || hourlySalary < HourlyEmployeeEnum.MIN_HOURLY_RATE.getValue()
            || normalHours > HourlyEmployeeEnum.MAX_WEEKLY_HOURS.getValue()
            || normalHours < HourlyEmployeeEnum.MIN_WEEKLY_HOURS.getValue()) {
      throw new IllegalArgumentException("Invalid Employee information");
    }

    this.hourlySalary = RoundingUtility.roundToTwoDecimalPlaces(hourlySalary);
    this.normalHours = RoundingUtility.roundToTwoDecimalPlaces(normalHours);
    this.useSpecialHours = false;
  }

  /**
   * Calculates and returns the pay for the current period.
   * This method takes into account regular hours, overtime, and any special hours set.
   *
   * @return The total pay for the period.
   */
  @Override
  public double getPayForThisPeriod() {
    double hoursToUse = useSpecialHours ? specialHours : normalHours;
    double regularHours = Math.min(hoursToUse, 40.00);
    double overtimeHours = Math.max(0, hoursToUse - 40.00);

    double regularPay = regularHours * hourlySalary;
    double overtimePay = overtimeHours * (HourlyEmployeeEnum.HOURLY_OVERTIME_RATE.getValue() * hourlySalary);

    useSpecialHours = false; // Reset to normal hours after payment if it is used above.
    return RoundingUtility.roundToTwoDecimalPlaces(regularPay + overtimePay);
  }

  /**
   * Returns the base hourly salary of the employee.
   *
   * @return The hourly salary rate.
   */
  @Override
  public double getBaseSalary() {
    return hourlySalary;
  }

  /**
   * Gives a percentage-based raise to the employee's hourly salary.
   *
   * @param raisePercent The percentage of the raise, between 0 and 10.
   * @throws IllegalArgumentException If the raise percentage is outside the valid range.
   */
  @Override
  public void giveRaiseByPercent(double raisePercent) throws IllegalArgumentException {
    if (raisePercent < 0 || raisePercent > 10) {
      throw new IllegalArgumentException("Raise percent must be between 0 and 10");
    }
    double newSalary = RoundingUtility.roundToTwoDecimalPlaces(
            hourlySalary * (1 + raisePercent / 100));
    if (newSalary <= HourlyEmployeeEnum.MAX_HOURLY_RATE.getValue()) {
      hourlySalary = newSalary;
    }
  }

  /**
   * Sets special hours for the current pay period.
   * This overrides the normal hours for the next pay calculation.
   *
   * @param hours The number of special hours to set.
   * @throws IllegalArgumentException If the hours are outside the valid range.
   */
  public void setSpecialHours(double hours) throws IllegalArgumentException {
    if (hours < HourlyEmployeeEnum.MIN_WEEKLY_HOURS.getValue()
            || hours > HourlyEmployeeEnum.MAX_WEEKLY_HOURS.getValue()) {
      throw new IllegalArgumentException("Special hours must be between 0 and 80");
    }
    this.specialHours = RoundingUtility.roundToTwoDecimalPlaces(hours);
    // allows us to set new hours worked.
    this.useSpecialHours = true; // this is the flag we will use to see if we will use normal hours
    // or special hours.
  }
}