import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.swing.*;


public class imageReader2  extends JPanel implements Runnable{

	
	public static BufferedImage img_b1,img_b2,img_w,img;
	public static BufferedImage img2;
	public static JFrame frame;
	public static JLabel label;
	public static JPanel panel,panel2;
	static int sleep_for_each_rotation;
	public static double radian;
	public static float start_sum,sum;
	public static int lines;
	public static float width,height;
	public static imageReader2 image1;
	public static imageReader2 image2;
	public static int divide_size;
	public static int time_to_wait2;
	
	public static void main(String[] args) {
		float nor,fps;
	
		lines = 9; 
		nor=(float)10;
		fps=(float)10;
	
	
	/*      if(args.length!=3)
		{
			System.out.println("\nInvalid Arguments");
			System.exit(1);
		}
        	lines=Integer.parseInt(args[0]);
		nor=Float.parseFloat(args[1]);
		fps=Float.parseFloat(args[2]);
         */
	
	image1=new imageReader2();
	image2=new imageReader2();
	divide_size=6;
	
	
	radian=Math.toRadians(360.0/lines);	
	
	float nor1=nor*10;
	float temp1=1000/(nor1*lines*divide_size);
	temp1=temp1*10;
	sleep_for_each_rotation=(int) temp1;	
	if(sleep_for_each_rotation<1)
		sleep_for_each_rotation=0;

        
      
	
	System.out.println("sleepin for " +sleep_for_each_rotation);
	
	float fps1=fps*10;
	temp1=1000/(fps1);
	temp1=temp1*10;
	int time_to_wait=(int) temp1;	
//	time_to_wait=time_to_wait/divide_size;
    
	if(time_to_wait<1)
		time_to_wait=0;
	
	width = 512; //Integer.parseInt(args[1]);
	height = 512; //Integer.parseInt(args[2]);	

	
	frame=new JFrame();
	
	panel = new JPanel();
	panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
	panel.add(image1);
	panel.add(image2);
	
	frame.add(panel,BorderLayout.CENTER);
	frame.pack();
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize((int)width*2, (int)height);
	frame.setVisible(true);
	 
   
	(new  Thread(new imageReader2())).start();
	
	
	while(true)
	{
		image2.setUI(image1.getUI());
		try {
			Thread.sleep(time_to_wait);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
  }
	public void paintComponent(Graphics g) 
	{
			g.clearRect(0, 0, (int)width,  (int)height);
			int count;
			count=lines;
			float sum=start_sum;
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
	@Override
	public void run() {
		// TODO Auto-generated method stub
		start_sum=0;
		
		while(true)
		{	//frame.repaint();
			
			image1.repaint();
			
			
			try {
				Thread.sleep(sleep_for_each_rotation);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			start_sum+=radian/divide_size;
			//start_sum++;
			if(start_sum>=360)
				start_sum=0;
			
		
		}
                
	
	}
}

