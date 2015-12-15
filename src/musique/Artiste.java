/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musique;

import java.io.Serializable;

/**
 *
 * @author Nabila
 */
public class Artiste implements Serializable {
    private Integer id;
    private String prenom;

    public Artiste() {
    }

    public Artiste(String prenom) {
        this.prenom = prenom;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
}
