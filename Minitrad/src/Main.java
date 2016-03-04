/********************************************************************************
 * Autores: Gerardo Atzrael Paredes Navarro | Juan Carlos Uribe Lucero
 * Carrera: Lic. En Computacion.
 * Materia: Programacion de Sistemas
 * Semestre | Turno: 6to Semestre | Vespertino 
 * Trabajo: Minitrad().
 * Programa que reconoce identificadores, tipos de numeros, correos electronicos, 
 * comentarios por medio de automatas.
 * Fecha de Creacion: Sabado 15 de Marzo 2014
 ********************************************************************************/
import java.util.Scanner;
public class Main {	
	public static boolean BuscarIguales(char caracter,String Cadenas){
		int tamaño = Cadenas.length();
		boolean bandera = false;
		for(int i=0; i<tamaño; i++){
			if(caracter == Cadenas.charAt(i)){bandera = true;}
		}
		return bandera;				
	}
	public static void Estados(int estado,boolean signo){
		if(estado == 2){System.out.println("Valido:[Identificador]");}
		if(estado == 6){System.out.println("Valido:[Comentario]");}	
		if(estado == 8){
			if(signo == true){
				System.out.println("Valido: [Natural con signo]");
			}else{
				System.out.println("Valido: [natural]");
			}
		}
		if(estado == 10){
			if(signo == true){
				System.out.println("Valido: [Real con signo]");
			}else{
				System.out.println("Valido: [Real]");
			}
		}
		if(estado == 13){
			if(signo == true){
				System.out.println("Valido: [Flotante con signo]");
			}else{
				System.out.println("Valido: [Flotante]");
			}
		}
		if(estado == 19){System.out.println("Valido:[Correo Electronico]");}	
				
		/*******************  ESTADOS DE RECHASO  *******************************/
		if(estado!=2 && estado!=6 && estado!=8 && estado!=10 && estado!= 13&& estado!= 19){
			System.out.println("Error: cadena invalida");
		}			
	}
	
