package Potwory;

public class Skeleton extends Monster
{
    public Skeleton()
    {
        System.out.println("Kreator domyślny klay Skeleton");
    }

    public void strzelaj()
    {
        System.out.println("Strzelam!!!");
    }

    @Override
    public String toString() {
        return getClass() + " To jest Szkielet";
    }

    @Override
    public void doubleHp()
    {

    }

    @Override
    public void enchanceAttack()
    {
        System.out.println("Atak wzmocniony interfejsem");
    }


}
