package es.florida.AE2_PSP;
import java.io.*;
import java.text.DecimalFormat;
public class CalcularProbabilidad {
	/**
	 * Método que calcula la probabilidad de colisión de un NEO
	 * 
	 * @author Claudiu Andrei Nechitescu
	 * */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		File fichero=new File(args[0]+".txt");
		FileWriter fw = new FileWriter(fichero);
		BufferedWriter bw = new BufferedWriter(fw);
		//Calculamos el tiempo de ejecución aquí ya que si lo hacemos en el lanzador, al ejecutarse en segundo plano, el tiempo es 0
		long tiempoInicioNEO;
		long tiempoFinalNEO;

		double posicionNEO = Double.parseDouble(args[1]);
		double velocidadNEO = Double.parseDouble(args[2]);

		double posicionTierra = 1;
		double velocidadTierra = 100;
		tiempoInicioNEO=System.currentTimeMillis();
		for (int i = 0; i < (50 * 365 * 24 * 60 * 60); i++) {
			posicionNEO = posicionNEO + velocidadNEO * i;
			posicionTierra = posicionTierra + velocidadTierra * i;
		}
		double resultado = 100 * Math.random()
				* Math.pow(((posicionNEO - posicionTierra) / (posicionNEO + posicionTierra)), 2);
		tiempoFinalNEO=System.currentTimeMillis();
		bw.write(Double.toString(resultado));
		bw.close();
		fw.close();
		fichero.createNewFile();
		DecimalFormat df = new DecimalFormat("0.00");
		System.err.println(args[0]+" - "+(resultado>10?"Pos sacabao: " + df.format(resultado):"Pos vivimos: "+ df.format(resultado)));
		System.out.println("Tiempo de ejecución " + args[0] + ": "
				+ (tiempoFinalNEO - tiempoInicioNEO) / 1000 + " segundos");

	}

}
