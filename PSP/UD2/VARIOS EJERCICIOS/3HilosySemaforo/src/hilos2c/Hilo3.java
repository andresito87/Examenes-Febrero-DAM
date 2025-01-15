package hilos2c;

import java.util.concurrent.Semaphore;

public class Hilo3 extends Thread
{
	 protected Semaphore P1;
	 
	 public Hilo3(Semaphore P1)
	 {
		 this.P1 = P1;
	 }
	 
	 public void run()
	 {
		 try
		 {
			 this.P1.acquire();
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 
		 for (int i=0; i<10; i++)
		 {
			 System.out.println("BYE ");
		 }
	 }
}