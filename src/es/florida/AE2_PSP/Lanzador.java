package es.florida.AE2_PSP;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Lanzador {
	/**
	 * Método que ejecuta el proceso de cálculo de probabilidad de colisión de un NEO
	 * 
	 * @author Claudiu Andrei Nechitescu
	 * @param nom El nombre del NEO
	 * @param pos Posición del NEO
	 * @param vel Velocidad del NEO
	 * */
	@SuppressWarnings("finally")
	public List<String> calculoProbabilidad(String nom, Double pos, Double vel) {
		String clase = "es.florida.AE2_PSP.CalcularProbabilidad";
		List<String> command = new ArrayList<>();

		try {

			String javaHome = System.getProperty("java.home");
			String javaBin = javaHome + File.separator + "bin" + File.separator + "java";
			String classpath = System.getProperty("java.class.path");
			String className = clase;

			command.add(javaBin);
			command.add("-cp");
			command.add(classpath);
			command.add(className);
			command.add(nom);
			command.add(pos.toString());
			command.add(vel.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			return command;
		}
	}
	/**
	 * Programa principal que lanza los procesos de cálculo
	 * 
	 * @author Claudiu Andrei Nechitescu
	 * */
	public static void main(String[] args) throws Exception {
		int cores = Runtime.getRuntime().availableProcessors();

		FileReader fr = new FileReader("NEOs.txt");
		BufferedReader br = new BufferedReader(fr);
		NEO neo = new NEO();
		String linea = "";
		String[] datos;
		Lanzador l = new Lanzador();
		long tiempoInicioApp;
		long tiempoFinalApp;
		Process process = null;
		tiempoInicioApp = System.currentTimeMillis();
		do {
			
				for (int i = 1; i <= cores; i++) {
					linea = br.readLine();
					if(linea!=null) {
					datos = linea.split(",");
					neo.nombre = datos[0];
					neo.posicion = Double.parseDouble(datos[1]);
					neo.velocidad = Double.parseDouble(datos[2]);
					ProcessBuilder builder=new ProcessBuilder(l.calculoProbabilidad(neo.nombre, neo.posicion, neo.velocidad));
					process = builder.inheritIO().start();
					}
					else {
						break;
					}
				}
			process.waitFor();
		} while (linea != null);
		tiempoFinalApp = System.currentTimeMillis();
		System.out.println("Tiempo de ejecución total: " + (tiempoFinalApp - tiempoInicioApp) / 1000 + " segundos");
	}

}