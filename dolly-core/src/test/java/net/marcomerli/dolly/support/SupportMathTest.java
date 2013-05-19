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

package net.marcomerli.dolly.support;

import static org.junit.Assert.*;
import net.marcomerli.dolly.test.TestCaseSupport;

import org.junit.Test;

public class SupportMathTest extends TestCaseSupport {

	@Test
	public void testFatt() throws Exception
	{
		assertNotNull( SupportMath.fatt( 0 ) );
		assertNotNull( SupportMath.fatt( 12 ) );
		assertTrue( SupportMath.fatt( 0 ).equals( 1L ) );
		assertTrue( SupportMath.fatt( 1 ).equals( 1L ) );
		assertTrue( SupportMath.fatt( 12 ).equals( 479001600L ) );
	}

	@Test
	public void testNPk() throws Exception
	{
		assertNotNull( SupportMath.nPk( 12, 4 ) );
		assertTrue( SupportMath.nPk( 12, 4 ).equals( 11880L ) );
	}

	@Test
	public void testNCk() throws Exception
	{
		assertNotNull( SupportMath.nCk( 12, 4 ) );
		assertTrue( SupportMath.nCk( 12, 4 ).equals( 495L ) );
	}
}
