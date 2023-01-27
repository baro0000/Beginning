package Main;

/**
 *  Pierwszy program w Javie - butelka umożliwiająca nalewanie i wylewanie wody przy zachowaniu pojemności.
 * @author Bartosz Kuliński
 * @version 1.0
 * To pierwsza część
 */
public class Pierwszy_program_butelka
{
    public static void main(String[] args)
    {
        Butelka bottle1 = new Butelka(1000);
        Butelka bottle2 = new Butelka(500);
        bottle1.pourWaterIn(900);
        bottle1.getWaterAmount();
        bottle1.przelejDoInnejButelki(800, bottle2);

    }
}

class Butelka
{//----------Atrybuty---------------------------------------
    /**
     * Zmienna określająca pojemność butelki
     */
    private int capacity;
    static int num = 1;
    private int bottleID;
    private int waterInsideBottle;

    /**
     * Domyślny konstruktor
     * @param cap określa pojemność danej butelki
     */
    //-----------Creator----------------------------------------
    Butelka(int cap)
    {
        capacity = cap;
        waterInsideBottle = 0;
        bottleID = num;
        num++;
    }

    //-----------Metody-----------------------------------------
    public void getCapacity()
    {
        System.out.println("Akcja butelka ID: " + bottleID);
        System.out.println("Ta butelka ma " + capacity + " ml pojemności.");
        System.out.println();
    }

    /**
     * Metoda sprawdzająca ile wody znajduje się w butelce
     */
    public void getWaterAmount()
    {
        System.out.println("Akcja butelka ID: " + bottleID);
        System.out.println("Ta butelka zawiera " + waterInsideBottle + " ml wody.");
        System.out.println();
    }

    private boolean checkInput(int x)
    {
        if (x > 0)
            return true;
        else
        {
            System.out.println("Podao wartość ujemną! Popraw wprowadzone dane!");
            return false;
        }
    }


    public void pourWaterIn(int waterAmount)
    {
        System.out.println("Akcja butelka ID: " + bottleID);
        if (checkInput(waterAmount))
        {
            if ((capacity - waterInsideBottle) >= waterAmount)
            {
                System.out.println("Woda została nalana do butelki.");
                waterInsideBottle += waterAmount;
                if (waterInsideBottle == capacity)
                    System.out.println("Butelka jest pełna!");
                else
                    System.out.println("W butelce pozostało " + (capacity - waterInsideBottle) + " ml miejsca.");
            }
            else
                System.out.println("Za mało pojemności w butelce, wybierz inną wartość!");
        }
    }

    public void pourWaterOut(int waterAmount)
    {
        System.out.println("Akcja butelka ID: " + bottleID);
        if (checkInput(waterAmount))
        {
            if (waterInsideBottle > 0)
            {
                if (waterInsideBottle >= waterAmount)
                {
                    waterInsideBottle -= waterAmount;
                    System.out.println("Woda została wylana.");
                    if (waterInsideBottle == capacity)
                        System.out.println("Butelka jest pusta!");
                    else
                        System.out.println("W butlece pozostało " + waterInsideBottle + " ml wody.");
                }
                else
                    System.out.println("W butelce nie ma wystarczającej ilości wody!");
            }
            else
                System.out.println("W butelce nie ma wody!");
        }
    }

    public void przelejDoInnejButelki(int waterAmount, Butelka butelka)
    {
        if(waterAmount <= this.waterInsideBottle)
        {
            int temp = 0;
            if(waterAmount <= (butelka.capacity - butelka.waterInsideBottle))
            {
                this.pourWaterOut(waterAmount);
                butelka.pourWaterIn(waterAmount);
            }
            else
            {
                temp = butelka.capacity - butelka.waterInsideBottle;
                System.out.println("W drugiej butelce nie ma wystarczającej ilości miejsca by przelać całość, więc przelano: " + temp);
                this.pourWaterOut(temp);
                butelka.pourWaterIn(temp);
            }
        }
    }

}


       /* for(int a = 0; a < 10; a++) {
            if (a % 2 == 0){
                System.out.print("  ");
            }
            if (a == 5){
                System.out.println("*  *  *  MARRY CHRISTMAS  *  *  *  ");
            }
            for (int i = 0; i < 10; i++) {
                System.out.print(" * ");
            }
            System.out.println();
        }*/

      /*  Scanner scanner = new Scanner(System.in);
        System.out.println("Input number from 1 to 5: ");
        int input = scanner.nextInt();
        System.out.println("Input before loop: " + input);

        while (input > 0){
            System.out.println("Number: " + input);
            input--;
        }
        System.out.println("Input after loop: " + input);
*/
       /* if (input > 5 || input < 1) {
            System.out.println("Value is incorrect!");
        } else {
            switch (input) {
                case 1 -> {
                    System.out.println("To za mało, tylko 1!");
                    System.out.println("To jest blok kodu");
                    System.out.println("To jest blok kodu");
                }
                case 2 -> System.out.println("To za mało, tylko 2!");
                case 3 -> System.out.println("To w sam raz!");
            }
        }*/
