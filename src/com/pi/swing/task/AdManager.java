package com.pi.swing.task;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

import org.apache.log4j.Logger;

import com.pi.swing.util.Config;

public class AdManager {
	private static final Logger logger = Logger.getLogger(AdManager.class);

	public static void main(String[] args) throws IOException {

		final AddLayout imageLayOut = new AddLayout();
		imageLayOut.imageFrame.setVisible(true);
		imageLayOut.imageFrame.setLocationRelativeTo(null);
		imageLayOut.imageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		imageLayOut.imageFrame.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent arg0) {
				logger.info("Strting Swing Window ....");
				try {
					AddLayout.image = ImageIO.read(new File(Config
							.getKey("imgpath")));
					AddLayout.panel.repaint();
					AddLayout.continueToRun = true;
				} catch (IOException e) {
					e.printStackTrace();
					logger.error("Error in fetchin default Image.");
				}

			}

			@Override
			public void windowIconified(WindowEvent arg0) {
			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
			}

			@Override
			public void windowClosing(WindowEvent arg0) {
			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				imageLayOut.close();
			}

			@Override
			public void windowActivated(WindowEvent arg0) {
			}
		});

		
		//ESC key Close 
		KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,
				0, false);
		Action escapeAction = new AbstractAction() {
			// close the frame when the user presses escape
			public void actionPerformed(ActionEvent e) {
				imageLayOut.imageFrame.dispose();
			}
		};
		imageLayOut.imageFrame.getRootPane()
				.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(escapeKeyStroke, "ESCAPE");
		imageLayOut.imageFrame.getRootPane().getActionMap()
				.put("ESCAPE", escapeAction);

	}

}
