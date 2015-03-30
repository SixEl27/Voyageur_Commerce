import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Interface extends JFrame{

	/**
	 * @param args
	 */
	public Interface(){
		super("Ma fenetre");
		WindowListener l = new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		};
		addWindowListener(l);
		JButton bouton = new JButton("Mon bouton");
		JPanel panneau = new JPanel();
		panneau.add(bouton);
		setContentPane(panneau);
		setSize(200,100);
		setVisible(true);
	}
}

