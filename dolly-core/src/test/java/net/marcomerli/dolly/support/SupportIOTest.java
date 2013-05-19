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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;

import net.marcomerli.dolly.memory.Memory;
import net.marcomerli.dolly.test.TestCaseSupport;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SupportIOTest extends TestCaseSupport {

	private static byte[] bytes;
	private static File tmp;

	@Before
	public void before() throws Exception
	{
		tmp = new File( Memory.location() + "tmp" );
		bytes = "Linux".getBytes();
	}

	@Test
	public void testWriteRead() throws Exception
	{
		SupportIO.write( new FileOutputStream( tmp ), bytes );
		byte[] read = SupportIO.read( new FileInputStream( tmp ) );

		assertNotNull( read );
		assertSame( bytes.length, read.length );
		assertTrue( Arrays.equals( bytes, read ) );
	}

	@After
	public void after() throws Exception
	{
		tmp.delete();
	}
}
