package bills.calculator.appConfig;

import java.util.Scanner;

public class ConfigsView {

  private Configs configuration = Configs.getInstance();

  // Configuration Menu
  public void menu(Scanner scanner) {
    System.out.println(
        "\n###CONFIGURATIONS###\n1 - Change Divisor\n2 - Change Ignored Character\n3 - Change Directory\n4 - Go Back!");
    int option = scanner.nextInt();
    switch (option) {
      case 1:
        System.out.print("Type change -> ");
        configuration.setDivisor(scanner.nextInt());
        System.out.println("Divisor changed");
        menu(scanner);
        break;
      case 2:
        System.out.print("Type change -> ");
        configuration.setIgnoreChar(scanner.next());
        System.out.println("Ignored Character changed");
        menu(scanner);
        break;
      case 3:
        System.out.print("Type change -> ");
        configuration.setDirectory(scanner.next());
        System.out.println("Directory changed");
        menu(scanner);
        break;
      case 4:
        configuration.writeFile();
        System.out.println(configuration.toString());
        break;
    }
  }
}
