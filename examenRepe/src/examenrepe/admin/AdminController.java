/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examenrepe.admin;

import examenrepe.levenshtein.LevenshteinDistance;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import com.sun.org.apache.bcel.internal.Constants;
import javafx.scene.Scene;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;


/**
 * FXML Controller class
 *
 * @author ander
 */
public class AdminController implements Initializable {

    @FXML
    private AnchorPane anchoraPane;
    String val1;
    @FXML
    private TilePane tilePane;
    String nombreArchivoCopiado;
    int porcentaje;
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        File carpeta = new File("FILES");
        File[] files = carpeta.listFiles();
        //recorremos los archivos y los comparamos obteniendo la mxima coincidencia y el nombre del archvio
        for(int i=0; i< files.length; i++){
            
            try {
                double y=0;
                FileReader fri = new FileReader(files[i]);
                BufferedReader bri = new BufferedReader(fri);
                String textoi = bri.readLine();
                for(int j=0; j< files.length; j++){
                    if(files[i]!=files[j]){
                        FileReader frj = new FileReader(files[j]);
                        BufferedReader brj = new BufferedReader(frj);
                        String textoj = brj.readLine();
                        double x=LevenshteinDistance.similarity(textoi, textoj)*100;
                        
                        if ( x>  y){
                            y=x;
                            porcentaje= (int)y;
                            nombreArchivoCopiado=files[j].getName();
                    }
                         val1 = String.format("%.2f", y);  
                         val1=val1+" %";
                    }
                   
                   
                    
                }
                
            } catch (IOException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
                    //creamos un borderpane por cada archivo para mostrar los datos
                    
                    Text texto = new Text();
                    Text textoCopiado =new Text();
                    Text textpor= new Text();
                    texto.setText(files[i].getName());
                    textoCopiado.setText(nombreArchivoCopiado);
                    textoCopiado.setDisable(true);
                    textpor.setFont(Font.font("verdana", FontWeight.LIGHT, FontPosture.REGULAR, 0x14));
                    String as = textpor.getText();
                    
                    
                    if (porcentaje>50)
                    textpor.setFill(Paint.valueOf("red"));
                    
                   
                    
                    textpor.setText(val1);
                  
                  BorderPane border = new BorderPane();
                  border.setPadding(new Insets(10, 10, 10,10) );
                  border.setCenter(textpor);
                  
                  border.setBottom(texto);
                  border.setStyle("-fx-border-width:1px;");
                  tilePane.getChildren().addAll(border);
                  
                  //le añadimos a cada borde un evento para hacer doble click y mostrar con quien se ha copiado
                  border.setOnMouseClicked(new EventHandler<MouseEvent>() {
                  @Override
                        public void handle(MouseEvent mouseEvent) {
                        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                            if(mouseEvent.getClickCount() == 2){
                                final Stage dialog = new Stage();
                                dialog.initModality(Modality.WINDOW_MODAL);
                                
                                VBox dialogVbox = new VBox(20);
                                dialogVbox.getChildren().add(new Text("Nombre del archivo con mayor coincidencia: "+textoCopiado.getText()));
                                dialogVbox.getChildren().add(new Text("Propietario del archivo: Ander" ));
                                
                                Scene dialogScene = new Scene(dialogVbox, 350, 150);
                                dialog.setScene(dialogScene);
                                dialog.show();
                                return;



}
}
}
});
                  
                  
                  
                    
            
        }
        
        
        
        
        
        
    }    
    
}
