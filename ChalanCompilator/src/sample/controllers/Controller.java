package sample.controllers;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;

import org.reactfx.Subscription;
import sample.Constants.Configs;

import java.io.File;
import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import static sample.Constants.Configs.sampleCode;

public class Controller extends Application {

    private Stage stage;
    @FXML public Pane paneSote;
    @FXML TextArea txtConsola;


    CodeArea codeArea = new CodeArea();
    @FXML protected void initialize (){
        codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));
        codeArea.replaceText(0,0,sampleCode);
        codeArea.setPrefSize(1200,330);
        HBox.setHgrow(codeArea, Priority.ALWAYS);
    Subscription cleanupWhenNoLongerNeedIt = codeArea
            .multiPlainChanges()
            .successionEnds(Duration.ofMillis(500))
            .subscribe(ignore -> codeArea.setStyleSpans(0, Configs.computeHighlighting(codeArea.getText())));
        paneSote.getChildren().add(codeArea);
    }//LLave del LOADDD
    public void evtSalir (ActionEvent event){ System.exit(0); }
    public void evtAbrir (ActionEvent event){
        FileChooser of= new FileChooser();
        of.setTitle("Abrir archivo ccf");
        FileChooser.ExtensionFilter filtro= new FileChooser.ExtensionFilter("Archivos .ccf","*.ccf");
        of.getExtensionFilters().add(filtro);
        File file = of.showOpenDialog(stage);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage=stage;
    }

    public void ejecutar (ActionEvent evento){
        compilar();
    }//llave ejecutar
    public void compilar(){

        long tInicial= System.currentTimeMillis();

        txtConsola.setText("");
        String texto= codeArea.getText();
        String [] renglones= texto.split("\\n");
        for (int x=0; x<renglones.length;x++){
            boolean bandera =false;
            if(!renglones[x].equals("")){
                for(int y=0; y<Configs.EXPRESIONES.length && bandera==false;y++){
                Pattern patron =  Pattern.compile(Configs.EXPRESIONES[y]);
                Matcher matcher= patron.matcher(renglones[x]);
                if(matcher.matches()){
                    bandera =true;
                }
            }//llave for y
                 }// llave IF, FOr X
            if (bandera==false){
                txtConsola.setText(txtConsola.getText()+"\n"+"Error de sintaxis en la linea"+(x+1));
            }
        }//llave for x
        long tFinal= System.currentTimeMillis()-tInicial;
        txtConsola.setText(txtConsola.getText()+"\n"+"Compilado en : "+tFinal+" milisegundos.");
    }//llave Compilar
}
