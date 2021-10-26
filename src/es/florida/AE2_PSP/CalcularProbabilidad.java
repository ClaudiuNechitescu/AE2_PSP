package es.florida.AE2_PSP;
import java.io.*;
import java.text.DecimalFormat;
public class CalcularProbabilidad {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		File fichero=new File(args[0]+".txt");
		FileWriter fw = new FileWriter(fichero);
		BufferedWriter bw = new BufferedWriter(fw);
		double posicionNEO = Double.parseDouble(args[1]);
		double velocidadNEO = Double.parseDouble(args[2]);

		double posicionTierra = 1;
		double velocidadTierra = 100;
		for (int i = 0; i < (50 * 365 * 24 * 60 * 60); i++) {
			posicionNEO = posicionNEO + velocidadNEO * i;
			posicionTierra = posicionTierra + velocidadTierra * i;
		}
		double resultado = 100 * Math.random()
				* Math.pow(((posicionNEO - posicionTierra) / (posicionNEO + posicionTierra)), 2);
		bw.write(Double.toString(resultado));
		bw.close();
		fw.close();
		fichero.createNewFile();
		DecimalFormat df = new DecimalFormat("0.00");
		System.err.println(resultado>10?"Pos sacabao: " + df.format(resultado):"Pos vivimos: "+ df.format(resultado));
	}

}
