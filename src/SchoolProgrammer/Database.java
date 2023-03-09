package SchoolProgrammer;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Database {
    /**
     * @param list jeśli lista nie jest pusta to zapisujemy wszystkie elementy do pliku
     */
    //------ZAPIS DO PLIKU------------------------------------------------------------------------------------
    public static void saveDatabaseToFile(ArrayList<Child> list) {
        File file = new File("database.txt");
        if(file.exists() && !file.isDirectory()) {
            file.delete();
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (list.size() > 0) {
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                    if (list != null) {
                        for (Child child : list) {
                            writer.write(child.toString() + "\n");
                        }
                    }
                    writer.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        else {
            try {
                file.createNewFile();
                saveDatabaseToFile(list);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void saveUserDatabaseToFile(ArrayList<UserAccount> listaKont){
        File file = new File("userAccountDatabase");
        if(file.exists() && !file.isDirectory()){
            file.delete();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (listaKont.size() > 0) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                if (listaKont != null) {
                    for (UserAccount user : listaKont) {
                        writer.write(user.toString() + "\n");
                    }
                }
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        /*else {
            try {
                file.createNewFile();
                saveUserDatabaseToFile(listaKont);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }*/
    }

//------WCZYTANIE Z PLIKU------------------------------------------------------------------------------------

    /**
     * @return Jeśli plik istnieje to odczytujemy wszystkie dane, odtwarzamy obiekty child i umieszczamy w liście, którą następnie zwracamy
     */
    public static ArrayList<Child> readDatabaseFromFile(){
        File file = new File("database.txt");
        ArrayList<Child> database = new ArrayList<>();
        if(file.exists() && !file.isDirectory()) {
            String line = "";
            Child tempChildObject;
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                while ((line = reader.readLine()) != null) {
                    tempChildObject = Builder.createChild(line);
                    database.add(tempChildObject);
                }
                reader.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return database;
    }

    public static ArrayList<UserAccount> readUserDatabaseFromFile() {
        File file = new File("userAccountDatabase");
        ArrayList<UserAccount> listaKont = new ArrayList<>();
        String sourceFromFile;

        if(file.exists() && !file.isDirectory()){
            UserAccount user;
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                while ((sourceFromFile = reader.readLine()) != null) {
                    user = Builder.createUserAccount(sourceFromFile);
                    listaKont.add(user);
                }
                reader.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return listaKont;
    }
}

