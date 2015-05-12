import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import javax.swing.JComponent;


public class Dessin extends JComponent{

	int X,Y;
	ArrayList<Shape> listeForme;
	Shape formeEnCours;
	
	public Dessin(){
		super();
		formeEnCours = null;
		listeForme = new ArrayList<Shape>();
		
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				X = e.getX();
				Y = e.getY();
				double tailleVille=10;
				formeEnCours = new Ellipse2D.Double(X-(tailleVille/2.0),Y-(tailleVille/2.0),tailleVille,tailleVille);
				repaint();
			}
		});
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.RED);
		if(formeEnCours!=null)
			g2.draw(formeEnCours);
		g2.setColor(Color.BLUE);
		for(int i=0;i<listeForme.size();i++)
			g2.fill(listeForme.get(i));
	}
}


