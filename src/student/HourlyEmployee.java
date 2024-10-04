package student;

/**
 * Represents an hourly employee in the system.
 * This class implements IEmployee interface and provides specific behavior for hourly workers.
 */
public class HourlyEmployee implements IEmployee {
  private String name;
  private String id;
  private double hourlySalary;
  private final double normalHours;
  private double specialHours;
  private boolean useSpecialHours;

  /**
   * Constructs a new HourlyEmployee with the specified details.
   *
   * @param name         The name of the employee.
   * @param id           The unique identifier of the employee.
   * @param hourlySalary The hourly rate of pay for the employee.
   * @param normalHours  The normal number of hours worked per week.
   * @throws IllegalArgumentException If any of the input parameters are invalid.
   */
  public HourlyEmployee(String name, String id, double hourlySalary, double normalHours)
          throws IllegalArgumentException {
    if (name == null || id == null || name.isEmpty() || id.isEmpty()) {
      throw new IllegalArgumentException("Name and ID must not be null or empty");
    }
    if (hourlySalary < 0 || hourlySalary > HourlyEmployeeEnum.MAX_HOURLY_RATE.getValue()
            || normalHours > HourlyEmployeeEnum.MAX_WEEKLY_HOURS.getValue()
            || normalHours < HourlyEmployeeEnum.MIN_WEEKLY_HOURS.getValue()) {
      throw new IllegalArgumentException("Invalid Employee information");
    }

    this.name = name;
    this.id = id;
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

    useSpecialHours = false; // Reset to normal hours after payment
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
   * If the raise would exceed the maximum allowed hourly rate, the salary is set to the maximum.
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
    hourlySalary = Math.min(newSalary, HourlyEmployeeEnum.MAX_HOURLY_RATE.getValue());
  }

  /**
   * Retrieves the unique identifier of the employee.
   *
   * @return The employee's unique ID as a String.
   */
  @Override
  public String getID() {
    return id;
  }

  /**
   * Retrieves the name of the employee.
   *
   * @return The employee's name.
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * Provides a string representation of the HourlyEmployee.
   *
   * @return A string containing the employee's name, ID, and hourly rate.
   */
  @Override
  public String toString() {
    return String.format("Name: %s%nID: %s%nBase Salary: $%.2f",
            this.getName(),
            this.getID(),
            this.getBaseSalary());
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
    this.useSpecialHours = true;
  }
}