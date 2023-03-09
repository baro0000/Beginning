package SchoolProgrammer;

import Potwory.Main;

import java.util.Scanner;

public class StartingProgram {
    MainMenu mainMenu = new MainMenu();
    AsystentLogowania logger = new AsystentLogowania();

    public void startMenu() {
        System.out.println("Witaj w moim pierwszym programie.");
        System.out.println("Jest to program symulujący zarządzanie przedszkolem");

        logger.userDatabase = Database.readUserDatabaseFromFile();
        boolean resultOfLogIn = asystentLogowania();
        int choice;
        while(true) {
            if (resultOfLogIn) {
                System.out.println("Wybierz co chcesz zrobić:");
                System.out.println("1. Przejdź do głównego programu");
                System.out.println("2. Zarządzaj kontami użytkowników");
                System.out.println("3. Zakończ program");
                choice = InputValidator.validate(3);
                switch (choice) {
                    case 1 -> mainMenu.startMenu();
                    case 2 -> userAccountsMenager();
                }
                if(choice == 3){
                    Database.saveUserDatabaseToFile(logger.userDatabase);
                    break;
                }


            } else {
                System.out.println("-----ODMOWA DOSTĘPU-----");
                Database.saveUserDatabaseToFile(logger.userDatabase);
                break;
            }
        }
    }


    public boolean asystentLogowania() {
        if (firstStartUp(logger.userDatabase.size())) {
            for (int i = 0; i < 20; i++)
                System.out.println();
        }
        return logger.loguj();
    }

    public boolean firstStartUp(int programInitializer) {
        if (programInitializer > 0)
            return false;

        System.out.println("Kreator pierwszego uruchomienia programu");
        UserAccount newUserAccount = logger.createNewUserAccount();
        logger.userDatabase.add(newUserAccount);
        programInitializer++;
        return true;
    }

    public void userAccountsMenager() {
        while (true) {
            for (int i = 0; i < 20; i++) {
                System.out.println();
            }
            System.out.println("Menager kont użytkowników");
            System.out.println("1. Dodaj nowego użytkownika");
            System.out.println("2. Usuń użytkownika");
            System.out.println("3. Wyczyść listę kont użytkowników");
            System.out.println("4. Wyjdź");

            int choice = InputValidator.validate(4);
            switch (choice) {
                case 1 -> logger.userDatabase.add(logger.createNewUserAccount());
                case 2 -> {
                    UserAccount user = logger.userDatabase.get(findUserAccount());
                    System.out.println("Czy na pewno usunąć?");
                    System.out.println("1. Tak");
                    System.out.println("2. Nie");
                    int choice2 = InputValidator.validate(2);
                    if(choice2 == 1){
                        logger.userDatabase.remove(user);
                        System.out.println("Usunięto pomyślnie");
                    }

                }
                case 3 -> logger.userDatabase.clear();
            }
            if(choice == 4)
                break;
        }
    }

    public int findUserAccount(){
        UserAccount temp;
        int choice = 0;
        if(logger.userDatabase.size() > 0) {
            for (int i = 0; i < logger.userDatabase.size(); i++) {
                temp = logger.userDatabase.get(i);
                System.out.println((i+1) + ". " + temp.login + " " + temp.haslo);
            }
            System.out.print("Wybierz pozycję do usunięcia: ");
            choice = InputValidator.validate(logger.userDatabase.size()) - 1;
        }
        return choice;
    }
}
