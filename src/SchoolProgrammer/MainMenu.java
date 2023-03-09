package SchoolProgrammer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Bartosz Kuliński
 * @version 1.0.0
 */

public class MainMenu {
    /**
     * Creator calls method to view menu.
     */
    MainMenu() {
    }


    /**
     * Method printing menu in console
     */
    private void printMenu() {
        System.out.println();
        System.out.println();
        System.out.println(" Main Menu");
        System.out.println(" 1. Child Database");
        System.out.println(" 2. Child stay report");
        System.out.println(" 3. Calculate frequency");
        System.out.println(" 4. Set costs");
        System.out.println(" 5. Calculate cost for each child");
        System.out.println(" 6. Parents interface");
        System.out.println(" 7. External keyboard");
        System.out.println(" 8. Exit");
        System.out.print(" Choose option number: ");
    }

    /**
     * Metoda korzystająca z wydruku menu, następnie wykorzystuje metodę inputValidator by upewnić się, że wprowadzone dane są prawidłowe. Korzystając ze switch'a uzyskujemy dostęp do różnych funkcji.
     * przy wybraniu nr 7 Exit - program ulega zakończeniu.
     *
     * @see MainMenu
     */
    public void startMenu() {
        ChildDatabase childDatabase = new ChildDatabase();
        childDatabase.childDatabase = Database.readDatabaseFromFile();
        OrganisationData.settingDate();

        while (true) {
            printMenu();

            Scanner scanner = new Scanner(System.in);
            int choice = InputValidator.validate(8);
            System.out.println(choice);

            for (int i = 0; i < 20; i++)
                System.out.println();

            switch (choice) {
                case 1 -> {
                    childDatabase.childDataMenu();
                }
                case 2 -> {
                    Child child = childDatabase.chooseChild();
                    child.drukujFrekwencje();

                    System.out.println("Wciśnij dowolny klawisz by powrócić do menu");
                    scanner.nextLine();
                }
                case 3 -> {
                    Child child = childDatabase.chooseChild();
                    int [] totalAttendanceTime = child.calculateAttendanceTime(0,0);
                    System.out.println("Całkowity czas pobytu " + child.getImie() + " " + child.getNazwisko() + " to " + totalAttendanceTime[0] + " godzin " + totalAttendanceTime[1] + " minut " + totalAttendanceTime[2] + " sekund.");
                    System.out.println("Wciśnij dowolny klawisz by powrócić do menu");
                    scanner.nextLine();
                }
                case 4 -> {
                    OrganisationData.setCosts();
                    System.out.println("Wciśnij dowolny klawisz by powrócić do menu");
                    scanner.nextLine();
                }
                case 5 -> {
                    generatePayments();
                    System.out.println("Wciśnij dowolny klawisz by powrócić do menu");
                    scanner.nextLine();
                }
                case 6 -> {
                    System.out.println("6. Parents interface");
                    System.out.println("Wciśnij dowolny klawisz by powrócić do menu");
                    scanner.nextLine();
                }
                case 7 -> {
                    keypad();
                }
                case 8 -> Database.saveDatabaseToFile(ChildDatabase.childDatabase);
            }

            if (choice == 8) {
                break;
            }
        }
    }

    private void keypad() {
        ExternalKeypad keypad = new ExternalKeypad();
        keypad.takeInput();
    }

    public void generatePayments(){
        double monthCost = 0;
        double monthDining = 0;
        int monthNumber = 0;
        int counter = 0;

        OrganisationData.drukujListeMiesiecy();
        System.out.println("Wybierz miesiąc: ");
        monthNumber = InputValidator.validate(12);


        for(Child child : ChildDatabase.childDatabase){
            //obliczamy z danego miesiąca całkowity czas sumując ilość godzin
            int[] totalTime = child.calculateAttendanceTime(2, monthNumber);
            if(totalTime[2] > 0)
                totalTime[1] += 1;
            if(totalTime[1] > 0)
                totalTime[0] += 1;
            // Obliczamy całkowity koszt do zapłaty
            monthCost = totalTime[0] * OrganisationData.stawkaZaCzasPobytuNaGodzine;
            monthDining = child.frekwencja[totalTime[3]].size() * OrganisationData.stawkaZaWyzywienie;
            try {
                InvoiceGenerator.generateNewInvoice(child, (totalTime[3] + 1), OrganisationData.getNextInvoiceNumber(), totalTime[0], child.frekwencja[totalTime[3]].size(), monthCost, monthDining);
                counter++;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if(counter > 0)
            System.out.println("Pomyślnie wygenerowano faktury do folderu");
        else
            System.out.println("Brak faktur do wygenerowania. Sprawdź czy wybrano odpowiedni miesiąc");
    }


}
