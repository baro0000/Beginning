package SchoolProgrammer;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputValidator
{
    /**
     * Method takes user input and check if it is int type. It prevents to proceed by user invalid data type like String, char or wrong option number.
     *
     * @return Method returns int value CHOICE if it is in menu range (is not smaller than 1 and not bigger than 7)
     */
    public static int validate(int numberOfPossibleOptions){
        int choice = 0;
        Scanner scanner = new Scanner(System.in);
        try
        {
            choice = scanner.nextInt();
            if(choice > numberOfPossibleOptions || choice < 1){
                throw new Exception("Numer opcji poza zasięgiem!");
            }
        } catch (InputMismatchException ex)
        {
            System.out.println(ex.getMessage() + " prawdopodobnie wprowadziłeś niewłaściwy typ danych!");
            validate(numberOfPossibleOptions);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage() + " - Wystąpił nieznany błąd! Skontaktuj się ze wsparciem technicznym.");
        }

        return choice;
    }
}
