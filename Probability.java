
public class Probability {
	double mean;
	double stdDiv;
	
	Probability(double mean, double stdDiv){
		this.mean = mean;
		this.stdDiv = stdDiv;
	}
	
	double probability(double x) {
		return (1/(stdDiv*Math.sqrt(2*Math.PI))) * Math.exp((-1/2)*((x-mean)/stdDiv));
	}
}