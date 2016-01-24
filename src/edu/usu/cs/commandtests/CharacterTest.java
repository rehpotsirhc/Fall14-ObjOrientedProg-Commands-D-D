package edu.usu.cs.commandtests;

import static org.junit.Assert.*;
import org.junit.Test;
import edu.usu.cs.command.Character;


public class CharacterTest
	{
		@Test
		public void creationTest()
			{
				String name = "MrWizard";
				int health = 999;
				int stamina = 0;
				int mana = 10000;
				Character c = new Character(name, health, stamina, mana);
				assertTrue(c.getName().equals(name) && c.getHealth() == health && c.getStamina() == stamina && c.getMana() == mana);
			}

		@Test
		public void creationTestCorectedValues()
			{
				String name = "MrWizard";
				int health = -5;
				int stamina = -999;
				int mana = 0;
				Character c = new Character(name, health, stamina, mana);
				assertTrue(c.getName().equals(name) && c.getHealth() == 1 && c.getStamina() == 0 && c.getMana() == 0);
			}

		@Test
		public void cloneTest()
			{
				String name = "MrWizard";
				int health = -5;
				int stamina = -999;
				int mana = 0;
				Character c = new Character(name, health, stamina, mana);
				Character objectCopy = c;
				Character clone = c.cloneMe();
				assertTrue(c.getName().equals(clone.getName()) && c.getHealth() == clone.getHealth() && c.getStamina() == clone.getStamina() && c.getMana() == clone.getMana());
				assertTrue(c != clone);
				assertTrue(objectCopy == c);
				clone.setName("bob");
				clone.setHealth(10);
				clone.setMana(100);
				clone.setStamina(1000);
				assertTrue(!c.getName().equals(clone.getName()) && c.getHealth() != clone.getHealth() && c.getStamina() != clone.getStamina() && c.getMana() != clone.getMana() && c.getName() == objectCopy.getName());
			}

		@Test
		public void copyTest()
			{
				String name = "MrWizard";
				int health = -5;
				int stamina = -999;
				int mana = 0;
				Character c = new Character(name, health, stamina, mana);
				
				
				Character copy = new Character("", 1, 1, 1);
				
				c.copyMe(copy);
				
				assertTrue(copy.getName().equals(name) && copy.getHealth() == 1 && copy.getStamina() == 0 && copy.getMana() == 0);
			}
	}
