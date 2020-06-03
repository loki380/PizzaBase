package viewandcontroller;

import models.Sauce;
import models.Size;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DetailsSauce extends JFrame{
    private Container cp; //Main Panel
    private Integer id;
    private Connection cn;
    public DetailsSauce(Connection cn, Integer id) throws SQLException {

        setSize(300,250);
        setTitle("Details Sauce");
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
        ResultSet rs = st.executeQuery("SELECT name,price " +
                "FROM Sauce "+
                "WHERE idSauce="+id);

        JLabel lname = new JLabel("Name:");
        JLabel lprice = new JLabel("Price:");
        lname.setBounds(20,20,100,30);
        lprice.setBounds(20,70,100,30);

        Sauce sauce = new Sauce();
        if(rs.next()){
            sauce.setName(rs.getString(1));
            sauce.setPrice(rs.getString(2));
        }

        JLabel lname1 = new JLabel(sauce.getName());
        JLabel lprice1 = new JLabel(sauce.getPrice());
        lname1.setBounds(120,20,100,30);
        lprice1.setBounds(120,70,100,30);

        cp.add(lname);
        cp.add(lprice);
        cp.add(lname1);
        cp.add(lprice1);
    }
}
