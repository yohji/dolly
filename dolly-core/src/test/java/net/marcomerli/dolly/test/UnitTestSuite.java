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

import net.marcomerli.dolly.ai.action.ActionTest;
import net.marcomerli.dolly.ai.expression.ExpressionJavaTest;
import net.marcomerli.dolly.ai.expression.ExpressionPythonTest;
import net.marcomerli.dolly.ai.step.StepTest;
import net.marcomerli.dolly.ai.task.TaskTest;
import net.marcomerli.dolly.core.model.HierarchyTreeTest;
import net.marcomerli.dolly.core.model.MultiMapTest;
import net.marcomerli.dolly.core.model.ReverseMapTest;
import net.marcomerli.dolly.core.model.ScrollableListTest;
import net.marcomerli.dolly.kernel.module.ModuleTest;
import net.marcomerli.dolly.memory.MemoryTest;
import net.marcomerli.dolly.memory.persistence.PersistenceManagerTest;
import net.marcomerli.dolly.memory.store.StoreDaemonTest;
import net.marcomerli.dolly.support.SupportOsTest;
import net.marcomerli.dolly.support.SupportIOTest;
import net.marcomerli.dolly.support.SupportMathTest;
import net.marcomerli.dolly.support.SupportNetworkTest;
import net.marcomerli.dolly.support.SupportRandomTest;
import net.marcomerli.dolly.support.SupportStringTest;
import net.marcomerli.dolly.system.DaemonStaticTest;
import net.marcomerli.dolly.system.OsTest;

import org.apache.commons.lang.time.StopWatch;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	ActionTest.class,
	ExpressionJavaTest.class,
	ExpressionPythonTest.class,
	StepTest.class,
	TaskTest.class,
	HierarchyTreeTest.class,
	MultiMapTest.class,
	ReverseMapTest.class,
	ScrollableListTest.class,
	ModuleTest.class,
	MemoryTest.class,
	PersistenceManagerTest.class,
	StoreDaemonTest.class,
	SupportIOTest.class,
	SupportMathTest.class,
	SupportNetworkTest.class,
	SupportRandomTest.class,
	SupportStringTest.class,
	SupportOsTest.class,
	DaemonStaticTest.class,
	OsTest.class
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