	/*** @param args*/
	@SuppressWarnings("resource")
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int estado,index,tamañoPalabra,tamañoCadena;
		String Digitos ="0123456789";
		String Letras = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";		
		boolean repetir=false,salir = false,signo = false;		
		Scanner leer = new Scanner(System.in);
		String cadena=null,palabra=null,opcion;
		do{	
			System.out.println("Ingresa cadena: ");
			cadena = leer.nextLine();
			tamañoCadena = cadena.length();
			while(cadena.equals("") || tamañoCadena>50){
				if(cadena.equals("")){System.out.println("\nNo se permiten vacios.");}
				else if(tamañoCadena>=50){System.out.println("\nNo se perminten cadenas mayores a 50 caracteres.");}
				System.out.println("Ingresa cadena: ");
				cadena = leer.nextLine();
				tamañoCadena = cadena.length();
			}			
			palabra = cadena.toUpperCase();		
			estado=1;index=0;tamañoPalabra = palabra.length();
			do{
				switch(estado){
					case 1:{
						/******************  NUMEROS  ***************************/
						if(BuscarIguales(palabra.charAt(index),"+-")){estado = 7;}
						if(BuscarIguales(palabra.charAt(index),Digitos)){estado = 8;}
						
						/***********************  IDENTIFICADOR  Ó CORREO ****************************/
						if(BuscarIguales(palabra.charAt(index),Letras)){estado = 2;}
						
						/*************************  COMENTARIO  **************************/
						if(BuscarIguales(palabra.charAt(index),"/")){estado=3;}
						
						/*********************** ERRORES  *******************/
						if(!BuscarIguales(palabra.charAt(index),Digitos+Letras+"/+-")){
							estado = 20;
						}
					}
					break;
					
					/******************  ESTADOS PARA IDENTIFICADOR  *********************/
					case 2:{						
						if(BuscarIguales(palabra.charAt(index),Digitos+Letras)){
							estado = 2;
						}else if(BuscarIguales(palabra.charAt(index),"._")){
							estado=14;
						}else if(BuscarIguales(palabra.charAt(index),"@")){
							estado=16;
						}else{estado=20;}
					}
					break;
					
					/******************** ESTADOS PARA COMENTARIO  **********************/
					case 3:{
						if(BuscarIguales(palabra.charAt(index),"*")){
							estado=4;
						}else{estado=20;}
					}
					break;					
					case 4:{
						if(BuscarIguales(palabra.charAt(index),"*")){
							estado = 5;
						}
					}
					break;					
					case 5:{
						if(BuscarIguales(palabra.charAt(index),"/")){
							estado = 6;
						}else if(!BuscarIguales(palabra.charAt(index),"/")){
							estado=4;
						}
					}
					break;					
					case 6:{						
						if(!BuscarIguales(palabra.charAt(index),"")){
							estado = 20;
						}
					}
					break;
					
					/**********************  ESTADOS DE NUMEROS  ***********************/
					case 7:{
						signo = true;
						if(BuscarIguales(palabra.charAt(index),Digitos)){
							estado = 8;
						}else{
							estado = 20;
						}					
					}
					break;	
					case 8:{						
						if(BuscarIguales(palabra.charAt(index),Digitos)){
							estado = 8;
						}else if(BuscarIguales(palabra.charAt(index),".")){
							estado = 9;
						}else if(BuscarIguales(palabra.charAt(index),"E")){
							estado = 11;
						}else{estado=20;}						
					}
					break;
					case 9:{
						if(BuscarIguales(palabra.charAt(index),Digitos)){
							estado = 10;
						}else{
							estado=20;
						}											
					}
					break;
					case 10:{						
						if(BuscarIguales(palabra.charAt(index),Digitos)){
							estado = 10;
						}else if(BuscarIguales(palabra.charAt(index),"E")){
							estado = 11;
						}else{estado=20;}						
					}
					break;
					case 11:{	
						if(BuscarIguales(palabra.charAt(index),"+-")){
							estado = 12;
						}else if(BuscarIguales(palabra.charAt(index),Digitos)){
							estado = 13;
						}else{estado=20;}													
					}
					break;
					case 12:{
						if(BuscarIguales(palabra.charAt(index),Digitos)){
							estado = 13;
						}else{estado=20;}														
					}
					break;
					case 13:{
						if(BuscarIguales(palabra.charAt(index),Digitos)){
							estado = 13;
						}else{estado=20;}														
					}
					break;
					/****************************  ESTADOS CORREO  ***********************/
					case 14:{
						if(BuscarIguales(palabra.charAt(index),Letras+Digitos)){
							estado = 15;
						}else{estado=20;}														
					}
					break;	
					case 15:{
						if(BuscarIguales(palabra.charAt(index),"._")){
							estado = 14;
						}else if(BuscarIguales(palabra.charAt(index),"@")){
							estado=16;
						}else if(BuscarIguales(palabra.charAt(index),Letras+Digitos)){
							estado=15;
						}else{estado=20;}														
					}
					break;
					case 16:{
						if(BuscarIguales(palabra.charAt(index),Letras)){
							estado = 17;
						}else{estado=20;}														
					}
					break;
					case 17:{
						if(BuscarIguales(palabra.charAt(index),Letras)){
							estado = 17;
						}else if(BuscarIguales(palabra.charAt(index),".")){
							estado=18;
						}else{estado=20;}														
					}
					break;
					case 18:{
						if(BuscarIguales(palabra.charAt(index),Letras)){
							estado = 19;
						}else{estado=20;}														
					}
					break;
					case 19:{
						if(BuscarIguales(palabra.charAt(index),Letras)){
							estado = 19;
						}else{estado=20;}														
					}
					break;
					
					/**********************  ESTADO DE ERROR  ****************************/
					case 20:{index = tamañoPalabra;}
					break;				
				}
				index++;
			}while(index<tamañoPalabra);
			
			
			/**************************  RESULTADO DE ESTADOS DE ACEPTACION  *****************************************/
			System.out.println("\nCadena: "+cadena);			
			Estados(estado,signo);			
			
			
			/******************  CICLO CONTINUAR PROGRAMA  ****************************************/
			do{
				System.out.println("\nOtra cadena? Si(a) No(b)");
				opcion = leer.nextLine();				
				if(opcion.equals("a")){
					salir=true;
					repetir=false;
				}
				if(opcion.equals("b")){
					System.out.println("Termino el programa con exito...");
					salir=false;
					repetir=false;
					}
				if(!opcion.equals("a")&& !opcion.equals("b")){
					System.out.println("Error: opcion invalida");
					repetir=true;
				}
			}while(repetir);			
		}while(salir);
	}	
}
