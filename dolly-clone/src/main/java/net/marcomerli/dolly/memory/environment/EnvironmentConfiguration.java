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

package net.marcomerli.dolly.memory.environment;

import net.marcomerli.dolly.memory.environment.Environment;
import net.marcomerli.dolly.system.model.Clone;

public abstract class EnvironmentConfiguration implements Environment {

	private static final long serialVersionUID = 1199479332546382950L;

	protected Clone creator;
	protected String home;
	protected String memory;
	protected Clone thisClone;

	public abstract String address();

	public abstract Clone creator();

	public abstract String home();

	public abstract String memory();

	public abstract Integer port();

	public abstract Clone thisClone();

	public abstract Long uid();

	@Override
	public String toString()
	{
		StringBuilder toString = new StringBuilder();
		toString.append( "this: " + thisClone );
		toString.append( "\ncreator: " + creator() );
		toString.append( "\nhome: " + home() );
		toString.append( "\nmemory: " + memory() );

		return toString.toString();
	}
}
