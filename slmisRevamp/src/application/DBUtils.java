package application;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;

public class DBUtils {
	
	public static void changeScene(ActionEvent event, String fxml, String title, String userID, String userPass) {
		Parent root = null;
		
		if(userID != null && userPass != null) {
			try {
				FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxml));
				root = loader.load();
				homeScreenController controller = loader.getController();
				controller.setUserID(userID, userPass);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
			
		else {
			try {
				root = FXMLLoader.load(DBUtils.class.getResource("/application/homeScreen.fxml"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setTitle(title);
		stage.setScene(new Scene(root));
		stage.show();
	}
}
