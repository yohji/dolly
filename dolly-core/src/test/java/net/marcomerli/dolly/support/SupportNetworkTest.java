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

package net.marcomerli.dolly.support;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.util.List;

import net.marcomerli.dolly.error.ErrorSupport;
import net.marcomerli.dolly.support.SupportNetwork.PortRange;
import net.marcomerli.dolly.test.TestCaseSupport;

import org.junit.Test;

public class SupportNetworkTest extends TestCaseSupport {
	
	static final String HOSTNAME = "127.0.0.1";

	@Test
	public void testScan() throws ErrorSupport
	{
		List<Integer> scan = SupportNetwork.scan( HOSTNAME, PortRange.WELL_KNOWN_PORTS );
		print( scan );
		
		assertNotNull( scan );
		assertNotSame( 0, scan.size() );
	}

	@Test
	public void testPing() throws ErrorSupport
	{
		assertTrue( SupportNetwork.ping( HOSTNAME ) );
	}

	@Test
	public void testLocalAddress() throws ErrorSupport
	{
		String address = SupportNetwork.localAddress();
		String aAddress = SupportNetwork.localAddress();

		assertNotNull( address );
		assertNotNull( aAddress );
		assertEquals( address, aAddress );
		assertTrue( address.split( "\\." ).length == 4 );
	}
}
