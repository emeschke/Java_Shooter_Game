package edu.uchicago.cs.java.finalproject.game.model;


import java.awt.*;
import java.util.Arrays;

import edu.uchicago.cs.java.finalproject.controller.Game;

public class Asteroid extends Sprite {

	
	private int nSpin;
	
	//radius of a large asteroid
	private final int RAD = 100;
	
	//nSize determines if the Asteroid is Large (0), Medium (1), or Small (2)
	//when you explode a Large asteroid, you should spawn 2 or 3 medium asteroids
	//same for medium asteroid, you should spawn small asteroids
	//small asteroids get blasted into debris
	public Asteroid(int nSize){
		
		//call Sprite constructor  Why do we need to call this constructor?
		super();
		
		
		//the spin will be either plus or minus 0-9
		int nSpin = Game.R.nextInt(10);
		if(nSpin %2 ==0)
			nSpin = -nSpin;
		setSpin(nSpin);
			
		//random delta-x
        //We don't need this because the asteroids only move up and down.
		int nDX = Game.R.nextInt(10);
        if(nDX %2 ==0)
			nDX = -nDX;
		setDeltaX(nDX);
		
		//random delta-y
		//Make sure the asteroids always move, so choose a speed for the y axis between 1-11.
        int nDY = Game.R.nextInt(10) + 1;
        if(nDY %2 ==0)
			nDY = -nDY;
		setDeltaY(nDY);
			
		assignRandomShape();
		
		//an nSize of zero is a big asteroid
		//a nSize of 1 or 2 is med or small asteroid respectively
		if (nSize == 0)
			setRadius(RAD);
		else
			setRadius(RAD/(nSize * 2));
		

	}
	

	
	
	public Asteroid(Asteroid astExploded){
	

		//call Sprite constructor
		super();
		
		int  nSizeNew =	astExploded.getSize() + 1;
		
		
		//the spin will be either plus or minus 0-9
		int nSpin = Game.R.nextInt(10);
		if(nSpin %2 ==0)
			nSpin = -nSpin;
		setSpin(nSpin);
			
		//random delta-x
		int nDX = Game.R.nextInt(10 + nSizeNew*2);
		if(nDX %2 ==0)
			nDX = -nDX;
		setDeltaX(nDX);
		
		//random delta-y
		//Random number plus the Size of the new one and a constant to make sure the asteroid moves some.
        int nDY = Game.R.nextInt(10+ nSizeNew*2) + 1;
        if(nDY %2 ==0)
			nDY = -nDY;
		setDeltaY(nDY);
			
		assignRandomShape();
		
		//an nSize of zero is a big asteroid
		//a nSize of 1 or 2 is med or small asteroid respectively

		setRadius(RAD/(nSizeNew * 2));
		setCenter(astExploded.getCenter());
		
		
		

	}
	
	public int getSize(){
		
		int nReturn = 0;
		
		switch (getRadius()) {
			case 100:
				nReturn= 0;
				break;
			case 50:
				nReturn= 1;
				break;
			case 25:
				nReturn= 2;
				break;
		}
		return nReturn;
		
	}

	//overridden
	public void move(){

        Point pnt = getCenter();
        //Make it so the asteroid doesn't move side to side.  Do this by over-riding the method and leaving the x coor
        //the same.
        double dX = pnt.x;


        //double dY = pnt.y + getDeltaY();
        double dY = pnt.y + getDeltaY();

        //this just keeps the sprite inside the bounds of the frame
        if (pnt.x > getDim().width) {
            setCenter(new Point(1, pnt.y));

        } else if (pnt.x < 0) {
            setCenter(new Point(getDim().width - 1, pnt.y));
        } else if (pnt.y > getDim().height) {
            setCenter(new Point(pnt.x, 1));

        } else if (pnt.y < 0) {
            setCenter(new Point(pnt.x, getDim().height - 1));
        } else {

            setCenter(new Point((int) dX, (int) dY));
        }


        //an asteroid spins, so you need to adjust the orientation at each move()
		setOrientation(getOrientation() + getSpin());
		
	}

	public int getSpin() {
		return this.nSpin;
	}
	

	public void setSpin(int nSpin) {
		this.nSpin = nSpin;
	}
	
	//this is for an asteroid only
	  public void assignRandomShape ()
	  {
	    int nSide = Game.R.nextInt( 7 ) + 7;
	    int nSidesTemp = nSide;

	    int[] nSides = new int[nSide];
	    for ( int nC = 0; nC < nSides.length; nC++ )
	    {
	      int n = nC * 48 / nSides.length - 4 + Game.R.nextInt( 8 );
	      if ( n >= 48 || n < 0 )
	      {
	        n = 0;
	        nSidesTemp--;
	      }
	      nSides[nC] = n;
	    }

	    Arrays.sort( nSides );

	    double[]  dDegrees = new double[nSidesTemp];
	    for ( int nC = 0; nC <dDegrees.length; nC++ )
	    {
	    	dDegrees[nC] = nSides[nC] * Math.PI / 24 + Math.PI / 2;
	    }
	   setDegrees( dDegrees);
	   
		double[] dLengths = new double[dDegrees.length];
			for (int nC = 0; nC < dDegrees.length; nC++) {
				if(nC %3 == 0)
				    dLengths[nC] = 1 - Game.R.nextInt(40)/100.0;
				else
					dLengths[nC] = 1;
			}
		setLengths(dLengths);

	  }

}
