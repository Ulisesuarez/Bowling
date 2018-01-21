/**
 * Tests de la clase TarjetaPuntos resolviendo el kata 
 * Bowling de The Coding Dojo Handbook por Emily Bache
 * 
 * Ulises Bruno Suárez del Real Ferriol
 */
package org.mvpigs.Bowling;

import org.mvpigs.Bowling.TarjetaPuntos;
import org.junit.Assert;
import org.junit.Test;

public class TestTarjetaPuntos {
	public static String puntos="12345123451234512345";
	/**Each game, or “line” of bowling, includes ten turns, or
	 *“frames” for the bowler.
	 *Quien: el jugador
	 *Que: tiene 10 turnos 
	 *Para: Sumar puntos*/
	@Test
	public void TestNumeroTurnosTarjetaPuntos(){
		
		 TarjetaPuntos partida1= new TarjetaPuntos(puntos);
		 Assert.assertEquals(10, partida1.getTurnos().length);
		
	}
	/**In each frame, the bowler gets up to two tries to knock
     *down all the pins.
	 *Quien: el jugador
     *Que: tiene hasta dos tiradas por turno
	 *Para: tirar todos los bolos
	 */
	@Test
	public void TestNumeroTiradasTurno(){
		TarjetaPuntos partida1= new TarjetaPuntos(puntos);
		for(int i=0;i<partida1.getTurnos().length-1;i++){
			Assert.assertTrue("no puede haber más de 2 tiradas por turno ",2>=partida1.getTurnos()[i].length());
		
		}
	}
	
	/**If in two tries, he fails to knock them all down, his score
	 *for that frame is the total number of pins knocked down
 	 *in his two tries.
	 *Quien: el jugador
	 *Que: no tira todos los bolos 
	 *Para:consigue las suma de los bolos tirados en las dos tiradas del turno 
	 */
	@Test
	public void TestSumaPuntos(){
		TarjetaPuntos partida1 = new TarjetaPuntos(puntos);
		Assert.assertEquals(60, partida1.getTotalPuntos());
		
	}
	/**If in two tries he knocks them all down, this is called
	 *a “spare” and his score for the frame is ten plus the
	 *number of pins knocked down on his next throw (in
	 *his next turn).
	 *Quien: el jugador
	 *Que: consigue tirar todos los bolos en dos tiradas
	 *Para: hacer un Spare
	 *representado por "/" equivalente a 10 puntos más la puntuación de la proxima tirada 
	 */
	
	@Test
	public void TestSumaPuntosSpare(){
		TarjetaPuntos partida = new TarjetaPuntos("5/5/5/5/5/5/5/5/5/5/-");
		Assert.assertEquals(145, partida.getTotalPuntos());
	}

	/**If on his first try in the frame he knocks down all the
	 *pins, this is called a “strike”. His turn is over, and his
	 *score for the frame is ten plus the simple total of the
	 *pins knocked down in his next two rolls
	 *Quien: el jugador
	 *Que: tira los bolos en la primera tirada
	 *Para:hacer un Strike
	 *representado por X equivalente a 10 puntos más la puntación de las dos proximas tiradas
	 *si haces Strike solo tienes una tirada por turno excepto para el ultimo turno
	 *Esto hace que el numero de tiradas totales cambie, teniendo que indicar cuando acaba cada turno*/
	
	@Test
	
	public void TestSumaPuntosStrike(){
		TarjetaPuntos partida = new TarjetaPuntos("XXXXXXXXXXXX");
		Assert.assertEquals(300, partida.getTotalPuntos());}
	@Test
	
	public void TestSumaPuntosZero(){
		TarjetaPuntos partida = new TarjetaPuntos("9-9-9-9-9-9-9-9-9-9-");
		Assert.assertEquals(90, partida.getTotalPuntos());}
}

