package client;



import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.rmi.RemoteException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Communication c = new Communication("TEST");

        Controller controller = new Controller(c);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        loader.setController(controller);
        Parent root = loader.load();

        Platform.setImplicitExit(true);
        primaryStage.setTitle("Chat");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.setOnCloseRequest(
                event -> {
                    try {
                        c.close();
                        System.out.print("Chiudendo");
                        Platform.exit();
                        System.exit(0);

                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
        );

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }




}
