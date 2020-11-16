package application;

import java.sql.Connection;



import controller.mainViewController;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
/**
 * Main Entrance point to our application, also contains db connection in variable con.
 */

import javafx.fxml.FXMLLoader;


public class MainFXApp extends Application {


	private static Scene scene;
	public static Connection con = null;
	
	public static Window primaryStage;
	public static Scene getScene(){
		return scene;
	}
	

	/**
	 * Creates the initial main page then calls mainViewController to create tabs.
	 */
	@Override
	public void start(Stage primaryStage) {
		try {

			//Leo= this is need to make second window primary and not the first window. 
			//it is needed to prevent the user from running the first windows when second is open
			this.primaryStage= primaryStage;
			con = DBConfig.getConnection();
			
			//CURRENTLY SET TO BYPASS LOGIN
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/loginView.fxml"));
			//AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/loginView.fxml"));

			Scene scene = new Scene(root,610,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Connects to database and then launches this class's start function. Uses Model.DBConnection.
	 */
	public static void main(String[] args) {
		launch(args);
	}
}