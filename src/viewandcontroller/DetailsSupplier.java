package viewandcontroller;

import models.Address;
import models.Supplier;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DetailsSupplier extends JFrame {
    private Container cp; //Main Panel
    private Integer id;
    private Connection cn;

    public DetailsSupplier(Connection cn, Integer id) throws SQLException {

        setSize(300,400);
        setTitle("Details Supplier");
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
        ResultSet rs = st.executeQuery("SELECT name, locality,postcode,street,nrHouse,nrFlat " +
                "FROM Supplier inner join Address " +
                "ON Address_idAddress=idAddress " +
                "WHERE idSupplier="+id);

        JLabel lname = new JLabel("Name:");
        JLabel llocality = new JLabel("Locality:");
        JLabel lpostcode = new JLabel("Postcode:");
        JLabel lstreet = new JLabel("Street:");
        JLabel lnr = new JLabel("NrHouse:");
        JLabel lnrflat = new JLabel("NrFlat:");
        lname.setBounds(20,20,100,30);
        llocality.setBounds(20,70,100,30);
        lpostcode.setBounds(20,120,100,30);
        lstreet.setBounds(20,170,100,30);
        lnr.setBounds(20,220,100,30);
        lnrflat.setBounds(20,270,100,30);

        Supplier supp = new Supplier();
        Address adres = new Address();
        if(rs.next()){
            supp.setName(rs.getString(1));
            adres.setLocality(rs.getString(2));
            adres.setPostcode(rs.getString(3));
            adres.setStreet(rs.getString(4));
            adres.setNrHouse(rs.getString(5));
            adres.setNrFlat(rs.getString(6));
        }
        JLabel lname1 = new JLabel(supp.getName());
        JLabel llocality1 = new JLabel(adres.getLocality());
        JLabel lpostcode1 = new JLabel(adres.getPostcode());
        JLabel lstreet1 = new JLabel(adres.getStreet());
        JLabel lnr1 = new JLabel(adres.getNrHouse());
        JLabel lnrflat1 = new JLabel(adres.getNrFlat());
        lname1.setBounds(120,20,100,30);
        llocality1.setBounds(120,70,100,30);
        lpostcode1.setBounds(120,120,100,30);
        lstreet1.setBounds(120,170,100,30);
        lnr1.setBounds(120,220,100,30);
        lnrflat1.setBounds(120,270,100,30);

        cp.add(lname);
        cp.add(llocality);
        cp.add(lpostcode);
        cp.add(lstreet);
        cp.add(lnr);
        cp.add(lnrflat);
        cp.add(lname1);
        cp.add(llocality1);
        cp.add(lpostcode1);
        cp.add(lstreet1);
        cp.add(lnr1);
        cp.add(lnrflat1);
    }
}
