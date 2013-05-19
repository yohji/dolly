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

import static org.junit.Assert.*;
import net.marcomerli.dolly.support.SupportRandom;
import net.marcomerli.dolly.test.TestCaseSupport;

import org.junit.Test;

public class SupportRandomTest extends TestCaseSupport {

	private static final Long SIZE = 12L;

	@Test
	public void testNextUID()
	{
		Long uid = SupportRandom.nextUid();
		Long aUid = SupportRandom.nextUid();
		Long aaUid = SupportRandom.nextUid();
		Long aaaUid = SupportRandom.nextUid();
		Long aaaaUid = SupportRandom.nextUid();
		Long aaaaaUid = SupportRandom.nextUid();

		assertNotSame( uid, SupportRandom.nextUid() );
		assertNotSame( aUid, SupportRandom.nextUid() );
		assertNotSame( aaUid, SupportRandom.nextUid() );
		assertNotSame( aaaUid, SupportRandom.nextUid() );
		assertNotSame( aaaaUid, SupportRandom.nextUid() );
		assertNotSame( aaaaaUid, SupportRandom.nextUid() );
	}

	@Test
	public void testNextBytes()
	{
		byte[] bytes = SupportRandom.nextBytes( SIZE );
		byte[] aBytes = SupportRandom.nextBytes( SIZE );
		byte[] aaBytes = SupportRandom.nextBytes( SIZE );
		byte[] aaaBytes = SupportRandom.nextBytes( SIZE );
		byte[] aaaaBytes = SupportRandom.nextBytes( SIZE );
		byte[] aaaaaBytes = SupportRandom.nextBytes( SIZE );

		assertNotSame( bytes, SupportRandom.nextBytes( SIZE ) );
		assertNotSame( aBytes, SupportRandom.nextBytes( SIZE ) );
		assertNotSame( aaBytes, SupportRandom.nextBytes( SIZE ) );
		assertNotSame( aaaBytes, SupportRandom.nextBytes( SIZE ) );
		assertNotSame( aaaaBytes, SupportRandom.nextBytes( SIZE ) );
		assertNotSame( aaaaaBytes, SupportRandom.nextBytes( SIZE ) );
	}

	@Test
	public void testNextString()
	{
		String string = SupportRandom.nextString( SIZE );
		String aString = SupportRandom.nextString( SIZE );
		String aaString = SupportRandom.nextString( SIZE );
		String aaaString = SupportRandom.nextString( SIZE );
		String aaaaString = SupportRandom.nextString( SIZE );
		String aaaaaString = SupportRandom.nextString( SIZE );

		assertNotSame( string, SupportRandom.nextString( SIZE ) );
		assertNotSame( aString, SupportRandom.nextString( SIZE ) );
		assertNotSame( aaString, SupportRandom.nextString( SIZE ) );
		assertNotSame( aaaString, SupportRandom.nextString( SIZE ) );
		assertNotSame( aaaaString, SupportRandom.nextString( SIZE ) );
		assertNotSame( aaaaaString, SupportRandom.nextString( SIZE ) );
	}

	@Test
	public void testNextAddress()
	{
		String address = SupportRandom.nextAddress();
		String aAddress = SupportRandom.nextAddress();
		String aaAddress = SupportRandom.nextAddress();
		String aaaAddress = SupportRandom.nextAddress();
		String aaaaAddress = SupportRandom.nextAddress();
		String aaaaaAddress = SupportRandom.nextAddress();

		assertTrue( address.split( "\\." ).length == 4 );
		assertTrue( aAddress.split( "\\." ).length == 4 );
		assertTrue( aaAddress.split( "\\." ).length == 4 );
		assertTrue( aaaAddress.split( "\\." ).length == 4 );
		assertTrue( aaaaAddress.split( "\\." ).length == 4 );
		assertTrue( aaaaaAddress.split( "\\." ).length == 4 );

		assertNotSame( address, SupportRandom.nextAddress() );
		assertNotSame( aAddress, SupportRandom.nextAddress() );
		assertNotSame( aaAddress, SupportRandom.nextAddress() );
		assertNotSame( aaaAddress, SupportRandom.nextAddress() );
		assertNotSame( aaaaAddress, SupportRandom.nextAddress() );
		assertNotSame( aaaaaAddress, SupportRandom.nextAddress() );
	}

	@Test
	public void testNextPid()
	{
		Integer pid = SupportRandom.nextPid();
		Integer aPid = SupportRandom.nextPid();
		Integer aaPid = SupportRandom.nextPid();
		Integer aaaPid = SupportRandom.nextPid();
		Integer aaaaPid = SupportRandom.nextPid();
		Integer aaaaaPid = SupportRandom.nextPid();

		assertNotSame( pid, SupportRandom.nextPid() );
		assertNotSame( aPid, SupportRandom.nextPid() );
		assertNotSame( aaPid, SupportRandom.nextPid() );
		assertNotSame( aaaPid, SupportRandom.nextPid() );
		assertNotSame( aaaaPid, SupportRandom.nextPid() );
		assertNotSame( aaaaaPid, SupportRandom.nextPid() );
	}

	@Test
	public void testNextPort()
	{
		Integer port = SupportRandom.nextPort();
		Integer aPort = SupportRandom.nextPort();
		Integer aaPort = SupportRandom.nextPort();
		Integer aaaPort = SupportRandom.nextPort();
		Integer aaaaPort = SupportRandom.nextPort();
		Integer aaaaaPort = SupportRandom.nextPort();

		assertNotSame( port, SupportRandom.nextPort() );
		assertNotSame( aPort, SupportRandom.nextPort() );
		assertNotSame( aaPort, SupportRandom.nextPort() );
		assertNotSame( aaaPort, SupportRandom.nextPort() );
		assertNotSame( aaaaPort, SupportRandom.nextPort() );
		assertNotSame( aaaaaPort, SupportRandom.nextPort() );
	}

	@Test
	public void testNextPath()
	{
		String path = SupportRandom.nextPath( 5 );
		String aPath = SupportRandom.nextPath( 5 );
		String aaPath = SupportRandom.nextPath( 5 );
		String aaaPath = SupportRandom.nextPath( 5 );
		String aaaaPath = SupportRandom.nextPath( 5 );
		String aaaaaPath = SupportRandom.nextPath( 5 );

		assertNotSame( path, SupportRandom.nextPath( 5 ) );
		assertNotSame( aPath, SupportRandom.nextPath( 5 ) );
		assertNotSame( aaPath, SupportRandom.nextPath( 5 ) );
		assertNotSame( aaaPath, SupportRandom.nextPath( 5 ) );
		assertNotSame( aaaaPath, SupportRandom.nextPath( 5 ) );
		assertNotSame( aaaaaPath, SupportRandom.nextPath( 5 ) );
	}
}
