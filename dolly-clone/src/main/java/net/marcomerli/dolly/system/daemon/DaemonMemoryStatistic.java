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
import net.marcomerli.dolly.memory.persistence.Persistence;
import net.marcomerli.dolly.memory.store.StoreClone;
import net.marcomerli.dolly.memory.store.StoreHost;
import net.marcomerli.dolly.memory.store.StoreMessage;
import net.marcomerli.dolly.system.Daemon;

import org.apache.log4j.Logger;

public class DaemonMemoryStatistic extends Daemon implements Persistence {

	private static final Logger logger = Logger.getLogger( DaemonMemoryStatistic.class );
	private static final long serialVersionUID = -2347569346952365923L;

	@Override
	public void init() throws Exception
	{}

	@Override
	public void task() throws Exception
	{
		locked = true;

		// Clone store
		if ( !DollyMemory.isLockedClones() ) {
			StoreClone storeClone = DollyMemory.clones();
			logger.info( "clones: " + storeClone );
			Kernel.release( storeClone );
		}

		// Message listener store
		if ( !DollyMemory.isLockedMListener() ) {
			StoreMessage storeMessage = DollyMemory.messagesListener();
			logger.info( "messagesListener: " + storeMessage );
			Kernel.release( storeMessage );
		}

		// Message sender store
		if ( !DollyMemory.isLockedMSender() ) {
			StoreMessage storeMessage = DollyMemory.messagesSender();
			logger.info( "messagesSender: " + storeMessage );
			Kernel.release( storeMessage );
		}

		// Hosts store
		if ( !DollyMemory.isLockedHosts() ) {
			StoreHost storeHost = DollyMemory.hosts();
			logger.info( "hosts: " + storeHost );
			Kernel.release( storeHost );
		}

		locked = false;
	}

	@Override
	public void kill() throws Exception
	{}
}
