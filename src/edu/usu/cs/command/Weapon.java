package edu.usu.cs.command;

public class Weapon
	{
		private String			name;
		private AttributeEffect	healthEffect;
		private AttributeEffect	staminaEffect;
		private AttributeEffect	manaEffect;

		public Weapon(AttributeEffect healthEffect, AttributeEffect staminaEffect, AttributeEffect manaEffect)
			{
				this.healthEffect = healthEffect;
				this.staminaEffect = staminaEffect;
				this.manaEffect = manaEffect;
			}

		public int doHealthEffect(int health)
			{
				return healthEffect.runEffect(health);
			}

		public int doStaminaEffect(int stamina)
			{
				return staminaEffect.runEffect(stamina);
			}

		public int doManaEffect(int mana)
			{
				return manaEffect.runEffect(mana);
			}

		public void setName(String name)
			{
				this.name = name;
			}

		public String getName()
			{
				return name;
			}
	}
