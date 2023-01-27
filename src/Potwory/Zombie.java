package Potwory;

public class Zombie extends Monster
{
    public Zombie()
    {
        System.out.println("Kreator domyślny klay Zombie");
    }

    public Zombie(double movementSpeed, double hp)
    {
        super(movementSpeed, hp);
        System.out.println("Konstruktor Nie domyślny klasy zombie");

    }

    @Override
    public void attack()
    {

    }

    @Override
    public String toString() {
        return getClass() + " To jest Zombiak";
    }

    @Override
    public void doubleHp()
    {

    }

    @Override
    public void enchanceAttack()
    {

    }
}
