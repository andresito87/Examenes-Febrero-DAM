package PaquetePrincipal;

public class HiloAuxiliar extends Thread
{
//c�digo del hilo
  public void run()
  {
    for(int i=10;i>=1;i--)
      System.out.print(i+",");
  }
}