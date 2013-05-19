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

public class ScrollableListTest extends TestCaseSupport {

	private static ScrollableList<String> list;

	@Before
	public void before() throws Exception
	{
		list = new ScrollableList<String>();
		list.add( "Terminal" );
		list.add( "Thunar" );
		list.add( "Mousepad" );
		list.add( "Nautilus" );
		list.add( "GEdit" );
		list.add( "Konqueror" );
		list.add( "Kopete" );
	}

	@Test
	public void testScrollableList()
	{
		assertNotNull( list );
		assertFalse( list.isEmpty() );
		assertNotNull( list.toString() );
	}

	@Test
	public void testScroll()
	{
		assertSame( "Terminal", list.next() );
		assertSame( "Thunar", list.next() );
		assertNotSame( "Thunar", list.prev() );
		assertSame( "Kopete", list.prev() );
	}
}
