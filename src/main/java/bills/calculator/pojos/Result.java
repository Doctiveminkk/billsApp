package bills.calculator.pojos;

import java.text.DecimalFormat;
import java.util.List;

//POJO that represents the program output
public class Result {

  private List<Bill> monthBills;
  private double totalAmount;
  private int divisor;
  private double partAmount;
  private DecimalFormat df = new DecimalFormat("#.##");

  // Getters and Setters
  public List<Bill> getMonthBills() {
    return monthBills;
  }

  public void setMonthBills(List<Bill> monthBills) {
    this.monthBills = monthBills;
  }

  public double getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(double totalAmount) {
    this.totalAmount = totalAmount;
  }

  public void setDivisor(int dividedAmount) {
    this.divisor = dividedAmount;
  }

  public double getPartAmount() {
    return partAmount;
  }

  public void setPartAmount(double partAmount) {
    this.partAmount = partAmount;
  }

  public String toString() {
    String message = "";
    for (Bill b : monthBills) {
      message = message + b.getName() + " " + b.getAmount() + " €\n";
    }
    return message = message + "Total: " + totalAmount + " €\n" + "Divided by " + divisor + ": " + df.format(partAmount)
        + " Euros\n";
  }

}