package SchoolProgrammer;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Bartosz Kuliński
 * @version 1.0.0
 */

public class Child {

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
    @Override
    public String toString() {
        String result = imie + ";;" + nazwisko + ";;" + wiek + ";;" + adresZamieszkania + ";;" + grupa + ";;" + indywidualnyKod + ";;";
        for (ArrayList<AttendanceOfDay> listaRaportowZMiesiaca : frekwencja)
            if (listaRaportowZMiesiaca != null)
                for (AttendanceOfDay raport : listaRaportowZMiesiaca)
                    result += raport.toString() + "#";
        return result;
    }

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
                } else {
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

    public void printLists() {
        for (ArrayList<AttendanceOfDay> lista : frekwencja) {
            if (lista != null) {
                for (AttendanceOfDay raport : lista) {
                    System.out.println(raport.dzien + "." + raport.miesiac + "." + raport.rok + " rok Godzina wejścia: " + raport.getGodzinaWejscia() + " - Godzina wyjścia: DUPA" + raport.godzinaWyjscia);
                    System.out.println(raport.getTimeOfAttendenceDay());
                }
            }
        }
    }

    public int[] calculateTime(ArrayList<int[]> listaRaportow) {
        int[] result = new int[3];
        int temp = 0;
        for (int[] time : listaRaportow) {
            for (int a = 2; a > -1; a--) {
                temp = result[a] + time[a];
                if (a == 0) {
                    result[a] = temp;
                    continue;
                }
                if (temp <= 59) {
                    result[a] = temp;
                } else {
                    result[a - 1] += temp / 60;
                    result[a] = temp % 60;
                }
            }
        }
        return result;
    }

    public int[] calculateAttendanceTime(int choice, int monthNumber) {
        int[] result = new int[4];
        ArrayList<int[]> temporary = new ArrayList<>();
        if (choice == 0) {
            System.out.println("Wybierz podsumowanie: ");
            System.out.println("1. Roczne");
            System.out.println("2. Miesięczne");
            choice = InputValidator.validate(2);

        }

        if (choice == 1) {
            ArrayList<int[]> totalSummaryList = new ArrayList<>();
            int[] totalSummary;
            for (int i = 0; i < frekwencja.length; i++) {
                if (frekwencja[i] != null) {
                    for (AttendanceOfDay raport : frekwencja[i])
                        temporary.add(raport.timeOfAttendenceDay);
                    totalSummary = calculateTime(temporary);
                    totalSummaryList.add(totalSummary);
                    temporary.clear();
                }
            }
                result = calculateTime(totalSummaryList);
                return result;

        } else if (choice == 2) {

            if(monthNumber == 0) {
                OrganisationData.drukujListeMiesiecy();
                monthNumber = InputValidator.validate(12);

            }
            result[3] = monthNumber - 1;
            for(AttendanceOfDay raport : frekwencja[monthNumber - 1])
                temporary.add(raport.timeOfAttendenceDay);
            int[] temp = calculateTime(temporary);
            result[0] = temp[0];
            result[1] = temp[1];
            result[2] = temp[2];

            return result;
        } else {
            System.out.println("Błąd! Coś poszło nie tak");
        }
        return result;
    }

//nietestowane
    /*public void zakonczenieRoku(int year) {
        //Domknięcie grudnia
        for (AttendanceOfDay raport : frekwencja[11]) {
            raport.closeDay();
        }

    }*/


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

    public void drukujFrekwencje() {
        String[] month = {"Styczeń", "Luty", "Marzec", "Kwiecień", "Maj", "Czerwiec", "Lipiec", "Sierpień", "Wrzesień", "Październik", "Listopad", "Grudzień"};
        for (int i = 0; i < frekwencja.length; i++) {
            if (frekwencja[i] != null) {
                System.out.println(month[i] + "\n");
                for (AttendanceOfDay raport : frekwencja[i]) {
                    System.out.println("Data: " + raport.dzien + "." + raport.miesiac + "." + raport.rok);
                    System.out.println("Godzina wjścia: " + raport.godzinaWejscia[0] + ":" + raport.godzinaWejscia[1] + ":" + raport.godzinaWejscia[2]);
                    System.out.println("Godzina wyjścia: " + raport.godzinaWyjscia[0] + ":" + raport.godzinaWyjscia[1] + ":" + raport.godzinaWyjscia[2]);
                    System.out.println("Czas pobytu: " + raport.timeOfAttendenceDay[0] + ":" + raport.timeOfAttendenceDay[1] + ":" + raport.timeOfAttendenceDay[2] + "\n");
                }
                System.out.println("-------------------------------------------------------------------------");
                System.out.println();
            }
        }
    }

}
