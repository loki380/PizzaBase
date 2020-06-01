package models;

public class Customer {
    private String name;
    private String surname;
    private String tel;
    private Integer idAdres;
    public Customer(String name, String surname, String tel, Integer idAdres){
        this.name=name;
        this.surname=surname;
        this.tel=tel;
        this.idAdres=idAdres;
    }
    public Customer(){
    }
    public String getName(){return this.name;}
    public String getSurname(){return this.surname;}
    public String getTel(){return this.tel;}
    public Integer getIdAdres(){return this.idAdres;}
    public void setName(String name){this.name=name;}
    public void setSurname(String surname){this.surname=surname;}
    public void setTel(String tel){this.tel=tel;}
    public void setIdAdres(Integer idAdres){this.idAdres=idAdres;}
    public boolean checkName(){
        if(name.matches("[a-zA-ZęółśążźćńĘÓŁŚĄŻŹĆŃ]*") && name.length()>0){
            return true;
        }else return false;
    }
    public boolean checkSurname(){
        if(surname.matches("[a-zA-ZęółśążźćńĘÓŁŚĄŻŹĆŃ]*") && surname.length()>0){
            return true;
        }else return false;
    }
    public boolean checkTel(){
        if(tel.matches("[0-9]{11}") && tel.length()>0){
            return true;
        }else return false;
    }

}
