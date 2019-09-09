package Menu;

public class Menu {

	public static void MenuInicio()
	{
		System.out.println("1- Iniciar Sesion"); //Inicio Sesion y voy a MenuInicioSesion()
		System.out.println("2- Registrarse"); //Registro a un usuario y vuelve a MenuInicio()
		System.out.println();
		System.out.println("0- Salir"); //Cierra Programa
		seleccionOpcion();
	}
	public static void MenuAdmin()
	{
		System.out.println("\n1- Crear Equipo"); //Creo un equipo, vuelve a MenuAdmin()
		System.out.println("2- Agregar Jugador a Equipo"); //Agrega jugador a equipo, vuelve a MenuAdmin()
		System.out.println("3- Crear Jugador"); //Creo un jugador, vuelve a MenuAdmin()
		System.out.println("4- Eliminar Equipo");//Elimina equipo, vuelve a MenuAdmin()
		System.out.println();
		System.out.println("0- Cerrar Sesion"); //Cierra Sesion y vuelve a MenuInicio()
		seleccionOpcion();
	}
	
	public static void MenuInicioSesion()
	{
		System.out.println("\n1- Apostar"); //Lleva a MenuTipoApuesta()
		System.out.println("2- Agregar Dinero a la Cuenta"); //Agrego dinero a la caja de ahorro del usuario, vuelve a MenuInicioSesion()
		System.out.println("3- Extraer Dinero de la Cuenta"); //Retiro cuenta de la caja de ahorro, vuelve a MenuInicioSesion()
		System.out.println();
		System.out.println("0- Cerrar Sesion");	//Cierra sesion y vuelve a MenuInicio()
		seleccionOpcion();
	}
	private static void seleccionOpcion() 
	{
        System.out.print("\nSeleccione:  ");
    }
	public static void opcionIncorrecta() 
	{
        System.out.println("\nOpcion no valida\n");
    }
	public static void limpiarPantalla() 
	{
		 System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }
}