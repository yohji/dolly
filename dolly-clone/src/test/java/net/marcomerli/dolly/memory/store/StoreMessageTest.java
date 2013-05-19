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

package net.marcomerli.dolly.memory.store;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.util.List;

import net.marcomerli.dolly.support.SupportRandom;
import net.marcomerli.dolly.system.model.Clone;
import net.marcomerli.dolly.system.network.Message;
import net.marcomerli.dolly.system.network.Protocol;
import net.marcomerli.dolly.test.TestCaseSupport;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.JVM)
public class StoreMessageTest extends TestCaseSupport {

	private static StoreMessage store;
	private static Message message;
	private static Long uid;
	private static Clone clone;

	@BeforeClass	
	public static void setup() throws Exception
	{
		store = new StoreMessage();
		message = new Message( Protocol.UPGRADE ).setContent( gnuLicense.getBytes() );
		uid = SupportRandom.nextUid();
		clone = new Clone( uid, "", 0 );
	}

	@Test
	public void testStore()
	{
		store.store( clone, message );

		assertTrue( store.contains( clone ) );
	}

	@Test
	public void testReadMessage()
	{
		List<Message> messages = store.values( uid );

		assertNotNull( messages );
		assertNotSame( 0, messages.size() );
	}
}
