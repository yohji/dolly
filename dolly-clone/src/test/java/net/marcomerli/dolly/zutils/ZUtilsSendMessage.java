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

package net.marcomerli.dolly.zutils;

import java.net.Socket;

import net.marcomerli.dolly.support.SupportIO;
import net.marcomerli.dolly.system.model.Clone;
import net.marcomerli.dolly.system.network.Message;
import net.marcomerli.dolly.system.network.Protocol;

import org.apache.log4j.Logger;

public class ZUtilsSendMessage implements Protocol {

	private static final Logger logger = Logger.getLogger( ZUtilsSendMessage.class );

	public static void main( String[] args )
	{
		try {
			Clone r = new Clone( 123L, "127.0.0.1", 45469 );
			Message m = new Message( TEST ).setContent( "MarcoMerli".getBytes() );
			send( r, m );

			logger.info( "Sended!" );
		}
		catch ( Exception e ) {
			logger.error( "", e );
		}
	}

	public static void send( Clone recipient, Message message ) throws Exception
	{
		Socket socket = new Socket( recipient.address(), recipient.port() );
		socket.shutdownInput();
		if ( socket.isConnected() && socket.isBound() )
			SupportIO.write( socket.getOutputStream(), message.toBytes() );

		socket.close();
	}
}
