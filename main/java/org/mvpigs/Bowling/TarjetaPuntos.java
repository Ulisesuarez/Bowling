package org.mvpigs.Bowling;

import java.util.Arrays;



public class TarjetaPuntos {

	private static final String CARACTERES_ESPECIALES="-X/";
	private String puntos="";
	private int totalPuntos=0;
	private String[] turnos=null;

	public TarjetaPuntos(String puntosJugador) {
		 /**Constructor*/
		
		this.puntos=puntosJugador;
		this.turnos=new String[]{"","","","","","","","","",""};
		this.definirTurnos();
		this.totalPuntos=0;
		this.calcularTotalPuntos();
	  
			};
			
	public TarjetaPuntos() {
		 /**Constructor*/
		
		
		this.turnos=new String[]{"","","","","","","","","",""};
		
		this.calcularTotalPuntos();
	  //this.totalPuntos
			};

	private void definirTurnos() {
		
		int turnoIndice=0;
		int numeroTurnosStrike = 0;
		int numeroTurnosDosTiradas=0;
		
		for (int tirada=0;tirada<this.puntos.length();tirada++){
			
			if ( noEsUltimoTurno(turnoIndice) && esStrike(this.puntos.charAt(tirada))){
				
				this.turnos[turnoIndice]="X";
				numeroTurnosStrike++;
				
			}
			else if(noEsUltimoTurno(turnoIndice) && this.turnos[turnoIndice].length()<2){
				
				this.turnos[turnoIndice]=this.turnos[turnoIndice]+this.puntos.charAt(tirada);
				numeroTurnosDosTiradas++;
				
			}
			if (noEsUltimoTurno(turnoIndice) && (esStrike(this.turnos[turnoIndice].charAt(0)) || turnos[turnoIndice].length() == 2)){
				
				turnoIndice++;
			}
		
		}
		
		/**Ultimo turno*/
		this.turnos[9]= this.puntos.substring(numeroTurnosStrike+(numeroTurnosDosTiradas));
		
	System.out.println(Arrays.toString(turnos));}

	private boolean noEsUltimoTurno(int turnoIndice) {
		return turnoIndice<(this.turnos.length-1);
	}

	private void calcularTotalPuntos() {
		
		int sumaPuntos=0;
		
		for (int turnoIndice=0;turnoIndice<this.turnos.length;turnoIndice++){
	
						
				if (esEspecial(this.turnos[turnoIndice])){

					sumaPuntos=sumaPuntos+calculaPuntosEspecial(this.turnos[turnoIndice],turnoIndice);
					
				}
				else{ //if(NoEsEspecial(this.turnos[i])){
					
					
					for (int tirada=0;tirada<this.turnos[turnoIndice].length();tirada++){
						int valorPuntos = Character.getNumericValue(this.turnos[turnoIndice].charAt(tirada));
						sumaPuntos=sumaPuntos+valorPuntos;
					}
				}
			}
			
		this.totalPuntos=sumaPuntos;	
		}
	
	private int calculaPuntosEspecial(String turno, int turnoIndice) {
		
		if(noEsUltimoTurno(turnoIndice)){
			
			if (esStrike(turno)){
				//System.out.println("STRIKE");
				return strike(turnoIndice);
			}
			
		    else if (turno.contains("/")){
		    	//System.out.println("SPARE");
		    	
				return spare(turnoIndice);
				}
			else if (turno.contains("-")){
					
				for (int tirada=0;tirada<this.turnos[turnoIndice].length();tirada++){
				
					if (noEsCero(this.turnos[turnoIndice].charAt(tirada))){		
						
						return Character.getNumericValue(this.turnos[turnoIndice].charAt(tirada));
					}
				}
			}
		}
		
		else{
			 return puntosUltimaTirada();
		}
		return 0;
	}

