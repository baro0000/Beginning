package SchoolProgrammer;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
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
    private static String[] grupy = {"Niedźwiadki", "Krasnoludki", "Płomyki"};
    public static ArrayList<Child> childDatabase = new ArrayList<>();

//__METODY_______________________________________________________________________________________________________________________________________________________________


    private void printChildDatabaseMenu()
    {
        System.out.println(" Child Database:");
        System.out.println(" 1. Add new child to database");
        System.out.println(" 2. Remove child from database");
        System.out.println(" 3. Print all children in database");
        System.out.println(" 4. Edit child data");
        System.out.println(" 5. Print group list");
        System.out.println(" 6. Return to main manu");
        System.out.print(" Choose option: ");
        // Kody:
        // Usunięcie zawartości bazy danych : wejdź w opcje 2 remove child -> przejdź przez całość i kliknij nie -> gdy pojawi się "Press any key to return to previous menu..." wpisz "ClearDatabase"

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

            Scanner scanner = new Scanner(System.in);
            int choice = InputValidator.validate(7);

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
                    removeChildFromDatabase();
                    System.out.println("Press any key to return to previous menu...");
                    String temp = scanner.nextLine();
                    if(temp.equals("ClearDatabase"))
                        childDatabase.clear();
                }
                case 3 ->
                {
                    for (Child child : childDatabase)
                    {
                        if (child instanceof Child)
                            child.drukujDaneUproszczone();
                    }
                    System.out.println("Press any key to return to previous menu...");
                    scanner.nextLine();

                }
                case 4 ->
                {
                   editData();
                }
                case 5 ->
                {
                    searchDatabaseForChildByGroup();
                }

            }

            if (choice == 6)
            {
                break;
            }

        }
    }

    /**
     * @return Funkcja zwraca niepowtarzalny kod 6-cio cyfrowy
     */
    public String nextPossibleIdCode()
    {
       ArrayList<String> codes = new ArrayList<>();
       for(Child child: childDatabase)
           codes.add(child.getIndywidualnyKod());
       String individualCode = "";
       Random rand = new Random();
       while(true) {
           for (int i = 0; i < 6; i++)
               individualCode += rand.nextInt(10);
           for(String item: codes){
               if(individualCode.equals(item))
                   individualCode = "";
           }
           if(!individualCode.equals(""))
               break;
       }
       return individualCode;
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
        Child child = new Child(imie, nazwisko, wiek, adresZamieszkania, grupa, nextPossibleIdCode());
        childDatabase.add(child);
    }

    private void removeChildFromDatabase()
    {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Integer> pozycje = searchDatabaseForChild();
        System.out.print("Wybierz pozycję do usunięcia z bazy danych: ");

        if (pozycje.get(0) >= 0)
        {
            //Integer childNumber = pozycje.get(InputValidator.validate(pozycje.size()) - 1); //pobiera pozycję obiektu z bazy danych i kopiuje jego referencje do zmiennej child.
            Child child = childDatabase.get(pozycje.get(InputValidator.validate(pozycje.size()) - 1));
            System.out.println("Czy na pewno chcesz usunąć tą pozycję?\n1. Tak\n2. Nie");
            int decyzja = InputValidator.validate(2);
            if(decyzja == 1)
            {
                childDatabase.remove(child);
                if(!childDatabase.contains(child))
                    System.out.println("Obiekt został poprawnie usunięty.");
                else
                    System.out.println("Coś poszło nie tak! Skontaktuj się ze wsparciem technicznym");
            }
        }
    }

    public ArrayList<Integer> searchDatabaseForChild(){
        System.out.println(" Wybierz metodę wyszukiwania:");
        System.out.println(" 1. Po imieniu i nazwisku");
        System.out.println(" 2. Po ID");
        System.out.println(" 3. Po grupie");
        ArrayList<Integer> result = new ArrayList<>();

        int choice = InputValidator.validate(3);
        switch(choice){
            case 1 -> {
                return searchDatabaseForChildByName();
            }
            case 2 -> {

                result.add(searchDatabaseForChildByID());
                return result;
            }
            case 3 -> {
                return searchDatabaseForChildByGroup();
            }
        }
        System.out.println("Coś poszło nie tak!!!");
        return result;
    }

    /**
     * Metoda przeszukuje bazę danych Array w poszukiwaniu dziecka po imieniu i nazwisku
     */
    private ArrayList<Integer> searchDatabaseForChildByName()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wprowadź Imie: ");
        String imie = scanner.nextLine();
        System.out.println("Wprowadź Nazwisko: ");
        String nazwisko = scanner.nextLine();

        int i = 0;   //wskaźnik czy jakekolwiek obiekty zostały znalezione
        ArrayList<Integer> pozycja = new ArrayList<Integer>(); // Uzyskujemy w postaci tablicy indeksy wszystkich obiektów, które pasują do kryteriów wyszukiwania
        pozycja.add(-1); // umożliwia to sprawdzenie w edycji czy jakikolwiek obiekt został dodany do tego array. Jeśli tak to pozycja[0] zwróci wartość inną niż ujemną.

        for(Child child: childDatabase){
            //Sprawdzamy czy obiekt jest typu Child
            if (child instanceof Child){
                // oraz czy jego imie i nazwisko zgadzają się z poszukiwanymi
                if (child.getImie().equals(imie) && child.getNazwisko().equals(nazwisko)){
                    //Jeśli to pierwszy znaleziony obiekt zastępujemy -1 indeksem tego obiektu
                    if(i == 0)
                        pozycja.set(i, childDatabase.indexOf(child));
                    //Zaś następne tylko dodajemy jako kolejne na liście
                    else
                        pozycja.add(childDatabase.indexOf(child));

                    i++;
                    System.out.print("### POZYCJA NR " + i + " #");
                    child.drukujDanePelne();
                }
            }
        }

        System.out.println();
        if (i == 0)
            System.out.println("Child data hasn't been found in database. Check your input");
        else
            System.out.println(i + " children has been found.");
        return pozycja;
    }

    private int searchDatabaseForChildByID()
    {
        Scanner scanner = new Scanner(System.in);
        int i = 1;
        System.out.println("Wprowadź numer ID: ");
        String id = InputValidator.validatePersonalId();

        for(Child child: childDatabase){
            //Sprawdzamy czy obiekt jest typu Child
            if (child instanceof Child){
                // oraz czy jego imie i nazwisko zgadzają się z poszukiwanymi
                if (child.getIndywidualnyKod().equals(id)){
                    System.out.print("### POZYCJA NR " + i + " #");
                    child.drukujDanePelne();
                    return childDatabase.indexOf(child);
                }
            }
        }
        System.out.println();
        return -1;
    }

    private ArrayList<Integer> searchDatabaseForChildByGroup()
    {
        System.out.println(" Wybierz grupę: ");
        System.out.println(" 1. Niedźwiadki");
        System.out.println(" 2. Krasnoludki");
        System.out.println(" 3. Płomyki");
        int choiceGrupa = InputValidator.validate(3);
        String grupa = "";
        switch (choiceGrupa)
        {
            case 1 -> grupa = grupy[0];
            case 2 -> grupa = grupy[1];
            case 3 -> grupa = grupy[2];
        }

        int i = 0;   //wskaźnik czy jakekolwiek obiekty zostały znalezione
        ArrayList<Integer> pozycja = new ArrayList<Integer>(); // Uzyskujemy w postaci tablicy indeksy wszystkich obiektów, które pasują do kryteriów wyszukiwania
        pozycja.add(-1); // umożliwia to sprawdzenie w edycji czy jakikolwiek obiekt został dodany do tego array. Jeśli tak to pozycja[0] zwróci wartość inną niż ujemną.

        for(Child child: childDatabase){
            //Sprawdzamy czy obiekt jest typu Child
            if (child instanceof Child){
                // oraz czy jego imie i nazwisko zgadzają się z poszukiwanymi
                if (child.getGrupa().equals(grupa)){
                    //Jeśli to pierwszy znaleziony obiekt zastępujemy -1 indeksem tego obiektu
                    if(i == 0)
                        pozycja.set(i, childDatabase.indexOf(child));
                        //Zaś następne tylko dodajemy jako kolejne na liście
                    else
                        pozycja.add(childDatabase.indexOf(child));

                    i++;
                    System.out.print("### POZYCJA NR " + i + " #");
                    child.drukujDanePelne();
                }
            }
        }

        System.out.println();
        if (i == 0)
            System.out.println("Child data hasn't been found in database. Check your input");
        else
            System.out.println(i + " children has been found.");
        return pozycja;
    }

    public Child chooseChild(){
        ArrayList<Integer> pozycje = searchDatabaseForChild();

        if(pozycje.get(0) >= 0)
            System.out.print("Wybierz pozycję do edycji: ");
        else{
            System.out.println("Błąd wyszukiwania! Spróbuj ponownie!");
        }

        return childDatabase.get(pozycje.get(InputValidator.validate(pozycje.size()) - 1));
    }

    /**
     * Metoda pozwala edytować dane obiektu dziecko
     */
    private int editData()
    {
        Scanner scanner = new Scanner(System.in);

         Child child = chooseChild(); //pobiera pozycję obiektu z bazy danych i kopiuje jego referencje do zmiennej child.

        if (child != null)
        {
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
        return 1;
    }


}
