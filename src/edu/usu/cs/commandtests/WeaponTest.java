package edu.usu.cs.commandtests;

import static org.junit.Assert.*;
import org.junit.Test;
import edu.usu.cs.command.AttributeEffect;
import edu.usu.cs.command.Weapon;

public class WeaponTest
	{
		@Test
		public void EffectsTest()
			{
				AttributeEffect healthEffect = new AttributeEffect(100);
				AttributeEffect staminaEffect = new AttributeEffect(.2);
				AttributeEffect manaEffect = new AttributeEffect(1.1);
				
				int health = 650;
				int stamina = 110;
				int mana = 10;
				
				Weapon w = new Weapon(healthEffect, staminaEffect, manaEffect);
				
				int damagedHealth = w.doHealthEffect(health);
				int damagedStamina = w.doStaminaEffect(stamina);
				int damagedMana = w.doManaEffect(mana);
				
				int healthEffected = healthEffect.runEffect(health);
				int staminaEffected = staminaEffect.runEffect(stamina);
				int manaEffected = manaEffect.runEffect(mana);
				
				assertTrue(damagedHealth == healthEffected && damagedStamina == staminaEffected && damagedMana == manaEffected);
				
				
			
			}
	}
