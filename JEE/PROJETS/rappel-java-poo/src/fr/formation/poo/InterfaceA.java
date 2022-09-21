package fr.formation.poo;

public interface InterfaceA {
	public void uneMethodeAExecuter();
	
	public default void uneMethodeDontJeConnaisLeComportement() {
		System.out.println("Je défini un comportement dans l'interface.");
	}
}
