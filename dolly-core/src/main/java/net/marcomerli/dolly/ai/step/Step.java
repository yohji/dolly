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

import net.marcomerli.dolly.error.ErrorAi;

public abstract class Step {

	protected List<Step> steps;

	public Step() {

		steps = new LinkedList<Step>();
	}

	public void register( Step... steps )
	{
		for ( Step s : steps )
			this.steps.add( s );
	}

	public abstract void execute() throws ErrorAi;
}
