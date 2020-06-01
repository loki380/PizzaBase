package viewandcontroller;

import models.Address;
import models.Supplier;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EditSupplier extends JFrame implements ActionListener, KeyListener {
    private Container cp; //Main Panel
    private Integer id;
    private Connection cn;
    private JButton bEdit;
    private JTextField tfName;
    private JTextField tfLocality;
    private JTextField tfPostcode;
    private JTextField tfStreet;
    private JTextField tfNr;
    private JTextField tfNrFlat;
    Supplier supp = new Supplier();
    Address adres = new Address();

    public EditSupplier(Connection cn, Integer id) throws SQLException {

        setSize(300,400);
        setTitle("Edit Supplier");
        setLocationRelativeTo(null);
        setResizable(false);

        cp = this.getContentPane();
        cp.setLayout(null);
        this.cn=cn;
        this.id=id;
        buildPanel();
    }
    private void buildPanel() throws SQLException {
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery("SELECT name, locality, postcode, street, nrHouse, nrFlat FROM Supplier inner join Address ON Address_idAddress=idAddress WHERE idSupplier="+id);

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

        if(rs.next()) {
            supp.setName(rs.getString(1));
            adres.setLocality(rs.getString(2));
            adres.setPostcode(rs.getString(3));
            adres.setStreet(rs.getString(4));
            adres.setNrHouse(rs.getString(5));
            adres.setNrFlat(rs.getString(6));
        }

        tfName = new JTextField(supp.getName());
        tfName.setBounds(120,20,150,30);
        tfName.addKeyListener(this);
        tfLocality = new JTextField(adres.getLocality());
        tfLocality.setBounds(120,70,150,30);
        tfLocality.addKeyListener(this);
        tfPostcode = new JTextField(adres.getPostcode());
        tfPostcode.setBounds(120,120,150,30);
        tfPostcode.addKeyListener(this);
        tfStreet = new JTextField(adres.getStreet());
        tfStreet.setBounds(120,170,150,30);
        tfStreet.addKeyListener(this);
        tfNr = new JTextField(adres.getNrHouse());
        tfNr.setBounds(120,220,150,30);
        tfNr.addKeyListener(this);
        tfNrFlat = new JTextField(adres.getNrFlat());
        tfNrFlat.setBounds(120,270,150,30);
        tfNrFlat.addKeyListener(this);

        bEdit = new JButton("Edit");
        bEdit.setBounds(100,320,100,30);
        bEdit.addActionListener(this);

        cp.add(lname);
        cp.add(llocality);
        cp.add(lpostcode);
        cp.add(lstreet);
        cp.add(lnr);
        cp.add(lnrflat);
        cp.add(tfName);
        cp.add(tfLocality);
        cp.add(tfPostcode);
        cp.add(tfStreet);
        cp.add(tfNr);
        cp.add(tfNrFlat);
        cp.add(bEdit);
    }
    private void update() throws SQLException {
        Statement st = cn.createStatement();
        supp.setName(tfName.getText());
        adres.setStreet(tfStreet.getText());
        adres.setLocality(tfLocality.getText());
        adres.setPostcode(tfPostcode.getText());
        adres.setNrHouse(tfNr.getText());
        adres.setNrFlat(tfNrFlat.getText());
        if(adres.getNrFlat().equals("")){
            if(adres.getStreet().equals("")){
                st.executeUpdate("UPDATE Address SET " +
                        "locality='"+adres.getLocality()+
                        "', postcode='"+adres.getPostcode()+
                        "', nrHouse="+adres.getNrHouse()+
                        " WHERE idAddress=(SELECT Address_idAddress FROM Supplier WHERE idSupplier="+id+")");
            }else{
                st.executeUpdate("UPDATE Address SET " +
                        "locality='"+adres.getLocality()+
                        "', postcode='"+adres.getPostcode()+
                        "', street='"+adres.getStreet()+
                        "', nrHouse="+adres.getNrHouse()+
                        " WHERE idAddress=(SELECT Address_idAddress FROM Supplier WHERE idSupplier="+id+")");
            }
        }else {
            if(adres.getStreet().equals("")){
                st.executeUpdate("UPDATE Address SET " +
                        "locality='"+adres.getLocality()+
                        "', postcode='"+adres.getPostcode()+
                        "', nrHouse="+adres.getNrHouse()+
                        ", nrFlat="+adres.getNrFlat()+
                        " WHERE idAddress=(SELECT Address_idAddress FROM Supplier WHERE idSupplier="+id+")");
            }else{
                st.executeUpdate("UPDATE Address SET " +
                        "locality='"+adres.getLocality()+
                        "', postcode='"+adres.getPostcode()+
                        "', street='"+adres.getStreet()+
                        "', nrHouse="+adres.getNrHouse()+
                        ", nrFlat="+adres.getNrFlat()+
                        " WHERE idAddress=(SELECT Address_idAddress FROM Supplier WHERE idSupplier="+id+")");
            }
        }
        st.executeUpdate("UPDATE Supplier SET "+
                "name='"+supp.getName()+"' WHERE idSupplier="+id);
        dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object z = e.getSource();
        if(z==bEdit){
            try {
                boolean correct = supp.checkName() && adres.checkLocality() && adres.checkPostcode() && adres.checkStreet() && adres.checkNrHouse() && adres.checkNrFlat();
                if(correct) update();
                else JOptionPane.showMessageDialog(null,"Oops, you enter wrong values", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        Object z = keyEvent.getSource();
        if(z==tfName){
            supp.setName(tfName.getText());
            if(supp.checkName()) tfName.setBackground(Color.GREEN);
            else tfName.setBackground(Color.RED);
        }else if(z==tfLocality){
            adres.setLocality(tfLocality.getText());
            if(adres.checkLocality()) tfLocality.setBackground(Color.GREEN);
            else tfLocality.setBackground(Color.RED);
        }else if(z==tfPostcode){
            adres.setPostcode(tfPostcode.getText());
            if(adres.checkPostcode()) tfPostcode.setBackground(Color.GREEN);
            else tfPostcode.setBackground(Color.RED);
        }else if(z==tfStreet){
            adres.setStreet(tfStreet.getText());
            if(adres.checkStreet()) tfStreet.setBackground(Color.GREEN);
            else tfStreet.setBackground(Color.RED);
        }else if(z==tfNr){
            adres.setNrHouse(tfNr.getText());
            if(adres.checkNrHouse()) tfNr.setBackground(Color.GREEN);
            else tfNr.setBackground(Color.RED);
        }else if(z==tfNrFlat){
            adres.setNrFlat(tfNrFlat.getText());
            if(adres.checkNrFlat()) tfNrFlat.setBackground(Color.GREEN);
            else tfNrFlat.setBackground(Color.RED);
        }
    }
}