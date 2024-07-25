
package MainFrame;


//Libraries Imports
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import javax.swing.plaf.basic.*;
import java.awt.event.*;
import java.sql.*;
import java.io.*;
import CustomizedComponents.*;

//Main JFrame
public class LoginPage extends JFrame{

    //Initializing variables
    Connection conn = null;
    String id;
    String password;
    String role;
    String role_id;   
    
    //Initialize Constructor
    public LoginPage()
    {

        // Define colors
        Color darkBlue = new Color(19, 26, 74);
        Color Gray = new Color(83, 88, 123);
        
        //Load SF Pro Rounded Font
        new loadCustomFonts("/assets/SF-Pro-Rounded-Regular.otf");
        
        //Loading Rounded Borders with Specific Colors
        roundedBorder blueBorder = new roundedBorder(20,darkBlue);
        roundedBorder grayBorder = new roundedBorder(20,Color.GRAY);
        
        // Create the frame and its main properties
        JFrame frame = new JFrame("University Election System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.setSize(1200, 800); // Jframe Size
        frame.setLayout(new BorderLayout()); //Setting Layout Border to Border
        frame.getContentPane().setBackground(Color.WHITE); //Setting Fram background

        // Create a rounded panel for the left side with the logo
        roundedPanel leftPanel = new roundedPanel(new GridBagLayout(), 30, darkBlue); // Creating Blue Left Panel
        leftPanel.setPreferredSize(new Dimension(600, 800)); //Left Panel Size
        leftPanel.setOpaque(false); // Make it transparent so only the rounded panel shows

        // Load "VOTE" Image
        ImageIcon voteIcon = new ImageIcon(LoginPage.class.getResource("/assets/voting.png")); //Load ImageIcon
        Image originalImage = voteIcon.getImage(); // Converting to Image
        int newWidth = 550; // Setting Width
        int newHeight = (int) ((double) newWidth / originalImage.getWidth(null) * originalImage.getHeight(null)); // Setting Height
        Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH); // Resizing Image
        voteIcon = new ImageIcon(resizedImage); 
        JLabel voteLabel = new JLabel(voteIcon); // Creating Image Label
        leftPanel.add(voteLabel); // Adding Image label to Left Panel

        // Create a panel for the right side with the login form
        JPanel rightPanel = new JPanel(); // Craeting Left Panel
        rightPanel.setLayout(new GridBagLayout()); 
        rightPanel.setBackground(Color.WHITE); // Right Panel Color

        //Create Grid Bag Layout with properties
        GridBagConstraints gbc = new GridBagConstraints(); // Creating Constraints
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(7, 2, 7, 2); // Setting Inner padding
        gbc.gridx = 0; // Set X Grid Value
        gbc.gridheight = 1; // Set Grid Height
        gbc.gridwidth = 1; // Set Grid Width
        gbc.ipadx = 2; // Set Inner X Padding
        gbc.ipady = 0; // Set Inner Y Padding
        
        // Welcome Back label 
        JLabel welcomeLabel = new JLabel("Welcome Back!",SwingConstants.CENTER); //Set Text
        welcomeLabel.setFont(new Font("SF Pro Rounded", Font.PLAIN, 50)); // Set Font
        welcomeLabel.setForeground(darkBlue); // Set text color to dark blue
        gbc.insets = new Insets(7,2,0,2); // Set outer padding
        gbc.gridy = 0; // Set Y Grid Number
        rightPanel.add(welcomeLabel, gbc); // Adding it to Right Panel
        
        // Participate in University Elections label
        JLabel subLabel = new JLabel("Participate in University Elections",SwingConstants.CENTER); //Set Text
        subLabel.setForeground(Gray); // Set Foreground Color
        subLabel.setFont(new Font("SF Pro Rounded", Font.PLAIN, 20)); // Set Font
        gbc.insets = new Insets(0,2,50,2); // Set outer padding
        gbc.gridy = 1;  // Set Y Grid Number
        rightPanel.add(subLabel, gbc); // Adding it to Right Panel

        // Role dropdown
        JComboBox<String> roleComboBox = new JComboBox<>(new String[]{"Role","Student", "Administrator"});  //Set Text
        roleComboBox.setFont(new Font("SF Pro Rounded Regular", Font.PLAIN, 18)); // Set Font
        roleComboBox.setPreferredSize(new Dimension(100,50)); // Set Size
        roleComboBox.setBackground(Color.WHITE); // Set Background Color
        roleComboBox.setForeground(darkBlue); // Set Foreground Color
        roleComboBox.setBorder(blueBorder); // Set Outer Border Color
        roleComboBox.setUI(new BasicComboBoxUI() { // Removing Inner Borders
            @Override
            protected JButton createArrowButton() {
                JButton button = super.createArrowButton();
                button.setBorder(BorderFactory.createEmptyBorder());
                button.setContentAreaFilled(false);
                return button;
            }
        });
        if (roleComboBox.isEditable()) {
            roleComboBox.setEditor(new BasicComboBoxEditor() {
                @Override
                protected JTextField createEditorComponent() {
                    JTextField editor = super.createEditorComponent();
                    editor.setBorder(BorderFactory.createEmptyBorder());
                    return editor;
                }
            });
        }
        roleComboBox.addActionListener(new ActionListener() { // Combo Box Action Listener
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    //Deleting "Role" Option after first Selection
                    if (roleComboBox.getSelectedIndex() != 0 && roleComboBox.getItemAt(0) == "Role")
                        {roleComboBox.removeItemAt(0);}
                    
                    //Setting role variable as the selected item
                    role = (String) roleComboBox.getSelectedItem();
                    role = role.toUpperCase();
                }
            });
        roleComboBox.requestFocus(); // giving Focus to Combo BOx
        gbc.insets = new Insets(5, 2, 5, 2); // Set outer padding
        gbc.gridy = 2; // Set Y Grid Number
        gbc.ipady = 10; // Set Inner padding
        rightPanel.add(roleComboBox, gbc); //Adding it to right panel

