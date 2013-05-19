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

import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import net.marcomerli.dolly.kernel.Kernel;
import net.marcomerli.dolly.memory.DollyMemory;
import net.marcomerli.dolly.memory.store.StoreMessage;
import net.marcomerli.dolly.system.Daemon;
import net.marcomerli.dolly.system.network.Message;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

public class DaemonMessageListener extends Daemon {

	private static final Logger logger = Logger.getLogger( DaemonMessageListener.class );
	private static final long serialVersionUID = 5380108629374705906L;

	private ServerSocket listener;

	@Override
	public void init() throws Exception
	{
		listener = new ServerSocket( DollyMemory.configuration().port() );
	}

	@Override
	public void task() throws Exception
	{
		try {
			Socket socket = listener.accept();
			socket.shutdownOutput();

			byte[] buffer = IOUtils.toByteArray( socket.getInputStream() );
			if ( Message.validate( buffer ) ) {

				locked = true;
				Message message = Message.decode( buffer );
				logger.debug( "<- " + message );

				StoreMessage store = DollyMemory.messagesListener();
				store.store( message );
				Kernel.release( store );
			}

			socket.close();
			locked = false;

		}
		catch ( SocketException e ) {}
	}

	@Override
	public void kill() throws Exception
	{
		if ( !listener.isClosed() )
			listener.close();
	}
}
