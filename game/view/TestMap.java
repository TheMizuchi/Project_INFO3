package view;

public class TestMap {

	public static void main (String[] args) {
		int carte[][] = new int[2][2];
		carte[0][0] = 0;
		carte[0][1] = 1;
		carte[1][0] = 2;
		carte[1][1] = 3;
		int carte2[][] = new int[7][7];
		
		

		MapView var = new MapView(carte);
		MapView var2 = new MapView(carte2);
		int res = var.hauteur();
		System.out.println("hauteur"+res);
		res = var.largeur();
		System.out.println("largeur"+res);
		res = var2.hauteur();
		System.out.println("hauteur"+res);
		res = var2.largeur();
		System.out.println("largeur"+res);
		
		var.paint(null);

	}

}