        // Student ID field
        placeholderTextField studentIdField = new placeholderTextField("ID", Color.GRAY, darkBlue, grayBorder, blueBorder); //Set Text
        studentIdField.setFont(new Font("SF Pro Rounded Regular", Font.PLAIN, 18)); //Set Font
        studentIdField.setBorder(grayBorder); // Set border color
        studentIdField.setPreferredSize(new Dimension(100,50)); // Set Size
        studentIdField.setForeground(Color.GRAY); //Set Background Color
        gbc.gridy = 3; // Set y Grid Number
        rightPanel.add(studentIdField, gbc); // Adding i to right panel

        // Password field
        placeholderPasswordField passwordField = new placeholderPasswordField("Password", Color.GRAY, darkBlue, grayBorder, blueBorder); // Set text
        passwordField.setFont(new Font("SF Pro Rounded Regular", Font.PLAIN, 18)); // Set Font
        passwordField.setBorder(grayBorder); // Set Border Color
        passwordField.setPreferredSize(new Dimension(100,50)); // Set Size
        passwordField.setForeground(Color.GRAY); // Set Foreground Color
        gbc.gridy = 4; // Sst Y Grid Number
        rightPanel.add(passwordField, gbc); // Adding it to right panel

        //Role Error Label
        JLabel roleErrorLabel = new JLabel("Please Enter a Valid Role",SwingConstants.CENTER); // Set Text
        roleErrorLabel.setForeground(Color.RED); // Set Foreground Color
        roleErrorLabel.setFont(new Font("SF Pro Rounded", Font.PLAIN, 16)); // Set Font
        roleErrorLabel.setVisible(false); // Set Visibility At First to False
        gbc.insets = new Insets(5,2,5,2); // Set Outer padding
        gbc.gridy = 6; // Set Y Grid Number
        rightPanel.add(roleErrorLabel, gbc); //Add to Right Panel
        
        //Wrong credenials Label
        JLabel credsErrorLabel = new JLabel("Incorrect ID or Password",SwingConstants.CENTER); // Set Text
        credsErrorLabel.setForeground(Color.RED); // Set Foreground Color
        credsErrorLabel.setFont(new Font("SF Pro Rounded", Font.PLAIN, 16));  // Set Font
        credsErrorLabel.setVisible(false); // Set Visibility At First to False
        gbc.insets = new Insets(5,2,5,2); // Set Outer padding
        gbc.gridy = 6; // Set Y Grid Number
        rightPanel.add(credsErrorLabel, gbc); //Add to Right Panel
        
