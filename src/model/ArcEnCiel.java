package model;

import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Classe représentant un arc en ciel
 */
public class ArcEnCiel implements Bean, Serializable {
    
    private final PropertyChangeSupport bean = new PropertyChangeSupport(this);
    private final ArrayList<Couleur> lesCouleurs = new ArrayList<>();
    /**
     * Constante identifiant la liste de Couleurs
     */
    public static final String PROP_LIST = "Liste_couleur";
    private String nom;
    /**
     * Constante identifiant le nom de l'Arc en ciel
     */
    public static final String PROP_NOM = "nom_arc_en_ciel";

    /**
     * Constructeur d'un arc en ciel
     * @param nom Nom de l'arc en ciel
     */
    public ArcEnCiel(String nom) {
        this.nom = nom;
    }

    /**
     * Getteur de nom
     * @return Retourne le nom de l'arc en ciel
     */
    public String getNom() {
        return nom;
    }

    /**
     * Setteur de nom
     * @param nom Nom de l'arc en ciel
     */
    public void setNom(String nom) {
        String old = this.nom;
        this.nom = nom;
        firePropertyChange(PROP_NOM, old, nom);
    }
    
    /**
     * Méthode permettant d'ajouter une couleur à l'arc en ciel
     * @param uneCouleur Couleur à ajouter
     */
    public void addCouleur(Couleur uneCouleur) {
        lesCouleurs.add(uneCouleur);
        fireIndexedPropertyChange(PROP_LIST+ADD, lesCouleurs.indexOf(uneCouleur), null, uneCouleur);
    }
    
    /**
     * Retourne une copie protégée de la liste de couleurs
     * @return La liste de couleurs
     */
    public List<Couleur> getLesCouleurs() {
        return Collections.unmodifiableList(lesCouleurs);
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
