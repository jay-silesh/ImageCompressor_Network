import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.swing.*;


public class imageReader2 extends Thread{

	
	public static BufferedImage img_b1,img_b2,img_w,img;
	public static JLabel label;
	public static JLabel label2;
	public static JFrame frame;
	static int sleep_for_each_rotation;
	public static BufferedImage [] test;
	public static BufferedImage [] test2;
	public static void diag_calculated1(int l,float width2,float height2)
	{
		
		
		int lines=l;
		float width = width2; //Integer.parseInt(args[1]);
		float height = height2; //Integer.parseInt(args[2]);
		int old_width_i=(int) width;
		int old_height_i=(int) height;
		int width_setting_up=(int) width;
		int height_setting_up=(int) height;
		
		img_b1 = new BufferedImage(old_width_i, old_height_i, BufferedImage.TYPE_INT_RGB);
		img_b2 = new BufferedImage(old_width_i, old_height_i, BufferedImage.TYPE_INT_RGB);
		img_w = new BufferedImage(old_width_i, old_height_i, BufferedImage.TYPE_INT_RGB);
		
		byte a = 0;
		byte r = (byte) 0; //bytes[ind];
		byte g = (byte) 0; //bytes[ind+height*width];
		byte b = (byte) 0; //bytes[ind+height*width*2]; 
		int pix = 0xff000000 | ((r & 0xff) << 16) | ((g & 0xff) << 8) | (b & 0xff);
		double radian=Math.toRadians(360.0/lines);
		int count=lines;
		
		float sum=0;
		for(int y = 0; y <height_setting_up; y++){
			
			for(int x = 0; x < width_setting_up; x++){
		 
				a = 0;
				r = (byte) 255; //bytes[ind];
				g = (byte) 255; //bytes[ind+height*width];
				b = (byte) 255; //bytes[ind+height*width*2]; 
				
				pix = 0xff000000 | ((r & 0xff) << 16) | ((g & 0xff) << 8) | (b & 0xff);
				img_b1.setRGB(x,y,pix);
				img_b2.setRGB(x,y,pix);
				img_w.setRGB(x,y,pix);
			}
		}
		
		
		
		 a = 0;
		 r = (byte) 0; //bytes[ind];
		 g = (byte) 0; //bytes[ind+height*width];
		 b = (byte) 0; //bytes[ind+height*width*2]; 
		pix = 0xff000000 | ((r & 0xff) << 16) | ((g & 0xff) << 8) | (b & 0xff);
		
		
		for(int i=0;i<old_height_i;i++)
		{
			img_b1.setRGB(0, i, pix);
			img_b1.setRGB( (old_width_i-1), i, pix);
			img_b2.setRGB(0, i, pix);
			img_b2.setRGB( (old_width_i-1), i, pix);
			
		}
		
		for(int i=0;i<old_width_i;i++)
		{
			img_b1.setRGB(i, 0, pix);
			img_b1.setRGB(i,(old_height_i-1), pix);
			img_b2.setRGB(i, 0, pix);
			img_b2.setRGB(i,(old_height_i-1), pix);
			
		}
		
		while(count>0)
		{
				for(int r1=0;r1<((Math.sqrt(2))* (height/2));r1++)
				{ 
					int x=(int) (r1*(Math.cos(sum)));
					int y=(int) (r1*(Math.sin(sum)));
					if( ((x+(height/2))>=height) || (x+(height)/2)<0|| ((y+(height/2))>=height) || (y+(height)/2)<0 ) 
					{
						continue;
					}
					else
						img_b1.setRGB((int)(x+(height/2)), (int)(y+(height/2)), pix);
					
				}
				sum+=radian;
				count--;		
		}
		
		sum=(float) (radian/2);
		count=lines;
		while(count>0)
		{
				for(int r1=0;r1<((Math.sqrt(2))* (height/2));r1++)
				{ 
					int x=(int) (r1*(Math.cos(sum)));
					int y=(int) (r1*(Math.sin(sum)));
					if( ((x+(height/2))>=height) || (x+(height)/2)<0|| ((y+(height/2))>=height) || (y+(height)/2)<0 ) 
					{
						continue;
					}
					else
						img_b2.setRGB((int)(x+(height/2)), (int)(y+(height/2)), pix);
					
				}
				sum+=radian;
				if(sum>=360)
					sum=360-sum;
				count--;		
		}
		
}
	
	
	
	public static void main(String[] args) {
   	

	/*int lines = 4; 
	float nor=(float) 4;
	float fps=(float) 8;
	*/
    
	if(args.length!=3)
	{
		System.out.println("\nInvalid Arguments");
		System.exit(1);
	}
	
	
	int lines=Integer.parseInt(args[0]);
	float nor=Float.parseFloat(args[1]);
	float fps=Float.parseFloat(args[2]);
	
	
	float nor1=nor*10;
	float temp1=1000/(nor1*2);
	temp1=temp1*10;
	sleep_for_each_rotation=(int) temp1;
	
	
	float fps1=fps*10;
	temp1=1000/(fps1*2);
	temp1=temp1*10;
	int time_to_wait=(int) temp1;
	
	float width = 512; //Integer.parseInt(args[1]);
	float height = 512; //Integer.parseInt(args[2]);
	
		
	diag_calculated1(lines,width,height);
	
	JFrame frame=new JFrame();
	label  = new JLabel(new ImageIcon(img_w));
	label2  = new JLabel(new ImageIcon(img_w));
	frame.getContentPane().add(label, BorderLayout.WEST);
	frame.getContentPane().add(label2, BorderLayout.EAST);
	frame.pack();
	frame.setVisible(true); 
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	
	img=img_b1;
	
	 (new imageReader2()).start();
	 
	 while(true)
		{
			label2.setIcon(new ImageIcon(img));
			try {
				Thread.sleep(time_to_wait);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		
		
		
   }
	
	public void run()
	{
		
		int i=0;
		while(true)
		{
			
			label.setIcon(new ImageIcon(img));
			
			try {
				Thread.sleep(sleep_for_each_rotation);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i++;
			if(i>1)
				i=0;
			if(i==1)
				img=img_b2;
			else
				img=img_b1;	
		
		}
	}
  
}