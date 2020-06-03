package viewandcontroller;

import models.Driver;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class DetailsDriver extends JFrame {
    private Container cp; //Main Panel
    private Integer id;
    private Connection cn;
    public DetailsDriver(Connection cn, Integer id) throws SQLException {

        setSize(300,250);
        setTitle("Details Driver");
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
        ResultSet rs = st.executeQuery("SELECT name, surname,pesel " +
                "FROM Driver "+
                "WHERE idDriver="+id);

        JLabel lname = new JLabel("Name:");
        JLabel lsurname = new JLabel("Surname:");
        JLabel lpesel = new JLabel("Pesel:");
        lname.setBounds(20,20,100,30);
        lsurname.setBounds(20,70,100,30);
        lpesel.setBounds(20,120,100,30);

        Driver driver = new Driver();
        if(rs.next()){
            driver.setName(rs.getString(1));
            driver.setSurname(rs.getString(2));
            driver.setPesel(rs.getString(3));
        }

        JLabel lname1 = new JLabel(driver.getName());
        JLabel lsurname1 = new JLabel(driver.getSurname());
        JLabel lpesel1 = new JLabel(driver.getPesel());
        lname1.setBounds(120,20,100,30);
        lsurname1.setBounds(120,70,100,30);
        lpesel1.setBounds(120,120,100,30);

        cp.add(lname);
        cp.add(lsurname);
        cp.add(lpesel);
        cp.add(lname1);
        cp.add(lsurname1);
        cp.add(lpesel1);
    }
}
