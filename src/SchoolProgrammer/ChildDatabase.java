package SchoolProgrammer;

import java.util.Scanner;

/**
 * @author Bartosz Kuliński
 * @version 1.0.0
 */

public class ChildDatabase
{

    /**
     * grupy określa kolekcje dostępnych grup do wykorzystania przy tworzeniu obiektu dziecka - każde dziecko zostanie przypisane do odpowiedniej grupy.
     */
    private static String [] grupy = {"Niedźwiadki", "Krasnoludki", "Płomyki"};
    private int nextId = 0;
    private Child [] childDatabase = new Child[10];

//__METODY_______________________________________________________________________________________________________________________________________________________________


    private void printChildDatabaseMenu(){
        System.out.println(" Child Database:");
        System.out.println(" 1. Add new child to database");
        System.out.println(" 2. Print all children in database");
        System.out.println(" 3. Search database for specific child");
        System.out.println(" 4. Edit child data");
        System.out.println(" 5. Return to main manu");
        System.out.print(" Choose option: ");


    }

    /**
     * Za pomocą switch odpowiada za funkcjonalność menu Child Database
     */
    public void childDataMenu(){
        while(true)
        {
            for (int i = 0; i < 20; i++)
                System.out.println();

            printChildDatabaseMenu();

            InputValidator inputValidator = new InputValidator();
            Scanner scanner = new Scanner(System.in);
            int choice = inputValidator.validate(5);

            switch (choice)
            {
                case 1 ->
                {
                    addChildToDatabase();
                    System.out.println("Child has been succesfully added. \n\nPress any key to return to previous menu...");
                    scanner.nextLine();
                }
                case 2 ->
                {
                    for(Child child: childDatabase){
                        if(child instanceof Child)
                            child.drukujDaneUproszczone();
                    }
                    System.out.println("Press any key to return to previous menu...");
                    scanner.nextLine();
                }
                case 3 ->
                {
                    System.out.println("Wprowadź Imie: ");
                    String imie = scanner.nextLine();
                    System.out.println("Wprowadź Nazwisko: ");
                    String nazwisko = scanner.nextLine();
                    int i = 0;
                    for(Child child: childDatabase){
                        if(child instanceof Child){
                            if(child.getImie().equals(imie) && child.getNazwisko().equals(nazwisko)){
                                child.drukujDanePelne();
                                i++;
                            }
                        }
                    }
                    if(i == 0)
                        System.out.println("Child data hasn't been found in database. Check your input");
                    else
                        System.out.println(i + " children has been found.");
                    System.out.println("Press any key to return to previous menu...");
                    scanner.nextLine();

                }
                case 4 ->
                {
                    System.out.println("Edytuj dane dziecka");
                    scanner.nextLine();
                }
            }

            if (choice == 5)
            {
                break;
            }
        }
    }
    /**
     * Opcja umożliwia dodanie dziecka do bazy danych. Najpiew pobiera potrzebne dane od użytkownika, następnie wykorzystuje je do utworzenia nowego obiektu typu Child, dodaje do bazy danych i zwiększa nextID o 1.
     */
    private void addChildToDatabase(){
        Scanner sc = new Scanner(System.in);

        String imie, nazwisko, adresZamieszkania, grupa = "Błąd";
        int wiek;

        System.out.println("Witaj w kreatorze dodawania nowego dziecka.");
        System.out.println("Wprowadź dane dziecka: ");

        System.out.print("Imie: ");
        imie = sc.nextLine();
        System.out.println();

        System.out.print("Nazwisko: ");
        nazwisko = sc.nextLine();
        System.out.println();

        System.out.print("Adres zamieszkania (Kod, miejscowość, ulica, nr domu, numer mieszkania): ");
        adresZamieszkania = sc.nextLine();
        System.out.println();

        System.out.print("Wiek: ");
        wiek = sc.nextInt();
        System.out.println();

        System.out.println("Przypisz dziecko do grupy - wybierz opcję: ");
        System.out.println("1. Niedźwiadki");
        System.out.println("2. Krasnoludki");
        System.out.println("3. Płomyki");
        int choice = InputValidator.validate(3);
        switch(choice){
            case 1 -> grupa = grupy[0];
            case 2 -> grupa = grupy[1];
            case 3 -> grupa = grupy[2];
        }
        Child child = new Child(imie, nazwisko, wiek, adresZamieszkania, grupa);
        childDatabase[nextId] = child;
        nextId++;
    }







}
