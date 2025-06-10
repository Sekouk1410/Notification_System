package model;

public class Employe {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private boolean estAdmin = false;

    public Employe(String nom, String prenom, String email, String motDePasse) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
    }
}
