package org.mvpigs.Bowling;

import java.util.Arrays;

public class TarjetaPuntos {

	
	 

	private String puntos;
	private String[] turnos={"","","","","","","","","",""};
	private int TotalPuntos;
	private String CARACTERES_ESPECIALES="-X/";

	public TarjetaPuntos(String puntosJugador) {
		
		
		this.puntos=puntosJugador;
		
		DefinirTurnos();
		
		this.TotalPuntos=CalcularTotalPuntos();
	};

	private void DefinirTurnos() {
		int j=0;
		
		int numeroTurnosStrike = 0;
		int numeroTurnosDosTiradas=0;
		for (int i=0;i<this.puntos.length();i++){
			if ( j<(this.turnos.length-1) && this.puntos.charAt(i)=='X'){
				this.turnos[j]="X";
				
				numeroTurnosStrike++;
				
			}
			else if(j<this.turnos.length-1 && this.turnos[j].length()<2){
				System.out.println(("hola"));
				System.out.println(i);
				System.out.println(this.puntos.charAt(i));
				this.turnos[j]=this.turnos[j]+this.puntos.charAt(i);
				System.out.println(Arrays.toString(this.turnos));
				System.out.println(this.turnos[j]);
				System.out.println(j);
				System.out.println(turnos[j].length());
				numeroTurnosDosTiradas++;
				
			}
			if (j<this.turnos.length-1 && isStrike(this.turnos[j].charAt(0)) || turnos[j].length() == 2){
				j++;
			}
		
		}
		System.out.println(this.puntos.substring(numeroTurnosStrike+(numeroTurnosDosTiradas)));
		this.turnos[9]= this.puntos.substring(numeroTurnosStrike+(numeroTurnosDosTiradas));
		}

	private int CalcularTotalPuntos() {
		int sumaPuntos=0;
		
		
		System.out.println(Arrays.toString(this.turnos));
		for (int i=0;i<this.turnos.length;i++){
			boolean NoEntradoFlag=true;
			
			System.out.println(sumaPuntos);
			for (int k=0;k<CARACTERES_ESPECIALES.length();k++){	
				if (NoEntradoFlag && this.turnos[i].contains(""+CARACTERES_ESPECIALES.charAt(k))){
					NoEntradoFlag=false;
					System.out.println("dentro");
					System.out.println(sumaPuntos);
					sumaPuntos=sumaPuntos+CalculapuntosEspecial(turnos[i],i);
					System.out.println("holaa");
					System.out.println(sumaPuntos);
					System.out.println("fin");
				}
				else if(k==0 && NoEsEspecial(this.turnos[i])){
					
					System.out.println("NOOO");
					for (int j=0;j<this.turnos[i].length();j++){
					int valorPuntos = Character.getNumericValue(this.turnos[i].charAt(j));
					sumaPuntos=sumaPuntos+valorPuntos;
					}
				}
			}
			
			
		}
	System.out.println(sumaPuntos);
	return sumaPuntos;	
	}
	
	private int CalculapuntosEspecial(String turno, int i) {
		System.out.println("calculus");
		System.out.println(turno);
		System.out.println(i);
		if(i!=9){
			System.out.println(turno);
			if (turno=="X"){
				System.out.println("STRIKE");
				return Strike(i);
			}
			
		    else if (turno.contains("/")){
		    	System.out.println("soycalculanew");
		    	System.out.println(Spare(i));
					return Spare(i);
				}
			else if (turno.contains("-")){
					
						for (int j=0;j<this.turnos[i].length();j++){
							if (this.turnos[i].charAt(j)!='-'){		
								return Character.getNumericValue(this.turnos[i].charAt(j));
							}
						}
					}
				}
			
		
		
		else{
			 return UltimaTirada();
		}
		return 0;
	}

