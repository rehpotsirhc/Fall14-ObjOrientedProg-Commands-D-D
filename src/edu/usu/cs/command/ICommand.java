package edu.usu.cs.command;

import java.util.NoSuchElementException;

public interface ICommand
	{
		void execute();
		void undo() throws NoSuchElementException;
	}
