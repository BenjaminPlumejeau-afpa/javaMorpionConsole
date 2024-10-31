public class Joueur {
    private String nom;
    private char symbole;

    public Joueur(String nom, char symbole) {
        this.setNom(nom);
        this.setSymbole(symbole);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public char getSymbole() {
        return symbole;
    }

    public void setSymbole(char symbole) {
        this.symbole = symbole;
    }
}
