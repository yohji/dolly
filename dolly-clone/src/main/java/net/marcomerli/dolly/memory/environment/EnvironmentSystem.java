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
import net.marcomerli.dolly.system.Os;

public abstract class EnvironmentSystem implements Environment {

	private static final long serialVersionUID = 2809855719024737572L;

	protected String encoding;
	protected String hostName;
	protected String javaHome;
	protected String javaVersion;
	protected Long lifeTime;
	protected Os os;
	protected transient Long uptime;

	public abstract String encoding();

	public abstract String hostName();

	public abstract String javaHome();

	public abstract String javaVersion();

	public abstract Long lifeTime();

	public abstract Os os();

	public abstract Long uptime();

	@Override
	public String toString()
	{
		StringBuilder toString = new StringBuilder();
		toString.append( "lifeTime: " + lifeTime() );
		toString.append( "\nuptime: " + uptime() );
		toString.append( "\nhostName: " + hostName() );
		toString.append( "\nprofile: " + os() );
		toString.append( "\nencoding: " + encoding() );
		toString.append( "\njavaHome: " + javaHome() );
		toString.append( "\njavaVersion: " + javaVersion() );

		return toString.toString();
	}
}
