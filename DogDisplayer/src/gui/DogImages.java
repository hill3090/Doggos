package gui;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;

import reader.JsonReader;
import org.json.*;
import java.io.IOException;
import java.net.URL;

/**
 * This class makes a call to https://dog.ceo/dog-api/ and returns images on 
 * GUI of different dogs.
 * @author hill3
 *
 */
public class DogImages extends JFrame {
	
	private JPanel imagePanel;
	private JPanel buttonPanel;
	private JLabel imageLabel;
	private JButton button;	
	private BufferedImage image;
	
	public DogImages()	{
		
		// Set title
		setTitle("Doggos");
		// Specify an action for the close button
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Create a BorderLayout Manager
		setLayout(new BorderLayout());
		// Build the panels
		buildImagePanel();
		buildButtonPanel();
		// Add the panels to the content pane
		add(imagePanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
		// Pack and display the window
		pack();
		setVisible(true);
	}
	
	/**
	 * The buildImagePanel method adds a label to a panel.
	 * @param args
	 */
	private void buildImagePanel()	{
		
		// Create a panel
		imagePanel = new JPanel();
		// Create a label
		imageLabel = new JLabel("Click the button to see an image of a dog.");
		// Add the label to the panel
		imagePanel.add(imageLabel);
	}
	
	/**
	 * The buildButtonPanel method adds a button to a panel
	 * @param args
	 */
	private void buildButtonPanel()	{
		
		ImageIcon smileyImage;
		
		// Create a panel
		buttonPanel = new JPanel();
		// Get the smiley face image
		smileyImage = new ImageIcon("D:\\Java\\SwingGuI\\Images\\Smiley.gif");
		// Create a button
		button = new JButton("Get Image");
		button.setIcon(smileyImage);
		// Register an action listener with the button.
		button.addActionListener(new ButtonListener());
		// Add button to the panel
		buttonPanel.add(button);
	}
	
	/**
	 * Private inner class that handles the event when the user clicks on the button
	 * @param args
	 */
	private class ButtonListener implements ActionListener	{
		
		public void actionPerformed(ActionEvent e)	{
			
			
			JSONObject json;
			JsonReader reader = new JsonReader();
			ImageIcon dogImage;
			String photo;
			
			try {
				// Make call to https://dog.ceo/dog-api/
				json = reader.readJsonFromUrl("https://dog.ceo/api/breeds/image/random");
				// Get string value associated with key value "message" object
				photo = json.getString("message");
				// Pass string value into a URL object and read into Image object
				image = ImageIO.read(new URL(photo));
				// Pass Image object into ImageIcon object
				dogImage = new ImageIcon(image);
				// Display ImageIcon in label
				imageLabel.setIcon(dogImage);
				// Remove text from label
				imageLabel.setText(null);
				// Pack the frame again to accomodate the new size of the label.
				pack();
				
			} catch (JSONException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new DogImages();
	}
}
