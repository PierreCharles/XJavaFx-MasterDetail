package model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Interface complète représentant un Bean. Un Bean est un objet métier
 * idéalement équipé pour du MVVM en JavaFX.
 */
public interface Bean {
    public static final String ADD    = "CHANGE_SUPPORT_BEAN_ADD";
    public static final String DELETE = "CHANGE_SUPPORT_BEAN_DELETE";
    public static final String UPDATE = "CHANGE_SUPPORT_BEAN_UPDATE";

    default public void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        getPropertyChangeSupport().firePropertyChange(propertyName, oldValue, newValue);
    }

    default public void addPropertyChangeListener(PropertyChangeListener listener) {
        getPropertyChangeSupport().addPropertyChangeListener(listener);
    }

    default public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        getPropertyChangeSupport().addPropertyChangeListener(propertyName, listener);
    }

    default public void removePropertyChangeListener(PropertyChangeListener listener) {
        getPropertyChangeSupport().removePropertyChangeListener(listener);
    }

    default public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        getPropertyChangeSupport().removePropertyChangeListener(propertyName, listener);
    }

    default public boolean hasListeners(String propertyName) {
        return getPropertyChangeSupport().hasListeners(propertyName);
    }

    default public void fireIndexedPropertyChange(String propertyName, int index, boolean oldValue, boolean newValue) {
        getPropertyChangeSupport().fireIndexedPropertyChange(propertyName, index, oldValue, newValue);
    }

    default public void fireIndexedPropertyChange(String propertyName, int index, int oldValue, int newValue) {
        getPropertyChangeSupport().fireIndexedPropertyChange(propertyName, index, oldValue, newValue);
    }

    default public void fireIndexedPropertyChange(String propertyName, int index, Object oldValue, Object newValue) {
        getPropertyChangeSupport().fireIndexedPropertyChange(propertyName, index, oldValue, newValue);
    }

    default public void firePropertyChange(PropertyChangeEvent event) {
        getPropertyChangeSupport().firePropertyChange(event);
    }

    default public void firePropertyChange(String propertyName, boolean oldValue, boolean newValue) {
        getPropertyChangeSupport().firePropertyChange(propertyName, oldValue, newValue);
    }

    default public void firePropertyChange(String propertyName, int oldValue, int newValue) {
        getPropertyChangeSupport().firePropertyChange(propertyName, oldValue, newValue);
    }

    default public PropertyChangeListener[] getPropertyChangeListeners(String propertyName) {
        return getPropertyChangeSupport().getPropertyChangeListeners(propertyName);
    }

    default public PropertyChangeListener[] getPropertyChangeListeners() {
        return getPropertyChangeSupport().getPropertyChangeListeners();
    }

    PropertyChangeSupport getPropertyChangeSupport();
}
