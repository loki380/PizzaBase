import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class editSupplier extends JFrame implements ActionListener {
    private Container cp; //Main Panel
    private Integer id;
    private Connection cn;
    private JButton bEdit;
    private JTextField tfName;
    private JTextField tfLocality;
    private JTextField tfPostcode;
    private JTextField tfStreet;
    private JTextField tfNr;

    public editSupplier(Connection cn, Integer id) throws SQLException {

        setSize(300,350);
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
        lname.setBounds(20,20,100,30);
        llocality.setBounds(20,70,100,30);
        lpostcode.setBounds(20,120,100,30);
        lstreet.setBounds(20,170,100,30);
        lnr.setBounds(20,220,100,30);

        tfName = new JTextField();
        tfName.setBounds(120,20,150,30);
        tfLocality = new JTextField();
        tfLocality.setBounds(120,70,150,30);
        tfPostcode = new JTextField();
        tfPostcode.setBounds(120,120,150,30);
        tfStreet = new JTextField();
        tfStreet.setBounds(120,170,150,30);
        tfNr = new JTextField();
        tfNr.setBounds(120,220,150,30);
        if(rs.next()) {
            tfName.setText(rs.getString(1));
            tfLocality.setText(rs.getString(2));
            tfPostcode.setText(rs.getString(3));
            tfStreet.setText(rs.getString(4));
            tfNr.setText(rs.getString(5));
        }

        bEdit = new JButton("Edit");
        bEdit.setBounds(100,270,100,30);
        bEdit.addActionListener(this);

        cp.add(lname);
        cp.add(llocality);
        cp.add(lpostcode);
        cp.add(lstreet);
        cp.add(lnr);
        cp.add(tfName);
        cp.add(tfLocality);
        cp.add(tfPostcode);
        cp.add(tfStreet);
        cp.add(tfNr);
        cp.add(bEdit);
    }
    private void update() throws SQLException {
        Statement st = cn.createStatement();
        String name = tfName.getText();
        String locality = tfLocality.getText();
        String postcode = tfPostcode.getText();
        String street = tfStreet.getText();
        String nr = tfNr.getText();
        st.executeUpdate("UPDATE Address SET " +
                "locality='"+locality+
                "', postcode='"+postcode+
                "', street='"+street+
                "', nrHouse="+nr+
                " WHERE idAddress=(SELECT Address_idAddress FROM Supplier WHERE idSupplier="+id+")");
        st.executeUpdate("UPDATE Supplier SET "+
                "name='"+name+"' WHERE idSupplier="+id);
        dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object z = e.getSource();
        if(z==bEdit){
            try {
                update();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }
}