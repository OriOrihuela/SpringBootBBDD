package org.formacio.setmana1.mvc;

import org.formacio.setmana1.data.LlibreOpsBasic;
import org.formacio.setmana1.domini.Llibre;
import org.formacio.setmana1.domini.Recomanacio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Completeu el codi per tal que la peticio /titol retorni el titol del llibre
 * amb l'isbn enviat com a parametre
 * <p>
 * Aquesta classe ha de fer un de LlibreOpsBasic: No poseu aqui codi de persistencia!
 */

@RestController
public class LlibreController {

    // Per aqui vos fara falta una referencia a un LlibreOpsBasic
    @Autowired
    private LlibreOpsBasic llibreOpsBasic = null;

    // Aquestes anotacions i firma del metode ja son correctes
    @RequestMapping(path = "/recomanacio")
    public Recomanacio obteLlibre(String isbn) {
        // Feis que retorni la recomanacio per el llibre indicat
        // emprat LlibreOpsBasic
        return llibreOpsBasic.recomenacioPer(isbn);
    }
}
