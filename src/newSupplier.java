import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class newSupplier extends JFrame implements ActionListener {
    private Container cp; //Main Panel
    private Connection cn;
    private JButton bAdd;
    private JTextField tfName;
    private JTextField tfLocality;
    private JTextField tfPostcode;
    private JTextField tfStreet;
    private JTextField tfNr;
    private ArrayList<Integer> idList = new ArrayList();

    public newSupplier(Connection cn) {

        setSize(300,350);
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
        lname.setBounds(20,20,100,30);
        llocality.setBounds(20,70,100,30);
        lpostcode.setBounds(20,120,100,30);
        lstreet.setBounds(20,170,100,30);
        lnr.setBounds(20,220,100,30);

        bAdd = new JButton("Add");
        bAdd.setBounds(100,270,100,30);
        bAdd.addActionListener(this);

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
        cp.add(bAdd);
    }
    private void addSupplier() throws SQLException {
        Statement st = cn.createStatement();
        String name = tfName.getText();
        String locality = tfLocality.getText();
        String postcode = tfPostcode.getText();
        String street = tfStreet.getText();
        String nr = tfNr.getText();
        st.executeUpdate("INSERT INTO Address(street,nrHouse,locality,postcode) VALUES" +
                "('"+street+
                "',"+nr+
                ", '"+locality+
                "', '"+postcode+"')");
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

    @Override
    public void actionPerformed(ActionEvent e) {
        Object z = e.getSource();
        if(z==bAdd){
            try {
                addSupplier();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}