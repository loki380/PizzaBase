package models;

public class Pizza {
    private String name;
    private String description;
    private String price;
    private Integer idCat;
    private Integer idSize;
    public Pizza(String name, String description, String price, Integer idCat, Integer idSize){
        this.name=name;
        this.description=description;
        this.price=price;
        this.idCat=idCat;
        this.idSize=idSize;
    }
    public Pizza(){
    }
    public String getName(){return this.name;}
    public String getDescription(){return this.description;}
    public String getPrice(){return this.price;}
    public Integer getIdCat(){return this.idCat;}
    public Integer getIdSize(){return this.idSize;}
    public void setName(String name){this.name=name;}
    public void setDescription(String description){this.description=description;}
    public void setPrice(String price){this.price=price;}
    public void setIdCat(Integer id){this.idCat=id;}
    public void setIdSize(Integer id){this.idSize=id;}

    public boolean checkName(){
        if(name.matches("[a-zA-ZęółśążźćńĘÓŁŚĄŻŹĆŃ]*") && name.length()>0){
            return true;
        }else return false;
    }
    public boolean checkDes(){
        if(description.matches("[a-zA-Z0-9ęółśążźćńĘÓŁŚĄŻŹĆŃ ]*") && description.length()>0){
            return true;
        }else return false;
    }
    public boolean checkPrice(){
        if(price.matches("[0-9]{1,2}((\\.[0-9]{1,2})|)") && price.length()>0){
            return true;
        }else return false;
    }
}
