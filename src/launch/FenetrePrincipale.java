package launch;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class FenetrePrincipale extends Application {

    /**
     * Méthode lançant la création de la fenêtre. Ne pas invoquer.
     * @param primaryStage fenêtre principale fournie par le système
     * @throws IOException lorsque le FXML n'est pas trouvé
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        
        Scene scene = new Scene(FXMLLoader.load(this.getClass().getResource("/fxml/FenetrePrincipale.fxml")));
        primaryStage.setScene(scene);
        primaryStage.setTitle("Le royaume des Arcs en ciel");
        primaryStage.show();
    }
    
}
