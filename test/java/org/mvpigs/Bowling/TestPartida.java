package org.mvpigs.Bowling;

import org.junit.Assert;
import org.junit.Test;

public class TestPartida {
	@Test
	
	public void TestSumaPuntosStrike(){
		TarjetaPuntos partida9 = new TarjetaPuntos("XX9-9-9-9-3/-----/-");
		Assert.assertEquals(104, partida9.getTotalPuntos());}
	
}


