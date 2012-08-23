import java.awt.Dimension;

import gui.RenamerFrame;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class StartRenamer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame renamerFrame = new RenamerFrame();
				renamerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				renamerFrame.setSize(new Dimension(800,600));
				renamerFrame.setLocationRelativeTo(null);
				renamerFrame.setVisible(true);
			}
		});

	}

}
