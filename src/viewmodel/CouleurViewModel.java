package viewmodel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Couleur;

/**
 * Classe représentant une couleur
 */
public class CouleurViewModel implements PropertyChangeListener {
    
    private final Couleur laCouleur;
    
    /**
     * Propriété représentant le rouge
     */
    private final StringProperty rouge = new SimpleStringProperty();
        public String getRouge() {return rouge.get();}
        public void setRouge(String value) {rouge.set(value);}
        public StringProperty rougeProperty() {return rouge;}

    /**
     * Propriété représentant le vert
     */
    private final StringProperty vert = new SimpleStringProperty();
        public String getVert() {return vert.get();}
        public void setVert(String value) {vert.set(value);}
        public StringProperty vertProperty() {return vert;}
    
    /**
     * Propriété représentant le bleu
     */
    private final StringProperty bleu = new SimpleStringProperty();
        public String getBleu() {return bleu.get();}
        public void setBleu(String value) {bleu.set(value);}
        public StringProperty bleuProperty() {return bleu;}
    
    /**
     * Constructeur de CouleurViewModel
     * @param laCouleur couleur à surveiller
     */
    public CouleurViewModel(Couleur laCouleur) {
        this.laCouleur = laCouleur;
        rouge.setValue(Integer.toString(laCouleur.getRouge()));
        vert.setValue(Integer.toString(laCouleur.getVert()));
        bleu.setValue(Integer.toString(laCouleur.getBleu()));
        laCouleur.addPropertyChangeListener(this);
    }
    
    /**
     * Méthode permettant d'agir lorsqu'un évènement provient des Beans écoutés
     * @param evt 
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(Couleur.PROP_BLEU)) {
            bleu.setValue(Integer.toString((int) evt.getNewValue()));
        }
        if (evt.getPropertyName().equals(Couleur.PROP_VERT)) {
            vert.setValue(Integer.toString((int) evt.getNewValue()));
        }
        if (evt.getPropertyName().equals(Couleur.PROP_ROUGE)) {
            rouge.setValue(Integer.toString((int) evt.getNewValue()));
        }
    }  
}
