package SchoolProgrammer;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.time.Month;
import java.time.LocalDate;

public class AttendanceOfDay implements Serializable
{
    int rok;
    int miesiac;
    int dzien;
    int[] godzinaWejscia = new int[3]; // w tablicy zachowany jest format tab[0] - godz, tab [1] - minuta, tab[2] - sekunda
    int[] godzinaWyjscia = new int[3]; // w tablicy zachowany jest format tab[0] - godz, tab [1] - minuta, tab[2] - sekunda
    int[] timeOfAttendenceDay;
    int closed = 0;
    AttendanceOfDay()
    {
        settingValues();
        settingEntrance();
    }

    //Gettery----------------------------------------------------------------------------------------

    public String getTimeOfAttendenceDay() {
        String result;
        if(timeOfAttendenceDay != null) {
            result = timeOfAttendenceDay[0] + ":" + timeOfAttendenceDay[1] + ":" + timeOfAttendenceDay[2];
        }
        else
            result = "";
        return result;
    }

    public String getGodzinaWejscia() {
        String result = godzinaWejscia[0] + ":" + godzinaWejscia[1] + ":" + godzinaWejscia[2];
        return result;
    }

    public String getGodzinaWyjscia() {
        String result;
        if(godzinaWyjscia != null) {
            result = godzinaWyjscia[0] + ":" + godzinaWyjscia[1] + ":" + godzinaWyjscia[2];
        }
        else
            result = "";
        return result;
    }

    /**
     * Metoda inicjowana w kreatorze. Ustawia właściwości dzien, miesiąc i rok w chwili tworzenia obiektu.
     */
    private void settingValues()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String czas = formatter.format(date);
        char[] tabZnakow = czas.toCharArray();

        this.dzien = Integer.parseInt("" + tabZnakow[0] + tabZnakow[1]);
        this.miesiac = Integer.parseInt("" + tabZnakow[3] + tabZnakow[4]);
        this.rok = Integer.parseInt("" + tabZnakow[6] + tabZnakow[7] + tabZnakow[8] + tabZnakow[9]);
    }

    /**
     * Metoda ustawia w formie tablicy godzine minute i sekunde wejscia
     */
    public void settingEntrance()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        String czas = formatter.format(date);
        char[] tabZnakow = czas.toCharArray();

        godzinaWejscia[0] = Integer.parseInt("" + tabZnakow[0] + tabZnakow[1]);
        godzinaWejscia[1] = Integer.parseInt("" + tabZnakow[3] + tabZnakow[4]);
        godzinaWejscia[2] = Integer.parseInt("" + tabZnakow[6] + tabZnakow[7]);
    }

    /**
     * Metoda ustawia w formie tablicy godzine minute i sekunde wejscia
     */
    public void settingExit()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        String czas = formatter.format(date);
        char[] tabZnakow = czas.toCharArray();

        godzinaWyjscia[0] = Integer.parseInt("" + tabZnakow[0] + tabZnakow[1]);
        godzinaWyjscia[1] = Integer.parseInt("" + tabZnakow[3] + tabZnakow[4]);
        godzinaWyjscia[2] = Integer.parseInt("" + tabZnakow[6] + tabZnakow[7]);
        closed++;
    }

    public int[] calculateDayTimeOfAttendance()
    {
        int[] result = new int[3];
        int temp = 0;

        for (int i = 0; i < godzinaWyjscia.length; i++)
        {
            temp = godzinaWyjscia[i] - godzinaWejscia[i];
            if(temp >= 0)
                result[i] = temp;
            else{
                if(result[i-1] > 0)
                {
                    result[i - 1] -= 1;
                    result[i] = temp + 60;
                }
                else
                    System.out.println("Błąd programu!");
            }
        }
        return result;
    }
    // po zamknięciu dnia ustawiamy godz wyjscia, liczymy czas pobytu i wskaznik zamkniecia ustawiamy z 0 na 1
    public void closeDay(){
        if(closed != 1) {
            godzinaWyjscia[0] = 18;
            godzinaWyjscia[1] = 00;
            godzinaWyjscia[2] = 00;
            closed++;
        }
        timeOfAttendenceDay = calculateDayTimeOfAttendance();
    }
}

