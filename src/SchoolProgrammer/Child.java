package SchoolProgrammer;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Bartosz Kuliński
 * @version 1.0.0
 */

public class Child implements Serializable {

    private String imie;
    private String nazwisko;
    private int wiek;
    private String adresZamieszkania;
    String grupa;

    private String indywidualnyKod = "none";
    public ArrayList<AttendanceOfDay>[] frekwencja = new ArrayList[12];

    public Child(String imie, String nazwisko, int wiek, String adresZamieszkania, String grupa, String kod) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.wiek = wiek;
        this.adresZamieszkania = adresZamieszkania;
        this.grupa = grupa;
        indywidualnyKod = kod;
    }

    //Gettery ----------------------------------------------------------------------------------------------------------
    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public int getWiek() {
        return wiek;
    }

    public String getAdresZamieszkania() {
        return adresZamieszkania;
    }

    public String getGrupa() {
        return grupa;
    }

    public String getIndywidualnyKod() {
        return indywidualnyKod;
    }

    // Settery ----------------------------------------------------------------------------------------------------------

    public void setImie(String imie) {
        this.imie = imie;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public void setWiek(int wiek) {
        this.wiek = wiek;
    }

    public void setAdresZamieszkania(String adresZamieszkania) {
        this.adresZamieszkania = adresZamieszkania;
    }

    public void setGrupa(String grupa) {
        this.grupa = grupa;
    }

    public void setIndywidualnyKod(String indywidualnyKod) {
        this.indywidualnyKod = indywidualnyKod;
    }

    //Metody  ----------------------------------------------------------------------------------------------------------
    public int dodajNowyRaport() {
        //Sprawdzenie czy pod wskazanym indeksem miesiąca jest już Arraylist, jeśli tak to dodaje kolejny raport dnia, jeśli nie to najpierw tworzy nowy Array list
        AttendanceOfDay raportDnia = new AttendanceOfDay();
        if (frekwencja[raportDnia.miesiac - 1] == null) {
            frekwencja[raportDnia.miesiac - 1] = new ArrayList<>();
            // Dodatkowo sprawdzamy czy jest dostępny miesiąc do tyłu. Jeśli tak to zamykamy wszystkie niezamknięte raporty
            if (raportDnia.miesiac - 1 > 0) {
                if (frekwencja[raportDnia.miesiac - 2] != null) {
                    for (AttendanceOfDay raport : frekwencja[raportDnia.miesiac - 2]) {
                        raport.closeDay();
                    }
                }
            }
        }

        //Pobieramy ostatni raport z tego samego miesiąca
        if (frekwencja[raportDnia.miesiac - 1].size() != 0) {
            AttendanceOfDay lastRaport = frekwencja[raportDnia.miesiac - 1].get(frekwencja[raportDnia.miesiac - 1].size() - 1);
            // Jeśli jest już taki plik
            if (lastRaport.dzien == raportDnia.dzien && lastRaport.miesiac == raportDnia.miesiac && lastRaport.rok == raportDnia.rok) {
                // i jest zamknięty to dajemy możliwość edycji - ustawienia nowej daty wyjścia
                if (lastRaport.closed == 1) {
                    System.out.println("Dzień został już zamknięty! Edytować?\n1. Tak\n2. Nie");
                    int choice = InputValidator.validate(2);
                    if (choice == 1) {
                        lastRaport.closed = 0;
                        lastRaport.settingExit();
                        System.out.println("Zmiana została zapisana pomyślnie.\nGodzina wejścia: " + lastRaport.getGodzinaWejscia() + "\nGodzina wyjścia: " + lastRaport.getGodzinaWyjscia());
                        lastRaport.closeDay();
                        return 0;
                    } else // Jeśli nie chce edytować to wychodzimy
                        return 0;
                }
                else {
                    // Jeśli nie jest zamknięty to zamykamy i wychodzimy
                    lastRaport.settingExit();
                    System.out.println("Zapisano wpis.\nGodzina wejścia: " + lastRaport.getGodzinaWejscia() + "\nGodzina wyjścia: " + lastRaport.getGodzinaWyjscia());
                    lastRaport.closeDay();
                    return 0;
                }
            }
        }
        //Jeśli takiego pliku jeszcze nie ma to dodajemy do listy
        System.out.println("Drugi raz tu nie dochodzimy");
        frekwencja[raportDnia.miesiac - 1].add(raportDnia);
        System.out.println("Zapisano wpis.\nGodzina wejścia: " + raportDnia.getGodzinaWejscia() + "\nGodzina wyjścia:");
        return 0;
    }

    public void printLists(){
        for(ArrayList<AttendanceOfDay> lista: frekwencja){
            if(lista != null){
                for(AttendanceOfDay raport: lista){
                    System.out.println(raport.dzien + "." + raport.miesiac + "." + raport.rok + " rok Godzina wejścia: " + raport.getGodzinaWejscia() + " - Godzina wyjścia: " + raport.godzinaWyjscia);
                    System.out.println(raport.getTimeOfAttendenceDay());
                }
            }
        }
    }
// nietestowane
    public void zakonczenieRoku(String year) {
        //Domknięcie grudnia
        for (AttendanceOfDay raport : frekwencja[11]) {
            raport.closeDay();
        }
        Database database = new Database();
        //Ustawienie nazwy pliku tak by można wyizolować kod osoby i rok
        String path = "src/SchoolProgrammer/" + "#" + indywidualnyKod + "#" + "-" + "#" + year + "#" + ".txt";
        ArrayList<AttendanceOfDay> lista = new ArrayList<>();
        try {
            //Przerobienie bazy na pojedyncza liste
            for (ArrayList<AttendanceOfDay> item : frekwencja) {
                for (AttendanceOfDay raport : item)
                    lista.add(raport);
            }
            database.writeObjectDayRaport(lista, path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void drukujDanePelne() {
        System.out.println("###################################################################################################");
        System.out.println("Dane z systemu:");
        System.out.println("Indywidualny kod ID: " + this.indywidualnyKod);
        System.out.println("Imię: " + this.imie);
        System.out.println("Nazwisko: " + this.nazwisko);
        System.out.println("Wiek: " + this.wiek);
        System.out.println("Adres zamieszkania: " + this.adresZamieszkania);
        System.out.println("Grupa: " + this.grupa);
    }

    public void drukujDaneUproszczone() {
        System.out.println("###################################################################################################");
        System.out.println("Indywidualny kod ID: " + this.indywidualnyKod);
        System.out.println("Imię: " + this.imie);
        System.out.println("Nazwisko: " + this.nazwisko);
        System.out.println("Grupa: " + this.grupa);
    }

}
