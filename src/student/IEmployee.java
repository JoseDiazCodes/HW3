package student;

/**
 * An interface representing the concept of Employees.
 */
public interface IEmployee {

  // NOTES (you may remove these notes after you understand the assignment
  // if they cause checkStyle issues):
  // The max HOURLY base salary is $50.00 (per hour).
  // The max Salaried base salary is $1 million per year.
  // HourlyEmployees earn 1.5x base salary for any time worked > 40 hours in a period.
  // SalariedEmployees are paid monthly. For one pay period their salary should be baseSalary/12


  /**
   * Returns pay for period.
   *
   * @return the employee's pay for the given period.
   *                  Hourly employees are paid weekly based on the number of hours worked.
   *                  Salaried employees are paid monthly, with a given paycheck
   *                  of 1/12 their yearly salary
   */
  double getPayForThisPeriod();

  /**
   * Returns the employee's base salary.
   *
   * @return The employee's base salary. For hourly employees, this is their hourly rate.
   *         For salaried employees, this is their yearly salary.
   */
  double getBaseSalary();

  /**
   * Raises the employee's base salary by a specified percentage.
   *
   * @param raisePercent The percentage to raise the salary, from 0% (minimum) to 10% (maximum).
   *                     This value should be between 0.0 and 10.0. The method internally
   *                     converts this percentage to a decimal value between 0 and 0.10
   *                     for calculations.
   */
  void giveRaiseByPercent(double raisePercent);

  /**
   * Retrieves the id of the employee.
   *
   * @return The employee's unique ID as a String.
   */
  String getID();

  /**
   * Returns Employee name.
   *
   * @return Returns employee name.
   */
  String getName();
}