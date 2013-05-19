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

import net.marcomerli.dolly.ai.expression.Expression;
import net.marcomerli.dolly.ai.task.Task;
import net.marcomerli.dolly.error.ErrorAi;

public class StepIf extends Step {

	protected Expression expression;
	protected Task ifTask;
	protected Task elseTask;

	public StepIf(Expression expression, Task task) {

		this( expression, task, null );
	}

	public StepIf(Expression expression, Task ifTask, Task elseTask) {

		this.expression = expression;
		this.ifTask = ifTask;
		this.elseTask = elseTask;
	}

	public void execute() throws ErrorAi
	{
		if ( expression.value() )
			ifTask.execute();

		else {
			if ( elseTask != null )
				elseTask.execute();
		}
	}
}
