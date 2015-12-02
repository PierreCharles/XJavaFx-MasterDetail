package model;

import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static model.Bean.ADD;

/**
 * Classe représentant un royaume
 */
public class RoyaumeDesArcsEnCiel implements Bean, Serializable {
    
    private final PropertyChangeSupport bean = new PropertyChangeSupport(this);
    private final ArrayList<ArcEnCiel> lesArcsEnCiel = new ArrayList<>();
    /**
     * Constante identifier la liste des arc en ciel
     */
    public static final String PROP_LIST = "Liste_arcenciel";
    
    /**
     * Méthode pour ajouter un arc en ciel au royaume
     * @param unArcEnCiel l'Arc en ciel à ajouter
     */
    public void addArcEnCiel(ArcEnCiel unArcEnCiel) {
        lesArcsEnCiel.add(unArcEnCiel);
        fireIndexedPropertyChange(PROP_LIST+ADD, lesArcsEnCiel.indexOf(unArcEnCiel), null, unArcEnCiel);
    }
    
    /**
     * Retourne la liste protégée des arcs en ciel
     * @return La liste des arcs en ciel
     */
    public List<ArcEnCiel> getLesArcsEnCiel() {
        return Collections.unmodifiableList(lesArcsEnCiel);
    }

    /**
     * Génère un PropertychangeSupport pour le traitement des évènements
     * @return le PropertyChangeSupport
     */
    @Override
    public PropertyChangeSupport getPropertyChangeSupport() {
        return bean;
    }
    
    
    
}
