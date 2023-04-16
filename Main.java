package tpIntegrador;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

 
public class Main {

	public static void main(String[] args) {

		/***************************************************************************
		                       * ARCHIVO RESULTADOS *
		 ***************************************************************************/

		Path ruta;
		List<String> lineasArchivo = null;
		String lineaVector[] = new String[4];

		// Leer resultados
		Collection<Partido> partidos = new ArrayList<Partido>();
		String equipoLocal = "", equipoVisitante = "", fase = "";
		int ronda = 0, golesLocal = 0, golesVisitante = 0;
		ResultadoEnum resultado = null;
		String nombre1 = "", nombre2 = "", nombre3 = "";

		try {

			ruta = Paths.get(
					"C:\\Users\\Win X\\eclipse-workspace\\TpIntegradorJavaEntrega3\\src\\tpIntegrador\\resultados.csv");
			lineasArchivo = Files.readAllLines(ruta, StandardCharsets.UTF_8);
		} catch (IOException e) {

			System.out.println("No se pudo leer la linea de resultados...");
			System.out.println(e.getMessage());
			System.exit(1);
		    }
		boolean primera = true;
		for (String linea : lineasArchivo) {
			if (primera) {
				primera = false;
			} else {
				lineaVector = linea.split(";"); // guardo los valores en un vector[]
				// System.out.println(linea); // IMPRIME DE PRUEBA
				fase = lineaVector[0];
				ronda = Integer.parseInt(lineaVector[1]); // <------------------------------------------------

				equipoLocal = lineaVector[2];
				equipoVisitante = lineaVector[5];
				Equipo equipo1 = new Equipo(equipoLocal);
				Equipo equipo2 = new Equipo(equipoVisitante);
				Partido partido = new Partido(fase, ronda, equipo1, equipo2);
				try {
					golesLocal = Integer.parseInt(lineaVector[3]);
					golesVisitante = Integer.parseInt(lineaVector[4]);
					partido.setGolesEquipo1(golesLocal);
					partido.setGolesEquipo2(golesVisitante);
					partidos.add(partido);

				} catch (NumberFormatException ex) {
					System.err.println("ERROR:\nEn Goles se recibió una Cadena\n" + ex.getMessage());
					System.exit(1);
				}

			  }
		   }

		/**************************************************************************
		                            * ARCHIVO PRONOSTICO *
		 ***************************************************************************/

		int puntos1Ronda1 = 0, puntos1Ronda2 = 0, puntos1Ronda3 = 0, puntos2Ronda1 = 0, puntos2Ronda2 = 0,
				puntos2Ronda3 = 0, puntos3Ronda1 = 0, puntos3Ronda2 = 0, puntos3Ronda3 = 0, puntos = 0, puntos2 = 0,
				puntos3 = 0, ptos1Ronda1 = 0, ptos1Ronda2 = 0, ptos1Ronda3 = 0, ptos2Ronda1 = 0, ptos2Ronda2 = 0,
				ptos2Ronda3 = 0, ptos3Ronda1 = 0, ptos3Ronda2 = 0, ptos3Ronda3 = 0, ptosXExtraFase1 = 0,
				ptosXExtraFase2 = 0, ptosXExtraFase3 = 0; // total puntos pesona

		try {

			// get a Connection object using a ConnectionString
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pronosticos?user=root&password=root"+"&useSSL=false");
			Statement consulta = con.createStatement();
			ResultSet result = consulta.executeQuery("select * from pronostico");
			
			while (result.next()) {
				
				// System.out.println(result.getString("Ronda") + "\t" +
			 	int participante = Integer.parseInt(result.getString("Id"));
				Equipo equipo1 = new Equipo(result.getString("EquipoL"));
				Equipo equipo2 = new Equipo(result.getString("EquipoV"));
				Partido partido = null;

				for (Partido partidoColeccion : partidos) {
					// Cual es el partido es igual al partido de pronostico que estamos comparando
					if (partidoColeccion.getEquipo1().getNombre().equals(equipo1.getNombre())
							&& partidoColeccion.getEquipo2().getNombre().equals(equipo2.getNombre())) {
						partido = partidoColeccion;
					  }
                    }

				Equipo equipo = null;

				if ("X".equals(result.getString("GanaL"))) {
                	equipo = equipo1;
					resultado = ResultadoEnum.GANA;
				    }
				if ("X".equals(result.getString("Empata"))) {
					equipo = equipo1;
					resultado = ResultadoEnum.EMPATA;
				    }
				if ("X".equals(result.getString("GanaV"))) {
					equipo = equipo1;
					resultado = ResultadoEnum.PIERDE;
				    }
				Pronostico pronostico = new Pronostico(partido, equipo, resultado);

				/**************************************************************************
				     * Sumar los puntos correspondientes a cada participante
				   
				 ***************************************************************************/

				if (participante == 1) {
					nombre1 = result.getString("Participante");

					if (partido.getRonda() == 1) {
						puntos1Ronda1 += pronostico.puntos();
						ptos1Ronda1 = puntos1Ronda1;
						if (puntos1Ronda1 == 6) {
							puntos1Ronda1 += 4;
						}
					  }
					if (partido.getRonda() == 2) {
						puntos1Ronda1 += pronostico.puntos();
						ptos1Ronda2 = puntos1Ronda2;
						if (puntos1Ronda1 == 6) {
							puntos1Ronda1 += 4;
						}
					  }
					if (partido.getRonda() == 3) {
						puntos1Ronda1 += pronostico.puntos();
						ptos1Ronda3 = puntos1Ronda3;
						if (puntos1Ronda1 == 6) {
							puntos1Ronda1 += 4;
						}
					  }
				   }

				if (partido.getFase().equals("A") && (ptos1Ronda1 == 6 && ptos1Ronda2 == 6 && ptos1Ronda3 == 6)) {
					ptosXExtraFase3 = 10;
				   }
				// --------------------------------------------------------------------------------------------//

				if (participante == 2) {
					nombre2 = result.getString("Participante");

					if (partido.getRonda() == 1) {
						puntos2Ronda1 += pronostico.puntos();
						ptos2Ronda1 = puntos2Ronda1;
						if (puntos2Ronda1 == 6) {
							puntos2Ronda1 += 4;
						}
					  }
					if (partido.getRonda() == 2) {
						puntos2Ronda2 += pronostico.puntos();
						ptos2Ronda2 = puntos2Ronda2;
						if (puntos2Ronda2 == 6) {
							puntos2Ronda2 += 4;
						}
					  }
					if (partido.getRonda() == 3) {
						puntos2Ronda2 += pronostico.puntos();
						ptos2Ronda3 = puntos2Ronda3;
						if (puntos2Ronda2 == 6) {
							puntos2Ronda3 += 4;
						}
					  }
			    	}

				if (partido.getFase().equals("A") && (ptos2Ronda1 == 6 && ptos2Ronda2 == 6 && ptos2Ronda3 == 6)) {
					ptosXExtraFase3 = 10;
				}
				// --------------------------------------------------------------------------------------------//

				if (participante == 3) {
					nombre3 = result.getString("Participante");

					if (partido.getRonda() == 1) {
						puntos3Ronda1 += pronostico.puntos();
						ptos3Ronda1 = puntos3Ronda1;
						if (puntos3Ronda1 == 6) {
							puntos3Ronda1 += 4;
						}
					  }

					if (partido.getRonda() == 2) {
						puntos3Ronda2 += pronostico.puntos();
						ptos3Ronda2 = puntos3Ronda2;
						if (puntos3Ronda2 == 6) {
							puntos3Ronda2 += 4;
						}
					  }
					if (partido.getRonda() == 3) {
						puntos3Ronda3 += pronostico.puntos();
						ptos3Ronda3 = puntos3Ronda3;
						if (puntos3Ronda3 == 6) {
							puntos3Ronda3 += 4;
						}
					  }
				   }

				if (partido.getFase().equals("A") && (ptos3Ronda1 == 6 && ptos3Ronda2 == 6 && ptos3Ronda3 == 6)) {
					ptosXExtraFase3 = 10;
				   }
			     }
			// Condición para sumar ptos Extras por Fase
			// Suma de ptos
			puntos = puntos1Ronda1 + puntos1Ronda2 + puntos1Ronda3 + ptosXExtraFase1;
			puntos2 = puntos2Ronda1 + puntos2Ronda2 + puntos2Ronda3 + ptosXExtraFase2;
			puntos3 = puntos3Ronda1 + puntos3Ronda2 + puntos3Ronda3 + ptosXExtraFase3;
			// Cerrar conexión
			con.close();
			consulta.close();
			result.close();

		} catch (SQLException e) {
			System.out.println("No se pudo leer la linea de pronostico...");
			System.out.println(e.getMessage());
			System.exit(1);
			e.printStackTrace();
		     }
		// Mostrar los puntos
		System.out.println("Pronóstico:\n+ Acierto por Participante:" + "\n* " + nombre1 + ": " + puntos + " ptos en total");
		System.out.println(	"* " + nombre2 + ": " + puntos2 + " ptos en total \n* " + nombre3 + ": " + puntos3 + " ptos en total");
		int total = puntos + puntos2 + puntos3;
		System.out.println("\n+ Cantidad de Aciertos: " + total);

	           }
             }

 
