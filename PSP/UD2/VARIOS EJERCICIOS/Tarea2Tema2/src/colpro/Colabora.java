package colpro;

public class Colabora
{
	public static void main(String[] args)
	{
		 try
		 {//lanzar la ejecuci�n de un proceso puede generar excepciones
			 Process nuevoProceso = Runtime.getRuntime().exec("java -jar produce.jar" );
			 //Lanzamos la ejecuci�n de un proceso
			 System.out.println("Creado");
		 }
		 catch (Exception ex)
		 {
			 System.err.println("Ha ocurrido un error"+
			 ex.toString());
		 }
	 }
}