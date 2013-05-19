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

package net.marcomerli.dolly.ai.step;

import static org.junit.Assert.*;
import net.marcomerli.dolly.error.ErrorAi;
import net.marcomerli.dolly.test.TestCaseSupport;

import org.junit.Test;

public class StepTest extends TestCaseSupport {

	@Test
	public void testStep()
	{
		Step s = new S();
		s.register( new S(), new S() );
		s.register( new S() );

		assertEquals( 3, s.steps.size() );
	}

	@Test
	public void testExecute()
	{
		Step s = new S();

		try {
			s.execute();
		}
		catch ( Exception e ) {
			fail();
		}
	}
}

class S extends Step {

	@Override
	public void execute() throws ErrorAi
	{}
}
