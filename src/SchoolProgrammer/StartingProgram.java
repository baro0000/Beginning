package SchoolProgrammer;

public class StartingProgram {
    public void startingMenu(){


    }

    public void parentsKeypad(){
        ExternalKeypad keypad = new ExternalKeypad();
        keypad.takeInput();
    }

    public void asystentLogowania(){
        AsystentLogowania asystent = new AsystentLogowania();
        asystent.loguj();
    }
}
