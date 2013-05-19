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

import java.net.ConnectException;
import java.net.Socket;

import net.marcomerli.dolly.error.ErrorMemory;
import net.marcomerli.dolly.kernel.Kernel;
import net.marcomerli.dolly.memory.DollyMemory;
import net.marcomerli.dolly.memory.store.StoreMessage;
import net.marcomerli.dolly.service.Service;
import net.marcomerli.dolly.support.SupportIO;
import net.marcomerli.dolly.system.Daemon;
import net.marcomerli.dolly.system.model.Clone;
import net.marcomerli.dolly.system.network.Message;

import org.apache.log4j.Logger;

public class ServiceCommunication implements Service<Daemon> {

	private static final Logger logger = Logger.getLogger( ServiceCommunication.class );

	public static void append( Clone recipient, Message message ) throws ErrorMemory
	{
		StoreMessage messages = DollyMemory.messagesSender();
		messages.store( recipient, message );
		Kernel.release( messages );
	}

	public static boolean alive( Clone host )
	{
		boolean send = false;
		try {
			Socket socket = new Socket( host.address(), host.port() );
			if ( socket.isConnected() && socket.isBound() ) {
				socket.close();
				send = true;
			}
		}
		catch ( Exception e ) {}

		return send;
	}

	public static boolean send( Clone recipient, Message message )
	{
		boolean send = false;
		try {
			Socket socket = new Socket( recipient.address(), recipient.port() );
			socket.shutdownInput();

			if ( !socket.isClosed() && socket.isConnected() && socket.isBound() ) {
				SupportIO.write( socket.getOutputStream(), message.toBytes() );
				socket.close();
				send = true;
			}
		}
		catch ( ConnectException e ) {}
		catch ( Exception e ) {
			logger.error( "", e );
		}

		return send;
	}
}
