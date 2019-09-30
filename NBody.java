public class NBody {
	/** Read Universe radius from the provided file name. */
	public static double readRadius(String fileName) {
		In data = new In(fileName);
		data.readInt();
		return data.readDouble();

	}

	/** Read planet information from the data given and add it to an array. */
	public static Planet[] readPlanets(String fileName){
		In data = new In(fileName);
		int numPlanets = data.readInt();
		data.readDouble();
		Planet[] allPlanets = new Planet[numPlanets];
		int index = 0;
		while (numPlanets > index) {
			Planet p = new Planet(data.readDouble(), data.readDouble(), data.readDouble(), data.readDouble(), data.readDouble(), data.readString());
			allPlanets[index] = p;
			index += 1;
		}
		return allPlanets;
	}

	/** Set the background and put the planets. Ready for the simulation! */
	public static void main(String[] args) {
		double t = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double universeRadius = readRadius(filename);
		Planet[] allPlanets = readPlanets(filename);

		StdDraw.setScale(-universeRadius, universeRadius);
		StdDraw.clear();
		StdDraw.picture(0, 0, "images/starfield.jpg");
		
		for (Planet p : allPlanets) {
			p.draw();
		}

		double time = 0;
		while (time < t) {
			double[] xForce = new double[allPlanets.length];
			double[] yForce = new double[allPlanets.length];
			for (int i = 0; i < allPlanets.length; i++) {
				xForce[i] = allPlanets[i].calcNetForceExertedByX(allPlanets);
				yForce[i] = allPlanets[i].calcNetForceExertedByY(allPlanets);
			}
			for (int i = 0; i < allPlanets.length; i++) {
				allPlanets[i].update(dt, xForce[i], yForce[i]);
			}
			StdDraw.picture(0, 0, "images/starfield.jpg");
			for (Planet p : allPlanets) {
					p.draw();
			}
			StdDraw.show(10);
			time += dt;

		}

		/** Print out the universe. Things move now!! */
		In data = new In(filename);
		int numPlanets = data.readInt();
		System.out.println(numPlanets);
		System.out.println(universeRadius);
		for (Planet p : allPlanets){
			System.out.println(p.xxPos + " " + p.yyPos + " " + p.xxVel + " " + p.yyVel + " " + p.mass + " " + p.imgFileName);
		}
	}

}
