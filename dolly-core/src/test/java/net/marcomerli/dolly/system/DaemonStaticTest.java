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

package net.marcomerli.dolly.system;

import static org.junit.Assert.*;
import net.marcomerli.dolly.kernel.Kernel;
import net.marcomerli.dolly.memory.Memory;
import net.marcomerli.dolly.memory.store.StoreDaemon;
import net.marcomerli.dolly.system.Daemon;
import net.marcomerli.dolly.test.TestCaseSupport;

import org.junit.Test;

public class DaemonStaticTest extends TestCaseSupport {

	protected static Integer pid;

	@Test
	public void testStart() throws Exception
	{
		Daemon daemon = Daemon.start( D.class );
		pid = daemon.pid();

		assertNotNull( daemon );
		StoreDaemon store = Memory.daemons();
		assertTrue( store.isActive( pid ) );
		Kernel.release( store );
	}

	@Test
	public void testStop() throws Exception
	{
		Daemon daemon = Daemon.stop( pid );

		assertEquals( pid, daemon.pid() );
		StoreDaemon store = Memory.daemons();
		assertFalse( store.isActive( pid ) );
		Kernel.release( store );
	}

	@Test
	public void testStopAll() throws Exception
	{
		Daemon.start( D.class );
		Daemon.start( D.class );
		Daemon.start( D.class );

		Daemon.stopAll( D.class );

		StoreDaemon store = Memory.daemons();
		assertTrue( store.actives().isEmpty() );
		Kernel.release( store );
	}
}

class D extends Daemon {

	private static final long serialVersionUID = 6108193753337577020L;

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
