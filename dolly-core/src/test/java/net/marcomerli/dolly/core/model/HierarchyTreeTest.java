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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Set;

import net.marcomerli.dolly.test.TestCaseSupport;

import org.junit.Before;
import org.junit.Test;

public class HierarchyTreeTest extends TestCaseSupport {

	private HierarchyTree<Integer, String> tree;

	@Before
	public void before() throws Exception
	{
		tree = new HierarchyTree<Integer, String>();
		tree.setRoot( 1, "ORHVSUVHOWRUVHO" );
		tree.put( 1, 11, "EWIVHOWEUVHWO" );
		tree.put( 1, 12, "OBIHWROBIHROW" );
		tree.put( 1, 13, "RBHRIBHOWWRTB" );
		tree.put( 11, 111, "OEHBOEVHWEVYHWV" );
		tree.put( 11, 112, "WIHOWGHOEGHOWEG" );
		tree.put( 111, 1111, "BHWROUBHOWBHOWRTUHB" );
		tree.put( 111, 1112, "RIBHORBHOWBHOBHOIUH" );
		tree.put( 12, 121, "LDBEIBRPBWNPBNW" );
		tree.put( 12, 122, "RTBPORJTBITBJPE" );
		tree.put( 121, 1211, "RUBHROBHROBHIBHBUH" );
		tree.put( 121, 1212, "WRTBHWROBUWRHBOHBO" );
		tree.put( 13, 131, "WEGIHWOEGHOGWEOHIUH" );
		tree.put( 131, 1311, "IHROBHRHBWOIBHWROBH" );
	}

	@Test
	public void testContains()
	{
		assertTrue( tree.contains( 1 ) );
		assertTrue( tree.contains( 11 ) );
		assertTrue( tree.contains( 111 ) );
		assertTrue( tree.contains( 1111 ) );
		assertTrue( tree.contains( 12 ) );
		assertTrue( tree.contains( 121 ) );
		assertTrue( tree.contains( 1211 ) );
		assertTrue( tree.contains( 13 ) );
		assertTrue( tree.contains( 131 ) );
		assertTrue( tree.contains( 1311 ) );
		assertFalse( tree.contains( 2 ) );
		assertFalse( tree.contains( 14 ) );
		assertFalse( tree.contains( 114 ) );
		assertFalse( tree.contains( 1114 ) );

		assertNotSame( 0, tree.size() );
		assertEquals( 14, tree.size() );
	}

	@Test
	public void testGet()
	{
		assertEquals( "ORHVSUVHOWRUVHO", tree.get( 1 ) );
		assertEquals( "EWIVHOWEUVHWO", tree.get( 11 ) );
		assertEquals( "OEHBOEVHWEVYHWV", tree.get( 111 ) );
		assertEquals( "BHWROUBHOWBHOWRTUHB", tree.get( 1111 ) );
		assertEquals( "OBIHWROBIHROW", tree.get( 12 ) );
		assertEquals( "LDBEIBRPBWNPBNW", tree.get( 121 ) );
		assertEquals( "RUBHROBHROBHIBHBUH", tree.get( 1211 ) );
		assertEquals( "RBHRIBHOWWRTB", tree.get( 13 ) );
		assertEquals( "WEGIHWOEGHOGWEOHIUH", tree.get( 131 ) );
		assertEquals( "IHROBHRHBWOIBHWROBH", tree.get( 1311 ) );
		assertNull( tree.get( 2 ) );
		assertNull( tree.get( 14 ) );
		assertNull( tree.get( 114 ) );
		assertNull( tree.get( 1114 ) );
	}

	@Test
	public void testKeyValues()
	{
		Set<Integer> keySet = tree.keySet();
		List<String> values = tree.values();

		assertNotNull( keySet );
		assertTrue( keySet.contains( 1 ) );
		assertTrue( keySet.contains( 1112 ) );
		assertEquals( tree.size(), keySet.size() );
		assertNotNull( values );
		assertTrue( values.contains( "ORHVSUVHOWRUVHO" ) );
		assertTrue( values.contains( "RIBHORBHOWBHOBHOIUH" ) );
		assertEquals( tree.size(), values.size() );
	}

	@Test
	public void testMerge()
	{
		HierarchyTree<Integer, String> merge = new HierarchyTree<Integer, String>();
		merge.setRoot( 112, "IUHBOSJBHSDOFUBHSODB" );
		merge.put( 112, 1121, "AVBOADFHBODFBHSDOFBH" );
		merge.put( 112, 1122, "STIBUHWIBHWRTIBUHOIO" );
		merge.put( 1121, 11211, "OSBIHSDOFBHSODFBHOHOI" );
		merge.put( 1121, 11212, "RTIHUROHROBHOBUHUOIBH" );
		String image = tree.toString();

		assertTrue( tree.merge( merge ) );
		assertTrue( tree.contains( 1121 ) );
		assertTrue( tree.contains( 1122 ) );
		assertTrue( tree.contains( 11211 ) );
		assertTrue( tree.contains( 11212 ) );
		assertEquals( 18, tree.size() );

		assertTrue( tree.merge( merge ) );
		assertEquals( 18, tree.size() );
		assertNotSame( image, tree.toString() );
	}

	@Test
	public void testRemove()
	{
		assertTrue( tree.remove( 13 ) );
		assertNull( tree.get( 13 ) );
		assertNull( tree.get( 131 ) );
		assertNull( tree.get( 1311 ) );
		assertEquals( 11, tree.size() );
	}
}
