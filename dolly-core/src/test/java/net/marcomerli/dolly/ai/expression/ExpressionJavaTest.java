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

package net.marcomerli.dolly.ai.expression;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import net.marcomerli.dolly.test.TestCaseSupport;

import org.junit.Test;

public class ExpressionJavaTest extends TestCaseSupport {

	@Test
	public void testJavaExpression() throws Exception
	{
		Map<String, Object> objs = new HashMap<String, Object>();
		objs.put( "s", new String( "hello" ) );
		objs.put( "n", new Integer( 5 ) );

		Expression e = ExpressionJava.solve( "s.length() == n", objs );
		Expression ee = ExpressionJava.solve( "s.indexOf( n - 1 ) == 'h' && s.startsWith( s )", objs );

		assertTrue( e.value() );
		assertFalse( ee.value() );
	}
}
