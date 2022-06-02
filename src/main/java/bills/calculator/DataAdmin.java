package bills.calculator;

import java.io.File;
import java.time.DateTimeException;
import java.time.Month;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import bills.calculator.appConfig.Configs;
import bills.calculator.pojos.Bill;
import bills.calculator.pojos.Result;

// Concerns every operation regarding data processing. File names must conform to the following convention "<year>-<month> <name>.<amount>".
public class DataAdmin {

	private static DataAdmin admin;
	private Configs configuration = Configs.getInstance();
	private Result result = new Result();
	private List<Bill> bills = new ArrayList<>();

	// Getters
	public List<Bill> getBills() {
		return bills;
	}

	public Result getResult() {
		return result;
	}

	// Constructor
	private DataAdmin() {
		processData();
	}

	public static DataAdmin getInstance() {
		if (admin == null) {
			admin = new DataAdmin();
		}
		return admin;
	}

	// Reads file names in specified folder
	public void processData() {
		File[] folder = new File(configuration.getDirectory()).listFiles();
		bills.clear();
		for (File file : folder) {
			if (!file.getName().startsWith(configuration.getIgnoreChar())) {
				StringDivider(file.getName());
			}
		}
	}

	// splits file names into year, month, name and amount. Creates bill instances
	// and adds them to the list.
	private void StringDivider(String str) {
		try {
			String[] dateArr = str.split(" ");
			String[] dateDiv = dateArr[0].split("-");
			String[] otherInfo = dateArr[1].split("\\.");
			String amount = otherInfo[1].replace(",", ".");
			Month month = Month.of(Integer.parseInt(dateDiv[1]));
			bills.add(new Bill(Integer.parseInt(dateDiv[0]), month, otherInfo[0], Double.parseDouble(amount)));
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("File name non conforming with norm -> " + str);
		}
	}

	// Sorts bills by selected date
	public void sortBillsByDate(int year, int selectedMonth) {
		System.out.println(selectedMonth + " of '" + year);
		Month month = Month.of(selectedMonth);
		List<Bill> monthBills = new ArrayList<>();
		for (Bill b : bills) {
			if (b.getYear() == (year) && b.getMonth().equals(month)) {
				monthBills.add(b);
			}
		}
		calculateAmount(monthBills);
	}

	// Calculates the amount and fills result instance's properties
	private void calculateAmount(List<Bill> monthBills) {
		Double amount = 0.0;
		for (Bill b : monthBills) {
			amount = amount + b.getAmount();
		}
		result.setMonthBills(monthBills);
		result.setTotalAmount(amount);
		result.setDivisor(configuration.getDivisor());
		result.setPartAmount(amount / configuration.getDivisor());
	}

	// Checks if selected year data exists
	public void getYears(int year) throws java.util.InputMismatchException {
		if (year == 0) {
			return;
		}
		for (Bill b : bills) {
			if (b.getYear() == year) {
				return;
			}
		}
		throw new InputMismatchException();
	}

	// Checks if selected month data exists
	public void getMonths(int month, int year) throws java.util.InputMismatchException, java.time.DateTimeException {
		if (month == 0) {
			return;
		}
		for (Bill b : bills) {
			if (b.getYear() == year) {
				if (b.getMonth().equals(Month.of(month))) {
					return;
				}
			}
		}
		throw new DateTimeException("That's in the future!");
	}

	// Makes a list of the years the user can choose
	public List<Integer> getAvailableYears() {
		List<Integer> yearList = new ArrayList<>();
		for (Bill b : bills) {
			if (!yearList.contains(b.getYear())) {
				yearList.add(b.getYear());
			}
		}
		return yearList;
	}

	// Makes a list of the months the user can choose within the selected year
	public List<Month> getAvailableMonths(int year) {
		List<Month> monthList = new ArrayList<>();
		for (Bill b : bills) {
			if (b.getYear() == year && !monthList.contains(b.getMonth())) {
				monthList.add(b.getMonth());
			}
		}
		return monthList;
	}
}