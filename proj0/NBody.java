/** NBody is a class that will actually run your simulation. 
This class will have NO constructor. */

public class NBody{
	public static double readRadius(String fileName) {
		In in = new In(fileName);
		in.readInt();
		return in.readDouble();
	}

	public static Planet[] readPlanets(String fileName) {
		In in = new In(fileName);
		int num=in.readInt();//number of planets
		in.readDouble();//constantG
		Planet[] p=new Planet[num];
		int i;
		for(i=0;i<num;i++)
		{
			double xP = in.readDouble();
			double yP = in.readDouble();
			double xV = in.readDouble();
			double yV = in.readDouble();
			double m = in.readDouble();
			String img = in.readString();
			p[i]=new Planet(xP, yP, xV, yV, m, img);
		}
		return p;

	}

	public static void main(String[] args) {

		/* Collecting All Needed Input */
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double uniRadius = readRadius(filename);

		//sth start bad from here.
		Planet[] p = readPlanets(filename);

		/* Drawing the Background */
		StdDraw.setScale(-uniRadius, uniRadius);
		StdDraw.clear();
		StdDraw.picture(0, 0, "images/starfield.jpg");

		/* Drawing All of the Planets */
		for (Planet pmov : p) {
			pmov.draw();
		}//new method*/

		/* Animation */
		StdDraw.enableDoubleBuffering();

		double time = 0;
		while(time <= T) {
			double[] xForces = new double[p.length];
			double[] yForces = new double[p.length];
			for(int i=0; i<p.length; i++) {
				xForces[i] = p[i].calcNetForceExertedByX(p);
				yForces[i] = p[i].calcNetForceExertedByY(p);
			}

			for(int i=0; i<p.length; i++) {
				p[i].update(dt, xForces[i], yForces[i]);
			}

			StdDraw.picture(0, 0, "images/starfield.jpg");
			for (Planet pmov : p) {
			pmov.draw();
		}
			StdDraw.show();
			StdDraw.pause(10);
			time += dt;
		}


		/* 
		   Printing the Universe
		   When reached time T, print out the final state of the universe.
		*/
		StdOut.printf("%d\n", p.length);
		StdOut.printf("%.2e\n", uniRadius);
		for (int i = 0; i < p.length; i++) {
		    StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		                  p[i].xxPos, p[i].yyPos, p[i].xxVel,
		                  p[i].yyVel, p[i].mass, p[i].imgFileName);   
		}

	}
} 
