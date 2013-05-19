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

package net.marcomerli.dolly.system.network;

import java.io.Serializable;
import java.lang.reflect.Field;

import net.marcomerli.dolly.error.ErrorMemory;
import net.marcomerli.dolly.memory.DollyMemory;

import org.apache.log4j.Logger;

public class Message implements Protocol, Serializable {

	private static final Logger logger = Logger.getLogger( Message.class );
	private static final long serialVersionUID = -3676913849726440931L;

	public static Message decode( final byte[] message )
	{
		return new Message( message );
	}

	public static boolean validate( final byte[] message )
	{
		boolean result = false;

		if ( message != null && message.length > 20 ) {
			if ( message[0] == (byte) HEADER ) {
				try {
					for ( Field field : Protocol.class.getFields() ) {
						if ( message[19] == field.getByte( field ) ) {
							String buffer = new String( message );
							buffer = buffer.substring( 1, 18 );
							Long.valueOf( buffer );

							result = true;
							break;
						}
					}
				}
				catch ( Exception e ) {}
			}
		}

		return result;
	}
	private byte[] content;

	protected byte type;

	private byte[] uid;

	public Message(byte type) throws ErrorMemory {

		uid = new byte[18];
		uid = String.valueOf( DollyMemory.configuration().uid() ).getBytes();

		this.type = type;
		content = new byte[0];
	}

	private Message(byte[] message) {

		uid = new byte[18];
		for ( int i = 1 ; i < 19 ; i++ )
			uid[i - 1] = message[i];

		this.type = message[19];

		content = new byte[message.length - 20];
		for ( int i = 20 ; i < message.length ; i++ )
			content[i - 20] = message[i];
	}

	public Message(Long uid, byte type) {

		this.uid = new byte[18];
		this.uid = String.valueOf( uid ).getBytes();

		this.type = type;
		content = new byte[0];
	}

	public byte[] content()
	{
		return content;
	}

	public String getContent()
	{
		return new String( content() );
	}

	public String getType()
	{
		String name = null;
		try {
			for ( Field field : Protocol.class.getFields() ) {
				if ( type == field.getByte( field ) ) {
					name = field.getName();
					break;
				}
			}
		}
		catch ( Exception e ) {
			logger.error( "", e );
		}

		return name;
	}

	public Message setContent( byte[] bytes )
	{
		content = bytes;
		return this;
	}

	public Integer size()
	{
		return content.length + 20;
	}

	public byte[] toBytes()
	{
		byte[] message = new byte[size()];
		message[0] = HEADER;

		for ( int i = 0 ; i < uid.length ; i++ )
			message[i + 1] = uid[i];

		message[19] = type;

		for ( int i = 0 ; i < content.length ; i++ )
			message[i + 20] = content[i];

		return message;
	}

	@Override
	public String toString()
	{
		StringBuilder toString = new StringBuilder();
		toString.append( uid() + "|" );
		toString.append( getType() + "\t|" );
		toString.append( getContent() );

		return toString.toString();
	}

	public byte type()
	{
		return type;
	}

	public Long uid()
	{
		return new Long( new String( uid ) );
	}

	public boolean validate()
	{
		return validate( toBytes() );
	}
}
