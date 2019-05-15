package org.formacio.setmana1.data;


import org.formacio.setmana1.domini.Llibre;
import org.formacio.setmana1.domini.Recomanacio;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 * Modifica aquesta classe per tal que sigui un component Spring que realitza les
 * operacions de persistencia tal com indiquen les firmes dels metodes
 */
@Repository
public class LlibreOpsBasic {

    /* ---- Properties of the class ---- */
    @PersistenceContext
    private EntityManager entityManager = null;


    /* ---- Getters ---- */
    public EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * Retorna el llibre amb l'ISBN indicat o, si no existeix, llança un LlibreNoExisteixException
     */
    public Llibre carrega(String isbn) throws LlibreNoExisteixException {
        Llibre llibre = getEntityManager().find(Llibre.class, isbn);
        if (llibre != null) {
            return llibre;
        } else {
            throw new LlibreNoExisteixException();
        }
    }

    /**
     * Sense sorpreses: dona d'alta un nou llibre amb les propietats especificaques
     */
    @Transactional
    public void alta(String isbn, String autor, Integer pagines, Recomanacio recomanacio, String titol) {
        Llibre llibre = new Llibre();
        llibre.setIsbn(isbn);
        llibre.setAutor(autor);
        llibre.setTitol(titol);
        llibre.setPagines(pagines);
        llibre.setRecomanacio(recomanacio);
        getEntityManager().persist(llibre);
    }

    /**
     * Elimina, si existeix, un llibre de la base de dades
     *
     * @param isbn del llibre a eliminar
     * @return true si s'ha esborrat el llibre, false si no existia
     */
    @Transactional
    public boolean elimina(String isbn) {
        try {
            Llibre llibre = carrega(isbn);
            getEntityManager().remove(llibre);
            return true;
        } catch (LlibreNoExisteixException e) {
            return false;
        }
    }

    /**
     * Guarda a bbdd l'estat del llibre indicat
     */
    @Transactional
    public void modifica(Llibre llibre) {
        getEntityManager().merge(llibre);
    }

    /**
     * Retorna true o false en funcio de si existeix un llibre amb aquest ISBN
     * (Aquest metode no llanca excepcions!)
     */
    public boolean existeix(String isbn) {
        return false;
    }

    /**
     * Retorna quina es la recomanacio per el llibre indicat
     * Si el llibre indicat no existeix, retorna null
     */
    public Recomanacio recomenacioPer(String isbn) {
        return null;
    }

}
