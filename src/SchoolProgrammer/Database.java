package SchoolProgrammer;

import java.io.*;

public class Database implements Serializable
{
        void saveObject(Child child) throws IOException, ClassNotFoundException
        {
            FileOutputStream fileOutputStream
                    = new FileOutputStream("src/SchoolProgrammer/databasefile");
            ObjectOutputStream objectOutputStream
                    = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(child);
            objectOutputStream.flush();
            objectOutputStream.close();
        }

        Child loadObject() throws IOException, ClassNotFoundException{

            FileInputStream fileInputStream
                    = new FileInputStream("src/SchoolProgrammer/databasefile");
            ObjectInputStream objectInputStream
                    = new ObjectInputStream(fileInputStream);
            Child p2 = (Child) objectInputStream.readObject();
            objectInputStream.close();

            return p2;
        }
    }

