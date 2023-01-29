package SchoolProgrammer;

import java.io.*;
import java.util.ArrayList;

public class Database implements Serializable
{
    public void writeObject(ArrayList<Child> listStudents) throws IOException
    {
        //Create FileOutputStream to write file
        FileOutputStream fos = new FileOutputStream("D:\\Programy\\Intellij\\Java Projects\\Beginning\\src\\SchoolProgrammer\\databasefile.txt");
        //Create ObjectOutputStream to write object
        ObjectOutputStream objOutputStream = new ObjectOutputStream(fos);
        //Write object to file
        for (Child obj : listStudents) {

            objOutputStream.writeObject(obj);
            objOutputStream.reset();
        }
        objOutputStream.close();
    }

    public ArrayList<Child> readObject() throws ClassNotFoundException, IOException {
        ArrayList<Child> listStudents = new ArrayList();
        //Create new FileInputStream object to read file
        FileInputStream fis = new FileInputStream("D:\\Programy\\Intellij\\Java Projects\\Beginning\\src\\SchoolProgrammer\\databasefile.txt");
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
}

