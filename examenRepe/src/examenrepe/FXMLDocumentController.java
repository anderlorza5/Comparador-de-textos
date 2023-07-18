/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examenrepe;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import static javafx.fxml.FXMLLoader.load;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author ander
 */
public class FXMLDocumentController implements Initializable {
    
    
    
    @FXML
    private Button botonUser;
    @FXML
    private Button botonAdmin;
    @FXML
    public AnchorPane Pane;
    @FXML
    private Button botonSalir;
    
    
    //funcion para mostrar la ventana d elogin
    @FXML
    private void cargarLogin() throws IOException {
        Pane.getChildren().clear();
        Pane newLoadedPane =   FXMLLoader.load(ExamenRepe.class.getResource("login/Login.fxml"));
        Pane.getChildren().add(newLoadedPane);
        
    }
    
    //funcion para mostrar la ventana del ususario
    @FXML
  public void cargarUser () throws IOException  {
      Pane.getChildren().clear();
      Pane newLoadedPane =   FXMLLoader.load(ExamenRepe.class.getResource("user/User.fxml"));
      Pane.getChildren().add(newLoadedPane);
}
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        //de entrada cargamos la ventana de usuario
        try {
            cargarUser();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    
    //funcion para cerrar el programa con la X
    @FXML
    private void salir(ActionEvent event) {
        
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    
    
}
