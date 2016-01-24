package edu.usu.cs.command;

import java.util.Scanner;


public class Driver
	{
		/*
		 * Note: This driver code is both quick and dirty. The point of this
		 * assignment is not to do this event driven stuff correctly, it is to
		 * do the command pattern correctly. So, have fun.
		 */
		static Character	warriorWizzzzard;
		static Invoker invoker;

		public static void main(String[] args)
			{
				warriorWizzzzard = new Character("Christophe", 1000, 1500, 999900);
				invoker = new Invoker();
				Scanner a = new Scanner(System.in);
				String next = a.next();
				while (next.equals("quit") == false)
					{
						doActionBasedOnStringInput(next);
						
						next = a.next();
					}
				a.close();
			}

		private static void doActionBasedOnStringInput(String s)
			{
				if (s.equals("blah"))
					{
						System.out.println("Calling blah!");
					}
				
				if (s.equals("club"))
					{
						AttributeEffect hDamage = new AttributeEffect(-10);
						AttributeEffect sDamage = new AttributeEffect(-10);
						AttributeEffect mDamage = new AttributeEffect(0);
						invoker.addCommand(new Attack(new Weapon(hDamage, sDamage, mDamage), warriorWizzzzard));
					}
				
				if(s.equals("manadrain"))
					{
						AttributeEffect hDamage = new AttributeEffect(0);
						AttributeEffect sDamage = new AttributeEffect(0);
						AttributeEffect mDamage = new AttributeEffect(.50);
						invoker.addCommand(new Attack(new Weapon(hDamage, sDamage, mDamage), warriorWizzzzard));
					}
				if (s.equals("attack"))
					{
						invoker.runCommand();
						System.out.println(warriorWizzzzard.toString());
						
					}
				if (s.equals("undo"))
					{
						invoker.undo();
						System.out.println(warriorWizzzzard.toString());
					}
				if (s.equals("redo"))
					{
						invoker.redo();
						System.out.println(warriorWizzzzard.toString());
					}
			}
	}
