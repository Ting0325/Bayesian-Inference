import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Driver {
	public static void main(String [] args) throws FileNotFoundException {
		//Probability pTest = new Probability(4.98,0.35);
		//System.out.println(pTest.probability(5));
		//System.out.println(Math.exp((-1/2)*Math.pow(((5-pTest.mean)/pTest.stdDiv),2)));
		//System.out.println( (-1/2)*Math.exp(Math.pow(((5-pTest.mean)/pTest.stdDiv),2)/**(-1.0/2)*/));
		Scanner sc = new Scanner(new File("wine.txt"));
		Scanner scIn = new Scanner(System.in);
		/*List of Wine instances*/
		List <Wine> list = new ArrayList();
		List <Wine> listType1 = new ArrayList();
		List <Wine> listType2 = new ArrayList();
		List <Wine> listType3 = new ArrayList();
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
		
		//List <Probability> substanceProb =  new ArrayList();
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
			//list.add(wine);
			
			switch(type) {
				case 1:	listType1.add(wine);
						break;
				case 2:	listType2.add(wine);
						break;
				case 3:	listType3.add(wine);
						break;
			}			
		}
		list.addAll(listType1);
		list.addAll(listType2);
		list.addAll(listType3);
		System.out.print(list);
		System.out.println(list.size());
		//get proir probilities
		prior1 = 59.0/178;//1.0/3;
		prior2 = 71.0/178;//1.0/3;
		prior3 = 47.0/178;//1.0/3;
		//get joint probalities
		for(int i = 1; i <=13;i++) {
			//List data = substanceData(list, i, 0, 58);
			List data = substanceData(listType1, i, 0, listType1.size()-1);
			type1Joint.add(new Probability(getMean(data),getStdDiv(data)));
		}
		for(int i = 1; i <=13;i++) {
			//List data = substanceData(list, i, 59, 129);
			List data = substanceData(listType2, i, 0, listType2.size()-1);
			type2Joint.add(new Probability(getMean(data),getStdDiv(data)));
		}
		for(int i = 1; i <=13;i++) {
			//List data = substanceData(list, i, 130, 177);
			List data = substanceData(listType3, i, 0, listType3.size()-1);
			type3Joint.add(new Probability(getMean(data),getStdDiv(data)));
		}
		/*
		for(int i = 1; i <=13;i++) {
			List data = substanceData(list, i, 0, 177);
			substanceProb.add(new Probability(getMean(data),getStdDiv(data)));
		}
		*/
		//get likelyhood functions
		
		
		//inference
		System.out.println("Start inference ,plz input data");
		//get input data
		int count = 0;
		int correct = 0;
		while(true) {
			count++;
			prior1 = 1.0/3;
			prior2 = 1.0/3;
			prior3 = 1.0/3;
			double[] inputData = new double[13];
			Wine input = list.get(count);
			/*
			for(int i = 0; i < 13;i++) {
				inputData[i] = scIn.nextDouble();
			}
			*/
			inputData[0] = input.Alcohol;
			inputData[1] = input.Malic;
			inputData[2] = input.Ash;
			inputData[3] = input.Alcalinity;
			inputData[4] = input.Magnesium;
			inputData[5] = input.phenols;
			inputData[6] = input.Flavanoids;
			inputData[7] = input.Nonflavanoid;
			inputData[8] = input.Proanthocyanins;
			inputData[9] = input.Color;
			inputData[10] = input.Hue;
			inputData[11] = input.OD280;
			inputData[12] = input.Proline;
			//System.out.println("\ninput data");
			for(int i = 0; i < 13;i++) {
				//System.out.print(inputData[i]+" ");
			}
			//get probability for 1
			//System.out.println("\n1:");
			//debug
			//System.out.println("debug");
			//System.out.println(Math.exp((-1/2)*Math.pow(((inputData[12]-type1Joint.get(12).mean)/type1Joint.get(12).stdDiv),2)));
			for(int i = 0;i < 13;i++) {
				//prior1 *= (type1Joint.get(i).probability(inputData[i])/substanceProb.get(i).probability(inputData[i]));
				double p1 = type1Joint.get(i).probability(inputData[i]);
				double p2 = type2Joint.get(i).probability(inputData[i]);
				double p3 = type3Joint.get(i).probability(inputData[i]);
				prior1 *= p1;///(p1+p2+p3);
				System.out.print(p1+" ");
			}
			//get probability for 2
			//System.out.println("\n2:");
			for(int i = 0;i < 13;i++) {
				double p1 = type1Joint.get(i).probability(inputData[i]);
				double p2 = type2Joint.get(i).probability(inputData[i]);
				double p3 = type3Joint.get(i).probability(inputData[i]);
				prior2 *= p2;///(p1+p2+p3);
				//prior2 *= (type2Joint.get(i).probability(inputData[i])/substanceProb.get(i).probability(inputData[i]));
				//System.out.print(p2+" ");
			}
			//get probability for 3
			//System.out.println("\n3:");
			for(int i = 0;i < 13;i++) {
				double p1 = type1Joint.get(i).probability(inputData[i]);
				double p2 = type2Joint.get(i).probability(inputData[i]);
				double p3 = type3Joint.get(i).probability(inputData[i]);
				prior3 *= p3;///(p1+p2+p3);
				//prior3 *= (type3Joint.get(i).probability(inputData[i])/substanceProb.get(i).probability(inputData[i]));
				//System.out.print(p3+" ");
			}
			
			//System.out.println("\nprior1: "+prior1);
			//System.out.println("prior2: "+prior2);
			//System.out.println("prior3: "+prior3);
			if(prior1 > prior2 && prior1 > prior3) {
				System.out.print("\ncatagory "+input.type+" prediction: 1");
				if(input.type == 1) {
					correct ++;
					System.out.print(" correct");
				}else
					System.out.print(" wrong");

			}else if(prior2 > prior1 && prior2 > prior3) {
				System.out.print("\ncatagory "+input.type+" prediction: 2");
				if(input.type == 2) {
					correct ++;
					System.out.print(" correct");
				}else
					System.out.print(" wrong");
			}else {
				System.out.print("\ncatagory "+input.type+" prediction: 3");
				if(input.type == 3) {
					correct ++;
					System.out.print(" correct");
				}else
					System.out.print(" wrong");
			}
			if(count == list.size()-1)
				break;
		}
		System.out.println("\nAccuracy = "+ ((double)correct/count));
		while(true) {

			prior1 = 1.0/3;
			prior2 = 1.0/3;
			prior3 = 1.0/3;
			double[] inputData = new double[13];

			int inputType = scIn.nextInt();
			for(int i = 0; i < 13;i++) {
				inputData[i] = scIn.nextDouble();
			}
			
			System.out.println("\ninput data");
			for(int i = 0; i < 13;i++) {
				System.out.print(inputData[i]+" ");
			}
			//get probability for 1
			System.out.println("\n1:");
			//debug
			//System.out.println("debug");
			//System.out.println(Math.exp((-1/2)*Math.pow(((inputData[12]-type1Joint.get(12).mean)/type1Joint.get(12).stdDiv),2)));
			for(int i = 0;i < 13;i++) {
				//prior1 *= (type1Joint.get(i).probability(inputData[i])/substanceProb.get(i).probability(inputData[i]));
				double p1 = type1Joint.get(i).probability(inputData[i]);
				double p2 = type2Joint.get(i).probability(inputData[i]);
				double p3 = type3Joint.get(i).probability(inputData[i]);
				prior1 *= p1;///(p1+p2+p3);
				System.out.println("feature "+inputData[i]+" ");
				System.out.println("mean "+type1Joint.get(i).mean+" ");
				System.out.println("std "+type1Joint.get(i).stdDiv+" ");
				System.out.println("likelyhood "+p1+" ");
			}
			//get probability for 2
			System.out.println("\n2:");
			for(int i = 0;i < 13;i++) {
				double p1 = type1Joint.get(i).probability(inputData[i]);
				double p2 = type2Joint.get(i).probability(inputData[i]);
				double p3 = type3Joint.get(i).probability(inputData[i]);
				prior2 *= p2;///(p1+p2+p3);
				//prior2 *= (type2Joint.get(i).probability(inputData[i])/substanceProb.get(i).probability(inputData[i]));
				System.out.print(p2+" ");
			}
			//get probability for 3
			System.out.println("\n3:");
			for(int i = 0;i < 13;i++) {
				double p1 = type1Joint.get(i).probability(inputData[i]);
				double p2 = type2Joint.get(i).probability(inputData[i]);
				double p3 = type3Joint.get(i).probability(inputData[i]);
				prior3 *= p3;///(p1+p2+p3);
				//prior3 *= (type3Joint.get(i).probability(inputData[i])/substanceProb.get(i).probability(inputData[i]));
				System.out.print(p3+" ");
			}
			
			//System.out.println("\nprior1: "+prior1);
			//System.out.println("prior2: "+prior2);
			//System.out.println("prior3: "+prior3);
			if(prior1 > prior2 && prior1 > prior3) {
				System.out.print("\ncatagory "+inputType+" prediction: 1");
				if(inputType == 1) {
					correct ++;
					System.out.print(" correct");
				}else
					System.out.print(" wrong");

			}else if(prior2 > prior1 && prior2 > prior3) {
				System.out.print("\ncatagory "+inputType+" prediction: 2");
				if(inputType == 2) {
					correct ++;
					System.out.print(" correct");
				}else
					System.out.print(" wrong");
			}else {
				System.out.print("\ncatagory "+inputType+" prediction: 3");
				if(inputType == 3) {
					correct ++;
					System.out.print(" correct");
				}else
					System.out.print(" wrong");
			}
			if(count == list.size()-1)
				break;
		}
	}
	


	static List substanceData(List<Wine> data,int substance,int start,int end) {
		List<Double> subData = new ArrayList();
		/*for(int i = start; i <= end;i ++) {
			subData.add(data.get(i).Alcalinity);
		}*/
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
