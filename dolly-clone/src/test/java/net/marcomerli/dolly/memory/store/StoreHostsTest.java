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

import static org.junit.Assert.*;
import net.marcomerli.dolly.support.SupportRandom;
import net.marcomerli.dolly.test.TestCaseSupport;

import org.junit.Before;
import org.junit.Test;

public class StoreHostsTest extends TestCaseSupport {

	private StoreHost store;
	private String addrs;
	private String aAddrs;
	private String aaAddrs;
	private Integer port;
	private Integer aPort;
	private Integer aaPort;

	@Before
	public void before()
	{
		store = new StoreHost();
		addrs = SupportRandom.nextAddress();
		aAddrs = SupportRandom.nextAddress();
		aaAddrs = SupportRandom.nextAddress();
		port = SupportRandom.nextPort();
		aPort = SupportRandom.nextPort();
		aaPort = SupportRandom.nextPort();
	}

	@Test
	public void testStore()
	{
		store.store( addrs, port );
		store.store( aAddrs, aPort );
		store.store( aaAddrs, aaPort );

		assertTrue( store.contains( addrs ) );
		assertTrue( store.contains( aAddrs ) );
		assertTrue( store.contains( aaAddrs ) );
	}
}
