package bills.calculator;

import java.time.DateTimeException;
import java.time.Month;
import java.util.InputMismatchException;
import java.util.Scanner;

import bills.calculator.appConfig.ConfigsView;

// Concerns user interaction with the program
public class InitView {

	private DataAdmin admin = DataAdmin.getInstance();
	private Scanner scanner = new Scanner(System.in);
	private CopyText copyText = new CopyText();
	private String cont = "n";

	// Displays main menu to the user
	public void menu() {
		while (!cont.equals("y")) {
			int year = configMenu(selectYear());
			int month = configMenu(selectMonth(year));
			scanner.nextLine();
			admin.sortBillsByDate(year, month);
			System.out.println(admin.getResult().toString());
			copyText.setClipboardContents(admin.getResult().toString());
			System.out.println("Done? y/n");
			cont = scanner.next();
		}
		exit();
	}

	// Reads user input on the year
	private int selectYear() {
		System.out.println("Available years:");
		for (Integer y : admin.getAvailableYears()) {
			System.out.println(y);
		}
		System.out.println("\nSelect year:");
		try {
			int year = scanner.nextInt();
			admin.getYears(year);
			return year;
		} catch (DateTimeException | InputMismatchException e) {
			System.out.println("[" + e + "]" + " Invalid Input\n");
			scanner.nextLine();
			return selectYear();
		}
	}

	// Reads user input on the month
	private int selectMonth(int year) {
		System.out.println("Available months:");
		for (Month m : admin.getAvailableMonths(year)) {
			System.out.println(m.getValue() + " " + m);
		}
		System.out.println("\nSelect month number:");
		try {
			int month = scanner.nextInt();
			admin.getMonths(month, year);
			return month;
		} catch (DateTimeException | InputMismatchException e) {
			System.out.println("[" + e + "]" + " Input must be between " + admin.getAvailableMonths(year) + "\n");
			scanner.nextLine();
			return selectMonth(year);
		}
	}

	// Access configuration menu
	private int configMenu(int n) {
		if (n == 0) {
			ConfigsView confView = new ConfigsView();
			confView.menu(scanner);
			admin.processData();
			menu();
		}
		return n;
	}

	// End process
	private void exit() {
		System.out.println("bye o/");
		System.exit(0);
	}
}