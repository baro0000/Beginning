package SchoolProgrammer;

import java.io.*;
import java.util.ArrayList;

public class Database implements Serializable {
    //################################################################################################################
    public void writeObject(ArrayList<Child> listOfChildren, String path) throws IOException {
        //Create FileOutputStream to write file
        FileOutputStream fos = new FileOutputStream(path);
        //Create ObjectOutputStream to write object
        ObjectOutputStream objOutputStream = new ObjectOutputStream(fos);
        //Write object to file
        for (Child obj : listOfChildren) {

            objOutputStream.writeObject(obj);
            objOutputStream.reset();
        }
        objOutputStream.close();
    }

    public void writeObjectDayRaport(ArrayList<AttendanceOfDay> listOfRaports, String path) throws IOException {
        //Create FileOutputStream to write file
        FileOutputStream fos = new FileOutputStream(path);
        //Create ObjectOutputStream to write object
        ObjectOutputStream objOutputStream = new ObjectOutputStream(fos);
        //Write object to file
        for (AttendanceOfDay raport : listOfRaports) {

            objOutputStream.writeObject(raport);
            objOutputStream.reset();
        }
        objOutputStream.close();
    }


    //################################################################################################################
    public ArrayList<Child> readObject(String path) throws ClassNotFoundException, IOException {
        ArrayList<Child> listStudents = new ArrayList<Child>();
        //Create new FileInputStream object to read file
        FileInputStream fis = new FileInputStream(path);
        //Create new ObjectInputStream object to read object from file
        ObjectInputStream obj = new ObjectInputStream(fis);
        try {
            while (fis.available() != -1) {
                //Read object from file
                Child std = (Child) obj.readObject();
                listStudents.add(std);
            }
        } catch (EOFException ex) {
            //ex.printStackTrace();
        }
        return listStudents;
    }

    public boolean ifFileExists(String path){
        File plik = new File(path);
        if(plik.exists() && !plik.isDirectory())
            return true;
        return false;
    }

}

