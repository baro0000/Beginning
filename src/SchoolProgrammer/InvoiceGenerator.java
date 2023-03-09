package SchoolProgrammer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class InvoiceGenerator {

    //DOKOŃCZYĆ : tworzenie folderu, generowanie faktury
    public static void generateNewInvoice(Child child, int month, int invoiceNumber,int hours, int days, double monthCost, double monthDiningCost) throws IOException {
        File directory = new File("src/SchoolProgrammer/Generated_Invoices");

        if (!directory.exists())
            Files.createDirectory(Paths.get("src/SchoolProgrammer/Generated_Invoices"));
        File file = new File("src/SchoolProgrammer/Generated_Invoices/" + child.getImie() + "_" + child.getNazwisko() + "_" + child.getIndywidualnyKod() + "_" + OrganisationData.miesiace[month -1]  + OrganisationData.year + ".txt");
        if (file.exists() && !file.isDirectory()) {
            file.delete();
            file.createNewFile();
        } else {
            file.createNewFile();
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write("Data wystawienia: " + OrganisationData.day + "." + OrganisationData.month + "." + OrganisationData.year + "rok");
        writer.newLine();
        writer.newLine();
        writer.write("Dane podmiotu wystawiającego: ");
        writer.newLine();
        writer.write("Przedszkole nr 16 w Mysłowicach");
        writer.newLine();
        writer.write("ul. Długa 21");
        writer.newLine();
        writer.write("41-400 Mysłowice");
        writer.newLine();
        writer.newLine();
        writer.write("Imie i nazwisko rodzica");
        writer.newLine();
        writer.write("Adres rodzica");
        writer.newLine();
        writer.newLine();
        writer.newLine();
        writer.write("                         FAKTURA VAT nr FV/" +  OrganisationData.getNextInvoiceNumber() + "/" + OrganisationData.month + "/" + OrganisationData.year);
        writer.newLine();
        writer.newLine();
        writer.newLine();
        writer.write("Całkowita liczba godzin opieki: " + hours + " godzin");
        writer.newLine();
        writer.write("Całkowita liczba dni opieki: " + days + " dni");
        writer.newLine();
        writer.write("Całkowity koszt wyżywienia: " + monthDiningCost + " zł - stawka żywieniowa za dzień: " + OrganisationData.stawkaZaWyzywienie);
        writer.newLine();
        writer.write("Całkowity koszt opieki: " + monthCost + " zł - stawka pobytu za godzinę " + OrganisationData.stawkaZaCzasPobytuNaGodzine + " zł");
        writer.newLine();
        writer.newLine();
        writer.write("Do zapłaty: " + (monthCost + monthDiningCost)+ " zł");
        writer.newLine();
        writer.newLine();
        writer.write("Czas zapłaty faktury do 14 dnia następnego miesiąca, na rachunek bankowy " + OrganisationData.bankAccountNumber + " tytułem \"opieka nad " + child.getImie() + " " + child.getNazwisko() + "\"");
        writer.newLine();
        writer.newLine();
        writer.newLine();
        writer.write("                                                 _______________________________");
        writer.newLine();
        writer.write("                                                   podpis i pieczęć wystawcy");

        writer.close();
    }
}

