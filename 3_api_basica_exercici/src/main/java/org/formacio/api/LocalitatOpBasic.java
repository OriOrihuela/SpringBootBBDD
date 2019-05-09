package org.formacio.api;


import org.springframework.stereotype.Component;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 * Modifica aquesta classe per tal que sigui un component Spring que realitza les
 * operacions de persistencia tal com indiquen les firmes dels metodes
 */

@Component
public class LocalitatOpBasic {

    /* ---- Properties ---- */
    @PersistenceContext
    private EntityManager entityManager = null;


    /* ---- Getters ---- */
    public EntityManager getEntityManager() {
        return entityManager;
    }


    /* ---- Behaviours ---- */
    public Localitat carrega(long id) {
        Localitat localitat = getEntityManager().find(Localitat.class, id);
        return localitat;
    }

    @Transactional
    public void alta(String nom, Integer habitants) {
        Localitat localitat = new Localitat();
        localitat.setNom(nom);
        localitat.setHabitants(habitants);
        getEntityManager().persist(localitat);
    }

    @Transactional
    public void elimina(long id) {
        Localitat localitat = carrega(id);
        if (localitat != null) {
            getEntityManager().remove(localitat);
        }
    }

    @Transactional
    public void modifica(Localitat localitat) {
        getEntityManager().merge(localitat);
    }

}
