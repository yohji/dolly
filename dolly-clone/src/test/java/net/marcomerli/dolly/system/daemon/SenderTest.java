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

import static org.junit.Assert.*;
import net.marcomerli.dolly.kernel.Kernel;
import net.marcomerli.dolly.memory.DollyMemory;
import net.marcomerli.dolly.memory.environment.EnvironmentConfiguration;
import net.marcomerli.dolly.memory.store.StoreDaemon;
import net.marcomerli.dolly.system.Daemon;
import net.marcomerli.dolly.system.model.Clone;
import net.marcomerli.dolly.system.network.Message;
import net.marcomerli.dolly.system.network.Protocol;
import net.marcomerli.dolly.system.service.ServiceCommunication;
import net.marcomerli.dolly.test.TestCaseSupport;

import org.junit.After;
import org.junit.Test;

public class SenderTest extends TestCaseSupport {

	protected static Integer pidSender;
	protected static Integer pidListener;

	@Test
	public void testSender() throws Exception
	{
		pidListener = Daemon.start( DaemonMessageListener.class ).pid();
		pidSender = Daemon.start( DaemonMessageSender.class ).pid();

		StoreDaemon daemons = DollyMemory.daemons();
		assertTrue( daemons.isActive( pidSender ) );
		Kernel.release( daemons );

		try {
			EnvironmentConfiguration conf = DollyMemory.configuration();
			Clone recipient = new Clone( conf.uid(), conf.address(), conf.port() );
			Message message = new Message( Protocol.TEST ).setContent( "Linux".getBytes() );
			ServiceCommunication.append( recipient, message );
		}
		catch ( Exception e ) {
			assertEquals( 0, 0 );
		}
	}

	@After
	public void after() throws Exception
	{
		Daemon.stop( pidSender );
		Daemon.stop( pidListener );
	}
}
