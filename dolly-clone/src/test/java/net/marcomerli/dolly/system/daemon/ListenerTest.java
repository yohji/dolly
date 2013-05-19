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

package net.marcomerli.dolly.system.daemon;

import static org.junit.Assert.assertTrue;
import net.marcomerli.dolly.kernel.Kernel;
import net.marcomerli.dolly.memory.DollyMemory;
import net.marcomerli.dolly.memory.store.StoreDaemon;
import net.marcomerli.dolly.support.SupportNetwork;
import net.marcomerli.dolly.system.Daemon;
import net.marcomerli.dolly.test.TestCaseSupport;

import org.junit.After;
import org.junit.Test;

public class ListenerTest extends TestCaseSupport {

	private Integer pid;

	@Test
	public void testListener() throws Exception
	{
		pid = Daemon.start( DaemonMessageListener.class ).pid();

		StoreDaemon store = DollyMemory.daemons();
		assertTrue( store.isActive( pid ) );
		Kernel.release( store );

		assertTrue( SupportNetwork.scan( 
			"127.0.0.1", DollyMemory.configuration().port() ) );
	}

	@After
	public void after() throws Exception
	{
		Daemon.stop( pid );
	}
}
