package SchoolProgrammer;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputValidator
{
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
            System.out.println(ex.getMessage() + "Wystąpił nieznany błąd! Skontaktuj się ze wsparciem technicznym.");
        }
        scanner.close();
        return choice;
    }
}
