package fr.formation;

import fr.formation.fonctionnel.Addition;
import fr.formation.fonctionnel.Operation;
import fr.formation.poo.Demo;
import fr.formation.poo.DemoAbstract;
import fr.formation.poo.DemoB;

public class Application {

	public static void main(String[] args) {
		
		System.out.println("Bonjour le monde !");
		
		
		DemoAbstract demo = new Demo();

		demo.comportementQueJeConnais();
//		demo.comportementQueJeConnaisPas();
		
		
		
		Operation op = new Addition();
		System.out.println(op.calc(5, 6));
		
		
		Operation op2 = new Operation() {
			@Override
			public int calc(int a, int b) {
				return a - b;
			}
		};
		
		System.out.println(op2.calc(5, 4));
		
		
		// Version "Programmation fonctionnelle"
		Operation op3 = (a, b) -> {
			return a * b;
		};
		System.out.println(op3.calc(4, 2));
		
		Operation op4 = (a, b) -> a / b;
		System.out.println(op4.calc(4, 2));
		
		Operation op5 = Application::demoMethodFunctional;
		System.out.println(op5.calc(1, 2));
		
		Operation op6 = Demo::demoMethodFunctional;
		System.out.println(op6.calc(1, 2));
	}
	
	public static int demoMethodFunctional(int a, int b) {
		return a + b + 3;
	}
}
