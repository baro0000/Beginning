package SchoolProgrammer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Bartosz Kuliński
 * @version 1.0.0
 */

public class ChildDatabase implements Serializable
{

    /**
     * grupy określa kolekcje dostępnych grup do wykorzystania przy tworzeniu obiektu dziecka - każde dziecko zostanie przypisane do odpowiedniej grupy.
     */
    private static String[] grupy = {"Niedźwiadki", "Krasnoludki", "Płomyki"};
    private int nextId;
    public ArrayList<Child> childDatabase = new ArrayList<>();

//__METODY_______________________________________________________________________________________________________________________________________________________________


    private void printChildDatabaseMenu()
    {
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
    public void childDataMenu()
    {
        while (true)
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
                    for (Child child : childDatabase)
                    {
                        if (child instanceof Child)
                            child.drukujDaneUproszczone();
                    }
                    System.out.println("Press any key to return to previous menu...");
                    scanner.nextLine();
                }
                case 3 ->
                {
                    //searchDatabaseForChild();
                    System.out.println("Press any key to return to previous menu...");
                    scanner.nextLine();

                }
                case 4 ->
                {
                   // editData();
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
    private void addChildToDatabase()
    {
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
        switch (choice)
        {
            case 1 -> grupa = grupy[0];
            case 2 -> grupa = grupy[1];
            case 3 -> grupa = grupy[2];
        }
        Child child = new Child(imie, nazwisko, wiek, adresZamieszkania, grupa);
        childDatabase.add(child);
        nextId++;
    }

    /**
     * Metoda przeszukuje bazę danych Array w poszukiwaniu dziecka
     */
    private int[] searchDatabaseForChild()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wprowadź Imie: ");
        String imie = scanner.nextLine();
        System.out.println("Wprowadź Nazwisko: ");
        String nazwisko = scanner.nextLine();

        int i = 0;   //wskaźnik czy jakekolwiek obiekty zostały znalezione
        int[] pozycja = new int[5]; // Uzyskujemy w postaci tablicy indeksy wszystkich obiektów, które pasują do kryteriów wyszukiwania
        pozycja[0] = -1; // umożliwia to sprawdzenie w edycji czy jakikolwiek obiekt został dodany do tego array. Jeśli tak to pozycja[0] zwróci wartość inną niż ujemną.

        for(Child child: childDatabase)
        /*for (int a = 0; a < childDatabase.size(); a++)
        {
            if (childDatabase[a] instanceof Child)
            {
                if (childDatabase[a].getImie().equals(imie) && childDatabase[a].getNazwisko().equals(nazwisko))
                {
                    if (i > 4)
                        System.out.println("Znaleziono zbyt wiele rekordów. Zawęź kryteria wyszukiwania!");
                    else
                    {
                        System.out.print("Pozycja nr " + (i + 1));
                        childDatabase[a].drukujDanePelne();
                        pozycja[i] = a;
                        i++;
                    }
                }
            }
        }*/
        if (i == 0)
            System.out.println("Child data hasn't been found in database. Check your input");
        else
            System.out.println(i + " children has been found.");
        return pozycja;
    }

    /**
     * Metoda pozwala edytować dane obiektu dziecko
     *//*
    private void editData()
    {
        Scanner scanner = new Scanner(System.in);

        int[] pozycje = searchDatabaseForChild();
        System.out.print("Wybierz pozycję do edycji: ");

        if (pozycje[0] >= 0)
        {
            Child child = childDatabase[pozycje[InputValidator.validate(5) - 1]]; //pobiera pozycję obiektu z bazy danych i kopiuje jego referencje do zmiennej child.
            while (true)
            {
                System.out.println("1. Imię: " + child.getImie());
                System.out.println("2. Nazwisko: " + child.getNazwisko());
                System.out.println("3. Wiek: " + child.getWiek());
                System.out.println("4. Adres zamieszkania: " + child.getAdresZamieszkania());
                System.out.println("5. Grupa: " + child.getGrupa());
                System.out.println("6. Zakończ edycje.");
                System.out.print("Wybierz pozycję do edycji: ");

                int choice = InputValidator.validate(6);
                switch (choice)
                {
                    case 1 -> child.setImie(scanner.nextLine());
                    case 2 -> child.setNazwisko(scanner.nextLine());
                    case 3 -> child.setWiek(Integer.parseInt(scanner.nextLine()));
                    case 4 -> child.setAdresZamieszkania(scanner.nextLine());
                    case 5 ->
                    {
                        System.out.println("Przypisz dziecko do grupy - wybierz opcję: ");
                        System.out.println("1. Niedźwiadki");
                        System.out.println("2. Krasnoludki");
                        System.out.println("3. Płomyki");
                        int choiceGrupa = InputValidator.validate(3);
                        switch (choiceGrupa)
                        {
                            case 1 -> child.setGrupa(grupy[0]);
                            case 2 -> child.setGrupa(grupy[1]);
                            case 3 -> child.setGrupa(grupy[2]);
                        }
                    }
                }
                if (choice == 6)
                    break;
            }
        }
    }*/


}
