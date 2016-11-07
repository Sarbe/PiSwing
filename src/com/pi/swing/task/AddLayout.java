package com.pi.swing.task;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import com.pi.swing.bean.ImagePayLoad;
import com.pi.swing.util.Config;
import com.pi.swing.util.ImageURL;

public class AddLayout {

	private static final Logger logger = Logger.getLogger(AddLayout.class);
	public JFrame imageFrame;
	static BufferedImage image = null;
	static JPanel panel = null;
	static JLabel lblImageDtl = null;
	static String touchFilePath = Config.getKey("touchFile");
	private static int imgCntr = 0;
	static boolean continueToRun = false;
	private String imgPath = Config.getKey("imgpath");
	Timer t = null;
	
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddLayout window = new AddLayout();
					window.imageFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	public AddLayout() {
		initialize();
	}

	private void initialize() {

		imageFrame = new JFrame();
		imageFrame.getContentPane().setLayout(new BorderLayout());

		panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				if (image != null) {
					g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
				}
			}
		};
		imageFrame.getContentPane().add(panel, BorderLayout.CENTER);
		
		if(Config.getKey("showLbl").equals("Y")){
			lblImageDtl = new JLabel("New label");
			imageFrame.getContentPane().add(lblImageDtl, BorderLayout.SOUTH);
		}
		
		imageFrame.setUndecorated(true);

		//imageFrame.setSize(500, 500);
		imageFrame.setLocationRelativeTo(null);
		imageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// imageFrame.setVisible(true);
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gs = ge.getDefaultScreenDevice();
		gs.setFullScreenWindow(imageFrame);
		imageFrame.validate();
		
		
		// Start the Task
		CustomTask task = new CustomTask();
		t = new Timer();
		t.schedule(task, 3000);
	}

	public void close() {
		continueToRun = false;
		t.cancel();
		t.purge();
	}

	class CustomTask extends TimerTask {
		@Override
		public void run() {
			System.out.println("FormLayout.Temp.run()");
			String startWindow = "Y";
			while (continueToRun) {
				try {
					
					File touchFile = new File(touchFilePath);

					if (touchFile.exists()) {
						//startWindow
						System.out.println("startWindow :: " + startWindow);
						ImagePayLoad img = ImageURL.getImage(startWindow);
						startWindow = "N";
						if (img.getMediaByteArray() != null && !img.getMediaByteArray().equals("")) {
							imgCntr++;
							logger.info("Showing Image :: " + img.getFileName() + " with duration - " + img.getDuration());
							if(Config.getKey("showLbl").equals("Y")){
								AddLayout.lblImageDtl.setText(imgCntr +" Showing Image :: " + img.getFileName() + " with duration - " + img.getDuration());
							}

							AddLayout.image = ImageIO.read(new ByteArrayInputStream(Base64.decodeBase64(img.getMediaByteArray())));
							AddLayout.panel.repaint();
							Thread.sleep(img.getDuration());
						} else {
							AddLayout.image = ImageIO.read(new File(imgPath));
							AddLayout.panel.repaint();
							Thread.sleep(5000);
						}
					} else {
						startWindow = "Y";
						AddLayout.image = ImageIO.read(new File(imgPath));
						AddLayout.panel.repaint();
						Thread.sleep(5000);
					}
				} catch (Exception e) {
					startWindow = "Y";
					e.printStackTrace();
					logger.error(e.getMessage());
					continue;
				}
			}

		}
	}
}
