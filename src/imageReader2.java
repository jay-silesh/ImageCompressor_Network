import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.swing.*;


public class imageReader2 extends JPanel{

	
	public static BufferedImage img_b1,img_b2,img_w,img;
	public static BufferedImage img2;
	public static JFrame frame;
	static int sleep_for_each_rotation;
	public static double radian;
	public static float start_sum,sum;
	public static int lines;
	public static float width,height;
	
	public void paintComponent(Graphics g) 
	{
		g.clearRect(0, 0, (int)width,  (int)height);
		int count;
		count=lines;
		sum=start_sum;
		while(count>=0)
		{
			for(int r1=0;r1<((Math.sqrt(2))* (height/2));r1++)
			{ 
				int x=(int) (r1*(Math.cos(sum)));
				int y=(int) (r1*(Math.sin(sum)));
				
				if( ((x+(height/2))==height) || (x+(height)/2)==0|| ((y+(height/2))==height) || (y+(height)/2)==0 ) 
				{
					g.drawLine(255+x, 255+y,255,255);
					break;
				}
				
			}
			sum+=radian;
			if(sum>360)
				sum=0;
			--count;		
		}
	}
	
	
	public static void main(String[] args) {
   	
	imageReader2 image1=new imageReader2();
	lines = 10; 
	float nor=(float) 4;
	float fps=(float) 18;
	
    
	radian=Math.toRadians(360.0/lines);
	
	
	float nor1=nor*10;
	float temp1=1000/(nor1*2*lines);
	temp1=temp1*10;
	sleep_for_each_rotation=(int) temp1;
	
	
	float fps1=fps*10;
	temp1=1000/(fps1);
	temp1=temp1*10;
	int time_to_wait=(int) temp1;
	
	width = 512; //Integer.parseInt(args[1]);
	height = 512; //Integer.parseInt(args[2]);
	
		
	
	JFrame frame=new JFrame();
	frame.setSize(512, 512);
	frame.add(image1);
	//frame.pack();
	frame.setVisible(true); 
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	start_sum=0;
	
	frame.repaint();
	while(true)
	{
		frame.repaint();
		try {
			Thread.sleep(sleep_for_each_rotation);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		start_sum+=radian/2;
		if(start_sum>=360)
			start_sum=0;
	}
	
  }
}