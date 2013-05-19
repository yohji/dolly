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

package net.marcomerli.dolly.system.model;

import static org.junit.Assert.*;
import net.marcomerli.dolly.test.TestCaseSupport;

import org.junit.Before;
import org.junit.Test;

public class CloneTest extends TestCaseSupport {

	private Clone clone;

	@Before
	public void setup() throws Exception
	{
		clone = new Clone( 123L, "127.0.0.1", 0 );
	}

	@Test
	public void testClone()
	{
		Clone aClone = new Clone( clone.toString() );

		assertNotNull( clone );
		assertNotNull( aClone );
		assertEquals( clone, aClone );
	}

	@Test
	public void testEquals()
	{
		Clone aClone = new Clone();
		assertFalse( aClone.equals( clone ) );

		Clone aaClone = new Clone( 123L, "127.0.0.1", 0 );
		assertTrue( aaClone.equals( clone ) );
	}

	@Test
	public void testToString()
	{
		String toString = clone.toString();

		assertTrue( toString.indexOf( "|" ) != -1 );
		assertEquals( 3, toString.split( "\\|" ).length );
	}
}
