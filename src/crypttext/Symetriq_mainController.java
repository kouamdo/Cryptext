/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crypttext;

import algo.sym;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


/**
 * FXML Controller class
 *
 * @author Ledoux
 */
public class Symetriq_mainController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    private String patch_File , content_File  , key_ ;
    
    @FXML Label patchFile , error;
    
    @FXML TextField key ;
    
    @FXML
    private void backSymetriqMainToMain(ActionEvent event) throws IOException {
        
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml")) ;
        
        Scene home_page_scene = new Scene(home_page_parent) ;
        
        Stage app_stage  =(Stage) ((Node) event.getSource()).getScene().getWindow() ;
        
        app_stage.setScene(home_page_scene) ;
        
        app_stage.show();
    }
    
    @FXML
    public Button closeButton;
    
    @FXML
public void handleCloseButtonAction(ActionEvent event) {
    Stage stage = (Stage) closeButton.getScene().getWindow();
    stage.close();
}
    
    @FXML
    private void FileChooser() throws FileNotFoundException, IOException
    {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
       
        if (selectedFile != null)this.patch_File = selectedFile.getAbsolutePath() ;
        
        patchFile.setText(this.patch_File);
        
        Scanner sc = new Scanner(new File(this.patch_File));
        
        String str = null;
        
        while(sc.hasNextLine()){  str = sc.nextLine(); }     

        this.content_File = str  ;   
}

    private void save()
    {
        String FILENAME = this.patch_File;
        BufferedWriter bw = null;
        FileWriter fw = null;
        try
        {
            String content = this.content_File;
            fw = new FileWriter(FILENAME);
            bw = new BufferedWriter(fw);
            bw.write(content);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try {
                    if (bw != null)
                    bw.close();
                    if (fw != null)
                    fw.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
        }
    }
    
    
    /*
    *   Commencons avec le chiffrage
    */
    
    @FXML
    private void chiffrage() throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, Exception
    {
      String mykey =key.getText();
      
      SecretKey key_ = new SecretKeySpec(mykey.getBytes(), "AES");
      
      sym encrypt = new sym(key_);
      
      String message = this.content_File ;
      
      this.content_File = encrypt.cryptSym(message) ;
      
      save() ;
      
    }
    
    /*
    Dechiffrage
    */
    @FXML
    private void dechiffrage() throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
    {
        
        String mykey =key.getText();
      
      SecretKey key_ = new SecretKeySpec(mykey.getBytes(), "AES");
      
      sym encrypt = new sym(key_);
            
     String message = this.content_File ;
     
     this.content_File = encrypt.decryptSym(message) ;
     
     save() ;
     
    }
    
    
    
    
    @FXML
        private void sizePrint()
    {
        error.setText("Taille de la clé : "+ (1+key.getText().getBytes().length)  + " / 16 bits");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
