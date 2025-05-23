package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

public class slmisRevampController {

    @FXML
    private Button loginButton;

    @FXML
    private TextField loginIDTxt;

    @FXML
    private PasswordField loginPassTxt;
    
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    @FXML
    void login(ActionEvent event) {
    	
    	String userID = loginIDTxt.getText();
    	String userPass = loginPassTxt.getText();
    	
    	if(userID.equals("") && userPass.equals("")) {
    		JOptionPane.showMessageDialog(null, "Login Failed");
		} else {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/slmis", "root", "");
				pst = con.prepareStatement("select * from login where userID = ? and userPass = ?");
				
				pst.setString(1, userID);
				pst.setString(2, userPass);
				
				rs = pst.executeQuery();
				
				if(rs.next()) {
					DBUtils.changeScene(event, "/application/homeScreen.fxml", "Welcome", userID, userPass);
				}
				else {
					JOptionPane.showMessageDialog(null, "Login Failed");
					loginIDTxt.setText("");
					loginPassTxt.setText("");
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
    }
    
    public void setUserID(String userID, String userPass) {
        System.out.println("Logged in as: " + userID);
    }


}
