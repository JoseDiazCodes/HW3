package student;

/**
 * Represents a salaried employee in the system.
 * This class implements IEmployee interface and provides specific behavior for salaried workers.
 */
public class SalariedEmployee implements IEmployee {
  private String name;
  private String id;
  private double yearlySalary;

  /**
   * Constructs a new SalariedEmployee with the specified details.
   *
   * @param name The name of the employee.
   * @param id The unique identifier of the employee.
   * @param yearlySalary The yearly salary of the employee.
   * @throws IllegalArgumentException If any of the input parameters are invalid.
   */
  public SalariedEmployee(String name, String id, double yearlySalary) throws IllegalArgumentException {
    if (name == null || id == null || name.isEmpty() || id.isEmpty()) {
      throw new IllegalArgumentException("Name and ID must not be null or empty");
    }
    if (yearlySalary < 0 || yearlySalary > SalaryEmployee.MAX_SALARY.getValue()) {
      throw new IllegalArgumentException("Invalid yearly salary");
    }
    this.name = name;
    this.id = id;
    this.yearlySalary = RoundingUtility.roundToTwoDecimalPlaces(yearlySalary);
  }

  /**
   * Calculates and returns the pay for the current period (monthly).
   * For salaried employees, this is always 1/12 of their yearly salary.
   *
   * @return The monthly pay, which is 1/12 of the yearly salary.
   */
  @Override
  public double getPayForThisPeriod() {
    return RoundingUtility.roundToTwoDecimalPlaces(yearlySalary / 12);
  }

  /**
   * Returns the base yearly salary of the employee.
   *
   * @return The yearly salary of the employee.
   */
  @Override
  public double getBaseSalary() {
    return yearlySalary;
  }

  /**
   * Gives a percentage-based raise to the employee's yearly salary.
   * If the raise would exceed the maximum allowed salary, the salary is set to the maximum.
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
            yearlySalary * (1 + raisePercent / 100));
    yearlySalary = Math.min(newSalary, SalaryEmployee.MAX_SALARY.getValue());
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
}