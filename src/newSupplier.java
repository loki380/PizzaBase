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
import java.util.ArrayList;

public class newSupplier extends JFrame implements ActionListener, KeyListener {
    private Container cp; //Main Panel
    private Connection cn;
    private JButton bAdd;
    private JTextField tfName;
    private JTextField tfLocality;
    private JTextField tfPostcode;
    private JTextField tfStreet;
    private JTextField tfNr;
    private JTextField tfNrFlat;
    private ArrayList<Integer> idList = new ArrayList();

    public newSupplier(Connection cn) {

        setSize(300,400);
        setTitle("New Supplier");
        setLocationRelativeTo(null);
        setResizable(false);

        cp = this.getContentPane();
        cp.setLayout(null);
        buildPanel();
        this.cn=cn;
    }

    private void buildPanel(){
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

        bAdd = new JButton("Add");
        bAdd.setBounds(100,320,100,30);
        bAdd.addActionListener(this);

        tfName = new JTextField();
        tfName.setBounds(120,20,150,30);
        tfName.addKeyListener(this);
        tfLocality = new JTextField();
        tfLocality.setBounds(120,70,150,30);
        tfLocality.addKeyListener(this);
        tfPostcode = new JTextField();
        tfPostcode.setBounds(120,120,150,30);
        tfPostcode.addKeyListener(this);
        tfStreet = new JTextField();
        tfStreet.setBounds(120,170,150,30);
        tfStreet.addKeyListener(this);
        tfNr = new JTextField();
        tfNr.setBounds(120,220,150,30);
        tfNr.addKeyListener(this);
        tfNrFlat = new JTextField();
        tfNrFlat.setBounds(120,270,150,30);
        tfNrFlat.addKeyListener(this);

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
        cp.add(bAdd);
    }
    private void addSupplier() throws SQLException {
        Statement st = cn.createStatement();
        String name = tfName.getText();
        String locality = tfLocality.getText();
        String postcode = tfPostcode.getText();
        String street = tfStreet.getText();
        String nr = tfNr.getText();
        String flat = tfNrFlat.getText();
        if(flat.equals("")){
            if(street.equals("")){
                st.executeUpdate("INSERT INTO Address(nrHouse,locality,postcode) VALUES" +
                        "(" + nr +
                        ", '" + locality +
                        "', '" + postcode + "')");
            }else{
                st.executeUpdate("INSERT INTO Address(street,nrHouse,locality,postcode) VALUES" +
                        "('" + street +
                        "'," + nr +
                        ", '" + locality +
                        "', '" + postcode + "')");
            }
        }else {
            if(street.equals("")){
                st.executeUpdate("INSERT INTO Address(nrHouse,nrFlat,locality,postcode) VALUES" +
                        "(" + nr +
                        ", "+flat+
                        ", '" + locality +
                        "', '" + postcode + "')");
            }else{
                st.executeUpdate("INSERT INTO Address(street,nrHouse,nrFlat,locality,postcode) VALUES" +
                        "('" + street +
                        "'," + nr +
                        "," + flat +
                        ", '" + locality +
                        "', '" + postcode + "')");
            }
        }
        storeid("SELECT idAddress FROM Address ORDER BY idAddress");
        int id=0;
        for(int c : idList){
            if(c>id) id=c;
        }
        st.executeUpdate("INSERT INTO Supplier(Address_idAddress, name) VALUES" +
                "("+id+
                ", '"+name+"')");
        dispose();
    }
    public void storeid(String query) throws SQLException {
        idList.clear();
        Statement statement = cn.createStatement();
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            idList.add(rs.getInt(1));
        }
    }
    private boolean checkName(){
        String name = tfName.getText();
        if(name.matches("[a-zA-ZęółśążźćńĘÓŁŚĄŻŹĆŃ]*") && name.length()>0){
            return true;
        }else return false;
    }
    private boolean checkLocality(){
        String name = tfLocality.getText();
        if(name.matches("[a-zA-ZęółśążźćńĘÓŁŚĄŻŹĆŃ]*") && name.length()>0){
            return true;
        }else return false;
    }
    private boolean checkPostcode(){
        String name = tfPostcode.getText();
        if(name.matches("[0-9][0-9]-[0-9][0-9][0-9]") && name.length()>0){
            return true;
        }else return false;
    }
    private boolean checkStreet(){
        String name = tfStreet.getText();
        if(name.matches("[a-zA-ZęółśążźćńĘÓŁŚĄŻŹĆŃ]*")){
            return true;
        }else return false;
    }
    private boolean checkNrHouse(){
        String name = tfNr.getText();
        if(name.matches("[0-9]*") && name.length()>0){
            return true;
        }else return false;
    }
    private boolean checkNrFlat(){
        String name = tfNrFlat.getText();
        if(name.matches("[0-9]*")){
            return true;
        }else return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object z = e.getSource();
        if(z==bAdd){
            try {
                boolean correct = checkName() && checkLocality() && checkPostcode() && checkStreet() && checkNrHouse();
                if(correct) addSupplier();
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
            if(checkName()) tfName.setBackground(Color.GREEN);
            else tfName.setBackground(Color.RED);
        }else if(z==tfLocality){
            if(checkLocality()) tfLocality.setBackground(Color.GREEN);
            else tfLocality.setBackground(Color.RED);
        }else if(z==tfPostcode){
            if(checkPostcode()) tfPostcode.setBackground(Color.GREEN);
            else tfPostcode.setBackground(Color.RED);
        }else if(z==tfStreet){
            if(checkStreet()) tfStreet.setBackground(Color.GREEN);
            else tfStreet.setBackground(Color.RED);
        }else if(z==tfNr){
            if(checkNrHouse()) tfNr.setBackground(Color.GREEN);
            else tfNr.setBackground(Color.RED);
        }else if(z==tfNrFlat){
            if(checkNrFlat()) tfNrFlat.setBackground(Color.GREEN);
            else tfNrFlat.setBackground(Color.RED);
        }
    }
}