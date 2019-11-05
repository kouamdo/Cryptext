/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crypttext;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author Ledoux
 */
public class FXMLDocumentController implements Initializable {
    
   
    
    
    @FXML
    public Button closeButton;
    
    @FXML
public void handleCloseButtonAction(ActionEvent event) {
    Stage stage = (Stage) closeButton.getScene().getWindow();
    stage.close();
}
    
    @FXML
    private void OpenAsymetriq_main(ActionEvent event) throws IOException {
        
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("asymetriq_main.fxml")) ;
        
        Scene home_page_scene = new Scene(home_page_parent) ;
        
        Stage app_stage  =(Stage) ((Node) event.getSource()).getScene().getWindow() ;
        
        app_stage.setScene(home_page_scene) ;
        
        app_stage.show();
    }
    
    @FXML
    private void OpenSymetriq_main(ActionEvent event) throws IOException {
        
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("symetriq_main.fxml")) ;
        
        Scene home_page_scene = new Scene(home_page_parent) ;
        
        Stage app_stage  =(Stage) ((Node) event.getSource()).getScene().getWindow() ;
        
        app_stage.setScene(home_page_scene) ;
        
        app_stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
