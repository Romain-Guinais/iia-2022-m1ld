package fr.formation.flyweight;

public class Application {
    public static void main(String[] args) {
        Texte texte = new Texte();
        
        texte.ajouter('H', 0, 12, 0);
        texte.ajouter('e', 0, 12, 0);
        texte.ajouter('l', 0, 12, 0);
        texte.ajouter('l', 0, 12, 0);
        texte.ajouter('o', 0, 12, 0);
        texte.ajouter(' ', 0, 12, 0);
        texte.ajouter('W', 0, 24, 0);
        texte.ajouter('o', 0, 24, 0);
        texte.ajouter('r', 0, 24, 0);
        texte.ajouter('l', 0, 24, 0);
        texte.ajouter('d', 0, 24, 0);

        texte.imprimer();

        // for (int i = 0; i < 1000000; i++) {
        //     texte.ajouter('d', 0, 24, 0);
        // }

        System.out.print("Mémoire utilisée : ");
        System.out.println(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
    }
}
