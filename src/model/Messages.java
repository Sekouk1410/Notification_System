package model;

public class Messages {
    private String objet;
    private String contenu;
    private String emailMessaExp;

    public Messages() {
    }

    public Messages(String objet, String contenu, String emailMessaExp) {
        this.objet = objet;
        this.contenu = contenu;
        this.emailMessaExp = emailMessaExp;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getEmailMessaExp() {
        return emailMessaExp;
    }

    public void setEmailMessaExp(String emailMessaExp) {
        this.emailMessaExp = emailMessaExp;
    }
}
