package golf;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        new Manguaken(); // käivitame konstruktori just siit!
    }
    public static void main(String[] args) {
        launch(args);
    }
}
