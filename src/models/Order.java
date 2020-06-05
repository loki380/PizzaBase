package models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Order {
    private Integer idCustomer;
    private Integer idDriver;
    private Integer idSauce;
    private String data;
    private String price;

    public Order(Integer idCustomer, Integer idDriver, Integer idSauce, String data, String price){
        this.idCustomer=idCustomer;
        this.idDriver=idDriver;
        this.idSauce=idSauce;
        this.data=data;
        this.price=price;
    }
    public Order(){
    }
    public String getData(){return this.data;}
    public String getPrice(){return this.price;}
    public Integer getIdCustomer(){return this.idCustomer;}
    public Integer getIdDriver(){return this.idDriver;}
    public Integer getIdSauce(){return this.idSauce;}
    public void setData(String data){this.data=data;}
    public void setPrice(String price){this.price=price;}
    public void setIdCustomer(Integer id){this.idCustomer=id;}
    public void setIdDriver(Integer id){this.idDriver=id;}
    public void setIdSauce(Integer id){this.idSauce=id;}
    public boolean checkPrice(){
        if(price.matches("[0-9]((\\.[0-9]{1,2})|)") && price.length()>0){
            return true;
        }else return false;
    }
    public boolean checkData() throws ParseException {
        Date nowDate = new Date();
        Date choseDate = new SimpleDateFormat("yyyy-MM-dd").parse(data);
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        if(choseDate.compareTo(nowDate)<0){
            return true;
        }else return false;
    }
}
