package student;

/**
 * Represents a salaried employee in the system.
 * This class extends AbstractEmployeeClass and implements specific behavior for salaried workers.
 * Salaried employees are paid a fixed yearly salary, which is divided into monthly payments.
 */
public class SalariedEmployee extends AbstractEmployeeClass {

  /**
   * The yearly salary of the employee.
   */
  private double yearlySalary;

  /**
   * Constructs a new SalariedEmployee with the specified details.
   *
   * @param name The name of the employee.
   * @param id The unique identifier of the employee.
   * @param yearlySalary The yearly salary of the employee.
   * @throws IllegalArgumentException
   *                  If the yearly salary is negative or exceeds the maximum allowed salary
   */
  public SalariedEmployee(String name, String id, double yearlySalary)
          throws IllegalArgumentException {
    super(name, id);
    if (yearlySalary < 0 || yearlySalary > SalaryEmployee.MAX_SALARY.getValue()) {
      throw new IllegalArgumentException("Invalid yearly salary");
    }
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
   * If the raise exceeds the maximum allowed salary, the salary is set to the maximum.
   *
   * @param raisePercent The percentage of the raise, between 0 and 10.
   * @throws IllegalArgumentException If the raise percentage is less than 0 or greater than 10.
   */
  @Override
  public void giveRaiseByPercent(double raisePercent) {
    if (raisePercent < 0) {
      throw new IllegalArgumentException("Raise percent must be non-negative");
    }
    double effectiveRaise = Math.min(raisePercent, 10);
    double newSalary = yearlySalary * (1 + effectiveRaise / 100);
    yearlySalary = Math.min(newSalary, SalaryEmployee.MAX_SALARY.getValue());
  }
}