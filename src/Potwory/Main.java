package Potwory;


import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

public class Main
{
    public static void main(String[] args)
    {
        Double [] tab = {5.0, 2.0, 3.0, 1.0, 6.0, 0.0};
        for(double item: tab){
            System.out.println(item);
        }

        System.out.println("Po sortowaniu w dół");
        Arrays.sort(tab);

        for(double item: tab){
            System.out.println(item);
        }

        System.out.println("Po sortowaniu w górę");
        Arrays.sort(tab, Collections.reverseOrder());

        for(double item: tab){
            System.out.println(item);
        }
    }
}
