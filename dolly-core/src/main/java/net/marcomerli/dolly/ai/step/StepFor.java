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

package net.marcomerli.dolly.ai.step;

import java.util.LinkedList;
import java.util.List;

import net.marcomerli.dolly.ai.task.Task;
import net.marcomerli.dolly.error.ErrorAi;

public class StepFor extends Step {

	protected List<Task> tasks;

	public StepFor(Task... tasks) {

		this.tasks = new LinkedList<Task>();
		for ( Task t : tasks )
			this.tasks.add( t );
	}

	public void execute() throws ErrorAi
	{
		for ( Task t : tasks )
			t.execute();
	}
}
