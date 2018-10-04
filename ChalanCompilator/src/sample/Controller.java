package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Controller extends Application {
    private Stage stage;
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
}
