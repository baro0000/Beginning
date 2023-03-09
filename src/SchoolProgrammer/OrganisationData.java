package SchoolProgrammer;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class OrganisationData {
    static double stawkaZaWyzywienie = 15.5;
    static double stawkaZaCzasPobytuNaGodzine = 5;
    static int year;
    static int month;
    static int day;
    static int invoiceNumber = 0;
    static String bankAccountNumber = "42 2312 2100 0000 2010 2345 2134";
    static String[] miesiace = {"Styczeń", "Luty", "Marzec", "Kwiecień", "Maj", "Czerwiec", "Lipiec", "Sierpień", "Wrzesień", "Październik", "Listopad", "Grudzień"};

    //------------------------------------------------------------------------------------------------
    public static void settingDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String czas = formatter.format(date);
        char[] tabZnakow = czas.toCharArray();

        day = Integer.parseInt("" + tabZnakow[0] + tabZnakow[1]);
        month = Integer.parseInt("" + tabZnakow[3] + tabZnakow[4]);
        year = Integer.parseInt("" + tabZnakow[6] + tabZnakow[7] + tabZnakow[8] + tabZnakow[9]);
    }

    public static void setCosts(){
        System.out.println("Wybierz opcję: ");
        System.out.println("1. Ustaw dobową stawkę żywieniową.");
        System.out.println("2. Ustaw godzinową stawkę pobytu.");

        int choice = InputValidator.validate(2);
        double temp;

        if(choice == 1){
            System.out.println("Wprowadź nową wartość:");
            temp = InputValidator.validateDoubleType();
            if(temp / stawkaZaWyzywienie > 2){
                System.out.println("Podana wartość jest ponad dwukrotnie większa od poprzedniej. Czy zatwierdzić?\n1. Tak\n2. Nie");
                choice = InputValidator.validate(2);
                if(choice == 1) {
                    stawkaZaWyzywienie = temp;
                    System.out.print("Zapisano pomyślnie. Nowa stawka to: " + stawkaZaWyzywienie + "\n");
                }
                else
                    setCosts();
            }
            else {
                stawkaZaWyzywienie = temp;
                System.out.print("Zapisano pomyślnie. Nowa stawka to: " + stawkaZaWyzywienie + "\n");
            }
        }
        else if(choice == 2){
            System.out.println("Wprowadź nową wartość:");
            temp = InputValidator.validateDoubleType();
            if(temp / stawkaZaCzasPobytuNaGodzine > 2){
                System.out.println("Podana wartość jest ponad dwukrotnie większa od poprzedniej. Czy zatwierdzić?\n1. Tak\n2. Nie");
                choice = InputValidator.validate(2);
                if(choice == 1) {
                    stawkaZaCzasPobytuNaGodzine = temp;
                    System.out.print("Zapisano pomyślnie. Nowa stawka to: " + stawkaZaWyzywienie + "\n");
                }
                else
                    setCosts();
            }
            else{
                stawkaZaCzasPobytuNaGodzine = temp;
                System.out.print("Zapisano pomyślnie. Nowa stawka to: " + stawkaZaWyzywienie + "\n");
            }
        }
        else
            System.out.println("Błąd! Opcja poza zasięgiem!");
    }

    public static int getNextInvoiceNumber(){
        invoiceNumber++;
        return invoiceNumber;
    }

    public static void drukujListeMiesiecy(){
        System.out.println("Wybierz miesiąc: ");
        System.out.println("1. Styczeń ");
        System.out.println("2. Luty ");
        System.out.println("3. Marzec ");
        System.out.println("4. Kwiecień ");
        System.out.println("5. Maj ");
        System.out.println("6. Czerwiec ");
        System.out.println("7. Lipiec ");
        System.out.println("8. Sierpień ");
        System.out.println("9. Wrzesień ");
        System.out.println("10. Październik ");
        System.out.println("11. Listopad ");
        System.out.println("12. Grudzień ");
    }
}
