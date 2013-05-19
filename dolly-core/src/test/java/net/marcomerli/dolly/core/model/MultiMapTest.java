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

import java.util.Collection;
import java.util.LinkedList;

import net.marcomerli.dolly.core.model.MultiMap;
import net.marcomerli.dolly.test.TestCaseSupport;

import org.junit.Before;
import org.junit.Test;

public class MultiMapTest extends TestCaseSupport {

	private static MultiMap<String, String> map;

	@Before
	public void before() throws Exception
	{
		map = new MultiMap<String, String>();
		map.put( "Xfce4", "Terminal" );
		map.put( "Xfce4", "Thunar" );
		map.put( "Xfce4", "Mousepad" );
		map.put( "Gnome", "Nautilus" );
		map.put( "Gnome", "GEdit" );
		map.put( "Kde", "Konqueror" );
		map.put( "Kde", "Kopete" );
	}

	@Test
	public void testMultiMap()
	{
		assertNotNull( map );
		assertFalse( map.isEmpty() );
		assertNotNull( map.toString() );
	}

	@Test
	public void testGet()
	{
		Collection<String> collection = map.get( "Xfce4" );

		assertEquals( 3, map.keySet().size() );
		assertEquals( 7, map.values().size() );
		assertEquals( 3, collection.size() );
	}

	@Test
	public void testContains()
	{
		assertTrue( map.containsKey( "Xfce4" ) );
		assertTrue( map.containsKey( "Gnome" ) );
		assertTrue( map.containsKey( "Kde" ) );

		Collection<String> values = new LinkedList<String>();
		values.add( "Terminal" );
		values.add( "Nautilus" );
		values.add( "Konqueror" );

		assertTrue( map.containsValue( values ) );
	}

	@Test
	public void testPutAll()
	{
		Collection<String> collection = new LinkedList<String>();
		collection.add( "K3b" );
		collection.add( "Amarok" );
		collection.add( "Kdevelop" );

		map.putAll( "Kde", collection );

		assertEquals( 5, map.get( "Kde" ).size() );
		assertEquals( 10, map.values().size() );
	}
}
