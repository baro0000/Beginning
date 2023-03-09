package SchoolProgrammer;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InputValidator {
    /**
     * Method takes user input and check if it is int type. It prevents to proceed by user invalid data type like String, char or wrong option number.
     *
     * @return Method returns int value CHOICE if it is in menu range (is not smaller than 1 and not bigger than X)
     */
    public static int validate(int numberOfPossibleOptions) {
        int choice = 0;
        Scanner scanner = new Scanner(System.in);
        try {
            choice = scanner.nextInt();
            if (choice > numberOfPossibleOptions || choice < 1) {
                System.out.println("Wprowadzono liczbę spoza zakresu! Wprowadź ponownie: ");
                choice = validate(numberOfPossibleOptions);
            }
        } catch (InputMismatchException ex) {
            System.out.println(ex.getMessage() + " prawdopodobnie wprowadziłeś niewłaściwy typ danych!");
            choice = validate(numberOfPossibleOptions);
        } catch (Exception ex) {
            System.out.println(ex.getMessage() + " - Wystąpił nieznany błąd! Skontaktuj się ze wsparciem technicznym.");
        }

        return choice;
    }

    public static double validateDoubleType() {
        double num = 0;
        Scanner scanner = new Scanner(System.in);
        try {
            num = scanner.nextDouble();
        } catch (InputMismatchException ex) {
            System.out.println(ex.getMessage() + " prawdopodobnie wprowadziłeś niewłaściwy typ danych!\nWprowadź ponownie:");
            num = validateDoubleType();
        } catch (Exception ex) {
            System.out.println(ex.getMessage() + " - Wystąpił nieznany błąd! Skontaktuj się ze wsparciem technicznym.");
        }

        return num;
    }

    /**
     * @return Metoda sprawdza czy wprowadzony przez użytkownika String składa się z 6 znaków i czy zawiera wyłacznie cyfry. Jeśli tak przekazuje ten kod dalej.
     */
    public static String validatePersonalId() {
        Scanner scanner = new Scanner(System.in);
        //lista dozwolonych znaków tj od 0 do 9
        ArrayList<String> listaZnakow = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            listaZnakow.add(String.valueOf(i));

        String[] tablicaKod;
        int wskaznik = 0;
        String kod = "empty";

        while (true) {
            kod = scanner.nextLine();
            //dzielimy wprowadzony String na tablicę Stringów zawierającą pojedyncze znaki
            tablicaKod = kod.split("");
            if (tablicaKod.length != 6)
                System.out.println("Kod musi być 6-cio cyfrowy! Wprowadź 6 cyfr");
            else {
                for (int i = 0; i < tablicaKod.length; i++) {
                    // Sprawdzamy czy zawiera tylko dozwolone liczby
                    if (listaZnakow.contains(tablicaKod[i])) {
                        if (i == 5) {
                            return kod;
                        }
                    }
                    else{
                            System.out.println("Kod musi się składać z cyfr z zakresu od 0 do 9! Wpisz ponownie.");
                        }
                    }
                }
            }
        }
    }
