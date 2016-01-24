package edu.usu.cs.commandtests;

import static org.junit.Assert.*;
import org.junit.Test;
import edu.usu.cs.command.AttributeEffect;


public class AttributeEffectTest
	{
		
		private void sumHelper(int number, int attribute)
		{
			AttributeEffect aE = new AttributeEffect(number);
			
			int result = aE.runEffect(attribute);
			
			int expectedResult = number + attribute;
			
			if(expectedResult < 0) expectedResult = 0;
			assertTrue(result == expectedResult && result >= 0);
		}
		
		private void prodHelper(double number, int attribute)
		{
			AttributeEffect aE = new AttributeEffect(number);
			
			int result = aE.runEffect(attribute);
			
			int expectedResult = (int)Math.floor((number) * attribute);
			if(expectedResult < 0) expectedResult = 0;
			assertTrue( result == expectedResult && result >= 0);
		}
		
		@Test
		public void sumEffectTestPos()
			{
				int number = 10;
				int attribute = 100;
				sumHelper(number, attribute);
			}
		@Test
		public void sumEffectTestNeg()
			{
				int number = -10;
				int attribute = 100;
				sumHelper(number, attribute);
			}
		
		@Test
		public void sumEffectToZero()
		{
			int number = -110;
			int attribute = 110;
			sumHelper(number, attribute);
		}
		
		@Test
		public void sumEffectPastZero()
		{
			int number = -500;
			int attribute = 110;
			sumHelper(number, attribute);
		}
		@Test
		public void sumEffectUpToZero()
		{
			int number = 105;
			int attribute = -105;
			sumHelper(number, attribute);
		}
		
		
		@Test
		public void prodEffectGrow()
		{
			double grower = 1.1;
			int attribute = 100;
			prodHelper(grower, attribute);
		}
		
		@Test
		public void prodEffectShrink()
		{
			double shrinker = .4;
			int attribute = 100;
			prodHelper(shrinker, attribute);
		}
		@Test
		public void prodEffectToZero()
		{
			double shrinker = .999;
			int attribute = 100;
			prodHelper(shrinker, attribute);
			
		}
	

		
	}
