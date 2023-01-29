package SchoolProgrammer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Bartosz Kuliński
 * @version 1.0.0
 */

public class MainMenu
{
    /**
     * Creator calls method to view menu.
     */
    MainMenu()
    {
        startMenu();
    }


    /**
     * Method printing menu in console
     */
    private void printMenu()
    {
        System.out.println();
        System.out.println();
        System.out.println(" Main Menu");
        System.out.println(" 1. Child Database");
        System.out.println(" 2. Child stay report");
        System.out.println(" 3. Calculate frequency");
        System.out.println(" 4. Set costs");
        System.out.println(" 5. Calculate cost for each child");
        System.out.println(" 6. Parents interface");
        System.out.println(" 7. Exit");
        System.out.print(" Choose option number: ");
    }

    /**
     * Metoda korzystająca z wydruku menu, następnie wykorzystuje metodę inputValidator by upewnić się, że wprowadzone dane są prawidłowe. Korzystając ze switch'a uzyskujemy dostęp do różnych funkcji.
     * przy wybraniu nr 7 Exit - program ulega zakończeniu.
     *
     * @see MainMenu
     */
    public void startMenu()
    {
        ChildDatabase database = new ChildDatabase();
        database.childDatabase = loadChildDatabase();

        while (true)
        {
            printMenu();

            Scanner scanner = new Scanner(System.in);
            int choice = InputValidator.validate(7);
            System.out.println(choice);

            for (int i = 0; i < 20; i++)
                System.out.println();

            switch (choice)
            {
                case 1 ->
                {
                    database.childDataMenu();
                }
                case 2 ->
                {
                    System.out.println("Child stay report");
                    System.out.println("Wciśnij dowolny klawisz by powrócić do menu");
                    scanner.nextLine();
                }
                case 3 ->
                {
                    System.out.println("Calculate frequency");
                    System.out.println("Wciśnij dowolny klawisz by powrócić do menu");
                    scanner.nextLine();
                }
                case 4 ->
                {
                    System.out.println("Set costs");
                    System.out.println("Wciśnij dowolny klawisz by powrócić do menu");
                    scanner.nextLine();
                }
                case 5 ->
                {
                    System.out.println("5. Calculate cost for each child");
                    System.out.println("Wciśnij dowolny klawisz by powrócić do menu");
                    scanner.nextLine();
                }
                case 6 ->
                {
                    System.out.println("6. Parents interface");
                    System.out.println("Wciśnij dowolny klawisz by powrócić do menu");
                    scanner.nextLine();
                }
                case 7 -> saveChildDatabase(database.childDatabase);
            }

            if (choice == 7)
            {
                scanner.close();
                break;
            }
        }
    }

    private void saveChildDatabase(ArrayList<Child> lista){
        Database database = new Database();
        //Zapisanie do pliku dzieci po zakończeniu programu
        try
        {
            database.writeObject(lista);
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    private ArrayList<Child> loadChildDatabase(){
        Database database = new Database();
        ArrayList<Child> childList;
        //Zapisanie do pliku dzieci po zakończeniu programu
        try
        {
            childList =  database.readObject();
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e)
        {
            throw new RuntimeException(e);
        }
        return childList;
    }


}
