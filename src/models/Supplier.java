package models;

public class Supplier {
    private String name="";
    private Integer idadres;
    public Supplier(String name, Integer idadres){
        this.name=name;
        this.idadres=idadres;
    }
    public Supplier(){
    }
    public String getName(){return this.name;}
    public Integer getIdadres(){return this.idadres;}
    public void setName(String name){this.name=name;}
    public void setIdadres(Integer id){this.idadres=id;}
    public boolean checkName(){
        if(name.matches("[a-zA-ZęółśążźćńĘÓŁŚĄŻŹĆŃ]*") && name.length()>0){
            return true;
        }else return false;
    }
}
