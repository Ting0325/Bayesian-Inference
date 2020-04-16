import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Driver {
	public static void main(String [] args) throws FileNotFoundException {
		Scanner sc = new Scanner(new File("wine.txt"));
		List <Wine> list = new ArrayList();
		while(sc.hasNextInt()) {
			int type;
			double Alcohol;
			double Malic;
			double Ash;
			double Alcalinity;
			double Magnesium;
			double phenols;
			double Flavanoids;
			double Nonflavanoid;
			double Proanthocyanins;
			double Color;
			double Hue;
			double OD280;
			double Proline;
			
				type = sc.nextInt();
				Alcohol = sc.nextDouble();
				Malic = sc.nextDouble();
				Ash = sc.nextDouble();
				Alcalinity = sc.nextDouble();
				Magnesium = sc.nextDouble();
				phenols = sc.nextDouble();
				Flavanoids = sc.nextDouble();
				Nonflavanoid = sc.nextDouble();
				Proanthocyanins = sc.nextDouble();
				Color = sc.nextDouble();
				Hue = sc.nextDouble();
				OD280 = sc.nextDouble();
				Proline = sc.nextDouble();
				Wine wine = new Wine( type, Alcohol, Malic, Ash, Alcalinity, Magnesium, phenols, Flavanoids, Nonflavanoid, Proanthocyanins, Color, Hue, OD280, Proline);
				list.add(wine);
			
		}
		System.out.print(list);
	}
}
