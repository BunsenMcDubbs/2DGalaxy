package twoSpace;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.LinkedList;

public class ParticleController {

	private ArrayList<Particle> particles;
	private int width, height;
	private float randFloat;
	
	public ParticleController(int wid, int hei){
		particles = new ArrayList<Particle>();
		width = wid; height = hei;
		randFloat = (float)Math.random();
	}
	
	public void add(Particle p){
		particles.add(p);
	}
	
	public ArrayList<Particle> removeParticles(int amt){
		ArrayList<Particle> removed = new ArrayList<Particle>(amt);
		for(int i = 0; i < amt; i++) removed.add(particles.remove(particles.size()-1));
		return removed;
	}
	
	public void update(){
		for(Particle p : particles){
			p.update();
		}
	}
	
	public void collsionDetect(Particle p){
		if(p.loc.x > width || p.loc.x < 0) {
			p.dir.x = -p.dir.x;
		}
		if(p.loc.y > height || p.loc.y < 0){
			p.dir.y = -p.dir.y;
		}
	}
	
	public void draw1(Graphics2D g2){
		g2.setStroke(new BasicStroke(5f));
		for(int i = 0; i < particles.size(); i++){
			int n = 20;
			for(int j = 0; j < n; j++){
				int k = (j+i) % particles.size();
				g2.setColor(new Color(randFloat,1-j/(float)n,j/(float)n, 1f));
				g2.drawLine((int)particles.get(i).loc.x, (int)particles.get(i).loc.y, (int)particles.get(k).loc.x, (int)particles.get(k).loc.y);
			}
			particles.get(i).draw(g2);
		}
	}
	
	public void draw(Graphics2D g2){
		draw1(g2);
	}

	public void split() {
		Particle[] p = new Particle[particles.size()];
		for(int i = 0; i < particles.size() + 1; i++){
			Particle curr = particles.get(i%particles.size());
			Particle next = particles.get((i+1)%particles.size());
			
			double x = (curr.loc.x + next.loc.x)/2;
			double y = (curr.loc.y + next.loc.y)/2;
			
			double dist = Math.sqrt(Math.pow(curr.loc.x - x, 2) + Math.pow(curr.loc.y - y, 2));
			
			

		}
	}
	
}
