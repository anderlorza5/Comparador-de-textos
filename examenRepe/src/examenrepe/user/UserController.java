/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examenrepe.user;

import examenrepe.levenshtein.LevenshteinDistance;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author ander
 */
public class UserController implements Initializable {

    @FXML
    private ComboBox<File> comboBox1;
    @FXML
    private ComboBox<File> comboBox2;
    @FXML
    private TextArea areaTexto1;
    @FXML
    private TextArea areaTexto2;
    @FXML
    private Button botonCheck;
    
    String text1;
    String text2;
    @FXML
    private Text porcentaje;
    @FXML
    private Button botonReset;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //añadimos a los combobox los archivos demla carpeta files
        File carpeta = new File("FILES");
        File[] files = carpeta.listFiles();
        comboBox1.getItems().addAll(files);
        comboBox2.getItems().addAll(files);
        
    }    
    
    //funcion para pasar a un sTring el texto del archivo seleccionado y mostrarlo por el text area
    @FXML
    private void selecionarTexto1(ActionEvent event ) throws FileNotFoundException, IOException {
        
        FileReader fr = new FileReader(comboBox1.getSelectionModel().getSelectedItem());
        BufferedReader br = new BufferedReader(fr);
        String texto = br.readLine();
        areaTexto1.setText(texto);
        text1=texto;
    }
    
    //igual pero apra el otro text area
    @FXML
    private void selecionarTexto2(ActionEvent event ) throws FileNotFoundException, IOException {
        
        FileReader fr = new FileReader(comboBox2.getSelectionModel().getSelectedItem());
        BufferedReader br = new BufferedReader(fr);
        String texto = br.readLine();
        areaTexto2.setText(texto);
        text2=texto;
        
    }
    //funcion para comparar los dos textos y quie nos saque un porcentaje con el algoritmo ese chungo
    @FXML
    private void comparar(ActionEvent event) {
        if (text1.equals("") || text2.equals("")){
           porcentaje.setText("ERROR");
        }else{
           double x=LevenshteinDistance.similarity(text1, text2)*100;
           String val1 = String.format("%.2f", x);
        porcentaje.setText(val1+"%"); 
        }
        
        
    }
    
    
    //funcion del boton resetear para reestablecer los valores predeterminados para todos los campos
    @FXML
    private void resetear(ActionEvent event) {
        comboBox1.getSelectionModel().clearSelection();
        comboBox2.getSelectionModel().clearSelection();
        porcentaje.setText("");
        areaTexto1.clear();
        areaTexto2.clear();
        text1="";
        text2="";
    }
    
     
}
