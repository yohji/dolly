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

package net.marcomerli.dolly.system;

import static org.junit.Assert.*;
import net.marcomerli.dolly.error.ErrorSystem;
import net.marcomerli.dolly.test.TestCaseSupport;

import org.junit.Before;
import org.junit.Test;

public class OsTest extends TestCaseSupport {
	
	private Os os;
	
	@Before
	public void before()
	{
		os = Os.instance();
		
		assertNotNull( os );
	}

	@Test
	public void testValues() throws ErrorSystem
	{
		assertNotNull( os.arch() );
		assertNotNull( os.name() );
		assertNotNull( os.version() );
		assertNotNull( os.type() );
		assertNotNull( os.is64Bit() );
		assertNotNull( os.isUnixLike() );
	}
}