        //Empty credenials Label
        JLabel credsEmptyLabel = new JLabel("ID and Password Fields are Required",SwingConstants.CENTER);  // Set Text
        credsEmptyLabel.setForeground(Color.RED); // Set Foreground Color
        credsEmptyLabel.setFont(new Font("SF Pro Rounded", Font.PLAIN, 16));  // Set Font
        credsEmptyLabel.setVisible(false); // Set Visibility At First to False
        gbc.insets = new Insets(5,2,5,2); // Set Outer padding
        gbc.gridy = 6; // Set Y Grid Number
        rightPanel.add(credsEmptyLabel, gbc); //Add to Right Panel
        
        // Login button
        roundedButton loginButton = new roundedButton("Log in", 20, darkBlue, Color.LIGHT_GRAY); // Set text
        loginButton.setFont(new Font("SF Pro Rounded Regular", Font.PLAIN, 18)); // Set Font 
        loginButton.setForeground(Color.WHITE); // Set button text color to white
        loginButton.setPreferredSize(new Dimension(100, 50)); // Set button size with 30 pixels height
        gbc.gridy = 5; // Set Y Grid Number
        gbc.insets = new Insets(5,2,0,2); // Set Outer Padding
        rightPanel.add(loginButton, gbc); // Add to Right Panel
        loginButton.addActionListener(new ActionListener() { // Add Action Lsitener
        public void actionPerformed(ActionEvent e) {
            
            // Initializing Labels visibility to False
            roleErrorLabel.setVisible(false);
            credsEmptyLabel.setVisible(false);
            credsErrorLabel.setVisible(false);

            // Check if ID and Password fields are empty or default values
            if ("ID".equals(studentIdField.getText()) || studentIdField.getText().isEmpty() || "Password".equals(passwordField.getText()) || passwordField.getText().isEmpty()) {
                    credsEmptyLabel.setVisible(true); // If Yes, Displaying this Message
                    return; // Exit early to prevent further processing
            }

            // Check if role is selected
            if ("Role".equals((String) roleComboBox.getSelectedItem()) && !credsErrorLabel.isVisible() && !credsEmptyLabel.isVisible()) {
                roleErrorLabel.setVisible(true); // If No, Displaying this Message
                return; // Exit early to prevent further processing
            }

            // Database Connection and login query
            conn = DBConnection.getConnection();
            id = studentIdField.getText(); // Getting ID from ID Text Field
            password = passwordField.getText(); // Getting Password from Password Text Field
            if ("STUDENT".equals(role)) role_id = "student_id"; // Setting role_id to proper role
            if ("ADMINISTRATOR".equals(role)) role_id = "admin_id"; // Setting role_id to proper role

            // Writing Query
            String query = "Select " + role_id + ", password from "+ role +" where " + role_id + " = '" + id + "' and password = '" + password + "';";

            // Getting Query Result
            try (Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);

                    // if Login credentials Found
                    if (rs.next()) {
                        
                        //Print this Message
                        System.out.println("Login Successful");
                        return;
                    } else {
                        
                        // If not, Display Incorrect Credentials Message
                        credsErrorLabel.setVisible(true);
                }
                    
            //Error handling
            } catch (SQLException ex) {
                credsErrorLabel.setVisible(true);
                ex.printStackTrace();
            } finally {
                try {
                    
                    // Closing Connection
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        });
        
        // Add panels to frame
        JPanel container = new JPanel(new BorderLayout(20, 20));
        container.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        container.setBackground(Color.WHITE);
        container.add(leftPanel, BorderLayout.WEST);
        container.add(rightPanel, BorderLayout.CENTER);
        
        frame.add(container);
        
        // Set frame visibility
        frame.setVisible(true);
        loginButton.requestFocusInWindow(); // Putting Focus To Login Button
        frame.getRootPane().setDefaultButton(loginButton); // Setting Focus to Login Button
        
    } 
    
    // Main Method
    public static void main(String[] args)
    {
        new LoginPage();
    }
}
