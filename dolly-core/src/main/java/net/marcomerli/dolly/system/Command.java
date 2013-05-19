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

package net.marcomerli.dolly.system;

public class Command {

	private String command;
	private Integer exitCode;
	private String output;

	public Command() {}

	public Command(String command) {
		this.command = command;
	}

	public String command()
	{
		return command;
	}

	public void setCommand( String command )
	{
		this.command = command;
	}

	public Integer exitCode()
	{
		return exitCode;
	}

	public void setExitCode( Integer returnCode )
	{
		this.exitCode = returnCode;
	}

	public String output()
	{
		return output;
	}

	public void setOutput( String output )
	{
		this.output = output;
	}

	public Boolean isSuccessful()
	{
		return ( exitCode == 0 );
	}
}
