import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

/**
   This component draws two car shapes.
*/
public class CarPanel extends JComponent
{  
	private Car car1;
	private int x,y, delay;
	private CarQueue carQueue;
	private int direction;
	
	CarPanel(int x1, int y1, int d, CarQueue queue)
	{
		delay = d;
        x=x1;
        y=y1;
        car1 = new Car(x, y, this);
        carQueue = queue;
	}
	public void startAnimation()
	   {
	      class AnimationRunnable implements Runnable
	      {
	         public void run()
	         {
	            try
	            {
	  
	            	while (true) {
	            	   direction = carQueue.deleteQueue();
	            	   if (direction == 2 && x != 250) // Right
	            		   x = x+10;
	            	   else
	            		   x = x-10;
	            	   if (direction == 3 && x != 20) // Left
	            		   x = x-10;
	            	   else
	            		   x = x+10;
	            	   if (direction == 1 && x != 0) // Down
	            		   y = y-10;
	            	   else
	            		   y = y+10;
	            	   if (direction == 0 && x != 400) // Up
	            		   y = y+10;
	            	   else
	            		   y = y-10;
	            	   repaint();
	            	   Thread.sleep(delay*1000);
	            	   // Else statements ensure the cars stay in frame.
	            	}
	            	   
	               
	            }
	            catch (InterruptedException exception)
	            {
	            	
	            }
	            finally
	            {
	            	
	            }
	         }
	      }
	      
	      Runnable r = new AnimationRunnable();
	      Thread t = new Thread(r);
	      t.start();
	   }
	
   public void paintComponent(Graphics g)
   {  
      Graphics2D g2 = (Graphics2D) g;

      car1.draw(g2,x,y);    
   }
}
