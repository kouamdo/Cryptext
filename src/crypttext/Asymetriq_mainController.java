/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crypttext;

import algo.asym.Key;
import algo.asym.RSA;
import algo.asym.RSAUtil;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
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

/**
 * FXML Controller class
 *
 * @author Ledoux
 */
public class Asymetriq_mainController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    private String patch_File , content_File  , key_private , key_public ;
    
    @FXML Label patchFile , error;
    
    @FXML TextField keyPublic , keyPrivate ;
    
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
    
    private void getFile() throws FileNotFoundException
    {        
        Scanner sc = new Scanner(new File(patchFile.getText()));
        
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
    Chiffrage
    */
    @FXML
    private void backAsymetriqMainToMain(ActionEvent event) throws IOException {
        
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml")) ;
        
        Scene home_page_scene = new Scene(home_page_parent) ;
        
        Stage app_stage  =(Stage) ((Node) event.getSource()).getScene().getWindow() ;
        
        app_stage.setScene(home_page_scene) ;
        
        app_stage.show();
    }
    
    @FXML
    private void chiffrage() throws Exception
    {
        getFile() ;
        try
        {
            this.key_public = keyPublic.getText() ;
            
            BigInteger modulo = new BigInteger(this.key_public.split(",")[1]);
            
            String SKpublic = this.key_public.split(",")[0] ;
            
            BigInteger number = RSAUtil.stringToNumber(this.content_File);
            
            BigInteger[] primes = RSAUtil.generateTwoPrimesP(5,SKpublic);
            
            Key[] keys = RSAUtil.buildKeysOf(primes[0], primes[1]);
            
            RSA rsa = new RSA(primes[0] , modulo,primes[1]);
            
            BigInteger crypted =  rsa.encrypt(number);
            
            this.content_File = ""+crypted ;
            
            save() ;
        }
        catch(Exception e)
                {
                    error.setText(e.getMessage());
                }
    }
    
    /*
    Dechiffrement
    */
    
    @FXML
    private void dechiffrage() throws FileNotFoundException, Exception
    {
        getFile() ;
        
        BigInteger crypted = new BigInteger(this.content_File);
        
        String s_publicKey = keyPublic.getText().split(",")[0] ;
        
        String s_privateKey = keyPrivate.getText().split(",")[0] ;
        
        String s_modulo = keyPublic.getText().split(",")[1] ;
        
        BigInteger b_public = new BigInteger(s_publicKey) , b_modulo = new BigInteger(s_modulo) , b_private = new BigInteger(s_privateKey) ;
        
        RSA rsa = new RSA(b_public , b_modulo,b_private);
        
        BigInteger decrypted = rsa.decrypt(crypted);
        
        String ans = RSAUtil.numberToString(decrypted);
        
        this.content_File = ans ;
        
        save() ;
    }
    
    @FXML
    private void generateKey() throws Exception
    {
        BigInteger[] primes = RSAUtil.generateTwoPrimes(5);
        
        Key[] keys = RSAUtil.buildKeysOf(primes[0], primes[1]);
        
        keyPublic.setText(keys[0].toString());
        
        keyPrivate.setText(keys[1].toString());
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
