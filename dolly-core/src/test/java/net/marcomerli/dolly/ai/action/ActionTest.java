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

package net.marcomerli.dolly.ai.action;

import static org.junit.Assert.*;
import net.marcomerli.dolly.test.TestCaseSupport;

import org.junit.Test;

public class ActionTest extends TestCaseSupport {

	@Test
	public void testArgument()
	{
		Action a = new A();
		a.add( "123", 83, 12.12 );

		assertEquals( "123", a.args.get( 0 ) );
		assertEquals( 83, a.args.get( 1 ) );
		assertEquals( 12.12, a.args.get( 2 ) );
	}

	@Test
	public void testAction()
	{
		Action a = new A();
		a.register( new A(), new A() );
		a.register( new A() );

		assertEquals( 3, a.actions.size() );
	}

	@Test
	public void testExecute()
	{
		Action a = new A();

		try {
			a.execute();
		}
		catch ( Exception e ) {
			fail();
		}
		assertNotNull( a.result() );
		assertEquals( "1-2-3", a.result() );
	}
}

class A extends Action {

	@Override
	public void init() throws Exception
	{}

	@Override
	public void perform() throws Exception
	{
		result = "1-2-3";
	}

	@Override
	public void end() throws Exception
	{}
}
