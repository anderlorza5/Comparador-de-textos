/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examenrepe.login;

import examenrepe.ExamenRepe;
import examenrepe.FXMLDocumentController;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.omg.CORBA.Environment;

/**
 * FXML Controller class
 *
 * @author ander
 */
public class LoginController implements Initializable {

    @FXML
    private AnchorPane ventanaLogin;
    @FXML
    private TextField user;
    @FXML
    private PasswordField password;
    
    private String usuario;
    private Environment env;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        //cargamos el archivo de configuracion y le pasamos a unos strings los valoresque debe tener el ususari y la contraseñña "admin"
        Properties propiedades = new Properties();
        try {
            propiedades.load(new FileReader("config.properties"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String nombreusuario = propiedades.getProperty("user");
        String contraseñausuario = propiedades.getProperty("password");
        
        
        
        //creamos un evento para que cuando se presione "enter" compruebe si esta bien la conraseña y el ususario y lance la ventana del admin
        user.setOnKeyPressed(new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            if(event.getCode() == KeyCode.ENTER){
                if (user.getText().equals(nombreusuario)&& password.getText().equals(contraseñausuario)){
                   AnchorPane pro= new AnchorPane();
                   pro =(AnchorPane) ventanaLogin.getParent();
                   pro.getChildren().clear();
                   Pane newLoadedPane;
                    try {
                        newLoadedPane = FXMLLoader.load(ExamenRepe.class.getResource("admin/Admin.fxml"));
                        pro.getChildren().add(newLoadedPane);
                    } catch (IOException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   
            }
        }
    }});
       
       //la misma funcion parqa el otro campo, no se como ponerla una vez y asociarla a los dos campos, esto es redundante
       password.setOnKeyPressed(new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            if(event.getCode() == KeyCode.ENTER){
                if (user.getText().equals(nombreusuario)&& password.getText().equals(contraseñausuario)){
                   AnchorPane pro= new AnchorPane();
                   pro =(AnchorPane) ventanaLogin.getParent();
                   pro.getChildren().clear();
                   Pane newLoadedPane;
                    try {
                        newLoadedPane = FXMLLoader.load(ExamenRepe.class.getResource("admin/Admin.fxml"));
                        pro.getChildren().add(newLoadedPane);
                    } catch (IOException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
        }
    }}); 
        
        
    }    

    
  
    
}
