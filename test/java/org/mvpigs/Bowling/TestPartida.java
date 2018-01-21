package org.mvpigs.Bowling;

import org.junit.Assert;
import org.junit.Test;

public class TestPartida {
	@Test
	
	public void TestSumaPuntosStrike(){
		TarjetaPuntos partida = new TarjetaPuntos("9-9-9-9-9-9-9-9-9-9-");
		Assert.assertEquals(90, partida.getTotalPuntos());}
	
}


