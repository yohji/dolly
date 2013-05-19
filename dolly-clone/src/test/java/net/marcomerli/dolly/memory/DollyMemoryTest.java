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

package net.marcomerli.dolly.memory;

import static org.junit.Assert.*;
import net.marcomerli.dolly.error.ErrorMemory;
import net.marcomerli.dolly.kernel.Kernel;
import net.marcomerli.dolly.memory.environment.EnvironmentConfiguration;
import net.marcomerli.dolly.memory.environment.EnvironmentSystem;
import net.marcomerli.dolly.memory.store.StoreClone;
import net.marcomerli.dolly.memory.store.StoreDaemon;
import net.marcomerli.dolly.memory.store.StoreHost;
import net.marcomerli.dolly.memory.store.StoreMessage;
import net.marcomerli.dolly.test.TestCaseSupport;

import org.junit.Test;

public class DollyMemoryTest extends TestCaseSupport {

	@Test
	public void testLocation()
	{
		String location = DollyMemory.location();

		assertNotNull( location );
	}

	@Test
	public void testConfiguration() throws ErrorMemory
	{
		EnvironmentConfiguration info = DollyMemory.configuration();

		assertNotNull( info );
	}

	@Test
	public void testSystem() throws ErrorMemory
	{
		EnvironmentSystem info = DollyMemory.system();

		assertNotNull( info );
	}

	@Test
	public void testDaemons() throws ErrorMemory
	{
		StoreDaemon store = DollyMemory.daemons();

		assertNotNull( store );
		Kernel.release( store );
	}

	@Test
	public void testClones() throws ErrorMemory
	{
		StoreClone store = DollyMemory.clones();

		assertNotNull( store );
		Kernel.release( store );
	}

	@Test
	public void testListener() throws ErrorMemory
	{
		StoreMessage store = DollyMemory.messagesListener();

		assertNotNull( store );
		Kernel.release( store );
	}

	@Test
	public void testSender() throws ErrorMemory
	{
		StoreMessage store = DollyMemory.messagesSender();

		assertNotNull( store );
		Kernel.release( store );
	}

	@Test
	public void testHosts() throws ErrorMemory
	{
		StoreHost store = DollyMemory.hosts();

		assertNotNull( store );
		Kernel.release( store );
	}
}
