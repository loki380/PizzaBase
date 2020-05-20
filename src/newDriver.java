import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class newDriver extends JFrame implements ActionListener{
    private Container cp; //Main Panel
    private Connection cn;
    private JButton bAdd;
    private JTextField tfName;
    private JTextField tfSurname;
    private JTextField tfPesel;

    public newDriver(Connection cn) {

        setSize(300,300);
        setTitle("New Driver");
        setLocationRelativeTo(null);
        setResizable(false);

        cp = this.getContentPane();
        cp.setLayout(null);
        buildPanel();
        this.cn=cn;
    }

    private void buildPanel(){
        JLabel lname = new JLabel("Name:");
        JLabel lsurname = new JLabel("Surname:");
        JLabel lpesel = new JLabel("Pesel:");
        lname.setBounds(20,20,100,30);
        lsurname.setBounds(20,70,100,30);
        lpesel.setBounds(20,120,100,30);

        bAdd = new JButton("Add");
        bAdd.setBounds(100,200,100,30);
        bAdd.addActionListener(this);

        tfName = new JTextField();
        tfName.setBounds(120,20,150,30);
        tfSurname = new JTextField();
        tfSurname.setBounds(120,70,150,30);
        tfPesel = new JTextField();
        tfPesel.setBounds(120,120,150,30);

        cp.add(lname);
        cp.add(lsurname);
        cp.add(lpesel);
        cp.add(bAdd);
        cp.add(tfName);
        cp.add(tfSurname);
        cp.add(tfPesel);
    }
    private void addDriver() throws SQLException {
        Statement st = cn.createStatement();
        String name = tfName.getText();
        String surname = tfSurname.getText();
        String pesel = tfPesel.getText();
        if(name.length()>0){
            if(surname.length()>0){
                if(pesel.length()==11){
                    st.executeUpdate("INSERT INTO Driver(name,surname,pesel) VALUES" +
                            "('"+tfName.getText()+
                            "', '"+tfSurname.getText()+
                            "', '"+tfPesel.getText()+"')");
                    dispose();
                }else{
                    JOptionPane.showMessageDialog(null,"Pesel must consist of 11 figure", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(null,"Surname must consist of more than one character", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null,"Name must consist of more than one character", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object z = e.getSource();
        if(z==bAdd){
            try {
                addDriver();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
