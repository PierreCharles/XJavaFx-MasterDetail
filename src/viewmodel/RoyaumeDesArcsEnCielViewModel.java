package viewmodel;

import java.beans.IndexedPropertyChangeEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ArcEnCiel;
import model.RoyaumeDesArcsEnCiel;

/**
 * Classe représetant un royaume
 */
public class RoyaumeDesArcsEnCielViewModel implements PropertyChangeListener {
    
    private RoyaumeDesArcsEnCiel leRoyaume = new RoyaumeDesArcsEnCiel();

    private final ObservableList<ArcEnCielViewModel> lesArcsEnCielObservables = FXCollections.observableArrayList();
    
    /**
     * Propriété représentant la liste des arcs en ciel
     */
    private final ListProperty<ArcEnCielViewModel> lesArcsEnCiel = new SimpleListProperty<>(lesArcsEnCielObservables);
        public ObservableList<ArcEnCielViewModel> getLesArcsEnCiel() {return lesArcsEnCiel.get();}
        public void setLesArcsEnCiel(ObservableList<ArcEnCielViewModel> value) {lesArcsEnCiel.set(value);}
        public ListProperty<ArcEnCielViewModel> lesArcsEnCielProperty() {return lesArcsEnCiel;}
        
    /**
     * Constructeur de RoyaumeDesArcsEnCielVM
     */            
    public RoyaumeDesArcsEnCielViewModel(){
        leRoyaume.getLesArcsEnCiel().forEach(unArcEnCiel->lesArcsEnCiel.add(new ArcEnCielViewModel(unArcEnCiel)));
        leRoyaume.addPropertyChangeListener(this);
    }

    /**
     * Méthode permettant la création d'un arc en ciel
     * @param nomArcEnCiel nom de l'arc en ciel à créer
     */
    public void creerArcEnCiel(String nomArcEnCiel) {
        ArcEnCiel lArc = new ArcEnCiel(nomArcEnCiel);
        leRoyaume.addArcEnCiel(lArc);
    }
    
    /**
     * Méthode permettant de sauvegarder le royaume
     * @param chemin fichier dans lequel il faut sauvegarder
     * @throws IOException 
     */
    public void save(File chemin) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(chemin))) {
            oos.writeObject(leRoyaume);
        }
    }
    
    /**
     * Méthode permettant de charger un royaume
     * @param chemin fichier dans lequel il faut charger
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public void load(File chemin) throws IOException, ClassNotFoundException {
        try (ObjectInputStream oos = new ObjectInputStream(new FileInputStream(chemin))) {
            leRoyaume.removePropertyChangeListener(this);
            leRoyaume = (RoyaumeDesArcsEnCiel) oos.readObject();
            leRoyaume.addPropertyChangeListener(this);
            recopierRoyaume();
        } catch (Exception e) {
            leRoyaume.addPropertyChangeListener(this);
            throw e;
        }
    }
    
    /**
     * Méthode permettant d'agir lorsqu'un évènement provient des Beans écoutés
     * @param evt 
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(RoyaumeDesArcsEnCiel.PROP_LIST+RoyaumeDesArcsEnCiel.ADD)) {
            lesArcsEnCielObservables.add(((IndexedPropertyChangeEvent)evt).getIndex(), new ArcEnCielViewModel((ArcEnCiel)evt.getNewValue()));            
        }
        if (evt.getPropertyName().equals(RoyaumeDesArcsEnCiel.PROP_LIST+RoyaumeDesArcsEnCiel.DELETE)) {
            lesArcsEnCielObservables.remove(((IndexedPropertyChangeEvent)evt).getIndex());
        }
    }

    private void recopierRoyaume() {
        leRoyaume.getLesArcsEnCiel().forEach(unArc -> lesArcsEnCielObservables.add(new ArcEnCielViewModel(unArc)));
    }
}
