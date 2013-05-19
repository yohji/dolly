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

package net.marcomerli.dolly.core.model;

import static org.junit.Assert.*;
import net.marcomerli.dolly.test.TestCaseSupport;

import org.junit.Before;
import org.junit.Test;

public class ReverseMapTest extends TestCaseSupport {

	private static ReverseMap<String, String> map;

	@Before
	public void before() throws Exception
	{
		map = new ReverseMap<String, String>();
		map.put( "Xfce4", "Thunar" );
		map.put( "Gnome", "Nautilus" );
		map.put( "Kde", "Konqueror" );
	}

	@Test
	public void testReverseMap()
	{
		assertNotNull( map );
		assertFalse( map.isEmpty() );
		assertNotNull( map.toString() );
	}

	@Test
	public void testGet()
	{
		String value = map.get( "Xfce4" );
		String aValue = map.get( "Kde" );

		assertEquals( "Xfce4", map.getKey( value ) );
		assertEquals( "Kde", map.getKey( aValue ) );
	}

	@Test
	public void testContains()
	{
		assertTrue( map.containsKey( "Xfce4" ) );
		assertTrue( map.containsKey( "Gnome" ) );
		assertTrue( map.containsKey( "Kde" ) );

		assertTrue( map.containsValue( "Thunar" ) );
		assertTrue( map.containsValue( "Nautilus" ) );
		assertTrue( map.containsValue( "Konqueror" ) );
	}
}
