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

package net.marcomerli.dolly.ai.action;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import net.marcomerli.dolly.system.model.Clone;
import net.marcomerli.dolly.system.network.Message;
import net.marcomerli.dolly.system.network.Protocol;
import net.marcomerli.dolly.test.TestCaseSupport;

import org.junit.Test;

public class ActionComposeMessageTest extends TestCaseSupport {

	@Test
	public void testComposeFile() throws Exception
	{
		Action a = new ActionComposeFileMessage( "pom.xml" );

		try {
			a.execute();
		}
		catch ( Exception e ) {
			assertEquals( 0, 0 );
		}

		Message message = (Message) a.result;
		assertTrue( Message.validate( message.toBytes() ) );
		assertTrue( message.type() == Protocol.FILE );
	}

	@Test
	public void testComposeProxy() throws Exception
	{
		Clone proxy = new Clone( 2L, "127.0.0.1", 2 );
		Message send = new Message( 1L, Protocol.TEST );
		send.setContent( new String( "hello" ).getBytes() );
		Action a = new ActionComposeProxyMessage( proxy, send );

		try {
			a.execute();
		}
		catch ( Exception e ) {
			assertEquals( 0, 0 );
		}

		Message message = (Message) a.result;
		assertTrue( Message.validate( message.toBytes() ) );
		assertTrue( message.type() == Protocol.PROXY );
	}
}
