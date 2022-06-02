package bills.calculator.pojos;

import java.time.Month;

// Representation of a bill
public class Bill {

	private int year;
	private Month month;
	private String name;
	private Double amount;

	public Bill(int year, Month month, String name, Double amount) {
		this.year = year;
		this.month = month;
		this.name = name;
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Bill [name= " + name + ", amount= " + amount + ", month= " + month + ", year= " + year + "]";
	}

	// Getters and Setters
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Month getMonth() {
		return month;
	}

	public void setMonth(Month month) {
		this.month = month;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
}
