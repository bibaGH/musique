/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musique;

/**
 *
 * @author Nabila
 */
public class Album {
    private Integer id;
    private String titre;
    private Artiste artiste;
    private Genre genre;

    public Album() {
    }

    public Album(String titre, Artiste artiste, Genre genre) {
        this.titre = titre;
        this.artiste = artiste;
        this.genre = genre;
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

    public Artiste getArtiste() {
        return artiste;
    }

    public void setArtiste(Artiste artiste) {
        this.artiste = artiste;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
    
}