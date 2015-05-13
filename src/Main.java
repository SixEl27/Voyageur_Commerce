
import javax.swing.JFrame;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interface().setVisible(true);
            }
        });
        /**
        VDC VDC1 = new VDC("Essai");
        Ville a = new Ville(0, 0, "a");
        Ville b = new Ville(10, 10, "b");
        VDC1.ajouterVille(a);
        VDC1.ajouterVille(b);
        */
    }

}
