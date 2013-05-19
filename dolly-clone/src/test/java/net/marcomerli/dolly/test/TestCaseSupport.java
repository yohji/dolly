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

package net.marcomerli.dolly.test;

import java.io.File;

import net.marcomerli.dolly.memory.Memory;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

public abstract class TestCaseSupport {

	protected static String gnuLicense;
	private static boolean system_setup = false;
	static {
		gnuLicense =
			"This program is free software; you can redistribute it and/or modify it \n"
				+ "under the terms of the GNU General Public License as published by \n"
				+ "the Free Software Foundation; either version 2 of the License, or \n" 
				+ "(at your option) any later version. \n"
				+ "This program is distributed in the hope that it will be useful, \n"
				+ "but WITHOUT ANY WARRANTY; without even the implied warranty of \n"
				+ "MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the \n" 
				+ "GNU General Public License for more details. \n"
				+ "You should have received a copy of the GNU General Public License \n"
				+ "along with this program; if not, write to the Free Software Foundation, \n"
				+ "Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.";
	}

	public TestCaseSupport() {

		if ( ! system_setup ) {
			Setup.loadMemory();
			system_setup = true;
		}
	}

	protected static void print( Object object )
	{
		getLogger().debug( object );
	}
	
	protected static void printf( String format, Object... object )
	{
		getLogger().debug( String.format( format + "\n", object ) );
	}
	
	protected static void printe( Throwable e )
	{
		getLogger().error( e );
	}

	protected static Logger getLogger()
	{
		return Logger.getRootLogger();
	}

	protected static Logger getLogger( Class<? extends TestCaseSupport> getClass )
	{
		return Logger.getLogger( getClass );
	}

	private static class Setup extends ClassLoader {

		protected static void loadMemory()
		{
			File target = new File( "target" );
			if ( ! target.exists() )
				target.mkdir();

			try {
				File mem = new File( "target" + File.separator + "dolly_memory");
				if ( mem.exists() )
					FileUtils.deleteDirectory( mem );
				mem.mkdir();

				Memory.setLocation( mem.getAbsolutePath() );
			}
			catch ( Exception e ) {}
		}
	}
}
