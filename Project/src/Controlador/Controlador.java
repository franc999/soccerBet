package Controlador;


import java.util.Scanner;
import Archivo.Archivo;
import Archivo.ArchivoFile;
import Menu.Menu;
import Clases.Administrador;
import Clases.Equipo;
import Clases.Jugador;
import Clases.Password;
import Clases.Programa;
import Clases.Torneo;
import Clases.Usuario;


public class Controlador {
	
	private Scanner scanner = new Scanner(System.in);
	private Programa programa;

	
	public Controlador(Programa t)
	{
		this.programa=t;
	}
	
	public void inicio() throws Exception 
	{	
		
		
		
		programa.agregarJugadoresAEquipo();
		Torneo torneo = new Torneo ("Copa", 16, programa.getEquipos());
        boolean hayAdmin = true;
        String opcion, opcion1, opcion2, opcion3;

			do 
            {
            	Menu.MenuInicio();
            	opcion = scanner.nextLine();
            	switch (opcion) 
            	{
            	     case "1":
            	  
            	    	System.out.print("\nIngrese Nombre de Usuario: ");
         	            String idUsuario = scanner.nextLine();
         	            System.out.print("Ingrese Contraseña: ");
         	            String idContraseña = scanner.nextLine();
         	            Usuario usuario = programa.devuelveUsuario(idUsuario);
         	            /*Autenticacion de credenciales para Admin*/
         	            if(idUsuario.equals(programa.getAdmin().getUsuario()))
         	            {
         	             
         	            	System.out.println("Inicio sesion de Administrador");
         	                Administrador admin = programa.getAdmin();
         	                if (admin.equals(Administrador.proveerDefaultAdmin()) )
         	                {
         	                	hayAdmin = false;
         	                }
         	                if (!hayAdmin) 
         	                {
         	                	System.out.println("Usted ha iniciado sesion con credenciales por defecto. " + "Se recomienda que estas sean modificadas.");
         	                }
         	                /*Menu de Administrador*/
         	                do 
         	                {
         	                	Menu.MenuAdmin();
         	                	opcion1= scanner.nextLine();
         	                	switch (opcion1) 
         	                	{
                                     case "1":
                                     	programa.agregarEquipo(admin.crearEquipo(scanner, programa));
                                     	Archivo.escribirObjeto(programa.getEquipos(), ArchivoFile.EQUIPOS);

                                     break;
                                     case "2":
                                         admin.agregarJugadorAEquipo(programa, scanner);
                                         Archivo.escribirObjeto(programa.getEquipos(), ArchivoFile.EQUIPOS);
                                           break;
                                     case "3":
                                    	 programa.agregarJugador(admin.crearJugador(scanner, programa));
                                    	 Archivo.escribirObjeto(programa.getJugadores(), ArchivoFile.JUGADORES);
                                     break;
                                     case "4":
                                    	 admin.eliminarEquipo(programa, scanner);
                                    	 Archivo.escribirObjeto(programa.getEquipos(), ArchivoFile.EQUIPOS);
                                     break;
                                       case "0":
                                           break;
                                       default:
                                           Menu.opcionIncorrecta();
                                }//switch
         	                  }while (!opcion1.equals("0"));
         	                
         	             }else if(usuario != null) {
         	            	 
         	            	if(usuario.getPassword().equals(idContraseña)) {
         	            		
	         	            	System.out.println("\nHa iniciado sesion exitosamente. Bienvenido " + usuario.getNombreyApellido());
	         	            	//Primer Menu de Usuario//
	         	            	do
	         	 	            {
	         	 	            	Menu.MenuInicioSesion();
	         	 	            	opcion2 = scanner.nextLine();
	         	 	            	switch (opcion2) 
	         	 	            	{
	         	 	            	   
		         	                                	 case "1":         
		         	                                     {
		         	                                    	if(usuario.getCuenta()!=0)
		        	         	                       		{
		         	                                    		 float cantidad=0;
				         	                                     System.out.println("Esta seguro que desea apostar? Pulse 's' para continuar: "); 
				         	                                     String rta = "";
				         	                                     rta = scanner.nextLine();
				         	                                    
				         	                                     if (rta.equals("s")) 
				         	                                     {
				         	                                    	 
				         	                                    	 System.out.println("Saldo en su cuenta :"+usuario.getCuenta());
				         	                                    	 System.out.println("Ingrese la cantidad que desea apostar :"); 
				         	                                    	 cantidad = scanner.nextFloat();
				         	                                    	 boolean flag = usuario.apostar(cantidad);
				         	                                    	 
				         	                                    	 if (flag == true) 
				         	                                    	 {
					         	                                    	 System.out.println(usuario.verApuesta());
					         	                                    	 //System.out.println("hola");
				         	                                    		 System.out.println(torneo.verFecha());
				         	                                    		 System.out.println("Ingresar codigo del partido: ");
						         	                                     int codigo = scanner.nextInt();
						         	                         
						         	                                     String comp1="no";
						         	                                     String comp=null;
						         	                                    
						         	                                     comp=torneo.verID(codigo);
						         	                                     if(comp1.equals(comp))
						         	                                     {
						         	                                    	System.out.println("Codigo mal ingresado");
						         	                                     }
						         	                                     else
						         	                                     {
						         	                                    	 System.out.println(comp);
						         	                                    	 System.out.println("Ingresar ID a elegir: ");
							         	                                     int id = scanner.nextInt();
							         	                                     usuario.setIdEquipo(id);
							         	                                      
							         	                                     int idE2=torneo.idPartidoPorCodigo(codigo, id);
							         	                                     if(idE2==0) 
							         	                                     {
							         	                                    	 System.out.println("\nID mal ingresado");
							         	                                     }
							         	                                     else
							         	                                     {
							         	                                    	 System.out.println(torneo.verCamisetaJugadores(id));
							         	                                    	 System.out.println("Ingrese el numero de camiseta del jugador");
							         	                                    	 int numCamiseta = scanner.nextInt();
							         	                                    	 System.out.println("Ingrese la cantidad de goles que espera del jugador");
							         	                                    	 int goles = scanner.nextInt();
							         	                                    	 System.out.println(torneo.simularFecha(codigo));
						         	                                    		 //boolean flag1 = usuario.gananciaDeApuestaPorCantidadDeUsuarios(id, idE2, torneo);
						         	                                    		 boolean flag1 = usuario.gananciaDeApuestasPorGolDeJugadores(numCamiseta, id, goles, torneo);
							         	                                    	 if (flag1 == true) {
						         	                                    			 System.out.println("Ha ganado en su apuesta :");
						         	                                    			 System.out.println("Saldo en su cuenta :"+usuario.getCuenta());
						         	                                    		 }
							         	                                     }
					         	                                    		
					         	                                    		programa.agregarUsuario(usuario);
					         	                                    		Archivo.escribirObjeto(programa.getUsuarios(), ArchivoFile.USUARIOS);
					         	                                    		
						         	                                     }
						         	                                     
						         	                                     
				         	                                    	}else
				        	         	                       		{
				        	         	                       		     System.out.println("No pose suficiente dinero en la cuenta");
				        	         	                       		}
				         	                                    	
				         	                                     }
				         	                                     else
				         	                                     {
			         	                                    		 break;
			         	                                    	 }
		        	         	                       		}
		         	                                    	else
		        	         	                       		{
		        	         	                       		     System.out.println("No pose suficiente dinero en la cuenta");
		        	         	                       		}
		         	                     }
	         	 	            	    break;
	         	 	            	    case "2":         
	         	                        {
	         	                         	float dinero = 0;
	         	                         	System.out.println("\nIngrese el dinero que desea agregar a la cuenta: ");
	         	                         	dinero = scanner.nextFloat();
	         	                         	boolean flag = usuario.agregarDinero(dinero);
	         	                         	if(flag==true)
	         	                         	{
	         	                         		System.out.println("\nDinero agregado, saldo actual: "+usuario.getCuenta());
	         	                         		programa.agregarUsuario(usuario);
	         	                         		Archivo.escribirObjeto(programa.getUsuarios(), ArchivoFile.USUARIOS);
	         	                         	}
	         	                         	else
	         	                         	{
	         	                         		System.out.println("\nHa ocurrido un error al agregar dinero");
	         	                         	}
	         	                         	
	         	                        }
	         	                        break;
	         	                        case "3":
	         	                        {
	         	                        	float dinero1 = 0;
	         	                         	System.out.println("\nIngrese el dinero que desea extraer de la cuenta: ");
	         	                         	dinero1 = scanner.nextFloat();
	         	                         	usuario.descontarDinero(dinero1);
	         	                            System.out.println("\nDinero extraido, saldo actual: "+usuario.getCuenta());
	         	                            programa.agregarUsuario(usuario);
        	                         		Archivo.escribirObjeto(programa.getUsuarios(), ArchivoFile.USUARIOS);
	         	                        
	         	                        }
	         	                        break;
	         	                        case "0":
	         	                        break;
	         	                        default:
	         	                             Menu.opcionIncorrecta();
	         	                 }//switch
	     	 	              }while (!opcion2.equals("0"));//do
	 	            		}//if
         	             }else {
         	            	 
         	            	 System.out.println("Usuario inexistente.");
         	             }
            	     break;
            	     case "2":
            	    
            	    	 programa.agregarUsuario(registrarse());
            	 		 Archivo.escribirObjeto(programa.getUsuarios(), ArchivoFile.USUARIOS);;
            	     
            	     break;
                     case "0":
                     break;
                     default:
                          Menu.opcionIncorrecta();
          }
	    }while (!opcion.equals("0"));//do
	}//inicio
	
