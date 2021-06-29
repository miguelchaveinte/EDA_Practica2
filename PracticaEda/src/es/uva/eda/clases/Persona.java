package es.uva.eda.clases;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.io.File;

public class Persona {
    int nac;
    int fac;
    int gen;
    String nom;

    public void parse(String lin1, String lin2) {
        nac = (lin1.charAt(0)-48)*10000+
              (lin1.charAt(1)-48)*1000+
              (lin1.charAt(2)-48)*100+
              (lin1.charAt(3)-48)*10+
              (lin1.charAt(4)-48);
        fac = (lin1.charAt(6)-48)*10000+
              (lin1.charAt(7)-48)*1000+
              (lin1.charAt(8)-48)*100+
              (lin1.charAt(9)-48)*10+
              (lin1.charAt(10)-48);
        gen = lin1.charAt(12)-48;
        nom = lin2;
    }
    public String getNombre(){
    	return nom;
    }
    public int getNacimiento() {
    	return nac;
    }
    public int getFallecimiento() {
    	return fac;
    }
    public static Persona[] leeFichero(String nomfich) throws IOException {
    	String filePath = new File("").getAbsolutePath();
        BufferedReader br = new BufferedReader(new FileReader(filePath+"/"+nomfich), 131072);
    	//ClassLoader classLoader = getClass().getClassLoader();
        //File file = new File(classLoader.getResource("fileTest.txt").getFile());
       // BufferedReader br = new BufferedReader(new FileReader(("fichero/"+nomfich)), 131072);
        int n = Integer.parseInt(br.readLine());
        System.out.println("_|____|____|____|____|");
        long t0, t1, t2;
        t0 = System.nanoTime();
        Persona[] dat = new Persona[n];
        for(int i = 0; i < n; i++) { dat[i] = new Persona(); }
        t1 = System.nanoTime()-t0;
        System.out.print("*");
        int lim = n/20;
        t0 = System.nanoTime();        
        for(int i = 0; i < n; i++) { 
            dat[i].parse(br.readLine(), br.readLine());
            if(i % lim == lim-1) { System.out.print("*"); }
        }
        t2 = System.nanoTime() - t0;        
        System.out.printf("\nCreacion array: %.5f seg. Lectura fichero: %.5f seg.\n", 1e-9*t1, 1e-9*t2);
        System.out.printf("n = %d personas en total.\n\n", dat.length);        
        return dat;
    }

}
