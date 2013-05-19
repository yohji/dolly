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

import static org.junit.Assert.*;
import net.marcomerli.dolly.test.TestCaseSupport;

import org.junit.Test;

public class MessageTest extends TestCaseSupport {

	private static byte[] bytes;
	private static final Long UID = 999999999999999999L;

	@Test
	public void testContent()
	{
		Message message = new Message( UID, Protocol.BIRTH );
		message.setContent( gnuLicense.getBytes() );

		assertNotNull( message.content() );
	}

	@Test
	public void testMessage()
	{
		Message message = new Message( UID, Protocol.BIRTH );
		Message aMessage = new Message( UID, Protocol.UPGRADE );

		assertNotNull( message );
		assertNotNull( aMessage );
		assertNotSame( message.type, aMessage.type );
	}

	@Test
	public void testMessageCreate()
	{
		Message message = new Message( UID, Protocol.BIRTH );
		byte[] empty = message.toBytes();
		message.setContent( gnuLicense.getBytes() );
		bytes = message.toBytes();

		assertNotNull( empty );
		assertNotSame( UID, empty.length );
		assertNotSame( empty, bytes );
		assertTrue( bytes.length > empty.length );
	}

	@Test
	public void testMessageDecode()
	{
		Message msg = new Message( UID, Protocol.BIRTH ).setContent( gnuLicense.getBytes() );
		bytes = msg.toBytes();
		Message message = Message.decode( bytes );

		assertNotNull( message );
		assertEquals( bytes.length, message.toBytes().length );
	}

	@Test
	public void testType()
	{
		Message birth = new Message( UID, Protocol.BIRTH );
		Message hierarchy = new Message( UID, Protocol.HIERARCHY );
		Message test = new Message( UID, Protocol.TEST );
		Message upgrade = new Message( UID, Protocol.UPGRADE );
		Message file = new Message( UID, Protocol.FILE );
		Message proxy = new Message( UID, Protocol.PROXY );

		assertEquals( "BIRTH", birth.getType() );
		assertEquals( "HIERARCHY", hierarchy.getType() );
		assertEquals( "TEST", test.getType() );
		assertEquals( "UPGRADE", upgrade.getType() );
		assertEquals( "FILE", file.getType() );
		assertEquals( "PROXY", proxy.getType() );
	}

	@Test
	public void testUid()
	{
		Message message = new Message( UID, Protocol.BIRTH );
		message.setContent( gnuLicense.getBytes() );

		assertNotNull( message.uid() );
	}

	@Test
	public void testValidate()
	{
		Message message = new Message( UID, Protocol.BIRTH );
		message.setContent( gnuLicense.getBytes() );
		String malformed = Protocol.HEADER + UID + String.valueOf( 0x2A ) + "?";

		assertTrue( Message.validate( message.toBytes() ) );
		assertFalse( Message.validate( malformed.getBytes() ) );
	}
}
