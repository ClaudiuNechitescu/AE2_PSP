package es.florida.AE2_PSP;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Lanzador {

	public void calculoProbabilidad(String nom, Double pos, Double vel) {
		String clase = "es.florida.AE2_PSP.CalcularProbabilidad";
		try {

			String javaHome = System.getProperty("java.home");
			String javaBin = javaHome + File.separator + "bin" + File.separator + "java";
			String classpath = System.getProperty("java.class.path");
			String className = clase;

			List<String> command = new ArrayList<>();
			command.add(javaBin);
			command.add("-cp");
			command.add(classpath);
			command.add(className);
			command.add(nom);
			command.add(pos.toString());
			command.add(vel.toString());

			ProcessBuilder builder = new ProcessBuilder(command);
			Process process = builder.inheritIO().start();
			process.waitFor();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		int cores = Runtime.getRuntime().availableProcessors();

		FileReader fr = new FileReader("NEOs.txt");
		BufferedReader br = new BufferedReader(fr);
		NEO neo = new NEO();
		String linea = "";
		String[] datos;
		Lanzador l = new Lanzador();
		for (int i = 1; i <= cores; i++) {
			linea = br.readLine();
			datos = linea.split(",");
			neo.nombre = datos[0];
			neo.posicion = Double.parseDouble(datos[1]);
			neo.velocidad = Double.parseDouble(datos[2]);
			l.calculoProbabilidad(neo.nombre,neo.posicion, neo.velocidad);
		
		}
	}

}