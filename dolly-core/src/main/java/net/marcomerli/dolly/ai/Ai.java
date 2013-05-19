/**
 *  Copyright (c) 2007 Marco Merli <yohji@marcomerli.net>
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software Foundation,
 *  Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package net.marcomerli.dolly.ai;

import java.util.LinkedList;
import java.util.List;

import net.marcomerli.dolly.ai.step.Step;
import net.marcomerli.dolly.error.ErrorAi;

import org.python.core.Py;
import org.python.util.PythonInterpreter;

import bsh.Interpreter;

public abstract class Ai {

	protected static Interpreter javaInterpreter;
	protected static PythonInterpreter pythonInterpreter;
	
	protected List<Step> steps;
	
	public Ai() {

		steps = new LinkedList<Step>();
	}

	public final void register( Step... steps )
	{
		for ( Step s : steps )
			this.steps.add( s );
	}

	public final void vivificate() throws ErrorAi
	{
		for ( Step s : steps )
			s.execute();
	}

	public abstract void setup() throws ErrorAi;

	public abstract void behaviour() throws ErrorAi;
	
	public static Interpreter javaInterpreter()
	{
		if ( javaInterpreter == null )
			javaInterpreter = new Interpreter();

		return javaInterpreter;
	}

	public static PythonInterpreter pythonInterpreter()
	{
		if ( pythonInterpreter == null ) {
			Py.initPython();
			pythonInterpreter = new PythonInterpreter();
		}

		return pythonInterpreter;
	}
}
