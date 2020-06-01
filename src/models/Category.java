package models;

public class Category {
    private String name="";
    public Category(String name){
        this.name=name;
    }
    public Category(){
    }
    public String getName(){return this.name;}
    public void setName(String name){this.name=name;}
    public boolean checkName(){
        if(name.matches("[a-zA-ZęółśążźćńĘÓŁŚĄŻŹĆŃ]*") && name.length()>0){
            return true;
        }else return false;
    }
}
