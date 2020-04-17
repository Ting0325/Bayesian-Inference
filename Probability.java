
public class Probability {
	double mean;
	double stdDiv;
	
	Probability(double mean, double stdDiv){
		this.mean = mean;
		this.stdDiv = stdDiv;
	}
	
	double probability(double x) {
		return (1/(stdDiv*Math.sqrt(2*Math.PI))) * Math.exp(Math.pow(((x-mean)/stdDiv),2)*(-1.0/2));
	}
}