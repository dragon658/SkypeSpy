package skype1;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class SkypeClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
	        while (true) {
	        	doJob();
	            Thread.sleep(60 * 1000);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public static void doJob() {
		Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
		BufferedImage capture;
		try {
			capture = new Robot().createScreenCapture(screenRect);
			
			String file_settings = "settings.txt";
			Scanner s = new Scanner(new File(file_settings)).useDelimiter("[;\r\n]+");
			int x = Integer.parseInt(s.next());
			int y = Integer.parseInt(s.next());
			String login = s.next();
			String pswd = s.next();
			String to = s.next();
			String title = s.next();
			String text = s.next();
			
			int rgb = capture.getRGB(x - 1, y - 1);
			int r = (rgb >> 16) & 0xFF;
			int g = (rgb >> 8) & 0xFF;
			int b = (rgb & 0xFF);
		    
		    String file = "already_new_message.txt";
		    String already_new_message = new Scanner(new File(file)).useDelimiter("\\Z").next();
		    
		    if (r != 255 || g != 255 || b != 255) {
		    	if (already_new_message.equals("Yes")) {
		    		System.out.println("New message - NOT send email");
		    	}
		    	else {
		    		already_new_message = "Yes";
		    		System.out.println("New message - send email");
		    		GoogleMail.Send(login, pswd, to, title, text);
		    	}
		    }
		    else {
		    	already_new_message = "No";
		    	System.out.println("No new messages");
		    }
		    
		    Files.write(Paths.get(file), already_new_message.getBytes());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
