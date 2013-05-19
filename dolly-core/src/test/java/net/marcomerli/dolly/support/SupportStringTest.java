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
import net.marcomerli.dolly.support.SupportString;
import net.marcomerli.dolly.test.TestCaseSupport;

import org.junit.Test;

public class SupportStringTest extends TestCaseSupport {

	private static final String STRING = "Linux linux LiNuX";

	@Test
	public void testTrimSpace()
	{
		String trimmed = SupportString.trimSpace( STRING );

		assertNotNull( trimmed );
		assertNotSame( trimmed.length(), STRING.length() );
	}

	@Test
	public void testContent()
	{
		char[] chars = SupportString.content( STRING );
		char[] aChars = SupportString.content( STRING );

		assertNotNull( chars );
		assertNotNull( aChars );
		assertEquals( STRING.length(), chars.length );
		assertEquals( chars.length, aChars.length );
	}

	@Test
	public void testCut()
	{
		String seq = "{:{;{~};}:}";

		assertNotNull( SupportString.cut( seq, '{', '}' ) );
		assertEquals( 9, SupportString.cut( seq, '{', '}' ).length() );
		assertEquals( 5, SupportString.cut( seq, '{', '}', false ).length() );
		assertEquals( 3, SupportString.cut( seq, ':', '~' ).length() );
		assertEquals( 11, SupportString.cut( seq, '[', ']' ).length() );
		assertEquals( 11, SupportString.cut( seq, '[', ']', false ).length() );
	}

	@Test
	public void testCutString()
	{
		String seq = "{:{;{~};}:}";

		assertNotNull( SupportString.cut( seq, "{:", "~}" ) );
		assertEquals( 7, SupportString.cut( seq, "{:", ":}" ).length() );
		assertEquals( 3, SupportString.cut( seq, "{:", "~}" ).length() );
		assertEquals( 1, SupportString.cut( seq, "{:{;{", "};}:}" ).length() );

		assertEquals( 17, SupportString.cut( "a{b{}, c{d{e{}}, f{}}", "{", "}}" ).length() );
		assertEquals( 11, SupportString.cut( "a{b{}, c{d{e{}}, f{}}", "{", "}}", false ).length() );
	}
}
