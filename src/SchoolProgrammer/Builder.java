package SchoolProgrammer;


import java.util.ArrayList;

public class Builder {
    public static AttendanceOfDay createAttendanceOfDay(String source){
        // ze source otrzymujemy : "10;2;2023;9:40:41;18:0:0;8:19:19;1";
        String[] listaBazowa = source.split(";");

        //dzielimy odpowiednio godz wejscia, wyjscia i czas pobytu przerabiając string na int
        String[] temp = listaBazowa[3].split(":");
        int[] godzinaWejscia = new int [temp.length];
        for(int i = 0; i < temp.length; i++)
            godzinaWejscia[i] = Integer.parseInt(temp[i]);

        String[] temp1 = listaBazowa[4].split(":");
        int[] godzinaWyjscia = new int [temp1.length];
        for(int i = 0; i < temp1.length; i++)
            godzinaWyjscia[i] = Integer.parseInt(temp1[i]);

        String[] temp2 = listaBazowa[5].split(":");
        int[] czasPobytu = new int [temp2.length];
        for(int i = 0; i < temp2.length; i++)
            czasPobytu[i] = Integer.parseInt(temp2[i]);


        AttendanceOfDay raport = new AttendanceOfDay(Integer.parseInt(listaBazowa[0]), Integer.parseInt(listaBazowa[1]), Integer.parseInt(listaBazowa[2]), godzinaWejscia, godzinaWyjscia, czasPobytu, Integer.parseInt(listaBazowa[6]));

        return raport;

    }

    public static Child createChild(String source) {
        // 0  |    1   | 2  |  3    |     4     |    5   |        6...
        // Przykładowy source : "Adam;;Kowalski;;33;;asdasd;;Krasnoludki;;661283;;10;2;2023;16:32:6;18:0:0;1:27:54;1#10;2;2023;16:32:6;18:0:0;1:27:54;1#10;2;2023;16:32:6;0:0:0;0:0:0;0#";
        String[] sourceList = source.split(";;");
        // Po podzieleniu stringa na array od razu tworzymy nowy obiekt child bo wiekszość danych to i tak string
        Child child = new Child(sourceList[0], sourceList[1], Integer.parseInt(sourceList[2]), sourceList[3], sourceList[4], sourceList[5]);

        //Następnie tworzymy listę raportów zawartych w pozycji 6 sourceList. Każdy string w tym array to osobne zrodlo do odtworzenia raportu
        if (sourceList.length >= 7) {
            String[] listOfRaportSources = sourceList[6].split("#");

            ArrayList<AttendanceOfDay>[] frekwencja = new ArrayList[12];

            for (int i = 0; i < listOfRaportSources.length; i++) {
                AttendanceOfDay raportDnia = createAttendanceOfDay(listOfRaportSources[i]);
                if (frekwencja[raportDnia.miesiac - 1] == null)
                    frekwencja[raportDnia.miesiac - 1] = new ArrayList<>();
                frekwencja[raportDnia.miesiac - 1].add(raportDnia);
            }
            child.frekwencja = frekwencja;
        }
            return child;

    }

    public static UserAccount createUserAccount(String source){
        // egz source =  "baro0000;tania0000;0";
        String [] sourceList = source.split(";");
        return new UserAccount(sourceList[0], sourceList[1], Integer.parseInt(sourceList[2]));
    }
}
