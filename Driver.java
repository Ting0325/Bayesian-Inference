import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Driver {
	public static void main(String [] args) throws FileNotFoundException {
		Scanner sc = new Scanner(new File("wine.txt"));
		Scanner scIn = new Scanner(System.in);
		/*List of Wine instances*/
		List <Wine> list = new ArrayList();
		
		/*prior probabilities*/
		double prior1;
		double prior2;
		double prior3;
		/*joint probabilities*/
		//type 1
		List <Probability> type1Joint =  new ArrayList();
		//type 2
		List <Probability> type2Joint =  new ArrayList();
		//type 3
		List <Probability> type3Joint =  new ArrayList();
		
		List <Probability> substanceProb =  new ArrayList();
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
		//System.out.print(list);
		//get proir probilities
		prior1 = 1.0/3;
		prior2 = 1.0/3;
		prior3 = 1.0/3;
		//get joint probalities
		for(int i = 1; i <=13;i++) {
			List data = substanceData(list, i, 0, 58);
			type1Joint.add(new Probability(getMean(data),getStdDiv(data)));
		}
		for(int i = 1; i <=13;i++) {
			List data = substanceData(list, i, 59, 129);
			type2Joint.add(new Probability(getMean(data),getStdDiv(data)));
		}
		for(int i = 1; i <=13;i++) {
			List data = substanceData(list, i, 130, 177);
			type3Joint.add(new Probability(getMean(data),getStdDiv(data)));
		}
		for(int i = 1; i <=13;i++) {
			List data = substanceData(list, i, 0, 177);
			substanceProb.add(new Probability(getMean(data),getStdDiv(data)));
		}
		//get likelyhood functions
		
		
		//inference
		System.out.println("Start inference ,plz input data");
		//get input data
		
		while(true) {
			double[] inputData = new double[13];
			for(int i = 0; i < 13;i++) {
				inputData[i] = scIn.nextDouble();
			}
			//get probability for 1
			System.out.println("\n1:");
			for(int i = 0;i < 13;i++) {
				prior1 *= (type1Joint.get(i).probability(inputData[i])/substanceProb.get(i).probability(inputData[i]));
				//System.out.print(type1Joint.get(i).probability(inputData[i])+" ");
			}
			//get probability for 2
			System.out.println("\n2:");
			for(int i = 0;i < 13;i++) {
				prior2 *= (type2Joint.get(i).probability(inputData[i])/substanceProb.get(i).probability(inputData[i]));
				//System.out.print(type2Joint.get(i).probability(inputData[i])+" ");
			}
			//get probability for 3
			System.out.println("\n3:");
			for(int i = 0;i < 13;i++) {
				prior3 *= (type3Joint.get(i).probability(inputData[i])/substanceProb.get(i).probability(inputData[i]));
				//System.out.print(type3Joint.get(i).probability(inputData[i])+" ");
			}
			
			System.out.println("\nprior1: "+prior1);
			System.out.println("prior2: "+prior2);
			System.out.println("prior3: "+prior3);
			if(prior1 > prior2 && prior1 > prior3) {
				System.out.print("catagory 1");
			}else if(prior2 > prior1 && prior2 > prior3) {
				System.out.print("catagory 2");
			}else {
				System.out.print("catagory 3");
			}
		}
	}
	


	static List substanceData(List<Wine> data,int substance,int start,int end) {
		List<Double> subData = new ArrayList();
		for(int i = start; i <= end;i ++) {
			subData.add(data.get(i).Alcalinity);
		}
		switch(substance) {
			case 1:		for(int i = start; i <= end;i ++) 
							subData.add(data.get(i).Alcohol);
						break;
			case 2:		for(int i = start; i <= end;i ++) 
							subData.add(data.get(i).Malic);
						break;
			case 3:		for(int i = start; i <= end;i ++) 
							subData.add(data.get(i).Ash);
						break;
			case 4:		for(int i = start; i <= end;i ++) 
							subData.add(data.get(i).Alcalinity);
						break;
			case 5:		for(int i = start; i <= end;i ++) 
							subData.add(data.get(i).Magnesium);
						break;
			case 6:		for(int i = start; i <= end;i ++) 
							subData.add(data.get(i).phenols);
						break;
			case 7:		for(int i = start; i <= end;i ++) 
							subData.add(data.get(i).Flavanoids);
						break;
			case 8:		for(int i = start; i <= end;i ++) 
							subData.add(data.get(i).Nonflavanoid);
						break;
			case 9:		for(int i = start; i <= end;i ++) 
							subData.add(data.get(i).Proanthocyanins);
						break;
			case 10:	for(int i = start; i <= end;i ++) 
							subData.add(data.get(i).Color);
						break;
			case 11:	for(int i = start; i <= end;i ++) 
							subData.add(data.get(i).Hue);
						break;
			case 12:	for(int i = start; i <= end;i ++) 
							subData.add(data.get(i).OD280);
						break;
			case 13:	for(int i = start; i <= end;i ++) 
							subData.add(data.get(i).Proline);
						break;
		}
		return subData;		
	}
	List subData(List<List> data,int x1,int y1,int x2,int y2) {
		List subData = new ArrayList();
		for(int i = y1; i <= y2;i ++) {
			for(int j = x1; j <= x2;j ++) {
				subData.add(data.get(i).get(j));
			}
		}
		return subData;
	}
	static double getMean(List<Double> values) {
		double mean = 0;
		for(int i =0; i <values.size();i++)
			mean += values.get(i);
		return mean / values.size();
	}
	
	static double getStdDiv(List<Double> values) {
		double mean = getMean(values);
		double sum = 0;
		for(int i =0; i <values.size();i++)
			sum += (values.get(i) - mean) * (values.get(i) - mean);
		return Math.sqrt(sum/(values.size()-1)); 
	}
}