	/**
	 * 
	 * Metodo para registrar un usuario al programa
	 * @return
	 * @throws Exception
	 */
	public Usuario registrarse()throws Exception
	{
		boolean rta=false;
		boolean rta2=false;
		String contraseña=null;
		String usuario=null;
		String nombreYapellido=null;
		String nacionalidad=null;
		String fechaNacimiento=null;
		
		while(!rta)
		{
			System.out.println("Ingresar Usuario: ");
            usuario = scanner.nextLine();

            if (programa.existeUsuario(usuario)) 
            {
                throw new Exception("El Nombre de Usuario ya existe\n");
            }
			System.out.println("\nIngresar Nombre y Apellido:");
			nombreYapellido = scanner.nextLine();
			System.out.println("\nIngresar Nacionalidad:");
			nacionalidad = scanner.nextLine();
			System.out.println("\nIngresar fecha de nacimiento(../../....):");
			fechaNacimiento = scanner.nextLine();
			while (!rta2) 
			{
                System.out.println("Ingrese contraseña alfanumerica(5-20 digitos): ");
                contraseña = scanner.nextLine();

                if (Password.hasLongitudCorrecta(contraseña)) {
                    rta2 = true;
                } 
                else {
                    System.out.println("La contraseña ingresada no cumple todos los requisitos: ");
                }
            }
            System.out.println("\nUSUARIO REGISTRADO");
			rta=true;
		}
		Usuario n = new Usuario(usuario, nombreYapellido, nacionalidad, fechaNacimiento, new Password(contraseña));
		return n;
	}//registrarse
	 
}

