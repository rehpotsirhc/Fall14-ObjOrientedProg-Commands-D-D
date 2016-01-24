package edu.usu.cs.command;

import java.util.NoSuchElementException;


public class Attack implements ICommand
	{
		// the receiver
		private Character	attacked;
		private Weapon		weapon;
		private Character	beforeAttack;

		public Attack(Weapon theWeapon, Character theAttacked)
			{
				this.attacked = theAttacked;
				this.weapon = theWeapon;
			}

	
		@Override
		public void execute()
			{
				beforeAttack = attacked.cloneMe();
				attacked.setHealth(weapon.doHealthEffect(attacked.getHealth()));
				attacked.setStamina(weapon.doStaminaEffect(attacked.getStamina()));
				attacked.setMana(weapon.doManaEffect(attacked.getMana()));
			}

		@Override
		public void undo() throws NoSuchElementException
			{
				if (beforeAttack != null)
					{
						beforeAttack.copyMe(attacked);
					} else
					throw new NoSuchElementException();
			}
	}
