package models;

public class Driver {
    private String name="";
    private String surname="";
    private String pesel="";
    public Driver(String name, String surname, String pesel){
        this.name=name;
        this.surname=surname;
        this.pesel=pesel;
    }
    public Driver(){
    }
    public String getName(){
        return this.name;
    }
    public String getSurname(){ return this.surname; }
    public String getPesel(){ return this.pesel; }
    public void setName(String name){
        this.name=name;
    }
    public void setSurname(String surname){
        this.surname=surname;
    }
    public void setPesel(String pesel){
        this.pesel=pesel;
    }
    public boolean checkName(){
        if(name.matches("[a-zA-ZęółśążźćńĘÓŁŚĄŻŹĆŃ]*") && name.length()>0){
            return true;
        }else return false;
    }
    public boolean checkSurName(){
        if(surname.matches("[a-zA-ZęółśążźćńĘÓŁŚĄŻŹĆŃ]*") && surname.length()>0){
            return true;
        }else return false;
    }
    public boolean checkPesel(){
        if(pesel.matches("[0-9]{11}") && pesel.length()>0){
            int suma=0;
            suma = Character.getNumericValue(pesel.charAt(0))*9+
                    Character.getNumericValue(pesel.charAt(1))*7+
                    Character.getNumericValue(pesel.charAt(2))*3+
                    Character.getNumericValue(pesel.charAt(3))*1+
                    Character.getNumericValue(pesel.charAt(4))*9+
                    Character.getNumericValue(pesel.charAt(5))*7+
                    Character.getNumericValue(pesel.charAt(6))*3+
                    Character.getNumericValue(pesel.charAt(7))*1+
                    Character.getNumericValue(pesel.charAt(8))*9+
                    Character.getNumericValue(pesel.charAt(9))*7;
            if(suma%10==Character.getNumericValue(pesel.charAt(10))) return true;
            else return false;
        }else return false;
    }
}
