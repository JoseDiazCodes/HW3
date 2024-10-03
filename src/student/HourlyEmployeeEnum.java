package student;

public enum HourlyEmployeeEnum {
  MIN_HOURLY_RATE(0),
  MAX_HOURLY_RATE(50.00),

  HOURLY_OVERTIME_RATE(1.50),

  MIN_WEEKLY_HOURS(0.00),

  MAX_WEEKLY_HOURS(80.00);

  private final double value;

  HourlyEmployeeEnum(double value) {
    this.value = value;
  }

  public double getValue() {
    return value;
  }

}
