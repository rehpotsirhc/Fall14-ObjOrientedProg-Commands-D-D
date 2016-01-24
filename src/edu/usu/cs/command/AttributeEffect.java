package edu.usu.cs.command;

public class AttributeEffect
	{
		private double	prodEffect;
		private int		sumEffect;

		public AttributeEffect(int sumEffect)
			{
				this.sumEffect = sumEffect;
				prodEffect = 1;
			}

		public AttributeEffect(double prodEffect)
			{
				this.prodEffect = prodEffect;
				this.sumEffect = 0;
			}

		public int runEffect(int attribute)
			{
				int value = (int) Math.floor((attribute + sumEffect) * prodEffect);
				if (value > 0)
					return value;
				else
					return 0;
			}
	}
