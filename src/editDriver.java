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

public class editDriver extends JFrame implements ActionListener, KeyListener {
    private Container cp; //Main Panel
    private Integer id;
    private Connection cn;
    private JButton bEdit;
    private JTextField tfName;
    private JTextField tfSurname;
    private JTextField tfPesel;

    public editDriver(Connection cn, Integer id) throws SQLException {

        setSize(300,300);
        setTitle("Edit Driver");
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
        ResultSet rs = st.executeQuery("SELECT name,surname,pesel FROM Driver WHERE idDriver="+id);

        JLabel lname = new JLabel("Name:");
        JLabel lsurname = new JLabel("Surname:");
        JLabel lpesel = new JLabel("Pesel:");
        lname.setBounds(20,20,100,30);
        lsurname.setBounds(20,70,100,30);
        lpesel.setBounds(20,120,100,30);

        tfName = new JTextField();
        tfName.setBounds(120,20,150,30);
        tfName.addKeyListener(this);
        tfSurname = new JTextField();
        tfSurname.setBounds(120,70,150,30);
        tfSurname.addKeyListener(this);
        tfPesel = new JTextField();
        tfPesel.setBounds(120,120,150,30);
        tfPesel.addKeyListener(this);
        if(rs.next()) {
            tfName.setText(rs.getString(1));
            tfSurname.setText(rs.getString(2));
            tfPesel.setText(rs.getString(3));
        }

        bEdit = new JButton("Edit");
        bEdit.setBounds(100,200,100,30);
        bEdit.addActionListener(this);

        cp.add(lname);
        cp.add(lsurname);
        cp.add(lpesel);
        cp.add(tfName);
        cp.add(tfSurname);
        cp.add(tfPesel);
        cp.add(bEdit);
    }
    private void update() throws SQLException {
        Statement st = cn.createStatement();
        String name = tfName.getText();
        String surname = tfSurname.getText();
        String pesel = tfPesel.getText();
        if(name.length()>0){
            if(surname.length()>0){
                if(pesel.length()==11){
                    st.executeUpdate("UPDATE Driver SET " +
                            "name='"+name+
                            "', surname='"+surname+
                            "', pesel='"+pesel+
                            "' WHERE idDriver="+id);
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
    private boolean checkName(){
        String name = tfName.getText();
        if(name.matches("[A-Za-z]*") && name.length()>0){
            return true;
        }else return false;
    }
    private boolean checkSurName(){
        String name = tfSurname.getText();
        if(name.matches("[A-Za-z]*") && name.length()>0){
            return true;
        }else return false;
    }
    private boolean checkPesel(){
        String pesel = tfPesel.getText();
        if(pesel.matches("[0-9]{11}") && pesel.length()>0){
            return true;
        }else return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object z = e.getSource();
        if(z==bEdit){
            try {
                 boolean correct = checkName() && checkPesel() && checkSurName();
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
        }else if(z==tfSurname){
            if(checkSurName()) tfSurname.setBackground(Color.GREEN);
            else tfSurname.setBackground(Color.RED);
        }else if(z==tfPesel){
            if(checkPesel()) tfPesel.setBackground(Color.GREEN);
            else tfPesel.setBackground(Color.RED);
        }
    }
}
