package student;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class IEmployeeTest {
  private HourlyEmployee snoopyHours;
  private SalariedEmployee lucy;
  private IEmployee woodStock;

  @BeforeEach
  void setUp() {
    snoopyHours = new HourlyEmployee("Snoopy", "111-CHLY-BRWN", 17.50, 20);
    lucy = new SalariedEmployee("Lucy", "222-22-2222", 70000.00);
    woodStock = new SalariedEmployee("Woodstock", "33-CHIRP", 180000.50);
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
  public void testGetPayForThisPeriod() {
    assertEquals(350.00, snoopyHours.getPayForThisPeriod(), 0.01);
    assertEquals(5833.33, lucy.getPayForThisPeriod(), 0.01);
    assertEquals(15000.04, woodStock.getPayForThisPeriod(), 0.01);

    snoopyHours.setSpecialHours(45);
    assertEquals(831.25, snoopyHours.getPayForThisPeriod(), 0.01);
  }

  @Test
  public void testGetBaseSalary() {
    assertEquals(17.50, snoopyHours.getBaseSalary(), 0.01);
    assertEquals(70000.00, lucy.getBaseSalary(), 0.01);
    assertEquals(180000.50, woodStock.getBaseSalary(), 0.01);
  }

  @Test
  public void testGiveRaiseByPercent() {
    snoopyHours.giveRaiseByPercent(5);
    assertEquals(18.38, snoopyHours.getBaseSalary(), 0.01);

    lucy.giveRaiseByPercent(10);
    assertEquals(77000.00, lucy.getBaseSalary(), 0.01);

    woodStock.giveRaiseByPercent(15); // Should be capped at 10%
    assertEquals(198000.55, woodStock.getBaseSalary(), 0.01);

    // Test raise beyond maximum salary
    SalariedEmployee maxEmployee = new SalariedEmployee("Max", "MAX-ID", 999999.99);
    maxEmployee.giveRaiseByPercent(10);
    assertEquals(1000000.00, maxEmployee.getBaseSalary(), 0.01);
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
  public void testSetSpecialHours() {
    snoopyHours.setSpecialHours(30);
    assertEquals(525.00, snoopyHours.getPayForThisPeriod(), 0.01);

    assertThrows(IllegalArgumentException.class, () -> snoopyHours.setSpecialHours(90));
    assertThrows(IllegalArgumentException.class, () -> snoopyHours.setSpecialHours(-1));

    // Test that special hours reset after getPayForThisPeriod
    snoopyHours.setSpecialHours(50);
    snoopyHours.getPayForThisPeriod();
    assertEquals(350.00, snoopyHours.getPayForThisPeriod(), 0.01);
  }

  @Test
  public void testRoundingPrecision() {
    HourlyEmployee precisionTest = new HourlyEmployee(
            "Precise", "PREC", 15.67, 40.5);
    assertEquals(638.55, precisionTest.getPayForThisPeriod(), 0.01);

    SalariedEmployee salaryPrecision = new SalariedEmployee(
            "SalPrec", "SPREC", 75432.10);
    assertEquals(6286.01, salaryPrecision.getPayForThisPeriod(), 0.01);
  }
}