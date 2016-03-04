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
import java.util.*;
 
public class Main{
	static String Letras ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	static String Numeros ="1234567890";
	static int aux =0,cont=1,Lineas=1,tamaño=0,o=0;;
	static StringTokenizer cadenaTokens;
	static String palabra=" ",Tipo="";
	  					  /******************  PALABRAS RESERVADAS  ***********************/
	private  final static String ArregloPalabras[][] ={{"01","SELECT"    },{"02","FROM"   },
						  {"03","WHERE"     }, {"04","AND"      },{"05","OR"        },{"06","IN"     },
						  {"07","INSERT"    },{"08","INTO"      },{"09","CREATE"    },{"10","TABLE"  },
						  {"11","CHAR"      },{"12","CONSTRAINT"},{"13","PRIMARY"   },{"14","KEY"    },
						  {"15","NOT"       },{"16","NULL"      },{"17","VALUES"    },{"18","NUMERIC"},
						  {"19","REFERENCES"},{"20","FOREIGN"   },{"21","CONSTRAINT"},{"12","UPDATE"},
						  {"23","" },{"24",""},{"25",""},{"26",""},{"27",""},{"28",""},{"29",""},		 
						  /******************  DELIMITADORES  *****************************/
						  {"30","."},{"31","," },{"32","("   },{"33",")"},{"34","'"},{"35","["},
						  {"36","]"},{"37",";" },{"38",""+'"'},{"39","" },{"40","" },{"41","" },
						  {"42","" },{"43",""  },{"44",""    },{"45","" },{"46","" },{"47","" },
						  {"48","" },{"49",""  },
						  /******************  OPERADORES  *******************************/
						  {"50","+"},{"51","-"},{"52","/"},{"53","*"},{"54","<"},{"55",">"},
						  {"56","" },{"57","" },{"58","" },{"59","" },{"60","" },{"61","" },
						  {"62","" },{"63","" },{"64","" },{"65","" },{"66","" },{"67","" },
						  {"68","" },{"69","" },						  
						  /******************  IDENTIFICADORES *******************************/
						  {"70",""},{"71",""},{"72",""},{"73","" },{"74","" },{"75",""},
						  {"76",""},{"77",""},{"78",""},{"79","" },{"80",""},{"81",""},
						  {"82",""},{"83","" },{"84",""},{"85",""},{"86",""},{"87",""},
						  {"88",""},{"89","" },{"90",""},{"91",""},{"92",""},{"93",""},
						  {"94",""},{"95","" },{"96",""},{"98",""},{"99",""},{"100",""},
						  {"101",""},{"102",""},{"103",""},{"104",""},{"105",""},{"106",""},
						  {"107",""},{"108",""},{"109",""},{"110",""},{"111",""},{"112",""},
						  {"113",""},{"114",""},{"115",""},{"116",""},{"117",""},{"118",""},
						  {"119",""},{"120",""},{"121",""},{"122",""},{"123",""},{"123",""},
						  {"124",""},{"125",""},{"126",""},{"127",""},{"128",""},{"129",""},
						  {"130",""},{"131",""},{"132",""},{"133",""},{"134",""},{"135",""},
						  {"136",""},{"137",""},{"138",""},{"139",""},{"140",""},{"141",""},
						  {"142",""},{"143",""},{"143",""},{"144",""},{"145",""},{"146",""},
						  {"147",""},{"148",""},{"149",""},{"150",""},
						  /********************  CONSTANTES  **************************************/
						  {"151",""},{"152",""},{"153",""},{"154",""},{"155",""},{"156",""},
						  {"157",""},{"158",""},{"159",""},{"160",""},{"161",""},{"162",""},
						  {"163",""},{"164",""},{"165",""},{"166",""},{"167",""},{"168",""},
						  {"169",""},{"170",""},{"171",""},{"172",""},{"173",""},{"173",""},
						  {"174",""},{"175",""},{"176",""},{"177",""},{"178",""},{"179",""},
						  {"180",""},{"181",""},{"182",""},{"183",""},{"184",""},{"185",""},
						  {"186",""},{"187",""},{"188",""},{"189",""},{"190",""},{"191",""},
						  {"192",""},{"193",""},{"193",""},{"194",""},{"195",""},{"196",""},
						  {"197",""},{"198",""},{"199",""},{"200",""},};
        
