/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import application.DBConfig;
import application.MainFXApp;

import java.util.Date;

import com.mysql.jdbc.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;



import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.chart.*;
import javafx.scene.Group;
/**
 * FXML Controller class
 *
 * @author sad2e
 */
public class BusinessPredictionController implements Initializable {

    Stage stage;
    Scene scene;
    Parent root;
    private static final int MYSQL_TABLE_DOES_NOT_EXIST = 1146;
    private static final int MYSQL_COLUMN_DOES_NOT_EXIST = 1054;
    private static final int MYSQL_TRUNCATED_INCORRECT_DOUBLE_VALUE = 1292;

    private MainFXApp main;

    //Connection to DB
    Connection conn = MainFXApp.con;
    
    public void setMain(MainFXApp mainIn){
        main = mainIn;
    }
    
    @FXML private Label weekLabel;
    @FXML private Label monthLabel;
    @FXML private Label yearLabel;

    @FXML private Label weekPredict;
    @FXML private Label monthPredict;
    @FXML private Label yearPredict;
    
    @FXML private Button backButton;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	int weekVal= 0;
    	int monthVal = 0;
    	int yearVal = 0;

        ResultSet rs = null;

        String IDQuery = "SELECT COUNT(*) FROM Previous_Appointment WHERE DATE(apDate) > (NOW() - INTERVAL 7 DAY)";

        try (
             PreparedStatement getID = conn.prepareStatement(IDQuery, Statement.RETURN_GENERATED_KEYS))
        {
            rs = getID.executeQuery();
            if (rs.next())
            {
                weekVal =  ((Number) rs.getObject(1)).intValue();
                System.out.println("Week Value: " + weekVal);
            }
        }//end try
        catch (SQLException e) {
           if (e.getErrorCode() == MYSQL_TABLE_DOES_NOT_EXIST){
               System.out.print("MySQL Table Does Not Exist.");
            }
           else if(e.getErrorCode() == MYSQL_COLUMN_DOES_NOT_EXIST)
               System.out.println("The column you attempted to insert data into does not exist. You may have formatted your values improperly.");
           else if(e.getErrorCode() == MYSQL_TRUNCATED_INCORRECT_DOUBLE_VALUE)
               System.out.println("The value  may not have been formatted correctly");
            e.printStackTrace();
        }//end catch


        weekLabel.setText(Integer.toString(weekVal));
        weekPredict.setText(Integer.toString(yearVal/52));



        String IDQuery2 = "SELECT COUNT(*) FROM Previous_Appointment WHERE DATE(apDate) > (NOW() - INTERVAL 30 DAY)";

        try (
             PreparedStatement getID = conn.prepareStatement(IDQuery2, Statement.RETURN_GENERATED_KEYS))
        {
            rs = getID.executeQuery();
            if (rs.next())
            {
                monthVal =  ((Number) rs.getObject(1)).intValue();
                System.out.println("Month Value: " + monthVal);
            }
        }//end try

        catch (SQLException e) {
            if (e.getErrorCode() == MYSQL_TABLE_DOES_NOT_EXIST){
                System.out.print("MySQL Table Does Not Exist.");
            }
            else if(e.getErrorCode() == MYSQL_COLUMN_DOES_NOT_EXIST) {
                System.out.println("The column you attempted to insert data into does not exist. You may have formatted your values improperly.");
            }
            else if(e.getErrorCode() == MYSQL_TRUNCATED_INCORRECT_DOUBLE_VALUE)
                System.out.println("The value  may not have been formatted correctly");
            e.printStackTrace();
        }//end catch



        monthLabel.setText(Integer.toString(monthVal));
        monthPredict.setText(Integer.toString(yearVal/12));


        String IDQuery3 = "SELECT COUNT(*) FROM Previous_Appointment WHERE DATE(apDate) > (NOW() - INTERVAL 365 DAY)";

        try (
             PreparedStatement getID = conn.prepareStatement(IDQuery3, Statement.RETURN_GENERATED_KEYS))
        {
            rs = getID.executeQuery();
            if (rs.next())
            {
                yearVal =  ((Number) rs.getObject(1)).intValue();
                System.out.println("Year Value: " + yearVal);
            }
        }//end try

        catch (SQLException e) {

            if (e.getErrorCode() == MYSQL_TABLE_DOES_NOT_EXIST){
                System.out.print("MySQL Table Does Not Exist.");
            }
            else if(e.getErrorCode() == MYSQL_COLUMN_DOES_NOT_EXIST) {
                System.out.println("The column you attempted to insert data into does not exist. You may have formatted your values improperly.");
            }
            else if(e.getErrorCode() == MYSQL_TRUNCATED_INCORRECT_DOUBLE_VALUE) {
                System.out.println("The value  may not have been formatted correctly");
            }
            e.printStackTrace();
        }//end catch

        yearLabel.setText(Integer.toString(yearVal));
        yearPredict.setText(Integer.toString(yearVal));

/*  Here I am starting some data visualization stuff... To be expanded on more meaningfully soon.

         public void start(Stage stage) {
            Scene scene = new Scene(new Group());
            stage.setTitle("Imported Fruits");
            stage.setWidth(500);
            stage.setHeight(500);

            ObservableList<PieChart.Data> pieChartData =
                    FXCollections.observableArrayList(
                            new PieChart.Data("Past Month", monthVal),
                            new PieChart.Data("Past Week", weekVal),
                            new PieChart.Data("Past Year", yearVal)
                    );
            final PieChart chart = new PieChart(pieChartData);
            chart.setTitle("Appointment Data");

            ((Group) scene.getRoot()).getChildren().add(chart);
            stage.setScene(scene);
            stage.show();
       }
*/







    } //end initialize
    
    @FXML public void buttonClicked(ActionEvent click) throws Exception {
        try{
            stage = (Stage) ((Button) click.getSource()).getScene().getWindow();
            
        
            String source = ((Node) click.getSource()).getId();
            
            switch (source) {
		case "historyButton":
                    root = FXMLLoader.load(getClass().getResource("/view/BusinessHistory.fxml"));
                    BusinessHistoryController act1 = new BusinessHistoryController();
                    act1.setMain(main);
                    break;
                case "backButton":
                    root = FXMLLoader.load(getClass().getResource("/view/mainView.fxml"));
                    patientViewController act2 = new patientViewController();
                    act2.setMain(main);
                    break;
		default:
                    break;
            }
            //sets fxml file as a scene
            scene = new Scene(root);
            //loads the scene on top of whatever stage the button is in
            stage.setScene(scene);
        }catch(Exception e){

            e.printStackTrace();
        }
    }   
}
