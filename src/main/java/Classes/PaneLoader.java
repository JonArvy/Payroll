package Classes;

import cw.payroll.Main;
import javafx.scene.layout.Pane;

import java.net.URL;

public class PaneLoader {
    private Pane pane;

    public Pane getPane() {
        try {
            URL fileURL = Main.class.getResource("");
            if (fileURL == null) {
                throw new java.io.FileNotFoundException("File not found");
            }
            //pane = new PaneLoader().load(fileURL);
        } catch (Exception e) {

        }
        return pane;
    }
}
