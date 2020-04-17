public class Wine {
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
	
	Wine(int type,double Alcohol,double Malic,double Ash,double Alcalinity,double Magnesium,double phenols,double Flavanoids,double Nonflavanoid,double Proanthocyanins,double Color,double Hue,double OD280,double Proline){
		this.type = type;
		this.Alcohol = Alcohol;
		this.Malic = Malic;
		this.Ash = Ash;
		this.Alcalinity = Alcalinity;
		this.Magnesium = Magnesium;
		this.phenols = phenols;
		this.Flavanoids = Flavanoids;
		this.Nonflavanoid = Nonflavanoid;
		this.Proanthocyanins = Proanthocyanins;
		this.Color = Color;
		this.Hue = Hue;
		this.OD280 = OD280;
		this.Proline = Proline;
	}
	
	public String toString() {
		String str = String.format("type = %d, Alcohol, Malic, Ash, Alcalinity, Magnesium, phenols, Flavanoids, Nonflavanoid, Proanthocyanins, Color, Hue, OD280, Proline\n", type);
		return str;
	}
}
