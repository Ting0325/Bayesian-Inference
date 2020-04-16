import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Driver {
	public static void main(String [] args) throws FileNotFoundException {
		Scanner sc = new Scanner(new File("wine.txt"));
		
		/*List of Wine instances*/
		List <Wine> list = new ArrayList();
		
		/*prior probabilities*/
		Probability prior1;
		Probability prior2;
		Probability prior3;
		/*joint probabilities*/
		//type 1
		List <Probability> type1Joint =  new ArrayList();
		//type 2
		List <Probability> type2Joint =  new ArrayList();
		//type 3
		List <Probability> type3Joint =  new ArrayList();
		
		/*Likelihood functions*/
		/*read values*/
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
		
		prior1 = new()
	}
	
	/*
	 * 
	 * 
	 * */
	List subData(List<List> data,int x1,int y1,int x2,int y2) {
		List subData = new ArrayList();
		for(int i = y1; i <= y2;i ++) {
			for(int j = x1; j <= x2;j ++) {
				subData.add(data.get(i).get(j));
			}
		}
		return subData;
	}
	double getMean(List<Double> values) {
		double mean = 0;
		for(int i =0; i <values.size();i++)
			mean += values.get(i);
		return mean / values.size();
	}
	
	double getStdDiv(List<Double> values) {
		double mean = getMean(values);
		double sum = 0;
		for(int i =0; i <values.size();i++)
			sum += (values.get(i) - mean) * (values.get(i) - mean);
		return Math.sqrt(sum/(values.size()-1)); 
	}
}
