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
import net.marcomerli.dolly.error.ErrorSystem;
import net.marcomerli.dolly.support.SupportOs;
import net.marcomerli.dolly.system.Command;
import net.marcomerli.dolly.test.TestCaseSupport;

import org.junit.Test;

public class SupportOsTest extends TestCaseSupport {

	private static final Command CMD_DIR_A = new Command( "ls" );
	private static final Command CMD_DIR_B = new Command( "ls" );

	@Test
	public void testExecute() throws ErrorSystem
	{
		SupportOs.execute( CMD_DIR_A );
		SupportOs.execute( CMD_DIR_B );

		assertNotNull( CMD_DIR_A );
		assertNotNull( CMD_DIR_A.output() );
		assertNotNull( CMD_DIR_A.exitCode() );

		assertEquals( CMD_DIR_A.output(), CMD_DIR_B.output() );
		assertEquals( CMD_DIR_A.exitCode(), CMD_DIR_B.exitCode() );
	}

	@Test
	public void testIsAccessible() throws ErrorSystem
	{
		String path = "/tmp";
		String aPath = "/";
		String aaPath = "/root";

		assertTrue( SupportOs.isAccessible( path ) );
		assertFalse( SupportOs.isAccessible( aPath ) );
		assertFalse( SupportOs.isAccessible( aaPath ) );
	}
}
