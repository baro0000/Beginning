package Main;

public class Human {
    private String name;
    private int age;
    private int id;
    private static int nextId = 1;

    public Human(String name, int age){
        this.name = name;
        this.age = age;
        this.id = nextId;
        nextId++;
    }

    public void printData(){
        System.out.println("Imię: " + name + " wiek: " + age + " ID konta: " + id);
    }





}
