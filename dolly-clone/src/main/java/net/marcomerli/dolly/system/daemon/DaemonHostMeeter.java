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

package net.marcomerli.dolly.system.daemon;

import net.marcomerli.dolly.kernel.Kernel;
import net.marcomerli.dolly.memory.DollyMemory;
import net.marcomerli.dolly.memory.store.StoreHost;
import net.marcomerli.dolly.support.SupportNetwork;
import net.marcomerli.dolly.support.SupportRandom;
import net.marcomerli.dolly.system.Daemon;

import org.apache.log4j.Logger;

public class DaemonHostMeeter extends Daemon {

	private static final Logger logger = Logger.getLogger( DaemonHostMeeter.class );
	private static final long serialVersionUID = -3456293876932569235L;

	@Override
	public void init() throws Exception
	{}

	@Override
	public void task() throws Exception
	{
		String address = SupportRandom.nextAddress();
		if ( SupportNetwork.ping( address ) ) {
			logger.debug( "Meet " + address );

			locked = true;
			logger.debug( "Scanning " + address );

			StoreHost store = DollyMemory.hosts();
			store.storeAll( address, SupportNetwork.scan( address ) );
			Kernel.release( store );

			locked = false;
		}
	}

	@Override
	public void kill() throws Exception
	{}
}
