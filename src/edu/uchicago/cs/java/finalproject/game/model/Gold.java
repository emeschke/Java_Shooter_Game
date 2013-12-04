package edu.uchicago.cs.java.finalproject.game.model;

import edu.uchicago.cs.java.finalproject.controller.Game;

import java.awt.*;
import java.util.ArrayList;

public class Gold extends Sprite {


	private int nSpin;

	public Gold() {

		super();

		ArrayList<Point> pntCs = new ArrayList<Point>();
		// top of ship
		pntCs.add(new Point(5, 5));
		pntCs.add(new Point(4,0));
		pntCs.add(new Point(4,2));
		pntCs.add(new Point(2,4));
		pntCs.add(new Point(1,1));
		pntCs.add(new Point(5, -5));
		pntCs.add(new Point(0,-4));
		pntCs.add(new Point(-2,1));
        pntCs.add(new Point(-5, -5));
		pntCs.add(new Point(-4,0));
		pntCs.add(new Point(1,-2));
		pntCs.add(new Point(2,-2));
		pntCs.add(new Point(-5, 5));
		pntCs.add(new Point(0,4));

		assignPolarPoints(pntCs);

		setExpire(250);
		setRadius(50);
		setColor(Color.YELLOW);


		//int nX = Game.R.nextInt(10);
		//Change from 0-10 for speed to 2-6
        //int nX = Game.R.nextInt(3) + 150 ;
        int nX =  15 ;
		int nY = Game.R.nextInt(10);
		int nS = Game.R.nextInt(5);
		
		//set random DeltaX
		if (nX % 2 == 0)
			setDeltaX(nX);
		else
			setDeltaX(-nX);

		//set rnadom DeltaY
		if (nY % 2 == 0)
			setDeltaX(nY);
		else
			setDeltaX(-nY);
		
		//set random spin
		//Commented out to turn off spin.
		//if (nS % 2 == 0)
		//	setSpin(nS);
		//else
		//	setSpin(-nS);

		//random point on the screen
        setCenter(new Point(Game.R.nextInt(Game.DIM.width),
                Game.R.nextInt(Game.DIM.height)*2/3));
        //Set the center at 100, 100
        //setCenter(new Point(100,100));

        //random orientation
		//keep orientation at top.
        setOrientation((360));

	}

	public void move() {
        Point pnt = getCenter();
        //double dX = pnt.x + getDeltaX();
        //This changes it to 3-6 for the speed.
        double dX = pnt.x + getDeltaX();
        //double dY = pnt.y + getDeltaY();
        double dY = pnt.y;

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

		setOrientation(getOrientation() + getSpin());

	}

	public int getSpin() {
		return this.nSpin;
	}

	public void setSpin(int nSpin) {
		this.nSpin = nSpin;
	}

	//override the expire method - once an object expires, then remove it from the arrayList.
	@Override
	public void expire() {
		if (getExpire() == 0)
			CommandCenter.movFloaters.remove(this);
		else
			setExpire(getExpire() - 1);
	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
		//fill this polygon (with whatever color it has)
		g.fillPolygon(getXcoords(), getYcoords(), dDegrees.length);
		//now draw a white border
		g.setColor(Color.WHITE);
		g.drawPolygon(getXcoords(), getYcoords(), dDegrees.length);
	}

}
