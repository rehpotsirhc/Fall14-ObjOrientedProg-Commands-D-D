package edu.usu.cs.commandtests;

import static org.junit.Assert.*;
import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Test;
import edu.usu.cs.command.Attack;
import edu.usu.cs.command.AttributeEffect;
import edu.usu.cs.command.Character;
import edu.usu.cs.command.Invoker;
import edu.usu.cs.command.Weapon;

public class AttackTest
	{
		private Character c;
		private Weapon w;
		private int health;
		private int stamina;
		private int mana;
		
		private int healthDamage;
		private double staminaDamage;
		private double manaDamage;
		
		private AttributeEffect healthEffect;
		private AttributeEffect staminaEffect; 
		private AttributeEffect manaEffect;	
		
		
		@Before
		public void setup()
			{
				health = 100;
				stamina = 100;
				mana = 100;
				
				healthDamage = -10;
				staminaDamage = .90;
				manaDamage = 1.1;
				
				healthEffect = new AttributeEffect(healthDamage);
				staminaEffect = new AttributeEffect(staminaDamage);
				manaEffect = new AttributeEffect(manaDamage);
				
				c = new Character("", health, stamina, mana);
				
				w = new Weapon(healthEffect, staminaEffect, manaEffect);
				
				
					
			}
		
		
	
		
		
		private void runSingleHelper(Attack a)
		{
		
			a.execute();
			
			assertTrue(c.getHealth() == healthEffect.runEffect(health));
			assertTrue(c.getStamina() == staminaEffect.runEffect(stamina));
			assertTrue(c.getMana() == manaEffect.runEffect(mana));
			
		}
		
		@Test
		public void runEmptyTest()
		{
			Attack a = new Attack(w, c);
			
			
			try
				{
					a.undo();
				}
			catch(NoSuchElementException e)
			{
				assertTrue(true);
				return;
			}
			assertTrue(false);
		}
	
		
		@Test
		public void runExecuteTest()
		{
			Attack a = new Attack(w, c);
		
			runSingleHelper(a);
			
			
		}
		@Test
		public void runUndoTest()
		{
			
			Attack a = new Attack(w, c);
			
			int origHealth = c.getHealth();
			int origStamina = c.getStamina();
			int origMana = c.getMana();
			runSingleHelper(a);
			
			a.undo();
			
			assertTrue(c.getHealth() == origHealth);
			assertTrue(c.getStamina() == origStamina);
			assertTrue(c.getMana() == origMana);
			
			
		}
		
	}
