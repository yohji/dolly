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

import net.marcomerli.dolly.test.TestCaseSupport;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DaemonInstanceTest extends TestCaseSupport {

	public static Long value;

	private Daemon pDaemon;
	private Daemon cDaemon;

	@Before
	public void before() throws Exception
	{
		pDaemon = new P();
		Daemon.start( pDaemon );
		cDaemon = new C();
		Daemon.start( cDaemon );
	}

	@Test
	public void testExecution() throws Exception
	{}

	@After
	public void after() throws Exception
	{
		Daemon.stop( pDaemon.pid );
		Daemon.stop( cDaemon.pid );
	}
}

class P extends Daemon {

	private static final long serialVersionUID = 6108193753337577020L;

	@Override
	public void init() throws Exception
	{}

	@Override
	public void task() throws Exception
	{
		if ( DaemonInstanceTest.value == null ) {
			locked = true;
			DaemonInstanceTest.value = System.currentTimeMillis();
			locked = false;
		}
	}

	@Override
	public void kill() throws Exception
	{}
}

class C extends Daemon {

	private static final long serialVersionUID = 6108193753337577021L;

	@Override
	public void init() throws Exception
	{}

	@Override
	public void task() throws Exception
	{
		if ( DaemonInstanceTest.value != null ) {
			locked = true;
			System.out.println( DaemonInstanceTest.value );
			DaemonInstanceTest.value = null;
			locked = false;
		}
	}

	@Override
	public void kill() throws Exception
	{}
}
