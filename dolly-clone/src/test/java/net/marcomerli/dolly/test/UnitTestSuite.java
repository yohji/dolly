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

package net.marcomerli.dolly.test;

import java.text.DecimalFormat;

import net.marcomerli.dolly.ai.action.ActionComposeMessageTest;
import net.marcomerli.dolly.memory.DollyMemoryTest;
import net.marcomerli.dolly.memory.environment.EnvironmentConfigurationTest;
import net.marcomerli.dolly.memory.environment.EnvironmentSystemTest;
import net.marcomerli.dolly.memory.store.StoreCloneTest;
import net.marcomerli.dolly.memory.store.StoreHostsTest;
import net.marcomerli.dolly.memory.store.StoreMessageTest;
import net.marcomerli.dolly.system.HaltTest;
import net.marcomerli.dolly.system.daemon.ListenerTest;
import net.marcomerli.dolly.system.daemon.SenderTest;
import net.marcomerli.dolly.system.daemon.SyncronizerTest;
import net.marcomerli.dolly.system.model.CloneTest;
import net.marcomerli.dolly.system.network.MessageTest;

import org.apache.commons.lang.time.StopWatch;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	ActionComposeMessageTest.class,
	DollyMemoryTest.class,
	EnvironmentConfigurationTest.class,
	EnvironmentSystemTest.class,
	StoreCloneTest.class,
	StoreHostsTest.class,
	StoreMessageTest.class,
	HaltTest.class,
	ListenerTest.class,
	SenderTest.class,
	SyncronizerTest.class,
	CloneTest.class,
	MessageTest.class
})
public class UnitTestSuite extends TestCaseSupport {

	private static StopWatch stopWatch = new StopWatch();

	@BeforeClass
	public static void before()
	{
		print( "UnitTestSuite | Starting.." );
		stopWatch.start();
	}

	@AfterClass
	public static void after()
	{
		stopWatch.stop();
		
		DecimalFormat fmt = new DecimalFormat("#,###");
		printf( "UnitTestSuite | Completed in %s millsec", 
			fmt.format( stopWatch.getTime() ) );
	}
}
