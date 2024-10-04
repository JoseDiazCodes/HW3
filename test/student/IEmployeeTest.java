package student;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IEmployeeTest {
  private HourlyEmployee snoopyHours;
  private SalariedEmployee lucy;
  private IEmployee woodStock;

  @BeforeEach
  void setUp() {
    snoopyHours = new HourlyEmployee(
            "Snoopy", "111-CHLY-BRWN", 17.50, 20);
    lucy = new SalariedEmployee(
            "Lucy", "222-22-2222", 70000.00);
    woodStock = new SalariedEmployee(
            "Woodstock", "33-CHIRP", 180000.50);
  }

  @Test
  public void testGetErrorWhenCreatingEmployee() {
    assertThrows(IllegalArgumentException.class, () -> new HourlyEmployee(
            null, null, 15.51, 30));
    assertThrows(IllegalArgumentException.class, () -> new HourlyEmployee(
            "Part-timer", "PT-TIME", -1, 30));
    assertThrows(IllegalArgumentException.class, () -> new HourlyEmployee(
            "Over-worker", "OT-TIME", 20, 100));
    assertThrows(IllegalArgumentException.class, () -> new SalariedEmployee(
            "", "", 50000));
    assertThrows(IllegalArgumentException.class, () -> new SalariedEmployee(
            "Overpaid", "RICH", 2000000));
  }

  @Test
  public void testGetHappyDayEmployee() {
    assertDoesNotThrow(() -> new HourlyEmployee(
            "Happy", "HAPPY-123", 20.00, 40));
    assertDoesNotThrow(() -> new SalariedEmployee(
            "Joyful", "JOY-456", 80000.00));
  }

  @Test
  public void testGetPayForThisPeriod() {
    assertEquals(350.00, snoopyHours.getPayForThisPeriod(), 0.01);
    assertEquals(5833.33, lucy.getPayForThisPeriod(), 0.01);
    assertEquals(15000.04, woodStock.getPayForThisPeriod(), 0.01);

    // Test overtime for hourly employee
    HourlyEmployee overtimeWorker = new HourlyEmployee(
            "Overtime", "OT-789", 20.00, 45);
    assertEquals(950.00, overtimeWorker.getPayForThisPeriod(), 0.01);
  }

  @Test
  public void testGetBaseSalary() {
    assertEquals(17.50, snoopyHours.getBaseSalary(), 0.01);
    assertEquals(70000.00, lucy.getBaseSalary(), 0.01);
    assertEquals(180000.50, woodStock.getBaseSalary(), 0.01);
  }

  @Test
  public void testGetID() {
    assertEquals("111-CHLY-BRWN", snoopyHours.getID());
    assertEquals("222-22-2222", lucy.getID());
    assertEquals("33-CHIRP", woodStock.getID());
  }

  @Test
  public void testGetName() {
    assertEquals("Snoopy", snoopyHours.getName());
    assertEquals("Lucy", lucy.getName());
    assertEquals("Woodstock", woodStock.getName());
  }

  @Test
  public void testGiveRaiseByPercent() {
    snoopyHours.giveRaiseByPercent(5);
    assertEquals(18.38, snoopyHours.getBaseSalary(), 0.01);

    lucy.giveRaiseByPercent(10);
    assertEquals(77000.00, lucy.getBaseSalary(), 0.01);

    // Test raise beyond maximum salary
    SalariedEmployee maxEmployee = new SalariedEmployee(
            "Max", "MAX-ID", 999999.99);
    maxEmployee.giveRaiseByPercent(10);
    assertEquals(1000000.00, maxEmployee.getBaseSalary(), 0.01);

    // Test invalid raise percentages
    assertThrows(IllegalArgumentException.class, () -> snoopyHours.giveRaiseByPercent(
            -1));
    assertThrows(IllegalArgumentException.class, () -> lucy.giveRaiseByPercent(
            10.1));
  }

  @Test
  public void testSetSpecialHours() {
    snoopyHours.setSpecialHours(30);
    assertEquals(525.00, snoopyHours.getPayForThisPeriod(), 0.01);

    snoopyHours.setSpecialHours(45);
    assertEquals(831.25, snoopyHours.getPayForThisPeriod(), 0.01);

    // Test that special hours reset after getPayForThisPeriod
    assertEquals(350.00, snoopyHours.getPayForThisPeriod(), 0.01);

    // Test invalid special hours
    assertThrows(IllegalArgumentException.class, () -> snoopyHours.setSpecialHours(-1));
    assertThrows(IllegalArgumentException.class, () -> snoopyHours.setSpecialHours(81));
  }

  @Test
  public void testHourlyEmployeeCopyConstructor() {
    HourlyEmployee original = new HourlyEmployee("John Doe", "JD-001", 20.00, 40);
    original.setSpecialHours(45);
    HourlyEmployee copy = new HourlyEmployee(original);

    assertEquals(original.getName(), copy.getName());
    assertEquals(original.getID(), copy.getID());
    assertEquals(original.getBaseSalary(), copy.getBaseSalary());
    assertEquals(original.getPayForThisPeriod(), copy.getPayForThisPeriod());

    // Modify the copy and check that the original is unchanged
    copy.giveRaiseByPercent(5);
    assertNotEquals(original.getBaseSalary(), copy.getBaseSalary());
  }

  @Test
  public void testSalariedEmployeeCopyConstructor() {
    SalariedEmployee original = new SalariedEmployee("Jane Doe", "JD-002", 60000.00);
    SalariedEmployee copy = new SalariedEmployee(original);

    assertEquals(original.getName(), copy.getName());
    assertEquals(original.getID(), copy.getID());
    assertEquals(original.getBaseSalary(), copy.getBaseSalary());
    assertEquals(original.getPayForThisPeriod(), copy.getPayForThisPeriod());

    // Modify the copy and check that the original is unchanged
    copy.giveRaiseByPercent(5);
    assertNotEquals(original.getBaseSalary(), copy.getBaseSalary());
  }
}