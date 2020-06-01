package models;

public class Size {
    private String name="";
    private String diameter="";
    public Size(String name, String diameter){
        this.name=name;
        this.diameter=diameter;
    }
    public Size(){
    }
    public String getName(){return this.name;}
    public String getDiameter(){return this.diameter;}
    public void setName(String name){this.name=name;}
    public void setDiameter(String diameter){this.diameter=diameter;}
    public boolean checkName(){
        if((name.equals("Large") || name.equals("Medium") || name.equals("Small") || name.equals("Extra Large"))){
            return true;
        }else return false;
    }
    public boolean checkDiameter(){
        if(diameter.matches("[0-9]{1,2}((\\.[0-9]?[0-9]?)|)") && diameter.length()>0){
            return true;
        }else return false;
    }
}
