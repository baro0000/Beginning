package Main;

import java.util.Scanner;

interface ZachowaniePoWcisnieciu
{
    void akcja();
}

interface ZachowaniePoWcisnieciu2{
    void akcja2(int a, int b);
}

class Przycisk
{
    void dodajAkcje(ZachowaniePoWcisnieciu z)
    {
        z.akcja();
    }
    void dodajAkcje(int a, int b, ZachowaniePoWcisnieciu2 z){
        z.akcja2(a, b);
    }
}
public class Samples
{
    public static void main(String[] args)
    {
        ZachowaniePoWcisnieciu z = () ->
                System.out.println("To akcja z interfejsu");

        ZachowaniePoWcisnieciu2 z2 = (int a, int b) ->
                System.out.println("To są dwa argumenty z interfejsu 2: " + a + " oraz " + b);



        Przycisk p = new Przycisk();
        Przycisk b = new Przycisk();
        p.dodajAkcje(z);
        b.dodajAkcje(5, 10, z2);

    }
}
