import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.swing.*;


public class imageReader2 {

	
	static BufferedImage img_b1,img_b2;
	public static void time_calculated(int l)
	{
		
		
		int lines=l;
		float scale=(float) 2;
		float width = 512; //Integer.parseInt(args[1]);
		float height = 512; //Integer.parseInt(args[2]);
		int old_width_i=(int) width;
		int old_height_i=(int) height;
		int width_setting_up=(int) width;
		int height_setting_up=(int) height;
		
		img_b1 = new BufferedImage(old_width_i, old_height_i, BufferedImage.TYPE_INT_RGB);
		img_b2 = new BufferedImage(old_width_i, old_height_i, BufferedImage.TYPE_INT_RGB);
		
		
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
			}
		}
		
		for(int i=0;i<old_height_i;i++)
		{
			img_b1.setRGB(0, i, pix);
			img_b1.setRGB( (old_width_i-1), i, pix);
			
		}
		
		for(int i=0;i<old_width_i;i++)
		{
			img_b1.setRGB(i, 0, pix);
			img_b1.setRGB(i,(old_height_i-1), pix);
			
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
			
			for(int y = 0; y <height_setting_up; y++){
				
				for(int x = 0; x < width_setting_up; x++){
			 
					a = 0;
					r = (byte) 255; //bytes[ind];
					g = (byte) 255; //bytes[ind+height*width];
					b = (byte) 255; //bytes[ind+height*width*2]; 
					
					pix = 0xff000000 | ((r & 0xff) << 16) | ((g & 0xff) << 8) | (b & 0xff);
					img_b2.setRGB(x,y,pix);
				}
			}
			
			for(int i=0;i<old_height_i;i++)
			{
				img_b2.setRGB(0, i, pix);
				img_b2.setRGB( (old_width_i-1), i, pix);
				
			}
			
			for(int i=0;i<old_width_i;i++)
			{
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
							img_b2.setRGB((int)(x+(height/2)), (int)(y+(height/2)), pix);
						
					}
					sum+=radian;
					if(sum>=360)
						sum=sum-360;
					count--;		
			}	
			
		
		
	}
	
	
	public int avg_pixel(int a,int b)
	{
		return  ( (((a) & 0xfefefeff) + ((b) & 0xfefefeff)) >> 1 );		
	}
	
   public static void main(String[] args) {
   	

	int lines=8;
	float scale=(float) 2;
	int filtering=0;
	int nor=2;
	
	
	float width = 512; //Integer.parseInt(args[1]);
	float height = 512; //Integer.parseInt(args[2]);
	
		
	int old_width_i=(int) width;
	int old_height_i=(int) height;
	
	int new_width_i=(int)(width/scale);
	int new_height_i=(int)(height/scale);
	int width_setting_up=(int) width;
	int height_setting_up=(int) height;

	
	
	
	
	BufferedImage img = new BufferedImage(old_width_i, old_height_i, BufferedImage.TYPE_INT_RGB);
	img=img_b1;
	
	
	for(int y = 0; y <height_setting_up; y++){
		
		for(int x = 0; x < width_setting_up; x++){
	 
			byte a = 0;
			byte r = (byte) 255; //bytes[ind];
			byte g = (byte) 255; //bytes[ind+height*width];
			byte b = (byte) 255; //bytes[ind+height*width*2]; 
			
			int pix = 0xff000000 | ((r & 0xff) << 16) | ((g & 0xff) << 8) | (b & 0xff);
			img.setRGB(x,y,pix);
		}
	}
	
	JFrame frame=new JFrame();
	JLabel label = new JLabel(new ImageIcon(img));
	frame.getContentPane().add(label, BorderLayout.WEST);
	frame.pack();
	frame.setVisible(true); 
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	float totaly_degree=0;
	
	while(true)
	{
	
	
		byte a = 0;
		byte r = (byte) 0; //bytes[ind];
		byte r3 = (byte) 255; //bytes[ind];
		byte g = (byte) 0; //bytes[ind+height*width];
		byte b = (byte) 0; //bytes[ind+height*width*2]; 
			
		int pix = 0xff000000 | ((r & 0xff) << 16) | ((g & 0xff) << 8) | (b & 0xff);
		int pix_temp = 0xff000000 | ((r & 0xff) << 16) | ((g & 0xff) << 8) | (b & 0xff);
		int pix2= 0xff000000 | ((r3 & 0xff) << 16) | ((g & 0xff) << 8) | (b & 0xff);
		
		double radian=Math.toRadians(360.0/lines);
		int count=lines;
		float sum=totaly_degree;
		
		
		for(int i=0;i<old_height_i;i++)
		{
			img.setRGB(0, i, pix);
			img.setRGB( (old_width_i-1), i, pix);
			
		}
		
		for(int i=0;i<old_width_i;i++)
		{
			img.setRGB(i, 0, pix);
			img.setRGB(i,(old_height_i-1), pix);
			
		}
		
		while(count>0)
		{
				if(count==lines)
					pix=pix2;
				else
					pix=pix_temp;
				for(int r1=0;r1<((Math.sqrt(2))* (height/2));r1++)
				{ 
					int x=(int) (r1*(Math.cos(sum)));
					int y=(int) (r1*(Math.sin(sum)));
					if( ((x+(height/2))>=height) || (x+(height)/2)<0|| ((y+(height/2))>=height) || (y+(height)/2)<0 ) 
					{
						continue;
					}
					else
						img.setRGB((int)(x+(height/2)), (int)(y+(height/2)), pix);
					
				}
				sum+=radian;
				count--;		
			}
			frame.repaint();		
			
			
			
			
			totaly_degree+=radian/2;
			if(totaly_degree>360)
				totaly_degree=0;
			
	
			
			try {
				Thread.sleep(0);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(int y = 0; y <height_setting_up; y++){
				
				for(int x = 0; x < width_setting_up; x++){
			 
					a = 0;
					r = (byte) 255; //bytes[ind];
					g = (byte) 255; //bytes[ind+height*width];
					b = (byte) 255; //bytes[ind+height*width*2]; 
					
					pix = 0xff000000 | ((r & 0xff) << 16) | ((g & 0xff) << 8) | (b & 0xff);
					img.setRGB(x,y,pix);
				}
			}
	
				frame.repaint();			
			
	}		
			
	
   
   }
  
}