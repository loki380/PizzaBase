package viewandcontroller;

import models.Size;

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

public class EditSize extends JFrame implements ActionListener, KeyListener {
    private Container cp; //Main Panel
    private Connection cn;
    private JButton bEdit;
    private JComboBox cbName;
    private JTextField tfDiameter;
    private ArrayList<Integer> idName = new ArrayList();
    private Integer id;
    Size size = new Size();

    public EditSize(Connection cn, Integer id) throws SQLException {

        setSize(300,300);
        setTitle("New Size");
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
        ResultSet rs = st.executeQuery("SELECT name, diameter FROM Size WHERE idSize="+id);

        JLabel lname = new JLabel("Name:");
        JLabel ldiameter = new JLabel("Diameter:");
        lname.setBounds(20,20,100,30);
        ldiameter.setBounds(20,70,100,30);

        cbName = new JComboBox();
        cbName.setBounds(120,20,150,30);
        tfDiameter = new JTextField();
        tfDiameter.setBounds(120,70,150,30);
        tfDiameter.addKeyListener(this);
        if(rs.next()){
            cbName.addItem(rs.getString(1));
            tfDiameter.setText(rs.getString(2));
        }
        cbName.addItem("Large");
        cbName.addItem("Medium");
        cbName.addItem("Small");
        cbName.addItem("Extra Large");
        size.setDiameter(tfDiameter.getText());
        size.setName((String) cbName.getSelectedItem());

        bEdit = new JButton("Edit");
        bEdit.setBounds(100,200,100,30);
        bEdit.addActionListener(this);

        cp.add(lname);
        cp.add(ldiameter);
        cp.add(cbName);
        cp.add(tfDiameter);
        cp.add(bEdit);
    }
    private void update() throws SQLException {
        Statement st = cn.createStatement();
        String name = (String) cbName.getSelectedItem();
        size = new Size(name,tfDiameter.getText());
        st.executeUpdate("UPDATE Size SET " +
                "name='"+size.getName()+
                "', diameter="+size.getDiameter()+" WHERE idSize="+id);
        dispose();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object z = e.getSource();
        if(z==bEdit){
            try {
                boolean correct = size.checkDiameter();
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
        if(z==tfDiameter){
            size.setDiameter(tfDiameter.getText());
            if(size.checkDiameter()) tfDiameter.setBackground(Color.GREEN);
            else tfDiameter.setBackground(Color.RED);
        }
    }
}
