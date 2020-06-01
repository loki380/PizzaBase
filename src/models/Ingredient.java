package models;

public class Ingredient {
    private String name;
    private String mass;
    private String price;
    private Integer idSupp;
    public Ingredient(String name, String mass, String price, Integer idSupp){
        this.name=name;
        this.mass=mass;
        this.price=price;
        this.idSupp=idSupp;
    }
    public Ingredient(){
    }
    public String getName(){return this.name;}
    public String getMass(){return this.mass;}
    public String getPrice(){return this.price;}
    public Integer getIdSupp(){return this.idSupp;}
    public void setName(String name){this.name=name;}
    public void setMass(String mass){this.mass=mass;}
    public void setPrice(String price){this.price=price;}
    public void setIdSupp(Integer id){this.idSupp=id;}

    public boolean checkName(){
        if(name.matches("[a-zA-ZęółśążźćńĘÓŁŚĄŻŹĆŃ]*") && name.length()>0){
            return true;
        }else return false;
    }
    public boolean checkMass(){
        if(mass.matches("[0-9]{1,3}((\\.[0-9]{1,2})|)") && mass.length()>0){
            return true;
        }else return false;
    }
    public boolean checkPrice(){
        if(price.matches("[0-9]{1,2}((\\.[0-9]{1,2})|)") && price.length()>0){
            return true;
        }else return false;
    }

}
