package viewmodel;

import java.beans.IndexedPropertyChangeEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ArcEnCiel;
import model.Couleur;

/**
 * Classe représent un Arc en ciel
 */
public class ArcEnCielViewModel implements PropertyChangeListener {

    private final ArcEnCiel lArcEnCiel;

    private final ObservableList<CouleurViewModel> lesCouleursObservables = FXCollections.observableArrayList();

    /**
     * Propriété contenant la liste des couleurs
     */
    private final ListProperty<CouleurViewModel> lesCouleurs = new SimpleListProperty<>(lesCouleursObservables);
        public ObservableList<CouleurViewModel> getLesCouleurs() {return lesCouleurs.get();}
        public void setLesCouleurs(ObservableList<CouleurViewModel> value) {lesCouleurs.set(value);}
        public ListProperty<CouleurViewModel> lesCouleursProperty() {return lesCouleurs;}
    
    /**
     * Propriété contenant le nom de l'arc en ciel
     */
    private final StringProperty nom = new SimpleStringProperty();
        public String getNom() {return nom.get();}
        public void setNom(String value) {nom.set(value);}
        public StringProperty nomProperty() {return nom;}    
    
    /**
     * Constructeur d'un arc en ciel VM
     * @param lArcEnCiel l'ArcEnCiel concerné
     */
    public ArcEnCielViewModel(ArcEnCiel lArcEnCiel) {
        this.lArcEnCiel = lArcEnCiel;
        nom.setValue(lArcEnCiel.getNom());
        recopierArcEnCiel();
        lArcEnCiel.addPropertyChangeListener(this);
    }

    /**
     * Méthode permettant de créer une couleur dans l'arc en ciel
     * @param rouge partie rouge
     * @param vert partie verte
     * @param bleu partie bleue
     */
    public void creerCouleur(int rouge, int vert, int bleu) {
        Couleur laNouvelleCouleur = new Couleur(rouge, vert, bleu);
        lArcEnCiel.addCouleur(laNouvelleCouleur);
    }
    
    /**
     * Méthode permettant d'agir lorsqu'un évènement provient des Beans écoutés
     * @param evt 
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(ArcEnCiel.PROP_LIST + ArcEnCiel.ADD)) {
            lesCouleursObservables.add(((IndexedPropertyChangeEvent) evt).getIndex(), new CouleurViewModel((Couleur) evt.getNewValue()));
        }
        if (evt.getPropertyName().equals(ArcEnCiel.PROP_LIST + ArcEnCiel.DELETE)) {
            lesCouleursObservables.remove(((IndexedPropertyChangeEvent) evt).getIndex());
        }
        if (evt.getPropertyName().equals(ArcEnCiel.PROP_NOM)) {
            nom.setValue(evt.getNewValue().toString());
        }
    }  
    
    private void recopierArcEnCiel() {
        lArcEnCiel.getLesCouleurs().forEach(uneCouleur -> lesCouleursObservables.add(new CouleurViewModel(uneCouleur)));
    }
}
