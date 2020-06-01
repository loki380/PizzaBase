package models;

public class Sauce {
    private String name="";
    private String price="";
    public Sauce(String name, String price){
        this.name=name;
        this.price=price;
    }
    public Sauce(){
    }
    public String getName(){return this.name;}
    public String getPrice(){return this.price;}
    public void setName(String name){this.name=name;}
    public void setPrice(String price){this.price=price;}
    public boolean checkName(){
        if(name.matches("[a-zA-ZęółśążźćńĘÓŁŚĄŻŹĆŃ]*") && name.length()>0){
            return true;
        }else return false;
    }
    public boolean checkPrice(){
        if(price.matches("[0-9]((\\.[0-9]{1,2})|)") && price.length()>0){
            return true;
        }else return false;
    }
}