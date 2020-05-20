import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class Window extends JFrame implements ActionListener {

    private Container cp;
    private Connection cn;
    private BackgroundPanel bg;
    private BufferedImage image;
    private JPanel pLogin;
    private JPanel pRight;
    private JPanel pLeft;
    private JTextField nameField;
    private JPasswordField passField;
    private JTable table;
    private JScrollPane scrollPane;
    private JButton bLogin;
    private JButton bOrder;
    private JButton bMenu;
    private JButton bDrivers;
    private JButton bIngredient;
    private JButton bNewOrder;
    private JButton bDetails;
    private JButton bBack;
    private JButton bExit;
    private JButton bPizza;
    private JButton bSauce;
    private JButton bCategory;
    private JButton bNewDriver;
    private JButton bEditDriver;
    private JButton bDeleteDriver;
    private ArrayList idList = new ArrayList();

    public Window() {

        setSize(925,600);
        setTitle("Application - Pizzeria Base");
        setLocationRelativeTo(null);
        setResizable(false);

        cp = this.getContentPane();
        setBG();
        addLoginPanel();

    }
    private void setBG(){
        cp.removeAll();
        try {
            image = ImageIO.read(new File("pizza1.jpg"));
        }catch(IOException ex){
            ex.printStackTrace();
        }
        ImageIcon BGIcon=new ImageIcon(image);

        bg = new BackgroundPanel(BGIcon);
        cp.add(bg);
        bg.setLayout(null);
        validate();
    }
    private void addLoginPanel(){
        JLabel name = new JLabel("Name: ");
        JLabel password = new JLabel("Password: ");
        pLogin=new JPanel();
        pLogin.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        pLogin.setBounds(500,150,300,200);
        pLogin.setLayout(null);
        bg.add(pLogin);

        nameField = new JTextField();
        nameField.setBounds(100,50,150,20);
        passField = new JPasswordField();
        passField.setBounds(100,90,150,20);
        name.setBounds(30,50,100,20);
        name.setForeground(Color.WHITE);
        password.setBounds(30,90,100,20);
        password.setForeground(Color.WHITE);
        bLogin = new JButton("Login");
        bLogin.setBounds(100,150,100,20);
        bLogin.addActionListener(this);

        pLogin.add(name);
        pLogin.add(nameField);
        pLogin.add(password);
        pLogin.add(passField);
        pLogin.add(bLogin);


    }
    private void setRightPanel(){
        pRight = new JPanel();
        pRight.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        pRight.setBounds(550,50,300,450);
        pRight.setLayout(null);

        bOrder = new JButton("Orders");
        bOrder.setBounds(30,30,240,40);
        bOrder.setBackground(new Color(0xBDBAA5));
        bOrder.addActionListener(this);

        bMenu = new JButton("Menu");
        bMenu.setBounds(30,100,240,40);
        bMenu.setBackground(new Color(0xBDBAA5));
        bMenu.addActionListener(this);

        bDrivers = new JButton("Drivers");
        bDrivers.setBounds(30,170,240,40);
        bDrivers.setBackground(new Color(0xBDBAA5));
        bDrivers.addActionListener(this);

        bIngredient = new JButton("Ingredient");
        bIngredient.setBounds(30,240,240,40);
        bIngredient.setBackground(new Color(0xBDBAA5));
        bIngredient.addActionListener(this);

        bExit = new JButton("Exit");
        bExit.setBounds(30,310,240,40);
        bExit.setBackground(new Color(0xBDBAA5));
        bExit.addActionListener(this);

        pRight.add(bOrder);
        pRight.add(bMenu);
        pRight.add(bDrivers);
        pRight.add(bIngredient);
        pRight.add(bExit);
        bg.add(pRight);

    }
    private void setRightOrder(){
        pRight.removeAll();

        bNewOrder = new JButton("New Order");
        bNewOrder.setBounds(30,30,240,40);
        bNewOrder.setBackground(new Color(0xBDBAA5));
        bNewOrder.addActionListener(this);

        bDetails = new JButton("Details");
        bDetails.setBounds(30,100,240,40);
        bDetails.setBackground(new Color(0xBDBAA5));
        bDetails.addActionListener(this);


        bBack = new JButton("Back");
        bBack.setBounds(30,170,240,40);
        bBack.setBackground(new Color(0xBDBAA5));
        bBack.addActionListener(this);

        pRight.add(bDetails);
        pRight.add(bNewOrder);
        pRight.add(bBack);
        bg.add(pRight);
    }
    private void setRightDriver(){
        pRight.removeAll();

        bNewDriver = new JButton("New Driver");
        bNewDriver.setBounds(30,30,240,40);
        bNewDriver.setBackground(new Color(0xBDBAA5));
        bNewDriver.addActionListener(this);

        bEditDriver = new JButton("Edit");
        bEditDriver.setBounds(30,100,240,40);
        bEditDriver.setBackground(new Color(0xBDBAA5));
        bEditDriver.addActionListener(this);

        bDeleteDriver = new JButton("Delete");
        bDeleteDriver.setBounds(30,170,240,40);
        bDeleteDriver.setBackground(new Color(0xBDBAA5));
        bDeleteDriver.addActionListener(this);

        bBack = new JButton("Back");
        bBack.setBounds(30,240,240,40);
        bBack.setBackground(new Color(0xBDBAA5));
        bBack.addActionListener(this);

        pRight.add(bNewDriver);
        pRight.add(bEditDriver);
        pRight.add(bDeleteDriver);
        pRight.add(bBack);
        bg.add(pRight);
    }
    private void setRightMenu(){
//        pRight.removeAll();
//
//        bNewPizza = new JButton("New Order");
//        bNewOrder.setBounds(30,30,240,40);
//        bNewOrder.setBackground(new Color(0xBDBAA5));
//        bNewOrder.addActionListener(this);
//
//        bHistoryOrder = new JButton("History Orders");
//        bHistoryOrder.setBounds(30,100,240,40);
//        bHistoryOrder.setBackground(new Color(0xBDBAA5));
//        bHistoryOrder.addActionListener(this);
//
//        bBack = new JButton("Back");
//        bBack.setBounds(30,170,240,40);
//        bBack.setBackground(new Color(0xBDBAA5));
//        bBack.addActionListener(this);
//
//        pRight.add(bNewOrder);
//        pRight.add(bHistoryOrder);
//        pRight.add(bBack);
//        bg.add(pRight);
    }
    private void showTable(String query) throws SQLException {
        Statement st = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = st.executeQuery(query);
        ResultSetMetaData rsmd = rs.getMetaData();
        rs.last();
        int amountRow=rs.getRow();
        rs.first();
        int amountColumns=rsmd.getColumnCount();

        String[] columnNames = new String[amountColumns];
        Object[][] data = new Object[amountRow][amountColumns];

        for(int i=0;i<amountColumns; i++){
            columnNames[i]=rsmd.getColumnName(i+1);
        }
        for(int j = 0; j < amountRow; j++) {
            for (int i = 0; i < 3; i++) {
                data[j][i] = rs.getString(i+1);
            }
            rs.next();
        }

        table = new JTable(data, columnNames);
        scrollPane = new JScrollPane(table);

        pLeft = new JPanel();
        pLeft.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        pLeft.setBounds(50,50,500,450);
        pLeft.setLayout(null);
        scrollPane.setBounds(30,30,400,400);
        pLeft.add(scrollPane);
        bg.add(pLeft);
        validate();
    }
    private void showDetails(){
        OrderDetails frame = new OrderDetails();
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
    private void newOrder(){
        newOrder frame = new newOrder();
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
    private void newDriver(){
        newDriver frame = new newDriver(cn);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                try {
                    pLeft.remove(scrollPane);
                    showTable("SELECT name as 'Name',surname as 'Surname', pesel as 'Pesel' FROM Driver ORDER BY name");
                    storeId("SELECT idDriver FROM Driver ORDER BY name");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
    private void editDriver() throws SQLException {
        try{
            int id = (int) idList.get(table.getSelectedRow());
            editDriver frame = new editDriver(cn, id);
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    try {
                        pLeft.remove(scrollPane);
                        showTable("SELECT name as 'Name',surname as 'Surname', pesel as 'Pesel' FROM Driver ORDER BY name");
                        storeId("SELECT idDriver FROM Driver ORDER BY name");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }catch(IndexOutOfBoundsException ex){
            JOptionPane.showMessageDialog(null,"You must choose driver", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void deleteDriver() throws SQLException {
        Statement st = cn.createStatement();
        try{
            int id = (int) idList.get(table.getSelectedRow());
            Integer choise = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this Driver?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if(choise==0) {
                st.executeUpdate("DELETE FROM Driver WHERE idDriver=" + id);
                showTable("SELECT name as 'Name',surname as 'Surname', pesel as 'Pesel' FROM Driver ORDER BY name");
                storeId("SELECT idDriver FROM Driver ORDER BY name");
            }
        }catch(IndexOutOfBoundsException ex){
            JOptionPane.showMessageDialog(null,"You must choose driver", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void connect() throws SQLException, ClassNotFoundException {
        String name=nameField.getText();
        String password=getPassword();
        cn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=master;", name, password);
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    }
    private String getPassword() {
        StringBuilder password = new StringBuilder();
        char[] pass = passField.getPassword();
        for (char c : pass) {
            password.append(c);
        }
        return password.toString();
    }
    public void storeId(String query) throws SQLException {
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
        if(z==bLogin){
            try {
                connect();
                System.out.println("Success connection with SQL");
                setBG();
                setRightPanel();
                validate();
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null,"Incorrect login or password", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else if(z==bOrder){
            setBG();
            setRightOrder();
            try {
                showTable("SELECT idOrder,Customer_idCustomer, dateProduction FROM Orderr");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            validate();
        }else if(z==bMenu){
            setBG();
            setRightMenu();
        }else if(z==bDrivers){
            setBG();
            setRightDriver();
            try {
                showTable("SELECT name as 'Name',surname as 'Surname', pesel as 'Pesel' FROM Driver ORDER BY name");
                storeId("SELECT idDriver FROM Driver ORDER BY name");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }else if(z==bIngredient){
            setBG();
        }else if(z==bExit){
            dispose();
        }else if(z==bBack){
            setBG();
            setRightPanel();
        }else if(z==bDetails){
            showDetails();
        }else if(z==bNewOrder){
            newOrder();
        }else if(z==bNewDriver){
            newDriver();
        }else if(z==bEditDriver){
            try {
                editDriver();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }else if(z==bDeleteDriver){
            try {
                deleteDriver();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
