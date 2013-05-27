package twoSpace;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Fractal extends JPanel implements ActionListener, MouseListener, ComponentListener{

	ArrayList<Line> l;
	Line line;
	int time;
	Timer t;
//	boolean clear; //for use with component listener for resizing issues when optimizing line drawing
	//performance (overlapping/clearing)
	
	public Fractal(){
		setSize(650,650);
		setPreferredSize(getSize());
		setBackground(Color.DARK_GRAY);
		setForeground(Color.CYAN);
		
		time = 0;
		t = new Timer(750, this);
		init();
//		clear = false;
	}

	
	public void init(){
		l = new ArrayList<Line>();
		int sides = 3;
		double interval = Math.PI*2/sides;
		double shift = 2d;
		double r = 450d;
		for(int i = 0; i < sides; i++){
			l.add(new Line(r, (double)interval*i + shift, r, (double)interval*(i+1) + shift));
			l.get(i).center = new Point2D.Double(0,0);
		}
	}
	
	public void paint(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		
//		if(time == 0)
			super.paint(g2);
			
		g2.setColor(Color.cyan);
		g2.translate(getWidth() / 2, getHeight() / 2);
		for (Line line : l)
			line.draw(g2);
	}
	
	public static Point2D.Double cartToPol(Point2D.Double p){
		return cartToPol(p, new Point2D.Double(0,0));
	}
	
	public static Point2D.Double cartToPol(Point2D.Double p, Point2D.Double center){
		double x = p.x - center.x;
		double y = p.y - center.y;
		boolean q3 = (y < 0 && x < 0);
		boolean q2 = (y > 0 && x < 0);
		
		double r = Math.sqrt(x*x + y*y);
		double theta = Math.atan(y/x);
		
		if(q3)
			theta += Math.PI;
		else if (q2)
			theta -= Math.PI;
		
		return new Point2D.Double(r,theta);
	}
	
	public static Point2D.Double polToCart(Point2D.Double p){
		return polToCart(p, new Point2D.Double(0,0));
	}
	
	public static Point2D.Double polToCart(Point2D.Double p, Point2D.Double center){
		double r = p.x;
		double theta = p.y;
		
		while (theta < 0)
			theta += (2 * Math.PI);
		
//		if(theta > Math.PI && theta < Math.PI*0.5)
//			System.out.println("Q2");
//		else if(theta > Math.PI *2)
//			System.out.println("Q5");
		
		double x = r * Math.cos(theta) + center.x;
		double y = r * Math.sin(theta) + center.y;
		
		return new Point2D.Double(x,y);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(time == 7) t.stop();
		System.out.println();
		time++;
		for(Line line : l)
			line.split();
		repaint();
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.print("Generation: " + time + "\n\tStart");
		int x = e.getX()-getWidth()/2;
		int y = e.getY()-getHeight()/2;
//		System.out.println(x + " " + y);
		for(Line line : l)
			line.split();
		repaint();
		
		System.out.println("\tEnd");
		
		time++;
//		t.start();
		
		
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void componentHidden(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void componentMoved(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void componentResized(ComponentEvent e) {
//		System.out.println("Resized");
//		clear = true;
//		repaint();
	}


	@Override
	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}
