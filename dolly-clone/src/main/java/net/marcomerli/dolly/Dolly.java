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

package net.marcomerli.dolly;

import net.marcomerli.dolly.ai.DollyAi;
import net.marcomerli.dolly.error.Error;
import net.marcomerli.dolly.kernel.Kernel;

import org.apache.log4j.Logger;

public class Dolly {

	private static final Logger logger = Logger.getLogger( Dolly.class );

	public static void main( String[] args )
	{
		try {
			new Kernel( new DollyAi() ).start();
		}
		catch ( Error e ) {
			logger.error( "", e );
		}
	}

	public static void halt()
	{
		System.exit( 0 );
	}
}
