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

public class editSupplier extends JFrame implements ActionListener, KeyListener {
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

    public editSupplier(Connection cn, Integer id) throws SQLException {

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
        ResultSet rs = st.executeQuery("SELECT name, locality, postcode, street, nrHouse FROM Supplier inner join Address ON Address_idAddress=idAddress WHERE idSupplier="+id);

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
        if(rs.next()) {
            tfName.setText(rs.getString(1));
            tfLocality.setText(rs.getString(2));
            tfPostcode.setText(rs.getString(3));
            tfStreet.setText(rs.getString(4));
            tfNr.setText(rs.getString(5));
        }

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
        String name = tfName.getText();
        String locality = tfLocality.getText();
        String postcode = tfPostcode.getText();
        String street = tfStreet.getText();
        String nr = tfNr.getText();
        String flat = tfNrFlat.getText();
        st.executeUpdate("UPDATE Address SET " +
                "locality='"+locality+
                "', postcode='"+postcode+
                "', street='"+street+
                "', nrHouse="+nr+
                " WHERE idAddress=(SELECT Address_idAddress FROM Supplier WHERE idSupplier="+id+")");
        if(flat.equals("")){
            if(street.equals("")){
                st.executeUpdate("UPDATE Address SET " +
                        "locality='"+locality+
                        "', postcode='"+postcode+
                        "', nrHouse="+nr+
                        " WHERE idAddress=(SELECT Address_idAddress FROM Supplier WHERE idSupplier="+id+")");
            }else{
                st.executeUpdate("UPDATE Address SET " +
                        "locality='"+locality+
                        "', postcode='"+postcode+
                        "', street='"+street+
                        "', nrHouse="+nr+
                        " WHERE idAddress=(SELECT Address_idAddress FROM Supplier WHERE idSupplier="+id+")");
            }
        }else {
            if(street.equals("")){
                st.executeUpdate("UPDATE Address SET " +
                        "locality='"+locality+
                        "', postcode='"+postcode+
                        "', nrHouse="+nr+
                        ", nrFlat="+flat+
                        " WHERE idAddress=(SELECT Address_idAddress FROM Supplier WHERE idSupplier="+id+")");
            }else{
                st.executeUpdate("UPDATE Address SET " +
                        "locality='"+locality+
                        "', postcode='"+postcode+
                        "', street='"+street+
                        "', nrHouse="+nr+
                        ", nrFlat="+flat+
                        " WHERE idAddress=(SELECT Address_idAddress FROM Supplier WHERE idSupplier="+id+")");
            }
        }
        st.executeUpdate("UPDATE Supplier SET "+
                "name='"+name+"' WHERE idSupplier="+id);
        dispose();
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
        if(z==bEdit){
            try {
                boolean correct = checkName() && checkLocality() && checkPostcode() && checkStreet() && checkNrHouse();
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