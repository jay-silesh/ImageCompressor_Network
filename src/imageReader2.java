import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.swing.*;


public class imageReader2 {

	
	static BufferedImage img_b1,img_b2,img_w;
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
   	

	int lines=4;
	int nor=10;
	int times_to_execute_for_one=lines*2; //basically 8 for 4 lines for one rotation  =8
	int times_to_execute_for_nor=times_to_execute_for_one*nor; //8*10= 80ms or 80 times it has to execute to complete for 10 rotations 
	int total_time_sleep=1000-times_to_execute_for_nor; // 1000-80=920ms left to sleep
	int sleep_for_each_rotation=total_time_sleep/(times_to_execute_for_nor); //920/80=11.5
	
	float width = 512; //Integer.parseInt(args[1]);
	float height = 512; //Integer.parseInt(args[2]);
	
		
	diag_calculated1(lines,width,height);
	
	JFrame frame=new JFrame();
	JLabel label  = new JLabel(new ImageIcon(img_b1));
	frame.getContentPane().add(label, BorderLayout.WEST);
	frame.pack();
	frame.setVisible(true); 
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	BufferedImage [] test={img_b1,img_b2};
	BufferedImage [] test2={img_b1,img_w};
	int i=0;
	while(true)
	{
		label.setIcon(new ImageIcon(test[i]));
		try {
			Thread.sleep(sleep_for_each_rotation);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		i++;
		if(i>1)
			i=0;
	
	}
	

	
  
   }
  
}