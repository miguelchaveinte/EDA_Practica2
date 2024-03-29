package es.uva.eda.uso;
import es.uva.eda.clases.*;

import java.io.IOException;
import java.util.*;
import java.lang.*;

public class PracticaEda {

	private static int posicionNombre=1;
	//private static int personasCondicion=0;
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		Persona[] dat;
		System.out.println("Nombre del fichero: ");
		String nombreFichero = in.nextLine();
		dat=Persona.leeFichero(nombreFichero);

		System.out.println("*** OPCIONES GENERALES ***");
		System.out.println("Numero de nombres a mostrar: ");
		int numeroMostrar=in.nextInt();
		in.nextLine();
		System.out.println("Nombre del usuario: ");
		String nombreMostrar=in.nextLine();
		
		System.out.println("*** BUCLE DE CONSULTAS ***");
		System.out.println("Introduzca el limite de ocurrencias: ");
		int limiteOcurrencias=in.nextInt();
		//int fechaInicio=traduceFecha(fecha1);
		//System.out.println("Introduzca la fecha de fin(dd/mm/aa): ");
		//String fecha2=in.nextLine();
		//int fechaFin=traduceFecha(fecha2);
		long t0,t1;
		t0 = System.nanoTime();
		Map<String,Nombre> mapaApariciones=new HashMap<>();
		insercion(dat,mapaApariciones);
		int numAparicionesNombre;
		if(mapaApariciones.get(nombreMostrar)==null )
			numAparicionesNombre=-1;
		else
			if(mapaApariciones.get(nombreMostrar).getNumApariciones()<limiteOcurrencias)
				numAparicionesNombre=-2;
			else
				numAparicionesNombre=mapaApariciones.get(nombreMostrar).getNumApariciones();
		//PriorityQueue<Nombre> nMasApariciones= new PriorityQueue<>(numeroMostrar);
		PriorityQueue<Nombre> nMasApariciones= new PriorityQueue<Nombre>(Collections.reverseOrder());
		mapaApariciones.forEach((k,v) -> extraccion(k,v,limiteOcurrencias,mapaApariciones,nMasApariciones,numAparicionesNombre,numeroMostrar));
		t1=(System.nanoTime()-t0);
		imprimir(nMasApariciones,numeroMostrar,nombreMostrar,numAparicionesNombre,t1,mapaApariciones.size());
	}
	
	
	public static void insercion(Persona[] dat,Map<String,Nombre> mapaApariciones) {
		for(int i=0;i<dat.length;i++) {
				if(mapaApariciones.containsKey(dat[i].getNombre())) {
					Nombre datNombre=mapaApariciones.get(dat[i].getNombre());
					datNombre.setNumApariciones(datNombre.getNumApariciones()+1);
					mapaApariciones.replace(dat[i].getNombre(), datNombre);
				}
				else {
					Nombre datNombre= new Nombre(dat[i].getNombre(),1);
					mapaApariciones.put(dat[i].getNombre(), datNombre);
				}
			}
		}	
	
	public static void extraccion(String clave,Nombre valor,int limiteOcurrencias,Map<String,Nombre> mapaApariciones,PriorityQueue<Nombre> nMasApariciones,int numAparicionesNombre,int numeroMostrar) {
		int aparicionesNuevo=valor.getNumApariciones();
		if(aparicionesNuevo>limiteOcurrencias) {
			if(aparicionesNuevo<numAparicionesNombre)
				PracticaEda.posicionNombre++;
			if(nMasApariciones.size()!=numeroMostrar) {
				nMasApariciones.add(valor);
			}
			else {
				if(nMasApariciones.peek().getNumApariciones()> aparicionesNuevo) {
					nMasApariciones.poll();
					nMasApariciones.add(valor);
				}			
			}
		}
	}
	
	public static void imprimir(PriorityQueue<Nombre> nMasApariciones,int numeroMostrar,String nombreMostrar,int numAparicionesNombre,long t1,int nombresDistintos ) {
		System.out.println();
		Nombre[] arrayOrdenado = new Nombre[numeroMostrar]; 
		for(int i=numeroMostrar-1;i>=0;i--) {
			arrayOrdenado[i]=nMasApariciones.poll();
		}
		for(int i=0;i<numeroMostrar;i++) {
			System.out.println("\t"+(i+1)+"."+arrayOrdenado[i].getNombre()+":"+" "+arrayOrdenado[i].getNumApariciones());
		}
		if(numAparicionesNombre!=-1 && numAparicionesNombre != -2)
			System.out.println("\t"+PracticaEda.posicionNombre+"."+nombreMostrar+":"+" "+numAparicionesNombre);
		else {
			System.out.println();
			if (numAparicionesNombre==-1)
				System.out.println("El nombre que introdujo a buscar no se encontró.");
			else
				System.out.println("El nombre que introdujo a buscar tiene un número de apariciones menor.");
		}
		System.out.println();
		System.out.println("*** MEDIDAS ***");
		System.out.printf("Tiempo de proceso: %.5f seg.%n",1e-9*t1);
		System.out.println("p= "+nombresDistintos+" nombres distintos");
	}
	public static int traduceFecha(String fec) {
	    String[] trozos = fec.split("/");
	    int dia = Integer.parseInt(trozos[0]);
	    int mes = Integer.parseInt(trozos[1]);
	    int año = Integer.parseInt(trozos[2]);
	    return 367*año - (7*(año+5001+(mes-9)/7))/4 + (275*mes)/9 + dia - 692561;
	}
}

