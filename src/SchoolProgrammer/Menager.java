package SchoolProgrammer;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author Bartosz Kuliński
 * @version 1.0.0
 */

public class Menager
{

    /**
     * grupy określa kolekcje dostępnych grup do wykorzystania przy tworzeniu obiektu dziecka - każde dziecko zostanie przypisane do odpowiedniej grupy.
     */
    public static String [] grupy = {"Niedźwiadki", "Krasnoludki", "Płomyki"};
    private int nextId = 0;
    public Child [] childDatabase = new Child[10];


    /**
     * Opcja umożliwia dodanie dziecka do bazy danych. Najpiew pobiera potrzebne dane od użytkownika, następnie wykorzystuje je do utworzenia nowego obiektu typu Child, dodaje do bazy danych i zwiększa nextID o 1.
     */
    public void addChildToDatabase(){
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
        sc.close();
    }







}
