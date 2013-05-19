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

import java.util.List;

import net.marcomerli.dolly.kernel.Kernel;
import net.marcomerli.dolly.memory.DollyMemory;
import net.marcomerli.dolly.memory.store.StoreMessage;
import net.marcomerli.dolly.system.Daemon;
import net.marcomerli.dolly.system.model.Clone;
import net.marcomerli.dolly.system.network.Message;
import net.marcomerli.dolly.system.service.ServiceCommunication;

import org.apache.log4j.Logger;

public class DaemonMessageSender extends Daemon {

	private static final Logger logger = Logger.getLogger( DaemonMessageSender.class );
	private static final long serialVersionUID = 5323563295695632653L;

	@Override
	public void init() throws Exception
	{}

	@Override
	public void task() throws Exception
	{
		StoreMessage store = DollyMemory.messagesSender();
		if ( store.hasMessages() ) {

			Clone recipient = store.next();
			Kernel.release( store );

			if ( ServiceCommunication.alive( recipient ) ) {
				store = DollyMemory.messagesSender();
				List<Message> messages = store.values( recipient );
				Kernel.release( store );

				for ( Message message : messages ) {
					locked = true;
					ServiceCommunication.send( recipient, message );
					logger.debug( "(" + recipient.uid() + ") -> " + message );
				}

				store = DollyMemory.messagesSender();
				store.remove( recipient );
				Kernel.release( store );
				locked = false;
			}
		} else
			Kernel.release( store );
	}

	@Override
	public void kill() throws Exception
	{}
}
