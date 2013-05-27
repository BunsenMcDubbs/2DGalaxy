package twoSpace;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GalaxyFrame extends JFrame{
	
	public static final Color bg = Color.DARK_GRAY;
	
	public GalaxyFrame(){
		super("Galaxy");
		setTitle("Galaxy");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		init();

		setVisible(true);
	}

	private void init() {
//		add(new AnimatedCircle());
		Fractal f = new Fractal();
		add(f);
		addMouseListener(f);
		addComponentListener(f);
		setSize();
//		setBackground(bg);
	}
	
	private void setSize() {
		setResizable(true);
		pack();
	}
	
	public static void main(String [] args){
		GalaxyFrame f = new GalaxyFrame();
	}

}
