package twoSpace;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class AnimatedCircle extends JPanel implements ActionListener{

	ParticleController p;
	double angle;
	int age;

	public AnimatedCircle(){
		setSize(800,800);
		setPreferredSize(getSize());
		setBackground(Color.DARK_GRAY);
		setForeground(Color.CYAN);
		Timer t = new Timer(1000/60, this);
		t.start();

		p = new ParticleController(getWidth(),getHeight());

		angle = 0;
		age = 0;

		init1();
	}

	private void init1(){
		int points = 20;
		double interval = (2*Math.PI)/(double)points;
		double angle = 0;
		for(int i = 0; i < points; i++){
			double x = Math.cos(angle);
			double y = Math.sin(angle);
			p.add(new Particle(5, x*100 + getWidth()/2, y*100 + getHeight()/2, x*i/15f, y*i/15f));
			angle += interval;
		}
	}

	public void paint1(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.LIGHT_GRAY);
		g2.fillRect(0, 0, getWidth(), getHeight());

		g2.setColor(Color.CYAN);
		p.draw(g2);
	}

	public void paint(Graphics g){
		paint1(g);
	}

	public void update(){
		p.update();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		update();
		repaint();
		age++;
	}


}
