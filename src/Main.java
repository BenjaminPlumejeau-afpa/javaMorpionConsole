import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // initialisation des joueurs
        System.out.println("Saisir les informations du joueur 1");
        Joueur joueur1 = initialisationJoueur();
        Joueur joueur2 = initialisationJoueur();

        // boucle de l'enchainement des parties
        boolean nouvellePartie = true;
        do {

            // initialisation de la partie
            char[][] grilleDeJeu = initialisationPartie();
            short turn = 1;
            Joueur joueurCourant;

            // tant que la partie n'est pas finie continuer vaut True
            boolean continuer = true;

            while (continuer) {

                afficherGrille(grilleDeJeu);

                if (turn % 2 == 0) {
                    // Tour du joueur 2 les tours pairs
                    joueurCourant = joueur2;
                } else {
                    // tour du joueur 1 les tours impairs
                    joueurCourant = joueur1;
                }

                tourDeJeu(joueurCourant, grilleDeJeu);

                // Vérification de l'état de la partie
                switch (etatPartie(grilleDeJeu, joueurCourant.getSymbole())) {
                    case 1: {
                        System.out.println(joueur1.getNom() + " à gagné !");
                        continuer = false;
                        nouvellePartie = rejouer();
                        break;
                    }
                    case 0: {
                        System.out.println("Egalité ! La partie est terminée");
                        continuer = false;
                        nouvellePartie = rejouer();
                        break;
                    }
                }

                turn++;
            }

        }
        while (nouvellePartie);
    }


    /**
     * Méthode créant un nouveau joueur en l'instanciant via les saisies de l'utilisateur
     *
     * @return un Joueur instancié
     */
    public static Joueur initialisationJoueur() {

        Scanner sc = new Scanner(System.in);

        System.out.println("Nom du joueur :");
        String nomJoueur = sc.nextLine();
        System.out.println("Symbole représentant le joueur :");
        char symboleJoueur = sc.next().charAt(0);

        return new Joueur(nomJoueur, symboleJoueur);

    }


    /**
     * Initialise une grille de morpion vide
     */
    public static char[][] initialisationPartie() {

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
        System.out.println(" x  0   1   2  ");
        System.out.println("y  --- --- --- ");
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
     * Méthode gérant le tour d'un joueur
     *
     * @param joueur le joueur dont c'est le tour
     * @param grille la grille de la partie en cours
     */
    public static void tourDeJeu(Joueur joueur, char[][] grille) {

        // Remmplissage d'une case de la grille
        System.out.println(joueur.getNom() + " ( " + joueur.getSymbole() + " ), saisir les coordonnées de la case à remplir");
        remplirCase(grille, joueur.getSymbole());
    }


    /**
     * Méthode demandant au joueur courant de remplir une case et modifie la matrice en conséquence
     *
     * @param grille  la grille  de la partie en cours
     * @param symbole le symbole représentant le joueur courant
     */
    public static void remplirCase(char[][] grille, char symbole) {
        Scanner sc = new Scanner(System.in);
        int x, y;
        // on boucle jusqu'a ce que le joueur saisisse une case vide
        do {
            // on vérifie que les coordonnées x et y soient valides
            do {
                System.out.println("x : ");
                x = sc.nextInt();
                if ((x < 0) || (x > 2)) {
                    afficherGrille(grille);
                    System.out.println("Coordonnée x invalide !");
                }
            } while ((x < 0) || (x > 2));
            do {
                System.out.println("y : ");
                y = sc.nextInt();
                if ((y < 0) || (y > 2)) {
                    afficherGrille(grille);
                    System.out.println("Coordonnée y invalide !");
                    System.out.println("x : " + x);
                }
            } while ((y < 0) || (y > 2));

            if (grille[y][x] != ' ') {
                afficherGrille(grille);
                System.out.println("Case occupée !");
            }
        }
        while (grille[y][x] != ' ');

        grille[y][x] = symbole;
        afficherGrille(grille);
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
                    break;
                }
            }
        }
        if (matchNul) {
            return 0;
        }

        // Sinon la partie continue
        return -1;
    }


    /**
     * Méthode demandant par saisi à l'utilisateur si une nouvelle partie doit être lancée ou non
     *
     * @return True pour relancer une partie, False pour arreter
     */
    public static boolean rejouer() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Souhaitez-vous faire une nouvelle partie ? (o/n)");
        char reponse = sc.next().charAt(0);
        return (reponse == 'o') || (reponse == 'O');
    }
}