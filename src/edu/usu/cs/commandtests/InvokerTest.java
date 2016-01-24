package edu.usu.cs.commandtests;

import static org.junit.Assert.*;
import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Test;
import edu.usu.cs.command.*;
import edu.usu.cs.command.Character;


public class InvokerTest
	{
		private Character		c;
		private Weapon			w;
		private int				health;
		private int				stamina;
		private int				mana;
		private int				healthDamage;
		private double			staminaDamage;
		private double			manaDamage;
		private AttributeEffect	healthEffect;
		private AttributeEffect	staminaEffect;
		private AttributeEffect	manaEffect;

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

		
		/*
		 * Thank God that math works...
		 */
		private void assertEffectHelper(int doXTimes, int undoXTimes)
			{
				int tempHealth = health;
				int tempStamina = stamina;
				int tempMana = mana;
				for (int t = 1; t <= doXTimes - undoXTimes; t++)
					{
						tempHealth = healthEffect.runEffect(tempHealth);
						tempStamina = staminaEffect.runEffect(tempStamina);
						tempMana = manaEffect.runEffect(tempMana);
					}
				assertTrue(c.getHealth() == tempHealth);
				assertTrue(c.getStamina() == tempStamina);
				assertTrue(c.getMana() == tempMana);
			}

		private void addXCommands(Invoker i, int x)
			{
				for (int c = 1; c <= x; c++)
					{
						Attack a = new Attack(w, this.c);
						i.addCommand(a);
					}
			}

		private void runXCommands(Invoker i, int x)
			{
				for (int c = 1; c <= x; c++)
					{
						i.runCommand();
					}
			}

		private Attack runSingleHelper(Invoker i)
			{
				Attack a = new Attack(w, c);
				i.addCommand(a);
				i.runCommand();
				return a;
			}

		private void runXHelper(Invoker i, int x)
			{
				for (int c = 1; c <= x; c++)
					{
						runSingleHelper(i);
					}
			}

		private void runXUndosHelper(Invoker i, int x)
			{
				for (int c = 1; c <= x; c++)
					{
						i.undo();
					}
			}

		private void runXRedosHelper(Invoker i, int x)
			{
				for (int c = 1; c <= x; c++)
					{
						i.redo();
					}
			}

		@Test
		public void runEmptyTest()
			{
				Invoker i = new Invoker();
				try
					{
						i.runCommand();
					}
				catch (NoSuchElementException e)
					{
						assertTrue(true);
						return;
					}
				assertTrue(false);
			}

		@Test
		public void runUndoOnEmptyTest()
			{
				Invoker i = new Invoker();
				try
					{
						i.undo();
					}
				catch (NoSuchElementException e)
					{
						assertTrue(true);
						return;
					}
				assertTrue(false);
			}

		@Test
		public void runRedoOnEmptyTest()
			{
				Invoker i = new Invoker();
				try
					{
						i.redo();
					}
				catch (NoSuchElementException e)
					{
						assertTrue(true);
						return;
					}
				assertTrue(false);
			}

		@Test
		public void runTooManyUndosTest()
			{
				Invoker i = new Invoker();
				int dos = 5;
				int undos = 6;
				runXHelper(i, 5);
				assertEffectHelper(dos, 0);
				try
					{
						runXUndosHelper(i, undos);
					}
				catch (NoSuchElementException e)
					{
						assertTrue(true);
						return;
					}
				assertTrue(false);
			}

		@Test
		public void runTooManyRedosTest()
			{
				Invoker i = new Invoker();
				int times = 3;
				int redos = 5;
				runXHelper(i, times);
				assertEffectHelper(times, 0);
				runXUndosHelper(i, times);
				assertEffectHelper(times, times);
				try
					{
						runXRedosHelper(i, redos);
					}
				catch (NoSuchElementException e)
					{
						assertTrue(true);
						return;
					}
				assertTrue(false);
			}

		@Test
		public void runSingleTest()
			{
				Invoker i = new Invoker();
				runSingleHelper(i);
				assertEffectHelper(1, 0);
			}

		@Test
		public void runSingleUndoTest()
			{
				Invoker i = new Invoker();
				runSingleHelper(i);
				assertEffectHelper(1, 0);
				i.undo();
				assertEffectHelper(1, 1);
			}

		@Test
		public void runSingleRedoTest()
			{
				Invoker i = new Invoker();
				runSingleHelper(i);
				assertEffectHelper(1, 0);
				i.undo();
				assertEffectHelper(1, 1);
				i.redo();
				assertEffectHelper(2, 1);
			}

		@Test
		public void runUndoChainTestEqual()
			{
				Invoker i = new Invoker();
				int times = 4;
				runXHelper(i, times);
				assertEffectHelper(times, 0);
				runXUndosHelper(i, times);
				assertEffectHelper(times, times);
			}

		@Test
		public void runUndoChainTestNotEqual()
			{
				Invoker i = new Invoker();
				int dos = 5;
				int undos = 3;
				runXHelper(i, dos);
				assertEffectHelper(dos, 0);
				runXUndosHelper(i, undos);
				assertEffectHelper(dos, undos);
			}

		@Test
		public void runUndoRedoChainTestEqualInterlaced()
			{
				Invoker i = new Invoker();
				int times = 3;
				for (int c = 1; c <= times; c++)
					{
						runSingleHelper(i);
						i.undo();
						i.redo();
						assertEffectHelper(2 * c, c);
					}
			}

		@Test
		public void runUndoRedoChainTestEqual()
			{
				Invoker i = new Invoker();
				int times = 3;
				runXHelper(i, times);
				assertEffectHelper(times, 0);
				runXUndosHelper(i, times);
				assertEffectHelper(times, times);
				runXRedosHelper(i, times);
				assertEffectHelper(2 * times, times);
				assertEffectHelper(times, 0);
			}

		@Test
		public void runTooManyCommandsTest()
			{
				Invoker i = new Invoker();
				int commandCount = 10;
				addXCommands(i, commandCount);
				try
					{
						runXCommands(i, commandCount + 5);
					}
				catch (NoSuchElementException e)
					{
						assertTrue(true);
						return;
					}
				assertTrue(false);
			}

		@Test
		public void runAddCommandsTest()
			{
				Invoker i = new Invoker();
				int commandCount = 10;
				addXCommands(i, commandCount);
				
				
				
				runXCommands(i, commandCount);
				assertEffectHelper(commandCount, 0);
					
			}
		
		@Test
		public void runAddCommandsThenUndoTest()
			{
				Invoker i = new Invoker();
				int commandCount = 10;
				addXCommands(i, commandCount);
				
				
				
				runXCommands(i, commandCount);
				assertEffectHelper(commandCount, 0);
				
				
				runXUndosHelper(i, commandCount);
				assertEffectHelper(commandCount, commandCount);
					
			}
		@Test
		public void runAddCommandsThenUndoRedoTest()
			{
				Invoker i = new Invoker();
				int commandCount = 10;
				addXCommands(i, commandCount);
				
				
				
				runXCommands(i, commandCount);
				assertEffectHelper(commandCount, 0);
				
				
				runXUndosHelper(i, commandCount);
				assertEffectHelper(commandCount, commandCount);
				
				
				runXRedosHelper(i, commandCount);
				
				assertEffectHelper(2 * commandCount, commandCount);
				
				
				assertEffectHelper(commandCount, 0);
					
			}
		
		@Test
		public void runAddCommandsThenUndoRedoBifurcateTest()
			{
				Invoker i = new Invoker();
				int commandCount = 10;
				int undoCount = 7;
				int redoCount = 3;
				addXCommands(i, commandCount);
				
				
				//attack 10 times
				runXCommands(i, commandCount);
				assertEffectHelper(commandCount, 0);
				
				
				
				//undo 7 of those attacks
				runXUndosHelper(i, undoCount);
				assertEffectHelper(commandCount, undoCount);
				
				
				//redo 3 of the undone attacks, making 6 total attacks done
				runXRedosHelper(i, redoCount);
				assertEffectHelper(commandCount + redoCount, undoCount);
				//another way of asserting the same thing
				//10 + 3 - 7 = 6
				assertEffectHelper(commandCount + redoCount - undoCount, 0);
				
				
				
				//do 10 more attacks
				addXCommands(i, commandCount);
		
				
				runXCommands(i, commandCount);
				//20 + 3 - 7 = 16 total attacks done (10 + 6 from before)
				assertEffectHelper(commandCount * 2 + redoCount, undoCount);
				
				
				//undo 7 attacks, making 9 attacks done
				runXUndosHelper(i, undoCount);
				//20 + 3 - 14 = 9, yay :)
				assertEffectHelper(commandCount * 2 + redoCount, undoCount * 2);
				
				
				//lets double check our character's stats, just in case... they shouldn't be at their original values
				assertTrue(c.getHealth() != 100);
				assertTrue(c.getStamina() != 100);
				assertTrue(c.getMana() != 100);
				
				
				//redo 3 attacks, making 12 done
				runXRedosHelper(i, redoCount);
				assertEffectHelper(commandCount * 2 + redoCount * 2, undoCount * 2);
				
				//another way of asserting that 12 net attacks have been performed
				assertEffectHelper(12, 0);
					
				
				runXUndosHelper(i, 12);
				
				assertEffectHelper(0, 0);
				
				//lets double check our character's stats, just in case... they should be at their original values now, since net attacks = 0
				
				assertTrue(c.getHealth() == 100);
				assertTrue(c.getStamina() == 100);
				assertTrue(c.getMana() == 100);
			}
		
		
		
		 
		
		
		
		
	}
