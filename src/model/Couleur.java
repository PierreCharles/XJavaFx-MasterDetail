package model;

import java.beans.PropertyChangeSupport;
import java.io.Serializable;

/**
 * Classe représentant une couleur d'un Arc en ciel
 */
public class Couleur implements Bean, Serializable {
    
    private final PropertyChangeSupport bean = new PropertyChangeSupport(this);
    
    private int rouge;
    /**
     * Constante identifiant l'attribut rouge
     */
    public static final String PROP_ROUGE = "Rouge";
    
    private int vert;
    /**
     * Constante identifiant l'attribut vert
     */
    public static final String PROP_VERT = "Vert";
    
    private int bleu;
    /**
     * Constante identifiant l'attribut bleu
     */
    public static final String PROP_BLEU = "Bleu";

    /**
     * Constructeur d'une couleur
     * @param rouge partie rouge
     * @param vert partie verte
     * @param bleu partie bleue
     */
    public Couleur(int rouge, int vert, int bleu) {
        this.rouge = rouge;
        this.vert = vert;
        this.bleu = bleu;
    }

    /**
     * Getteur de Rouge
     * @return la partie rouge
     */
    public int getRouge() {
        return rouge;
    }

    /**
     * Setteur de Rouge
     * @param rouge La partie rouge
     */
    public void setRouge(int rouge) {
        int old = this.rouge;
        this.rouge = rouge;
        firePropertyChange(PROP_ROUGE, old, rouge);
    }

    /**
     * Getteur de Vert
     * @return la partie verte
     */
    public int getVert() {
        return vert;
    }

    /**
     * Setteur de Vert
     * @param vert la partie verte
     */
    public void setVert(int vert) {
        int old = this.vert;
        this.vert = vert;
        firePropertyChange(PROP_VERT, old, vert);
    }

    /**
     * Getteur de bleu
     * @return la partie bleue
     */
    public int getBleu() {
        return bleu;
    }

    /**
     * Setteur de bleu
     * @param bleu la partie bleue
     */
    public void setBleu(int bleu) {
        int old = this.bleu;
        this.bleu = bleu;
        firePropertyChange(PROP_BLEU, old, bleu);
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
