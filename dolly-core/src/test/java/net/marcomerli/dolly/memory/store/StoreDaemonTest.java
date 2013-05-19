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
import static org.junit.Assert.assertTrue;
import net.marcomerli.dolly.support.SupportRandom;
import net.marcomerli.dolly.system.Daemon;
import net.marcomerli.dolly.test.TestCaseSupport;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.JVM)
public class StoreDaemonTest extends TestCaseSupport {

	private static StoreDaemon store;
	private static Integer pid;
	private static Integer aPid;

	@BeforeClass
	public static void beforeClass() throws Exception
	{	
		store = new StoreDaemon();		
		pid = SupportRandom.nextPid();
		aPid = SupportRandom.nextPid();		
	}

	@Test
	public void testStoreDaemon()
	{
		store.store( pid, new D() );
		store.store( aPid, new D() );

		assertTrue( store.contains( pid ) );
		assertTrue( store.contains( aPid ) );
	}

	@Test
	public void testReadDaemon()
	{
		Daemon aDaemon = store.values( pid );
		Thread thread = store.thread( pid );

		assertNotNull( aDaemon );
		assertNotNull( thread );
	}

	@Test
	public void testReadDaemonInfo()
	{
		Long uptime = store.uptime( pid );

		assertNotNull( uptime );
	}
}

class D extends Daemon {

	private static final long serialVersionUID = 3245693256923746592L;

	@Override
	public void init() throws Exception
	{}

	@Override
	public void task() throws Exception
	{}

	@Override
	public void kill() throws Exception
	{}
}
