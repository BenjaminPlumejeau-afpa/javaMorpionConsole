import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // initialisation de la partie
        char[][] grilleDeJeu = initialisation();
        afficherGrille(grilleDeJeu);
        char joueur1 = 'O';
        char joueur2 = 'X';

        // tant que la partie n'est pas finie continuer vaut True
        boolean continuer = true;

        while (continuer) {

            // tour du joueur 1
            System.out.println("Joueur 1, saisir les coordonnées de la case à remplir");
            remplirCase(grilleDeJeu, joueur1);
            afficherGrille(grilleDeJeu);
            switch (etatPartie(grilleDeJeu, joueur1)) {
                case 1: {
                    System.out.println("Le joueur 1 à gagné !");
                    continuer = false;
                    break;
                }
                case 0: {
                    System.out.println("Egalité ! La partie est terminée");
                    continuer = false;
                    break;
                }
                default: {
                    // tour du joueur 2
                    System.out.println("Joueur 2, saisir les coordonnées de la case à remplir");
                    remplirCase(grilleDeJeu, joueur2);
                    afficherGrille(grilleDeJeu);
                    if (etatPartie(grilleDeJeu, joueur2) == 1) {
                        System.out.println("Le joueur 2 à gagné !");
                        continuer = false;
                    } else if (etatPartie(grilleDeJeu, joueur2) == 0) {
                        System.out.println("Egalité ! La partie est terminée");
                        continuer = false;
                    }
                }
            }


        }
    }


    /**
     * Initialise une grille de morpion vide
     */
    public static char[][] initialisation() {

        char[][] grille = new char[3][3];
        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[i].length; j++) {
                grille[i][j] = ' ';
            }
        }
        return grille;
    }


    /**
     * Affiche et met en forme la grille passée en paramètre
     *
     * @param grille grille de morpion
     */
    public static void afficherGrille(char[][] grille) {
        System.out.println("    0   1   2  ");
        System.out.println("   --- --- --- ");
        for (int i = 0; i < grille.length; i++) {
            System.out.print(i + " |");
            for (int j = 0; j < grille[i].length; j++) {
                System.out.print(" " + grille[i][j] + " |");
            }
            System.out.println();
            System.out.println("   --- --- --- ");
        }
    }


    /**
     * Méthode demandant au joueur courant de remplir une cas et modifie la matrice en conséquence
     *
     * @param grille  la grille  de la partie en cours
     * @param symbole le symbole représentant le joueur courant
     */
    public static void remplirCase(char[][] grille, char symbole) {
        Scanner sc = new Scanner(System.in);
        int x, y;
        do {
            System.out.println("x : ");
            x = sc.nextInt();
            System.out.println("y : ");
            y = sc.nextInt();

            if (grille[y][x] != ' ') {
                System.out.println("Case occupée !");
            }
        }
        while (grille[y][x] != ' ');

        grille[y][x] = symbole;
    }


    /**
     * Méthode vérifiant l'état de la partie, soit le joueur en paramètre à gagné, soit la partie est un mach nul,
     * soit la partie continue
     *
     * @param grille la grille de jeu
     * @param joueur le joueur dont on vérifie la victoire
     * @return 1 : le joueur à gagné; 0 : égalité; -1 : la partie continue
     */
    public static short etatPartie(char[][] grille, char joueur) {
        // vérification des diagonales
        if (((grille[0][0] == joueur) && (grille[1][1] == joueur) && (grille[2][2] == joueur))
                ||
                ((grille[2][0] == joueur) && (grille[1][1] == joueur) && (grille[0][2] == joueur))) {
            return 1;
        }

        boolean matchNul = true;

        for (int i = 0; i < grille.length; i++) {
            // vérification des lignes et colonnes
            if (((grille[i][0] == joueur) && (grille[i][1] == joueur) && (grille[i][2] == joueur))
                    ||
                    ((grille[0][i] == joueur) && (grille[1][i] == joueur) && (grille[2][i] == joueur))) {
                return 1;
            }

            // vérification de match nul
            for (int j = 0; j < grille[i].length; j++) {
                if (matchNul && (grille[i][j] == ' ')) {
                    matchNul = false;
                }
            }
        }
        if (matchNul) {
            return 0;
        }

        // Sinon la partie continue
        return -1;
    }
}