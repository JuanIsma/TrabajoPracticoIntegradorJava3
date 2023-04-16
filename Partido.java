package tpIntegrador;

public class Partido {

	String fase;
	int ronda;
	Equipo equipo1;
	Equipo equipo2;
	int golesEquipo1;
	int golesEquipo2;

	public Partido() {
	}

	public Partido(String fase, int ronda, Equipo equipo1, Equipo equipo2) {
		this.fase = fase;
		this.ronda = ronda;
		this.equipo1 = equipo1;
		this.equipo2 = equipo2;

	}

	public String getFase() {
		return fase;
	}

	public void setFase(String fase) {
		this.fase = fase;
	}

	public int getRonda() {
		return ronda;
	}

	public void setRonda(int ronda) {
		this.ronda = ronda;
	}

	public Equipo getEquipo1() {
		return equipo1;
	}

	public void setEquipo1(Equipo equipo1) {
		this.equipo1 = equipo1;
	}

	public Equipo getEquipo2() {
		return equipo2;
	}

	public void setEquipo2(Equipo equipo2) {
		this.equipo2 = equipo2;
	}

	public int getGolesEquipo1() {
		return golesEquipo1;
	}

	public void setGolesEquipo1(int golesEquipo1) {
		this.golesEquipo1 = golesEquipo1;
	}

	public int getGolesEquipo2() {
		return golesEquipo2;
	}

	public void setGolesEquipo2(int golesEquipo2) {
		this.golesEquipo2 = golesEquipo2;
	}

	@Override
	public String toString() {
		return " \n -Equipo1: \n" + equipo1 + " -GolesEquipo1: " + golesEquipo1 + "\n -Equipo2: \n" + equipo2
				+ " GolesEquipo2: " + golesEquipo2 + "\n";
	}

	public ResultadoEnum resultado(Equipo equipo) {

		if (golesEquipo1 == golesEquipo2) {
			return ResultadoEnum.EMPATA;
		}
		if (equipo.getNombre().equals(equipo1.getNombre())) {
			if (golesEquipo1 > golesEquipo2) {
				return ResultadoEnum.GANA;
			} else {
				return ResultadoEnum.PIERDE;
			}
		} else {
			// como equipo no es equipo1, entonces es equipo2
			if (golesEquipo2 > golesEquipo1) {
				return ResultadoEnum.GANA;
			} else {
				return ResultadoEnum.PIERDE;
			}
		}

	}
}
