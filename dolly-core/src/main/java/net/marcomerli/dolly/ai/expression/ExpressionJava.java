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

import java.util.Map;

import net.marcomerli.dolly.ai.Ai;
import net.marcomerli.dolly.error.ErrorAi;

public class ExpressionJava extends Expression {

	public static ExpressionJava solve( String expression ) throws ErrorAi
	{
		ExpressionJava exp = new ExpressionJava();
		exp.setExpression( expression );
		exp.calculate();

		return exp;
	}

	public static ExpressionJava solve( String expression, Map<String, Object> expressionObjects ) throws ErrorAi
	{
		ExpressionJava exp = new ExpressionJava();
		exp.setExpression( expression, expressionObjects );
		exp.calculate();

		return exp;
	}

	@Override
	public void calculate() throws ErrorAi
	{
		try {
			if ( expressionObjects != null ) {
				for ( String name : expressionObjects.keySet() )
					Ai.javaInterpreter().set( name, expressionObjects.get( name ) );
			}

			Ai.javaInterpreter().eval( "Boolean abcdefghilmnopqrstuvz = (" + expression + ");" );
			value = (Boolean) Ai.javaInterpreter().get( "abcdefghilmnopqrstuvz" );
		}
		catch ( Exception e ) {
			throw new ErrorAi( e );
		}
	}
}
