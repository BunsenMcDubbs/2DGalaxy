package twoSpace;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

public class Line {

	Point2D.Double start;
	Point2D.Double end;
	Point2D.Double center;

	Line mLeft, mRight, left, right, bottom;

	boolean split;
	boolean pol;
	boolean centered;

	public Line(int x1, int y1, int x2, int y2){
		start = new Point2D.Double(x1, y1);
		end = new Point2D.Double(x2, y2);
		center = new Point2D.Double(0,0);
		pol = false;
		split = false;
		centered = false;
	}

	public Line(double r, double theta, double r2, double theta2){
		start = new Point2D.Double(r, theta);
		end = new Point2D.Double(r2, theta2);
		center = new Point2D.Double(0,0);
		pol = true;
		split = false;
		centered = false;
	}

	public Line(Point2D.Double start, Point2D.Double end, Point2D.Double center) {
		this.start = start;
		this.end = end;
		this.center = center;
		pol = false;
		split = false;
		centered = false;
	}

	public void split(){
		if (split == true) {
			mLeft.split();
			mRight.split();
			left.split();
			right.split();
			bottom.split();
			return;
		}
		split = true;
		polToCart();

		Point2D.Double mid = midpoint(start, end);

		Point2D.Double leftTemp = midpoint(start, mid);
		Point2D.Double rightTemp = midpoint(mid, end);

		double dist = Math.sqrt(Math.pow((leftTemp.x - rightTemp.x),2) + Math.pow((leftTemp.y - rightTemp.y),2));
		dist = 1.7320508075688772 * dist / 2;
		mid = Fractal.cartToPol(mid, center);
		mid.x += dist;
		mid = Fractal.polToCart(mid, center);

		Point2D.Double cMid = Fractal.cartToPol(mid, center);
		Point2D.Double cLeft = Fractal.cartToPol(start, center);
		Point2D.Double cRight = Fractal.cartToPol(end, center);

		cMid.x -= dist/1.5;

		double sidedist = Math.sqrt((leftTemp.x - start.x) * (leftTemp.x - start.x) + (leftTemp.y - start.y) * (leftTemp.y - start.y));
		sidedist = 1.7320508075688772 * sidedist / 3;

		cLeft.x -= sidedist;
		cRight.x -= sidedist;

		cMid = Fractal.polToCart(cMid, center);
		cLeft = Fractal.polToCart(cLeft, center);
		cRight = Fractal.polToCart(cRight, center);

		mLeft = new Line(leftTemp, mid, cMid);
		mRight = new Line(mid, rightTemp, cMid);
		left = new Line(start, leftTemp, cLeft);
		right = new Line(rightTemp, end, cRight);
		bottom = new Line(leftTemp, rightTemp, cMid);

	}

	public static Point2D.Double midpoint(Point2D.Double start, Point2D.Double end){
		return new Point2D.Double(start.x/2 + end.x/2, start.y/2 + end.y/2);
	}

	public boolean cartToPol(){
		if(pol == true) return false;
		start = Fractal.cartToPol(start, center);
		end = Fractal.cartToPol(end, center);
		pol = true;
		return true;
	}

	public boolean polToCart(){
		if(pol == false) return false;
		start = Fractal.polToCart(start, center);
		end = Fractal.polToCart(end, center);
		pol = false;
		return true;
	}

	public void draw(Graphics2D g2){

		g2.setColor(Color.cyan);
//		g2.setStroke(new BasicStroke(2));
		polToCart();
		if(!split) g2.draw(new Line2D.Double(start, end));
		else{
//			if(left.split){
				left.draw(g2);
				right.draw(g2);
				bottom.draw(g2);
//			}
			mLeft.draw(g2);
			mRight.draw(g2);
			if (!left.split){
//				g2.setColor(Color.green);
//				g2.setStroke(new BasicStroke(1));
//				Point2D.Double ltemp = midpoint(this.left.start, this.left.end);
//				g2.drawLine((int)ltemp.x, (int)ltemp.y, (int)left.center.x, (int)left.center.y);
//				g2.setColor(Color.red);
//				Point2D.Double rtemp = midpoint(this.right.start, this.right.end);
//				g2.drawLine((int)rtemp.x, (int)rtemp.y, (int)right.center.x, (int)right.center.y);
//				g2.setColor(Color.orange);
//				Point2D.Double mrtemp = midpoint(this.mRight.start, this.mRight.end);
//				g2.drawLine((int)mrtemp.x, (int)mrtemp.y, (int)mRight.center.x, (int)mRight.center.y);
//				g2.setColor(Color.magenta);
//				Point2D.Double mltemp = midpoint(this.mLeft.start, this.mLeft.end);
//				g2.drawLine((int)mltemp.x, (int)mltemp.y, (int)mLeft.center.x, (int)mLeft.center.y);


			}
		}

	}

}
