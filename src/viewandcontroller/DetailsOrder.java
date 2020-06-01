package viewandcontroller;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DetailsOrder extends JFrame {
    private Container cp; //Main Panel
    private Integer id;
    private Connection cn;
    private Integer i=0;
    private ArrayList<String> listLabel = new ArrayList();

    public DetailsOrder(Connection cn, Integer id) throws SQLException {

        setSize(550,500);
        setTitle("Details Pizza");
        setLocationRelativeTo(null);
        setResizable(false);

        cp = this.getContentPane();
        cp.setLayout(null);
        this.cn=cn;
        this.id=id;
        buildPanel();
    }
    private void buildPanel() throws SQLException {
        Statement st = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        Statement st1 = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        Statement st2 = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        Statement st3 = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = st.executeQuery("SELECT Customer.name, Customer.surname, Customer.tel, Address.street, Address.nrHouse, Address.nrFlat, Address.locality, Address.postcode "+
                "FROM Orderr inner join Customer " +
                "ON Customer_idCustomer=idCustomer inner join Address " +
                "ON Address_idAddress=idAddress " +
                "WHERE idOrder="+id);
        ResultSet rs1 = st1.executeQuery("SELECT Sauce.name, Driver.name, Driver.surname, price_delivery, dateProduction "+
                "FROM Orderr inner join Sauce " +
                "ON Sauce_idSauce=idSauce inner join Driver " +
                "ON Driver_idDriver=idDriver " +
                "WHERE idOrder="+id);
        ResultSet rs2 = st2.executeQuery("SELECT Pizza.price, amount "+
                "FROM Order_has_Pizza inner join Pizza " +
                "ON Pizza_idPizza=idPizza " +
                "WHERE Orderr_idOrder="+id);
        ResultSet rs3 = st3.executeQuery("SELECT Pizza.name, amount "+
                "FROM Order_has_Pizza inner join Pizza " +
                "ON Pizza_idPizza=idPizza " +
                "WHERE Orderr_idOrder="+id);

        String name="",surname="", tel="",street="",nrHouse="", nrFlat="",locality="",postcode="";
        if(rs.next()) {
            name = rs.getString(1);
            surname = rs.getString(2);
            tel = rs.getString(3);
            street = rs.getString(4);
            nrHouse = rs.getString(5);
            nrFlat = rs.getString(6);
            locality = rs.getString(7);
            postcode = rs.getString(8);
        }
        String sauce="", drivername="",driversurname="",priced="", dateP="";
        if(rs1.next()) {
            sauce = rs1.getString(1);
            drivername = rs1.getString(2);
            driversurname = rs1.getString(3);
            priced = rs1.getString(4);
            dateP = rs1.getString(5);
        }
        Double fullprice=0.0;
        if(rs2.next()) {
            do{
                fullprice += rs2.getDouble(1)*rs2.getInt(2);
            }while (rs2.next());
        }

        JLabel lCustomer = new JLabel("Customer: ");
        JLabel ltel = new JLabel("Tel: ");
        JLabel lAddress = new JLabel("Address:");
        JLabel lLocality = new JLabel("Locality:");
        JLabel lDriver = new JLabel("Driver:");
        JLabel lpriced = new JLabel("Price Delivery:");
        JLabel lSauce = new JLabel("Sauce:");
        JLabel ldataP = new JLabel("Data Production: ");
        JLabel lfullprice = new JLabel("Full Price:");
        JLabel lpizza = new JLabel("Pizza:");

        lCustomer.setBounds(20,20,100,30);
        lCustomer.setForeground(Color.BLACK);
        ltel.setBounds(20,70,100,30);
        ltel.setForeground(Color.BLACK);
        lAddress.setBounds(20,120,100,30);
        lAddress.setForeground(Color.BLACK);
        lLocality.setBounds(20,170,100,30);
        lLocality.setForeground(Color.BLACK);
        lDriver.setBounds(20,220,100,30);
        lDriver.setForeground(Color.BLACK);
        lpriced.setBounds(20,270,100,30);
        lpriced.setForeground(Color.BLACK);
        lSauce.setBounds(20,320,100,30);
        lSauce.setForeground(Color.BLACK);
        ldataP.setBounds(20,370,100,30);
        ldataP.setForeground(Color.BLACK);
        lfullprice.setBounds(20,420,100,30);
        lfullprice.setForeground(Color.BLACK);
        lpizza.setBounds(300,20,100,30);
        lpizza.setForeground(Color.BLACK);

        JLabel lCustomer1 = new JLabel(name+" "+surname);
        JLabel lAddress1 = new JLabel();
        if(nrFlat==null){
            lAddress1.setText(street+" "+nrHouse);
        }else{
            lAddress1.setText(street+" "+nrHouse+"/"+nrFlat);
        }
        JLabel lLocality1 = new JLabel(locality+" "+postcode);
        JLabel ltel1 = new JLabel(tel);
        JLabel lDriver1 = new JLabel(drivername+" "+driversurname);
        JLabel lpriced1 = new JLabel(priced);
        JLabel lSauce1 = new JLabel(sauce);
        JLabel ldataP1 = new JLabel(dateP);
        JLabel lfullprice1 = new JLabel(fullprice.toString());

        lCustomer1.setBounds(120,20,120,30);
        lCustomer1.setForeground(Color.BLACK);
        ltel1.setBounds(120,70,120,30);
        ltel1.setForeground(Color.BLACK);
        lAddress1.setBounds(120,120,120,30);
        lAddress1.setForeground(Color.BLACK);
        lLocality1.setBounds(120,170,120,30);
        lLocality1.setForeground(Color.BLACK);
        lDriver1.setBounds(120,220,120,30);
        lDriver1.setForeground(Color.BLACK);
        lpriced1.setBounds(120,270,120,30);
        lpriced1.setForeground(Color.BLACK);
        lSauce1.setBounds(120,320,120,30);
        lSauce1.setForeground(Color.BLACK);
        ldataP1.setBounds(120,370,120,30);
        ldataP1.setForeground(Color.BLACK);
        lfullprice1.setBounds(120,420,120,30);
        lfullprice1.setForeground(Color.BLACK);


        int j=0;
        if(rs3.next()) {
            do{
                JLabel lpizza1 = new JLabel(rs3.getString(1)+" x"+rs3.getString(2));
                lpizza1.setBounds(300+j,70+i,100,20);
                cp.add(lpizza1);
                i += 30;
                if(i>200) j = 100;
            }while (rs3.next());
        }

        cp.add(lCustomer);
        cp.add(ltel);
        cp.add(lAddress);
        cp.add(lLocality);
        cp.add(lDriver);
        cp.add(lpriced);
        cp.add(lSauce);
        cp.add(ldataP);
        cp.add(lfullprice);
        cp.add(lpizza);

        cp.add(lCustomer1);
        cp.add(ltel1);
        cp.add(lAddress1);
        cp.add(lLocality1);
        cp.add(lDriver1);
        cp.add(lpriced1);
        cp.add(lSauce1);
        cp.add(ldataP1);
        cp.add(lfullprice1);

        validate();
    }
}