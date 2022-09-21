package fr.formation.poo;

public class Demo extends DemoAbstract implements InterfaceA {

	
	@Override
	public void comportementQueJeConnais() {
		System.out.println("Autre comportement ... connu");
	}
	
	public void comportementQueJeConnaisPas() {
		System.out.println("OK, ça je sais faire.");
	}
	
	public void comportementQueJeConnaisPas(Boolean ok) {
		System.out.println("OK, ça je sais faire, deuxièmement..");
	}

	@Override
	public void uneMethodeAExecuter() {
		
	}

	public static int demoMethodFunctional(int a, int b) {
		return a + b + 10;
	}
}