	public static  void Separar(){        	
	    if (cadenaTokens.hasMoreTokens()){
	    	palabra=cadenaTokens.nextToken();
	    }else{palabra="";}          
	} 	
	/******************  METODO PARA BUSCAR LOS TIPOS DE TOKENS EN LA TABLA  *****************/
	public static String BuscarTipo(String palabra){
		for(int x = 0; x < ArregloPalabras.length;x++){
			if(ArregloPalabras[x][1].equals(palabra)){
				if(x>=0 && x <=29){
					return ArregloPalabras[x][0]+" / Palabra Reservada";
				}else if(x>=30 && x <=49){
					return ArregloPalabras[x][0]+" / Delimitador";
				}else if(x>=50 && x <=69){
					return ArregloPalabras[x][0]+" / Operador";
				}else if(x>=70 && x <=150){
					return ArregloPalabras[x][0]+" / Identificador";
				}				
			}else{
				/**********************  EN CASO DE NO ESTAR EL IDENTIFICADOR O LA CONSTANTE, LO AGREGA ************/
				if(x>70 && x <150){
					for(int i = 70 ; i <= 150;i++){
						if(ArregloPalabras[i][1].equals("")){
								ArregloPalabras[i][1]=palabra;
								return ArregloPalabras[i][0]+" / Identificador";
							}else if(BuscarIguales(Numeros, palabra)){
								ArregloPalabras[i][1]=palabra;
								return ArregloPalabras[i][0]+" / Contador";
							}											
					}					
				}
				
			}
		}		
		return "";
	}
	/****************  BUSCAR SI ES IDENTIFICADOR O CONSTANTE  **********************************/
	public static boolean BuscarIguales(String cadenas,String palabra){
		int tamaño = cadenas.length(),tamañoPalabra = palabra.length(),aux=0;
		boolean bandera = false;
		for(int i=0;i<tamaño;i++){
			if(aux<tamañoPalabra){				
				if(palabra.charAt(aux) == cadenas.charAt(i)){					
					bandera = true;	
				}
				aux++;
			}			
		}
		return bandera;				
	}
	/****************  METODO DE IMPRECION DE TODA LA TABLA  ************************************/
	public static void ImprimirTabla(){
		for(int x = 0; x < ArregloPalabras.length;x++){
			for(int y = 0; y < 2; y++){System.out.print(" "+ArregloPalabras[x][y]);}
			System.out.println("");		
		}		
	}	
	
	/**************************************************************************/
    public static void main(String args[]){  
    	boolean salir=false,repetir=false;
    	String opcion="";
		Scanner leer = new Scanner(System.in);
    	ArrayList<String> cadena = new ArrayList<String>();
    	do{
    	System.out.println("Ingresa el comando SQL:");
    	
    	do{   			
            cadena.add(leer.nextLine().toUpperCase());
        }while(!cadena.get(o++).equals(""));
    	
    	tamaño = cadena.size();
    	System.out.println("No.E | No.Linea | ( TOKEN )   [Codigo/Tipo]");
    	
    	do{         	        	
        	cadenaTokens = new StringTokenizer((String) cadena.get(aux),"=,.()'*[];<>/ "+'"',true);
	        do{
	        	if(!palabra.equals(" ")){	  
	        		Tipo=BuscarTipo(palabra);
	        		System.out.println(cont+"       "+Lineas+"          ( "+palabra+" )       [ "+Tipo+" ]");
	        		cont++;
	        	}
	            Separar();
	        }while (!palabra.equals("")); 
	        Lineas++;aux++;palabra=" ";
        }while(aux<tamaño);
    	System.out.println();
    	//ImprimirTabla();
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
    	cont=0;
		}while(salir);
    }
    
    
}