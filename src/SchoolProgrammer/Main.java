package SchoolProgrammer;
/**
 * @author Bartosz Kuliński
 * @version 1.0.0
 */
public class Main
{
    public static void main(String[] args)
    {
        //MainMenu programmer = new MainMenu();
        AttendanceOfDay x = new AttendanceOfDay();
        x.godzinaWejscia[0] = 12;
        x.godzinaWejscia[1] = 40;
        x.godzinaWejscia[2] = 00;

        x.godzinaWyjscia[0] = 13;
        x.godzinaWyjscia[1] = 10;
        x.godzinaWyjscia[2] = 50;

        int[] wynik = x.calculateDayTimeOfAttendance();
        for(int value: wynik){
            System.out.print(value + " : ");
        }
    }
}
