package student;

public enum SalaryEmployee {
  MAX_SALARY(1000000.00);

  private final double value;

  SalaryEmployee(double value) {
    this.value = value;
  }

  public double getValue() {
    return value;
  }

}
