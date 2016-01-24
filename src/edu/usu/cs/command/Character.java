package edu.usu.cs.command;

public class Character
	{
		private String	name;
		private int		health;
		private int		stamina;
		private int		mana;

		public Character(String name, int health, int stamina, int mana)
			{
				this.name = name;
				
				if(health < 0) health = 1;
				this.health = health;
				
				if(stamina < 0) stamina = 0;
				this.stamina = stamina;
				
				if(mana < 0) mana = 0;
				this.mana = mana;
			}

		public String getName()
			{
				return name;
			}

		public void setName(String name)
			{
				this.name = name;
			}

		public int getHealth()
			{
				return health;
			}

		public void setHealth(int health)
			{
				this.health = health;
			}

		public int getStamina()
			{
				return stamina;
			}

		public void setStamina(int stamina)
			{
				this.stamina = stamina;
			}

		public int getMana()
			{
				return mana;
			}

		public void setMana(int mana)
			{
				this.mana = mana;
			}
	
		
		public Character cloneMe()
		{
			return new Character(name, health, stamina, mana);
		}
		
		public Character copyMe(Character copy)
		{
			if(copy == null) copy = new Character("", 0, 0, 0);
			
			copy.setName(getName());
			copy.setHealth(getHealth());
			copy.setStamina(getStamina());
			copy.setMana(getMana());
			
			return copy;
		}
		
		@Override
		public String toString()
		{
			return name + ": " + health + ", " + stamina + ", " + mana;
		}
	
	
	
	}