	private int puntosUltimaTirada() {
		
		int puntos=0;
		boolean notDoneSpareFlag=true;
		for (int tirada=0;tirada<this.turnos[9].length();tirada++){
			
			
			if (esStrike(this.turnos[9].charAt(tirada))){
				
				puntos=puntos+10;
			}
			
			else if (notDoneSpareFlag && esSpare(this.turnos[9])){
				notDoneSpareFlag=false;
				if (esSpare(""+this.turnos[9].charAt(1))){
					
					puntos=puntos+10;
					
					if (noEsEspecial(this.turnos[9].charAt(2))){
						
						puntos=puntos+Character.getNumericValue(this.turnos[9].charAt(2));
						
					}
				}
				else{ //if Spare es la ultima tirada
					
					puntos=puntos+10;
				}
			}
			
			else if (!esSpare(this.turnos[9]) && noEsEspecial(this.turnos[9].charAt(tirada))){
					
					puntos=puntos+Character.getNumericValue(this.turnos[9].charAt(tirada));
				}
		}
		
		return puntos;	
	}
		
	private int spare(int turnoIndice) {
		
		int puntos=10;
		
		if (esStrike(this.turnos[turnoIndice+1].charAt(0))){
			puntos=puntos+10;
		}
		else if (noEsCero(this.turnos[turnoIndice+1].charAt(0))){
		puntos=puntos+Character.getNumericValue(this.turnos[turnoIndice+1].charAt(0));
		}
		
		return puntos;
	}
	
	private boolean esEspecial(String turno){
		return !noEsEspecial(turno);
	}
	
	private boolean noEsEspecial(char tirada){
		
		return !((""+tirada).contains(""+CARACTERES_ESPECIALES.charAt(0))) &&
				!((""+tirada).contains(""+CARACTERES_ESPECIALES.charAt(1))) &&
				!((""+tirada).contains(""+CARACTERES_ESPECIALES.charAt(2)));
	}
	
	
	private boolean noEsEspecial(String turno) {
		
		return !((turno).contains(""+CARACTERES_ESPECIALES.charAt(0))) &&
				!((turno).contains(""+CARACTERES_ESPECIALES.charAt(1))) &&
				!((turno).contains(""+CARACTERES_ESPECIALES.charAt(2)));
	}
	
	private boolean noEsCero(char tirada) {
		// TODO Auto-generated method stub
		return tirada != '-';
	}
	
	private boolean esStrike(char tirada) {
		// TODO Auto-generated method stub
		return tirada=='X';
	}

	private boolean esStrike(String turno){
		return turno.contains("X");
	}
	
	private boolean esSpare(String turno){
		return turno.contains("/");
		
	}

	private int strike(int turno) {
		
		int puntos=10;
		boolean primeroStrike=false;
		boolean notDoneSpareFlag=true;
		
		for (int turnoIndice=turno+1;turnoIndice<=turno+2;turnoIndice++){
			

			if ( notDoneSpareFlag && turnoIndice<this.turnos.length && esStrike(this.turnos[turnoIndice])){
				
				puntos=puntos+10;
				primeroStrike=true;
				}
			else if (!primeroStrike) {
				
				if(notDoneSpareFlag && esSpare(this.turnos[turno+1])){
					notDoneSpareFlag=false;
					puntos=puntos+10;
					}
				else{
					for (int tirada=0;tirada<2;tirada++){
						
						if (notDoneSpareFlag && noEsEspecial(this.turnos[turno+1].charAt(tirada))){
							puntos=puntos+Character.getNumericValue(this.turnos[turno+1].charAt(tirada));
							
						}
			
					}return puntos;
				
						}
			}
			else{ //if primeroStrike:
				
				if (turnoIndice==this.turnos.length){ 
					
					if(esStrike(this.turnos[9].charAt(1))){
						
						puntos=puntos+10;
						}
					if(noEsEspecial(this.turnos[9].charAt(1))){
							
						puntos=puntos+Character.getNumericValue(this.turnos[9].charAt(1));
					
						
					}
				}
				if (turno+2<this.turnos.length && noEsEspecial(this.turnos[turno+2].charAt(0))){
						
					puntos=puntos+Character.getNumericValue(this.turnos[turno+2].charAt(0));
				
			}
						
			
			}
			
		}
		
		
		return puntos;
	}

	public String[] getTurnos() {
		return this.turnos;
	}
	public void setTurnos(String[] turn) {
		this.turnos=turn;
	}

	public int getTotalPuntos() {

		return this.totalPuntos;
	}

}
