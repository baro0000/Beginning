package SchoolProgrammer;

import java.util.ArrayList;
import java.util.Scanner;

public class ExternalKeypad {

    public void takeInput() {
        String kod = "error";
        System.out.println("Wprowadź 6-cio cyfrowy indywidualny kod:");
        do {
            kod = InputValidator.validatePersonalId();
        } while (kod.equals("error"));

        for (Child child : ChildDatabase.childDatabase) {
            //Sprawdzamy czy obiekt jest typu Child
            if (child instanceof Child)
                if (!kod.equals("error"))
                    if (child.getIndywidualnyKod().equals(kod)) {
                        child.drukujDaneUproszczone();
                        child.dodajNowyRaport();
                    }
        }
    }
}
