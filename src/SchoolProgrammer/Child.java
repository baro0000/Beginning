package SchoolProgrammer;

import java.io.Serializable;

/**
 * @author Bartosz Kuliński
 * @version 1.0.0
 */

public class Child implements Serializable
{

    private String imie;
    private String nazwisko;
    private int wiek;
    private String adresZamieszkania;

    String grupa;

    public Child(String imie, String nazwisko, int wiek, String adresZamieszkania, String grupa){
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.wiek = wiek;
        this.adresZamieszkania = adresZamieszkania;
        this.grupa = grupa;
    }

    //Gettery ----------------------------------------------------------------------------------------------------------
    public String getImie()
    {
        return imie;
    }

    public String getNazwisko()
    {
        return nazwisko;
    }

    public int getWiek()
    {
        return wiek;
    }

    public String getAdresZamieszkania()
    {
        return adresZamieszkania;
    }

    public String getGrupa()
    {
        return grupa;
    }

    // Settery ----------------------------------------------------------------------------------------------------------

    public void setImie(String imie)
    {
        this.imie = imie;
    }

    public void setNazwisko(String nazwisko)
    {
        this.nazwisko = nazwisko;
    }

    public void setWiek(int wiek)
    {
        this.wiek = wiek;
    }

    public void setAdresZamieszkania(String adresZamieszkania)
    {
        this.adresZamieszkania = adresZamieszkania;
    }

    public void setGrupa(String grupa)
    {
        this.grupa = grupa;
    }

    //Metody  ----------------------------------------------------------------------------------------------------------
    public void drukujDanePelne(){
        System.out.println("###################################################################################################");
        System.out.println("Dane z systemu:");
        System.out.println("Imię: " + this.imie);
        System.out.println("Nazwisko: " + this.nazwisko);
        System.out.println("Wiek: " + this.wiek);
        System.out.println("Adres zamieszkania: " + this.adresZamieszkania);
        System.out.println("Grupa: " + this.grupa);
    }

    public void drukujDaneUproszczone(){
        System.out.println("###################################################################################################");
        System.out.println("Imię: " + this.imie);
        System.out.println("Nazwisko: " + this.nazwisko);
        System.out.println("Grupa: " + this.grupa);
    }

}
