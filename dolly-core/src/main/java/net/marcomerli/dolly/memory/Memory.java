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

package net.marcomerli.dolly.memory;

import java.io.File;

import net.marcomerli.dolly.error.Error;
import net.marcomerli.dolly.error.ErrorMemory;
import net.marcomerli.dolly.kernel.Kernel;
import net.marcomerli.dolly.memory.store.StoreDaemon;

public class Memory {

	protected static StoreDaemon daemonStore;
	protected static String location;

	public static StoreDaemon daemons() throws ErrorMemory
	{
		try {
			if ( daemonStore == null ) {
				daemonStore = new StoreDaemon();
				Kernel.lock( daemonStore );
				return daemonStore;
			}

			Kernel.waitFor( daemonStore );
			Kernel.lock( daemonStore );
		}
		catch ( Error e ) {
			throw new ErrorMemory( e );
		}

		return daemonStore;
	}

	public static boolean isLockedDaemons()
	{
		return ( daemonStore != null ) ? daemonStore.isLocked() : false;
	}

	public static String location()
	{
		return location;
	}

	public static void setLocation( String path )
	{
		path = path.trim();
		if ( !path.endsWith( File.separator ) )
			location = path + File.separator;
		else
			location = path;
	}
	
	protected Memory() {}
}
