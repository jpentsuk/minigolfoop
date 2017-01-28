package golf;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Created by kasutaja on 23.12.2016.
 */
public class Manguaken {

    Levelid komponendid = new Levelid();
    Pane pane = new Pane();
    Scene stseen2 = new Scene(pane,400, 400);
    Stage aken2 = new Stage();
    Button btn;
    Label tutvustus;

    public Manguaken()
    {
        tervitusaken(); // kuvame akna
        mangualustusnuppjatutvustus(); // kuvame nupu
        btn.setOnAction(event -> {
            gamemainwindow();  // nuppu vajutades käivitatakse meetod, mis toob aknasse vajalikud komponendid
            aknasuurendamine();
        });
    }
    public void tervitusaken()
    {
        pane.setStyle("-fx-background-color: red");
        aken2.setScene(stseen2);
        aken2.show();
    }
    public void mangualustusnuppjatutvustus()
    {
        tutvustus = new Label("Tere tulemast mängima minigolfi ! \n Peale mängu käivitamist valige \n" +
                "       slaiderilt löögitugevus \n         ja vajutage vastavat \n               lööginuppu.");
        tutvustus.setLayoutX(90);
        tutvustus.setLayoutY(120);
        tutvustus.setFont(Font.font(16));
        btn = new Button("Käivita mäng");
        btn.setFont(Font.font(16));
        btn.setLayoutX(135);
        btn.setLayoutY(40);
        pane.getChildren().addAll(btn,tutvustus);

    }
    public void gamemainwindow()
    {
        pane.getChildren().removeAll(btn,tutvustus);
        pane.setStyle("-fx-background-color: green");

        pane.getChildren().addAll(komponendid.walls()); // lisame levelisse seinad
        pane.getChildren().addAll(komponendid.loonupud(3)); // nupud
        pane.getChildren().addAll(komponendid.auk(12), komponendid.pall(10), komponendid.slaider(),
                komponendid.veekogu(), komponendid.lookideloendamine());
        komponendid.animatsioon();// augu, palli, slaideri, veekogu ja Label i löökide loendamiseks
    }
    public void aknasuurendamine()//Timeline kasutamise kood pärineb Oracle Help-st
    {
        Timeline timeline = new Timeline(); // animatsiooniga muudame väikse akna suureks
        KeyValue kv = new KeyValue(aken2.minWidthProperty(),900);
        KeyValue kv1 = new KeyValue(aken2.minHeightProperty(),600);
        final KeyFrame kf = new KeyFrame(Duration.millis(1500), kv,kv1); // 1,5 sek jooksul
        timeline.getKeyFrames().add(kf);
        timeline.play();
    }
}