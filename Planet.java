public class Planet {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	private double constG = 6.67e-11;

	/** Record planet information. */
	public Planet(double xP, double yP, double xV, double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	/** Access the recorded planet information. */
	public Planet(Planet p){
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	/** Calculate distance of a planet p from current planet */
	public double calcDistance(Planet p) {
		double r = Math.sqrt((xxPos - p.xxPos)*(xxPos - p.xxPos) + (yyPos - p.yyPos)*(yyPos - p.yyPos));
		return r;
	}

	/** Calculate the force exerted by planet p and another planet, by newton's pairs, the force of p on the planet and planet on p will be the same. */
	public double calcForceExertedBy(Planet p) {
		double rSquared = calcDistance(p) * calcDistance(p);
		return (constG * mass * p.mass / rSquared);
	}

	/** Calculate the exerted force's X-axis vector. */
	public double calcForceExertedByX(Planet p){
		return calcForceExertedBy(p) * (p.xxPos - xxPos) / calcDistance(p);
	}

	/** Calculate the exerted force's Y-axis vector. */
	public double calcForceExertedByY(Planet p){
		return calcForceExertedBy(p) * (p.yyPos - yyPos) / calcDistance(p);
	}

	/** Calculate the net force in X-axis, using individual x-axis force vectors previously calculated. */
	public double calcNetForceExertedByX(Planet[] allPlanets) {
 		double netForce = 0;
 		for (Planet p : allPlanets){
 			if (!this.equals(p)){
 				netForce += calcForceExertedByX(p);
 			}
 		}
 		return netForce;
	} 

	/** Calculate the net force in Y axis, using individual y-axis force vectors previously calculated. */
	public double calcNetForceExertedByY(Planet[] allPlanets) {
		double netForce = 0;
 		for (Planet p : allPlanets){
 			if (!this.equals(p)){
 				netForce += calcForceExertedByY(p);
 			}
 		}
 		return netForce;
	} 

	/** Update the position of a planet based on the velocity and acceleration due to net force. */
	public void update(double dt, double fX, double fY) {
		double aX = fX / mass;
		double aY = fY / mass;
		xxVel += aX * dt;
		yyVel += aY * dt;
		xxPos += xxVel * dt;
		yyPos += yyVel * dt;
	}

	/** Draw the planets in right positions! */
	public void draw(){
		StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
	}

}