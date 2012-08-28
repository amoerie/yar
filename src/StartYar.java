import java.awt.Dimension;

import gui.YarFrame;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class StartYar {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame renamerFrame = new YarFrame();
				renamerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				renamerFrame.setSize(new Dimension(800,600));
				renamerFrame.setLocationRelativeTo(null);
				renamerFrame.setVisible(true);
			}
		});

	}

}
