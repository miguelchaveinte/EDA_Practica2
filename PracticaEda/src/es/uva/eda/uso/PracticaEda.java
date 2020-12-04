package es.uva.eda.uso;
import es.uva.eda.clases.*;

import java.io.IOException;
import java.util.*;
import java.lang.*;

import es.uva.eda.clases.Persona;

public class PracticaEda {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
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
		System.out.println("Introduzca la fecha de inicio(dd/mm/aa): ");
		String fecha1=in.nextLine();
		int fechaInicio=traduceFecha(fecha1);
		System.out.println("Introduzca la fecha de fin(dd/mm/aa): ");
		String fecha2=in.nextLine();
		int fechaFin=traduceFecha(fecha2);
		Map<String,Nombre> mapaApariciones=new HashMap<>();
		insercion(fechaInicio,fechaFin,dat,mapaApariciones);
		int posicionNombre=1;
		
	}
	
	
	public static void insercion(int fechaInicio,int fechaFin,Persona[] dat,Map<String,Nombre> mapaApariciones) {
		for(int i=0;i<dat.length;i++) {
			if(fechaInicio<=dat[i].getNacimiento() && dat[i].getNacimiento()<=fechaFin)
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
	public static int traduceFecha(String fec) {
	    String[] trozos = fec.split("/");
	    int dia = Integer.parseInt(trozos[0]);
	    int mes = Integer.parseInt(trozos[1]);
	    int año = Integer.parseInt(trozos[2]);
	    return 367*año - (7*(año+5001+(mes-9)/7))/4 + (275*mes)/9 + dia - 692561;
	}
}

