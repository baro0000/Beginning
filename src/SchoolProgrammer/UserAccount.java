package SchoolProgrammer;

public class UserAccount {
    public String login;
    public String haslo;
    public int blocked = 0;

    UserAccount(String login, String haslo){
        this.login = login;
        this.haslo = haslo;
    }
    UserAccount(String login, String haslo, int blocked){
        this.login = login;
        this.haslo = haslo;
        this.blocked = blocked;
    }
    @Override
    public String toString(){
        return login + ";" + haslo + ";" + blocked;
    }
}
