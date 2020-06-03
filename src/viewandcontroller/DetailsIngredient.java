package viewandcontroller;

import models.Driver;
import models.Ingredient;
import models.Supplier;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DetailsIngredient extends JFrame {
    private Container cp; //Main Panel
    private Integer id;
    private Connection cn;
    public DetailsIngredient(Connection cn, Integer id) throws SQLException {

        setSize(300,250);
        setTitle("Details Ingredient");
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
        ResultSet rs = st.executeQuery("SELECT Ingredient.name, mass,price, Supplier.name " +
                "FROM Ingredient inner join Supplier " +
                "ON Supplier_idSupplier=idSupplier "+
                "WHERE idIngredient="+id);

        JLabel lname = new JLabel("Name:");
        JLabel lweight = new JLabel("Weight:");
        JLabel lprice = new JLabel("Price:");
        JLabel lsupplier = new JLabel("Supplier:");
        lname.setBounds(20,20,100,30);
        lweight.setBounds(20,70,100,30);
        lprice.setBounds(20,120,100,30);
        lsupplier.setBounds(20,170,100,30);

        Ingredient ing = new Ingredient();
        Supplier supp = new Supplier();
        if(rs.next()){
            ing.setName(rs.getString(1));
            ing.setMass(rs.getString(2));
            ing.setPrice(rs.getString(3));
            supp.setName(rs.getString(4));
        }

        JLabel lname1 = new JLabel(ing.getName());
        JLabel lweight1 = new JLabel(ing.getMass());
        JLabel lprice1 = new JLabel(ing.getPrice());
        JLabel lsupplier1 = new JLabel(supp.getName());
        lname1.setBounds(120,20,100,30);
        lweight1.setBounds(120,70,100,30);
        lprice1.setBounds(120,120,100,30);
        lsupplier1.setBounds(120,170,100,30);

        cp.add(lname);
        cp.add(lweight);
        cp.add(lprice);
        cp.add(lsupplier);
        cp.add(lname1);
        cp.add(lweight1);
        cp.add(lprice1);
        cp.add(lsupplier1);
    }
}
