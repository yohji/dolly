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

package net.marcomerli.dolly.support;

import java.io.File;

import net.marcomerli.dolly.error.ErrorSystem;
import net.marcomerli.dolly.system.Command;

import org.apache.commons.io.IOUtils;

public class SupportOs implements Support {

	public static boolean isAccessible( String directory ) throws ErrorSystem
	{
		boolean result = false;
		File path = new File( directory );
		if ( path.exists() && path.isDirectory() ) {
			try {
				File f = new File( directory + File.separator + "f" );
				f.createNewFile();
				f.delete();

				result = true;
			}
			catch ( Exception e ) {}
		}

		return result;
	}

	public static void execute( Command command ) throws ErrorSystem
	{
		try {
			Process p = Runtime.getRuntime().exec( command.command() );
			command.setOutput( IOUtils.toString( p.getInputStream() ) );
			command.setExitCode( p.waitFor() );
		}
		catch ( Exception e ) {
			throw new ErrorSystem(e);
		}
	}
	
	private SupportOs() {}
}
