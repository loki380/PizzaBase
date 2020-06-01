package models;

public class Address {
    private String street="";
    private String nrHouse;
    private String nrFlat;
    private String locality;
    private String postcode;
    public Address(String street, String locality,String postcode, String nrHouse,String nrFlat){
        this.street=street;
        this.locality=locality;
        this.postcode=postcode;
        this.nrHouse=nrHouse;
        this.nrFlat=nrFlat;
    }
    public Address(){

    }
    public String getStreet(){return this.street;}
    public String getLocality(){return this.locality;}
    public String getPostcode(){return this.postcode;}
    public String getNrHouse(){return this.nrHouse;}
    public String getNrFlat(){return this.nrFlat;}
    public void setStreet(String street){this.street=street;}
    public void setLocality(String locality){this.locality=locality;}
    public void setPostcode(String postcode){this.postcode=postcode;}
    public void setNrHouse(String nrHouse){this.nrHouse=nrHouse;}
    public void setNrFlat(String nrFlat){this.nrFlat=nrFlat;}
    public boolean checkLocality(){
        if(locality.matches("[a-zA-ZęółśążźćńĘÓŁŚĄŻŹĆŃ]*") && locality.length()>0){
            return true;
        }else return false;
    }
    public boolean checkPostcode(){
        if(postcode.matches("[0-9][0-9]-[0-9][0-9][0-9]") && postcode.length()>0){
            return true;
        }else return false;
    }
    public boolean checkStreet(){
        if(street.matches("[a-zA-ZęółśążźćńĘÓŁŚĄŻŹĆŃ]*")){
            return true;
        }else return false;
    }
    public boolean checkNrHouse(){
        if(nrHouse.matches("[0-9]*") && nrHouse.length()>0){
            return true;
        }else return false;
    }
    public boolean checkNrFlat(){
        if(nrFlat.matches("[0-9]*")){
            return true;
        }else return false;
    }
}
