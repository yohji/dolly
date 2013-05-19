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

package net.marcomerli.dolly.ai.task;

import java.util.LinkedList;
import java.util.List;

import net.marcomerli.dolly.ai.action.Action;
import net.marcomerli.dolly.error.ErrorAi;

public abstract class Task {

	protected List<Action> actions;
	protected List<Task> tasks;

	public Task() {

		actions = new LinkedList<Action>();
		tasks = new LinkedList<Task>();
	}

	public void execute() throws ErrorAi
	{
		try {
			init();
			for ( Task t : tasks )
				t.execute();

			perform();
			for ( Action action : actions )
				action.execute();

			end();
		}
		catch ( Exception e ) {
			throw new ErrorAi( e );
		}
	}

	public void register( Action... actions )
	{
		for ( Action a : actions )
			this.actions.add( a );
	}

	public void register( Task... tasks )
	{
		for ( Task t : tasks )
			this.tasks.add( t );
	}

	public abstract void init() throws Exception;

	public abstract void perform() throws Exception;

	public abstract void end() throws Exception;
}
