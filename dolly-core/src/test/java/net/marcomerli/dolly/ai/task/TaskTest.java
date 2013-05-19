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

package net.marcomerli.dolly.ai.task;

import static org.junit.Assert.*;
import net.marcomerli.dolly.ai.action.Action;
import net.marcomerli.dolly.test.TestCaseSupport;

import org.junit.Test;

public class TaskTest extends TestCaseSupport {

	@Test
	public void testAction()
	{
		T t = new T();
		t.register( new A(), new A() );
		t.register( new A() );

		assertEquals( 3, t.actions.size() );
	}

	@Test
	public void testTask()
	{
		T t = new T();
		t.register( new T(), new T() );

		assertEquals( 2, t.tasks.size() );
	}

	@Test
	public void testExecute()
	{
		try {
			T task = new T();
			task.execute();
		}
		catch ( Exception e ) {
			fail();
		}
	}
}

class T extends Task {

	@Override
	public void init() throws Exception
	{}

	@Override
	public void perform() throws Exception
	{}

	@Override
	public void end() throws Exception
	{}
}

class A extends Action {

	@Override
	public void init() throws Exception
	{}

	@Override
	public void perform() throws Exception
	{}

	@Override
	public void end() throws Exception
	{}
}
