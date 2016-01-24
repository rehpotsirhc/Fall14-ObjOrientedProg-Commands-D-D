package edu.usu.cs.command;

import java.util.*;


public class Invoker
	{
		private Queue<ICommand>	commands	= new LinkedList<ICommand>();
		private List<ICommand>	chain		= new ArrayList<ICommand>();
		private int				pointer		= -1;

		public void addCommand(ICommand c)
			{
				commands.add(c);
			}

		public void runCommand() throws NoSuchElementException
			{
				if (commands.size() > 0)
					{
						ICommand c = commands.remove();
						if (pointer >= 0) chain = chain.subList(0, pointer + 1);
						chain.add(c);
						pointer = chain.size() - 1;
						c.execute();
					} else
					{
						throw new NoSuchElementException();
					}
			}

		public void undo() throws NoSuchElementException
			{
				if (pointer >= 0 && pointer < chain.size())
					chain.get(pointer--).undo();
				else
					throw new NoSuchElementException();
			}

		public void redo() throws NoSuchElementException
			{
				if (pointer + 1 >= 0 && pointer + 1 < chain.size())
					chain.get(++pointer).execute();
				else
					throw new NoSuchElementException();
			}
	}
