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

import java.util.List;

import net.marcomerli.dolly.kernel.Kernel;
import net.marcomerli.dolly.memory.DollyMemory;
import net.marcomerli.dolly.memory.store.StoreDaemon;
import net.marcomerli.dolly.system.daemon.DaemonSyncronizer;

import org.apache.log4j.Logger;

public class Halt implements Runnable {

	private static final Logger logger = Logger.getLogger( Halt.class );

	public void run()
	{
		try {
			StoreDaemon daemonStore = DollyMemory.daemons();
			List<Daemon> actives = daemonStore.actives();
			Kernel.release( daemonStore );

			for ( Daemon daemon : actives ) {
				if ( !daemon.name().equals( DaemonSyncronizer.class.getName() ) )
					Daemon.stop( daemon.pid() );
			}

			Daemon.stopAll( DaemonSyncronizer.class );
			logger.info( "Dolly halted. Uptime " + DollyMemory.system().uptime() );
		}
		catch ( Exception e ) {
			logger.error( "", e );
		}
	}
}
