package student;

abstract class AbstractEmployeeClass implements IEmployee {
  protected String name;
  protected String id;

  protected AbstractEmployeeClass(String name, String id) throws IllegalArgumentException {
    if (name == null || id == null || name.isEmpty() || id.isEmpty()) {
      throw new IllegalArgumentException("Name and ID must not be null or empty");
    }
    this.name = name;
    this.id = id;
  }

  @Override
  public abstract double getPayForThisPeriod();

  @Override
  public abstract double getBaseSalary();

  @Override
  public abstract void giveRaiseByPercent(double raisePercent);

  @Override
  public String getID() {
    return id;
  }

  @Override
  public String getName() {
    return name;
  }
}