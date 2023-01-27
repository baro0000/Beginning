package Potwory;

public abstract class Monster extends Object implements Buff
{
    public double hp;
    public int buffUp = 0;
    public double movementSpeed;

    public Monster()
    {
        System.out.println("Kreator domyślny klay Monster");
    }
    
    public Monster(double movementSpeed, double hp)
    {
        this.hp = hp;
        this.movementSpeed = movementSpeed;
        System.out.println("Kreator NIEdomyślny klay Monster");
    }

    public void attack()
    {
        if(buffUp == 1){
            enchanceAttack();
        }
        else{
            System.out.println("Zwykły atak");
        }
    }



}
