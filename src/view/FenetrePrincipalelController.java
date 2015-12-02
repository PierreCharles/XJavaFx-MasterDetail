package view;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import viewmodel.ArcEnCielViewModel;
import viewmodel.CouleurViewModel;
import viewmodel.RoyaumeDesArcsEnCielViewModel;

/**
 * FXML Controller class
 */
public class FenetrePrincipalelController {

    private final FileChooser.ExtensionFilter exf = new FileChooser.ExtensionFilter("Fichier royaume", "*.roy");
    
    @FXML
    private RoyaumeDesArcsEnCielViewModel leRoyaume;

    @FXML
    private ListView<ArcEnCielViewModel> listeDesArcsEnCiel;

    @FXML
    private ListView<CouleurViewModel> listeDesCouleurs;
    
    @FXML
    private ColorPicker laCouleurChoisie;

    @FXML
    private void clicCharger() {
        FileChooser fenetreChoixFichier = new FileChooser();
        fenetreChoixFichier.setTitle("Charger un royaume...");     
        fenetreChoixFichier.getExtensionFilters().add(exf);
        fenetreChoixFichier.setSelectedExtensionFilter(exf);
        File fichier = fenetreChoixFichier.showOpenDialog(listeDesCouleurs.getScene().getWindow());
        if (fichier != null) {
            try {
                listeDesCouleurs.itemsProperty().unbind();
                if (listeDesCouleurs.getItems() != null) {
                    listeDesCouleurs.getItems().clear();
                }
                listeDesArcsEnCiel.itemsProperty().unbind();
                if (listeDesArcsEnCiel.getItems() != null) {
                    listeDesArcsEnCiel.getItems().clear();
                }
                leRoyaume.load(fichier);
                listeDesArcsEnCiel.itemsProperty().bind(leRoyaume.lesArcsEnCielProperty());
                listeDesCouleurs.itemsProperty().bind(Bindings.select(listeDesArcsEnCiel, "selectionModel", "selectedItem", "lesCouleurs"));
            } catch (IOException | ClassNotFoundException ex) {
                showAlert(ex.getMessage());
            }
        }
    }

    @FXML
    private void clicQuitter() {
        listeDesCouleurs.getScene().getWindow().hide();
    }

    @FXML
    private void clicSauvegarder() {
        FileChooser fenetreChoixFichier = new FileChooser();
        fenetreChoixFichier.setTitle("Enregistrer le royaume sous...");
        fenetreChoixFichier.getExtensionFilters().add(exf);
        fenetreChoixFichier.setSelectedExtensionFilter(exf);
        File fichier = fenetreChoixFichier.showSaveDialog(listeDesCouleurs.getScene().getWindow());
        if (fichier != null) {
            try {
                leRoyaume.save(fichier);
            } catch (IOException ex) {
                showAlert(ex.getMessage());
            }
        }
    }
    
    @FXML
    private void creerCouleur(){
        if (listeDesArcsEnCiel.getSelectionModel().getSelectedIndex() != -1) {
            Color laCouleur= laCouleurChoisie.getValue();
            listeDesArcsEnCiel.getSelectionModel().getSelectedItem().creerCouleur(Double.valueOf(laCouleur.getRed()*255).intValue(), Double.valueOf(laCouleur.getGreen()*255).intValue(), Double.valueOf(laCouleur.getBlue()*255).intValue());
        }
    }

    @FXML
    private void creerArcEnCiel() {
        TextInputDialog fenetreSaisie = new TextInputDialog();
        fenetreSaisie.setTitle("Créer un nouvel arc en ciel");
        fenetreSaisie.setContentText("Saisissez le nom du nouvel arc en ciel : ");
        fenetreSaisie.setHeaderText(null);
        Optional<String> retour = fenetreSaisie.showAndWait();
        if (retour.isPresent()) {
            leRoyaume.creerArcEnCiel(retour.get());
        }
        
    }
    
    /**
     * Méthode appelée à la création de la fenêtre
     */
    public void initialize() {        
        listeDesArcsEnCiel.setCellFactory(param -> new ListCell<ArcEnCielViewModel>() {
            @Override
            protected void updateItem(ArcEnCielViewModel item, boolean empty) {
                super.updateItem(item, empty);
                if (!empty) {
                    textProperty().bind(item.nomProperty());
                } else {
                    textProperty().unbind();
                    setText("");
                }
            }
        });
        
        listeDesCouleurs.setCellFactory(param -> new ListCell<CouleurViewModel>(){

            @Override
            protected void updateItem(CouleurViewModel item, boolean empty) {
                super.updateItem(item, empty);
                if (!empty) {
                    textProperty().bind(item.rougeProperty().concat(new SimpleStringProperty("/").concat(item.vertProperty().concat(new SimpleStringProperty("/").concat(item.bleuProperty())))));
                    setTextFill(new Color(Integer.valueOf(item.getRouge())/255., Integer.valueOf(item.getVert())/255., Integer.valueOf(item.getBleu())/255., 1));
                } else {
                    textProperty().unbind();
                    setText("");
                }
            }
            
        });
    }
    
    private void showAlert(String message) {
        Alert messageAlerte = new Alert(Alert.AlertType.ERROR);
        messageAlerte.setTitle("Erreur à la création d'un étudiant");
        messageAlerte.setContentText(message);
        messageAlerte.setHeaderText(null);
        messageAlerte.showAndWait();
    }
}
