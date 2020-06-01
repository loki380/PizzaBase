package viewandcontroller;

import models.Category;

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

public class EditCategory extends JFrame implements ActionListener, KeyListener {
    private Container cp; //Main Panel
    private Connection cn;
    private JButton bEdit;
    private JTextField tfName;
    private Integer id;
    Category category = new Category();

    public EditCategory(Connection cn, Integer id) throws SQLException {

        setSize(300,200);
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
        ResultSet rs = st.executeQuery("SELECT name FROM Category WHERE idCategory="+id);
        JLabel lname = new JLabel("Name:");
        lname.setBounds(20,20,100,30);

        if(rs.next()){
            category.setName(rs.getString(1));
        }

        tfName = new JTextField(category.getName());
        tfName.setBounds(120,20,150,30);
        tfName.addKeyListener(this);

        bEdit = new JButton("Edit");
        bEdit.setBounds(100,100,100,30);
        bEdit.addActionListener(this);

        cp.add(lname);
        cp.add(tfName);
        cp.add(bEdit);
    }
    private void update() throws SQLException {
        Statement st = cn.createStatement();
        category = new Category(tfName.getText());
        st.executeUpdate("UPDATE Category SET " +
                "name='"+category.getName()+"' WHERE idCategory="+id);
        dispose();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object z = e.getSource();
        if(z==bEdit){
            try {
                boolean correct = category.checkName();
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
            category.setName(tfName.getText());
            if(category.checkName()) tfName.setBackground(Color.GREEN);
            else tfName.setBackground(Color.RED);
        }
    }
}