	private int UltimaTirada() {
		System.out.println("ultima tirada!");
		int puntos=0;
		for (int i=0;i<this.turnos[9].length();i++){
			if (isStrike(this.turnos[9].charAt(i))){
				System.out.println("ultimoStrike");
				puntos=puntos+10;
			}
			else if (i==0 && isSpare(this.turnos[9])){
				System.out.println("ultimo Spare!");
				System.out.println(this.turnos[9].charAt(1));
				System.out.println(isSpare(""+this.turnos[9].charAt(1)));
				if (isSpare(""+this.turnos[9].charAt(1))){
					System.out.print("que?");
					System.out.println("enmedio Spare!");
					System.out.println(puntos);
					puntos=puntos+10;
					System.out.println(puntos);
					if (NoEsEspecial(this.turnos[9].charAt(2))){
						System.out.println("voy por alli");
						System.out.println(this.turnos[9].charAt(2));
						puntos=puntos+Character.getNumericValue(this.turnos[9].charAt(2));
						
					}
				}
				else{
					System.out.println("voy por aqui");
					puntos=puntos+10;
				}
			}
			else if (NoEsEspecial(this.turnos[9].charAt(i))){
					System.out.println(puntos);
					System.out.println(this.turnos[9].charAt(i));
					puntos=puntos+Character.getNumericValue(this.turnos[9].charAt(i));
					System.out.println(puntos);}
			}
		return puntos;	
		}
		
	private int Spare(int i) {
		int puntos=10;
		
		if (isStrike(this.turnos[i+1])){
			puntos=puntos+10;
		}
		else if (! isZero(this.turnos[i+1].charAt(0))){
		puntos=puntos+Character.getNumericValue(this.turnos[i+1].charAt(0));
				}
		
		return puntos;
	}
	
	private boolean NoEsEspecial(char tirada){
		
		return !((""+tirada).contains(""+CARACTERES_ESPECIALES.charAt(0))) &&
				!((""+tirada).contains(""+CARACTERES_ESPECIALES.charAt(1))) &&
				!((""+tirada).contains(""+CARACTERES_ESPECIALES.charAt(2)));
	}
	
	private boolean NoEsEspecial(String tirada) {
		
		return !((tirada).contains(""+CARACTERES_ESPECIALES.charAt(0))) &&
				!((tirada).contains(""+CARACTERES_ESPECIALES.charAt(1))) &&
				!((tirada).contains(""+CARACTERES_ESPECIALES.charAt(2)));
	}
	
	private boolean isZero(char tirada) {
		// TODO Auto-generated method stub
		return tirada == '-';
	}
	
	private boolean isStrike(char tirada) {
		// TODO Auto-generated method stub
		return tirada=='X';
	}

	private boolean isStrike(String turno){
		return turno.contains("X");
	}
	
	private boolean isSpare(String turno){
		return turno.contains("/");
		
	}

	private int Strike(int turno) {
		System.out.println("turno");
		System.out.println(turno);
		int puntos=10;
		boolean primeroStrike=false;
		for (int j=turno+1;j<turno+3;j++){
			System.out.println(j);
			if (j<this.turnos.length && isStrike(this.turnos[j].charAt(0))){
				puntos=puntos+10;
				primeroStrike=true;
				}
			else if (!primeroStrike) {
				if(isSpare(this.turnos[turno+1])){
					puntos=puntos+10;
					}
				else{
					for (int k=0;k<2;k++){
						if (! isZero(this.turnos[turno+1].charAt(k))){
							puntos=puntos+Character.getNumericValue(this.turnos[turno+1].charAt(k));
						}
			
					}
				
						}
			}
			else{
				if (j==this.turnos.length){
					if(isStrike(this.turnos[9].charAt(1))){
						puntos=puntos+10;
						
					}
				}
				if (turno+2<this.turnos.length && ! isZero(this.turnos[turno+2].charAt(0))){
						puntos=puntos+Character.getNumericValue(this.turnos[turno+2].charAt(0));
				
			}
						
			
			}
			
		}
		
		
		return puntos;
	}

	public String[] getTurnos() {
		return this.turnos;
	}

	public int getTotalPuntos() {
		return TotalPuntos;
	}

}
