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

package net.marcomerli.dolly.system.service;

import net.marcomerli.dolly.error.ErrorMemory;
import net.marcomerli.dolly.error.ErrorPersistence;
import net.marcomerli.dolly.kernel.Kernel;
import net.marcomerli.dolly.memory.DollyMemory;
import net.marcomerli.dolly.memory.persistence.Persistence;
import net.marcomerli.dolly.memory.persistence.PersistenceHandler;
import net.marcomerli.dolly.memory.store.StoreClone;
import net.marcomerli.dolly.memory.store.StoreHost;
import net.marcomerli.dolly.memory.store.StoreMessage;
import net.marcomerli.dolly.service.Service;
import net.marcomerli.dolly.system.Daemon;

public class ServiceSyncronizer implements Service<Daemon>, Persistence {

	public static void sync() throws ErrorMemory, ErrorPersistence
	{
		// Sync clones
		if ( !DollyMemory.isLockedClones() ) {
			StoreClone clones = DollyMemory.clones();
			if ( clones.isTouched() ) {
				PersistenceHandler.write( STORE_CLONE, clones );
				clones.setTouched( false );
			}
			Kernel.release( clones );
		}

		// Sync listener messages
		if ( !DollyMemory.isLockedMListener() ) {
			StoreMessage messages = DollyMemory.messagesListener();
			if ( messages.isTouched() ) {
				PersistenceHandler.write( STORE_MESSAGE_LISTENER, messages );
				messages.setTouched( false );
			}
			Kernel.release( messages );
		}

		// Sync sender messages
		if ( !DollyMemory.isLockedMSender() ) {
			StoreMessage messages = DollyMemory.messagesSender();
			if ( messages.isTouched() ) {
				PersistenceHandler.write( STORE_MESSAGE_SENDER, messages );
				messages.setTouched( false );
			}
			Kernel.release( messages );
		}

		// Sync hosts
		if ( !DollyMemory.isLockedHosts() ) {
			StoreHost hosts = DollyMemory.hosts();
			if ( hosts.isTouched() ) {
				PersistenceHandler.write( STORE_HOSTS, hosts );
				hosts.setTouched( false );
			}
			Kernel.release( hosts );
		}
	}

	private ServiceSyncronizer() {}
}
