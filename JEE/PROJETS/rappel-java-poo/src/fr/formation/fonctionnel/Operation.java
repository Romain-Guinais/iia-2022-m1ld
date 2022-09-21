package fr.formation.fonctionnel;

@FunctionalInterface
public interface Operation {
	public int calc(int a, int b);
	
	public default int calc2(int a, int b) {
		return a + b - 20;
	}
}
