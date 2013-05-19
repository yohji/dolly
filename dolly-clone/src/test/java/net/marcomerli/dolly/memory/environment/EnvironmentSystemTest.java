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

package net.marcomerli.dolly.memory.environment;

import static org.junit.Assert.*;
import net.marcomerli.dolly.memory.environment.EnvironmentSystem;
import net.marcomerli.dolly.memory.environment.EnvironmentSystemExt;
import net.marcomerli.dolly.system.Os;
import net.marcomerli.dolly.test.TestCaseSupport;

import org.junit.BeforeClass;
import org.junit.Test;

public class EnvironmentSystemTest extends TestCaseSupport {

	private static EnvironmentSystem information;
	
	@BeforeClass
	public static void beforeClass() throws Exception
	{
		information = new EnvironmentSystemExt();
	}

	@Test
	public void testLifeTime()
	{
		Long lifeTime = information.lifeTime();

		assertNotSame( "", lifeTime );
		assertEquals( lifeTime, information.lifeTime() );
	}

	@Test
	public void testHostName()
	{
		String host = information.hostName();

		assertNotSame( "", host );
		assertEquals( host, information.hostName() );
	}

	@Test
	public void testProfile()
	{
		Os os = information.os();

		assertNotSame( "", os.name() );
		assertEquals( os, information.os );
	}

	@Test
	public void testUptime()
	{
		Long uptime = information.uptime();

		assertNotSame( "", uptime );
		assertEquals( uptime, information.uptime() );
	}

	@Test
	public void testEnconding()
	{
		String encoding = information.encoding();

		assertNotSame( "", encoding );
		assertEquals( encoding, information.encoding() );
	}

	@Test
	public void testJavaHome()
	{
		String javaHome = information.javaHome();

		assertNotSame( "", javaHome );
		assertEquals( javaHome, information.javaHome() );
	}

	@Test
	public void testJavaVersion()
	{
		String javaVersion = information.javaVersion();

		assertNotSame( "", javaVersion );
		assertEquals( javaVersion, information.javaVersion() );
	}
}
