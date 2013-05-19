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
import net.marcomerli.dolly.error.ErrorSupport;
import net.marcomerli.dolly.memory.environment.EnvironmentConfiguration;
import net.marcomerli.dolly.memory.environment.EnvironmentConfigurationExt;
import net.marcomerli.dolly.system.model.Clone;
import net.marcomerli.dolly.test.TestCaseSupport;

import org.junit.BeforeClass;
import org.junit.Test;

public class EnvironmentConfigurationTest extends TestCaseSupport {

	private static EnvironmentConfiguration configuration;

	@BeforeClass
	public static void beforeClass() throws Exception
	{
		configuration = new EnvironmentConfigurationExt();
	}

	@Test
	public void testThisClone()
	{
		Clone clone = configuration.thisClone();

		assertNotSame( 0, clone.uid() );
		assertNotSame( "0.0.0.0", clone.address() );
		assertNotSame( 0, clone.port() );
	}

	@Test
	public void testCreator() throws ErrorSupport
	{
		EnvironmentConfigurationExt configuration = new EnvironmentConfigurationExt();
		configuration.setCreator( new Clone() );

		assertEquals( new Clone(), configuration.creator() );
	}

	@Test
	public void testHome()
	{
		String home = configuration.home();

		assertNotSame( "", home );
		assertEquals( home, configuration.home() );
	}

	@Test
	public void testMemory()
	{
		String memory = configuration.memory();

		assertNotSame( "", memory );
		assertEquals( memory, configuration.memory() );
	}
}
