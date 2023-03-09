package SchoolProgrammer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class AsystentLogowania {
    ArrayList<UserAccount> userDatabase = new ArrayList<>();

    public boolean loguj(){
        return sprawdzDaneLogowania();
    }

    private boolean sprawdzDaneLogowania(){
        Scanner scanner = new Scanner(System.in);
        for(int i = 3; i > 0; i--) {
            System.out.println("Asystent logowania:");
            System.out.println("Uwaga - asystent uwzględnia wielkość liter!");

            System.out.print("Login: ");
            String login = scanner.nextLine();
            System.out.print("Hasło: ");
            String haslo = scanner.nextLine();


            for(UserAccount user : userDatabase) {
                if (user.blocked == 0){
                    if (login.equals(user.login) && haslo.equals(user.haslo))
                        return true;
                    else if (login.equals(user.login)) {
                        System.out.println("Wprowadzono niepoprawny login lub hasło! Pozostało " + (i - 1) + " prób.");
                    }
                    else
                        System.out.println("Nie znaleziono konta powiązanego z tym loginem");
                    if (i == 0)
                        user.blocked++;
                }
                else
                    System.out.println("To konto zostało zablokowane! Skontaktuj się z administratorem.");
            }
        }
        System.out.println("Wprowadzono trzykrotnie niepoprawne dane logowania!");
        return false;
    }

    public UserAccount createNewUserAccount(){
        System.out.println("Utwórz nowe konto:");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wprowadź login: ");
        String login = scanner.nextLine();
        System.out.println("Wprowadź hasło: ");
        String haslo = scanner.nextLine();
        UserAccount newAccount = new UserAccount(login, haslo);
        return newAccount;
    }
}
