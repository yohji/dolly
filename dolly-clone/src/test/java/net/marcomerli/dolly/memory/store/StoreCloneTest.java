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

package net.marcomerli.dolly.memory.store;

import static org.junit.Assert.*;
import net.marcomerli.dolly.support.SupportRandom;
import net.marcomerli.dolly.system.model.Clone;
import net.marcomerli.dolly.test.TestCaseSupport;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class StoreCloneTest extends TestCaseSupport {

	private StoreClone store;
	private static Clone CLONE;
	private static Clone A_CLONE;
	private static Clone AA_CLONE;
	private static Clone AAA_CLONE;
	
	@BeforeClass
	public static void beforeClass() throws Exception
	{
		CLONE = new Clone( 1L, "34.46.124.235", 4578 );
		A_CLONE = new Clone( 11L, "56.236.87.236", 27457 );
		AA_CLONE = new Clone( 12L, "77.56.76.45", 6796 );
		AAA_CLONE = new Clone( 13L, "67.57.98.235", 5832 );	
	}

	@Before
	public void before() throws Exception
	{
		store = new StoreClone();
		store.setRoot( CLONE );
		store.store( CLONE.uid(), A_CLONE );
		store.store( CLONE.uid(), AA_CLONE );
		store.store( CLONE.uid(), AAA_CLONE );
	}

	@Test
	public void testContains()
	{
		assertTrue( store.contains( 1L ) );
		assertTrue( store.contains( 11L ) );
		assertTrue( store.contains( 12L ) );
		assertTrue( store.contains( 13L ) );
		assertFalse( store.contains( SupportRandom.nextUid() ) );
		assertEquals( 4, store.size() );
	}

	@Test
	public void testFind()
	{
		Clone c = store.find( 1L );
		Clone cc = store.find( 12L );
		Clone ccc = store.find( SupportRandom.nextUid() );

		assertNotNull( c );
		assertNotNull( cc );
		assertNull( ccc );
	}

	@Test
	public void testMerge()
	{
		StoreClone merge = new StoreClone();
		merge.setRoot( CLONE );
		merge.store( CLONE.uid(), new Clone( 11L, "34.124.54.23", 3456 ) );
		merge.store( CLONE.uid(), new Clone( 12L, "45.124.125.2", 34823 ) );
		merge.store( 11L, new Clone( 111L, "11.46.176.76", 6587 ) );
		merge.store( 111L, new Clone( 1111L, "43.67.212.78", 8973 ) );
		merge.store( 111L, new Clone( 1112L, "148.65.223.6", 7897 ) );
		merge.store( 1111L, new Clone( 11111L, "45.145.67.135", 26788 ) );
		merge.store( 1111L, new Clone( 11112L, "12.135.87.157", 5785 ) );
		merge.store( 12L, new Clone( 121L, "189.89.234.98", 14788 ) );
		merge.store( 12L, new Clone( 122L, "12.45.137.5", 43678 ) );
		merge.store( 121L, new Clone( 1211L, "67.78.23.67", 4213 ) );
		merge.store( 1211L, new Clone( 12111L, "212.57.125.76", 54845 ) );

		StoreClone render = StoreClone.render( merge.toString() );
		assertTrue( store.merge( render ) );
		assertTrue( store.contains( CLONE.uid() ) );
		assertTrue( store.contains( 11L ) );
		assertTrue( store.contains( 12L ) );
		assertTrue( store.contains( 111L ) );
		assertTrue( store.contains( 1111L ) );
		assertTrue( store.contains( 1112L ) );
		assertTrue( store.contains( 11111L ) );
		assertTrue( store.contains( 11112L ) );
		assertTrue( store.contains( 121L ) );
		assertTrue( store.contains( 122L ) );
		assertTrue( store.contains( 1211L ) );
		assertTrue( store.contains( 12111L ) );
		assertEquals( 16, store.size() );

		assertTrue( store.merge( render ) );
		assertEquals( 16, store.size() );
	}
}
