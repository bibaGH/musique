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
public class Piste implements Serializable {
    private Integer id;
    private String titre;
    private String duree;
    private Album album;

    public Piste() {
    }

    public Piste(String titre, String duree, Album album) {
        this.titre = titre;
        this.duree = duree;
        this.album = album;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
    
    
}
