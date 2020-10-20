public class Planet{
	
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	private double constantG=6.67e-11; 

	public Planet(double xP, double yP, double xV,
		double yV, double m, String img){
		this.xxPos=xP;
		this.yyPos=yP;
		this.xxVel=xV;
		this.yyVel=yV;
		this.mass=m;
		this.imgFileName=img;
	}
	/** The second constructor should take in a Planet object 
	and initialize an identical Planet object 
	(i.e. a copy). */
	public Planet(Planet p){
		this.xxPos=p.xxPos;
		this.yyPos=p.yyPos;
		this.xxVel=p.xxVel;
		this.yyVel=p.yyVel;
		this.mass=p.mass;
		this.imgFileName=p.imgFileName;
	}
	/** This method will take in a single Planet and should return a double 
	equal to the distance between the supplied planet 
	and the planet that is doing the calculation. */
	public double calcDistance(Planet p){
		return(Math.sqrt((this.xxPos-p.xxPos)*(this.xxPos-p.xxPos)
			+(this.yyPos-p.yyPos)*(this.yyPos-p.yyPos)));
	}
	/** method takes in a planet, and returns a double describing the force 
	exerted on this planet by the given planet.*/
	public double calcForceExertedBy(Planet p){
		return (constantG*this.mass*p.mass
			/(this.calcDistance(p)*this.calcDistance(p)));
	}
	/** these two methods describe the force 
	exerted in the X and Y directions, respectively. */
	public double calcForceExertedByX(Planet p){
		return (calcForceExertedBy(p)*(p.xxPos-this.xxPos)/calcDistance(p));
	}

	public double calcForceExertedByY(Planet p){
		return (calcForceExertedBy(p)*(p.yyPos-this.yyPos)/calcDistance(p));
	}

	public double calcNetForceExertedByX(Planet p[]){
		int i;
		double Netx=0;
		for(i=0;i<p.length;i++)
		{
			if(!this.equals(p[i]))
			{
				Netx+=calcForceExertedByX(p[i]);
			}
		}
		return Netx;
	}

	public double calcNetForceExertedByY(Planet p[]){
		int i;
		double Nety=0;
		for(i=0;i<p.length;i++)
		{
			if(!this.equals(p[i]))
			{
				Nety+=calcForceExertedByY(p[i]);
			}
		}
		return Nety;
	}

	public void update(double dt,double fX,double fY){
		double aNetx=fX/this.mass;
		double aNety=fY/this.mass;

		this.xxVel+=dt*aNetx;
		this.yyVel+=dt*aNety;

		this.xxPos+=dt*this.xxVel;
		this.yyPos+=dt*this.yyVel;
	}

	/* Drawing One Planet */
	public void draw(){
		String filename = "images/" + this.imgFileName;
		StdDraw.picture(this.xxPos, this.yyPos, filename);
	}
}
