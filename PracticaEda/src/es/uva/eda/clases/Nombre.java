package es.uva.eda.clases;

public class Nombre {
	private String nombre;
	private int numApariciones;
	
	public Nombre(String nombre,int apariciones) {
		setNombre(nombre);
		setNumApariciones(apariciones);
	}
	public String getNombre() {
		return nombre;
	}
	public int getNumApariciones() {
		return numApariciones;
	}
	public void setNombre(String nombre) {
		this.nombre=nombre;
	}
	public void setNumApariciones(int apariciones) {
		numApariciones=apariciones;
	}
}
