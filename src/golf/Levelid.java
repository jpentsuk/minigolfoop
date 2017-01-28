package golf;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;
import java.util.ArrayList;

/**
 * Created by kasutaja on 25.12.2016.
 */
public class Levelid{

    // klassimuutujad
    int slaiderivaartus;
    int voitudearv=0;
    int lookidearv = 0;
    int [] a = new int [] {50,250,500,250}; // need 3 massiivi sisaldavad leveli joonte algus- ja lõpppunktide
    int [] b = new int [] {500,250,500,100}; // kuvamiseks vajalikke koordinaate
    int [] c = new int [] {800,100,800,550};
    double suundparemalevoivasakule = 0;
    double suundallavoiyles = 0;
    double [] tugevused;
    boolean pallbasseini = false;
    Circle pall,auk;
    Rectangle bassein;
    Slider slider;
    Label loogid;
    ArrayList<Button> nupud = new ArrayList<Button>();
    ArrayList<Line> seinad = new ArrayList<Line>();

    public Label lookideloendamine() // Label löökide loenduse kuvamiseks
    {
        loogid = new Label("Löökidearv  " + lookidearv);
        loogid.setLayoutX(310);
        loogid.setLayoutY(100);
        loogid.setFont(Font.font(20));
        return loogid;
    }
    public Circle pall (int a) // luuakse pall
    {
        pall = new Circle(a);
        pall.setCenterX(100);
        pall.setCenterY(350);
        pall.setFill(Color.WHITE);
        return pall;
    }
    public Circle auk (int a) // luuakse auk, kuhu pall tuleb lüüa
    {
        auk = new Circle(a);
        auk.setCenterX(80);
        auk.setCenterY(490);
        auk.setStroke(Color.RED);
        return auk;
    }
    public Rectangle veekogu() // luuakse veekogu
    {
        bassein = new Rectangle(100,100,Color.AQUA);
        bassein.setX(548);
        bassein.setY(297);
        return bassein;
    }
    public Slider slaider() //luuakse slaider
    {
        slider = new Slider();
        slider.setLayoutX(300);
        slider.setLayoutY(175);
        slider.setMin(1);
        slider.setMax(10);
        slider.setValue(1);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        return slider;
    }
    public ArrayList<Line> walls() // luuakse seinad, mis pannakse Arraylisti ja antakse neile koordinaatideks
    {                               // massiivides olnud väärtused
        for (int i = 0; i <3 ; i++) {
            seinad.add(new Line(a[0],a[1],a[2],a[3]));
            for (int j = 1; j < a.length; j++) {
                a[j]+=150;
            }
        }
        for (int i = 0; i < 2; i++) {
            seinad.add(new Line(b[0],b[1],b[2],b[3]));
            for (int j = 0; j <b.length ; j++) {
                b[j]+=150;
            }
        }
        for (int i = 0; i < 2; i++) {
            seinad.add(new Line(c[0],c[1],c[2],c[3]));
            c[0]-=750;
            c[1]+=150;
            c[2]-=750;
        }
        seinad.add(new Line(500,100,800,100));
        return seinad;
    }
    public void pidurdus()
    {
        //kui hakkab ükskõik millises suunas toimuma mingi liikumine, siis
        // liikumise kiirus väheneb iga framega 0.01 võrra
        if (suundparemalevoivasakule >0) {
            suundparemalevoivasakule-=0.01;
        }
        if(suundparemalevoivasakule <0){
            suundparemalevoivasakule+=0.01;
        }
        if(suundallavoiyles >0) {
            suundallavoiyles-=0.01;
        }
        if(suundallavoiyles <0) {
            suundallavoiyles+=0.01;
        }
    }
    public double sliderivaartus() // vastavalt kasutaja poolt antud slaideriväärtusele pannakse massiivi 10
    {                               // numbrit
        lookidearv++;
        loogid.setText("Löökidearv  " + lookidearv);// loendatakse lööke ja kuvatakse Labeli peale

        double slidervalue = Math.round(slider.getValue()); // võetakse slaideri väärtus ja ümardatakse täisarvuks
        slidervalue--; // lahutatakse üks, kuna massiivi elemendid hakkavad 0-st
        slaiderivaartus = (int) slidervalue;
        tugevused = new double[10];
        double loogitugevus = 0.5;
        for (int i = 0; i < tugevused.length; i++) { // lisatakse arvud massiivi
            tugevused[i] = loogitugevus;
            loogitugevus+=0.5;
        }
        return tugevused[slaiderivaartus]; // tagastatakse vastav väärtus
    }
    public ArrayList<Button> loonupud(int mitu) { // luuakse 9 nuppu, 3 tk reas, kokku 3 rida

        int nupunumber = 0;
        int nupuykoordinaat = 40;
        int rida1nurk = 45;
        int rida3nurk = 135;
        for (int i = 0; i < mitu; i++) { // ridade arv
            int nupuxkoordinaat = 50;
            for (int j = 0; j <mitu ; j++) { // mitu nuppu on ühes reas
                nupud.add(new Button("          "));
                nupud.get(nupunumber).setLayoutX(nupuxkoordinaat);
                nupud.get(nupunumber).setLayoutY(nupuykoordinaat);
                nupuxkoordinaat+=70;
                nupunumber++;
            }
            nupuykoordinaat+=70;
        }
        nupud.get(4).setText("Slaider"); // keskmisele nupule tekst
        for (int i = 0; i <3 ; i++) { // paneme nupud nurkade alla, et oleks suund arusaadav, kuhu lüüakse
            nupud.get(i).setRotate(rida1nurk);
            rida1nurk+=45;
        }
        for (int i = 6; i <9 ; i++) { // ka kolmanda rea nupud nurkade alla
            nupud.get(i).setRotate(rida3nurk);
            rida3nurk-=45;
        }
        return nupud; // tagastatakse arraylist
    }
    public void animatsioon() { // liikumine

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                pidurdus(); // vastavalt löögile algab palli aeglustumine
                double palliY = pall.getCenterY(); // juhendaja Krister Viirsaarelt
                double palliuusY = palliY + suundallavoiyles; // juhendajalt

                //kui puudutab vertikaalseid jooni
                if (pall.getBoundsInParent().intersects(seinad.get(3).getBoundsInParent())
                        || pall.getBoundsInParent().intersects(seinad.get(4).getBoundsInParent())
                        || pall.getBoundsInParent().intersects(seinad.get(5).getBoundsInParent())
                        || pall.getBoundsInParent().intersects(seinad.get(6).getBoundsInParent())
                        ) {
                    // siis muudab liikumissuuna vastassuunaliseks
                    suundparemalevoivasakule*=-1;
                }
                pall.setCenterX(pall.getCenterX() + suundparemalevoivasakule);//juhendajalt
                pall.setCenterY(palliuusY);// juhendajalt
                // kui puudutab horisontaalseid jooni
                if (pall.getBoundsInParent().intersects(seinad.get(0).getBoundsInParent())
                        || pall.getBoundsInParent().intersects(seinad.get(1).getBoundsInParent())
                        || pall.getBoundsInParent().intersects(seinad.get(2).getBoundsInParent())
                        || pall.getBoundsInParent().intersects(seinad.get(7).getBoundsInParent())) {
                    // samuti muudab liikumissuuna vastassuunaliseks
                    suundallavoiyles*=-1;
                }
                //kui pall on piisavalt augu keskel
                if (Math.abs(pall.getCenterX()-auk.getCenterX())<5 && Math.abs(pall.getCenterY() - auk.getCenterY())<5)
                {
                    voitudearv++;
                    stop(); // peatame animatsiooni
                    pallivahenemine(); // käivitame vähendamise meetodi
                }
                if (pall.getBoundsInParent().intersects(bassein.getBoundsInParent())) // kui pall puutub basseini
                {

                    stop();
                    pallivahenemine();
                    pallbasseini = true;
                }
            }
        }.start();
        nuppudefunktsioonid(); // nuppudele antakse funktsioonid
    }

    public void nuppudefunktsioonid()
    {
        nupud.get(5).setOnAction(event -> { // kui kasutaja lööb palli paremale
            sliderivaartus();
            suundparemalevoivasakule += tugevused[slaiderivaartus];
        });
        nupud.get(3).setOnAction(event -> { // kui lööb vasakule
            sliderivaartus();
            suundparemalevoivasakule -= tugevused[slaiderivaartus];
        });
        nupud.get(1).setOnAction(event -> { // kui lööb üles
            sliderivaartus();
            suundallavoiyles -= tugevused[slaiderivaartus];
        });
        nupud.get(7).setOnAction(event -> { // kui lööb alla
            sliderivaartus();
            suundallavoiyles += tugevused[slaiderivaartus];
        });
        nupud.get(2).setOnMousePressed(event -> { // kui lööb kirdesse
            sliderivaartus();
            suundparemalevoivasakule += tugevused[slaiderivaartus];
            suundallavoiyles -= tugevused[slaiderivaartus];
        });
        nupud.get(8).setOnMousePressed(event -> { // kui lööb kagusse
            sliderivaartus();
            suundparemalevoivasakule += tugevused[slaiderivaartus];
            suundallavoiyles += tugevused[slaiderivaartus];
        });
        nupud.get(0).setOnMousePressed(event -> { // kui lööb loodesse
            sliderivaartus();
            suundparemalevoivasakule -= tugevused[slaiderivaartus];
            suundallavoiyles -= tugevused[slaiderivaartus];
        });
        nupud.get(6).setOnMousePressed(event -> { // kui lööb edelasse
            sliderivaartus();
            suundparemalevoivasakule =- tugevused[slaiderivaartus];
            suundallavoiyles += tugevused[slaiderivaartus];
        });
        nupud.get(4).setOnAction(event -> { // slaider lähtestatakse
            slider.setValue(1);
        });
    }
    public void pallivahenemine() { // vähendame 2 sek jooksul palliraadiust, tekitades efekti, et pall läks auku või vette
        Timeline timeline = new Timeline();// Timeline kood on Oracle Help-st
        KeyValue kv = new KeyValue(pall.radiusProperty(), 0);
        final KeyFrame kf = new KeyFrame(Duration.millis(2000), kv);
        timeline.getKeyFrames().add(kf);
        timeline.play();
        timeline.setOnFinished(event -> {
            if (voitudearv==2)
            {
                mangulopp(); // kui on võidetud mõlemad levelid, siis suunatakse mängu lõppu
            }
            else if(voitudearv==1)
            {
                if (pallbasseini==true) // kui ollakse teises levelis ja pall läheb vette, siis laetakse uuesti teine level
                {
                    animatsioon();
                    pall.setCenterX(100);
                    pall.setCenterY(350);
                    pall.setRadius(10);
                }
                else if(pallbasseini==false) // kui võidetakse teine level, siis on võitude arv 2
                {
                    uuslevel();
                    animatsioon();
                }
                pallbasseini = false; // taastame muutuja algse väärtuse
            }
            else if(voitudearv==0) // kui ei ole ühtegi levelit võidetud, siis laetakse uuesti esimene level
            {
                animatsioon();
                pall.setCenterX(100);
                pall.setCenterY(350);
                pall.setRadius(10);
            }
            suundparemalevoivasakule =0; // anname muutujatele enne iga lööki väärtuse 0
            suundallavoiyles =0;
        });
    }
    public void uuslevel() // loome uue leveli, muutes olemasoleva leveli komponentide asukohti
    {
        lookidearv = 0;
        loogid.setText("Löökidearv  " + lookidearv); // löökide lugemine nulli
        seinad.get(6).setStartY(seinad.get(6).getStartY()+50); // nihutame seinu
        for (int i = 0; i <2 ; i++) {
            seinad.get(i).setEndY(seinad.get(i).getEndY()+50);
            seinad.get(i).setStartY(seinad.get(i).getStartY()+50);
        }
        for (int i = 3; i <5 ; i++) {
            seinad.get(i).setStartY(seinad.get(i).getStartY()+50);
        }
        for (int i = 0; i <8 ; i++) {
            seinad.get(i).setStrokeWidth(5);
        }
        bassein.setWidth(400); // veekogu
        bassein.setHeight(90);
        bassein.setX(200);
        bassein.setY(327);
        pall.setCenterX(100); // toome palli algsesse asendisse
        pall.setCenterY(350);
        pall.setRadius(10);
    }
    public void mangulopp() // peidame kõik komponendid, v.a. Label
    {
        for (int i = 0; i <8 ; i++) {
            seinad.get(i).setVisible(false);
        }
        for (int i = 0; i <9 ; i++) {
            nupud.get(i).setVisible(false);
        }
        bassein.setVisible(false);
        auk.setVisible(false);
        slider.setVisible(false);
        loogid.setText("  Mäng läbitud!\nAkna sulgemiseks\n  klikkige kirjale!");
        loogid.setFont(Font.font(40));
        loogid.setOnMousePressed(event -> Platform.exit()); // klikkides Labeli peal, saab väljuda

    }
}












