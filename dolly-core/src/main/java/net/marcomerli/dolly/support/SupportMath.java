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

public class SupportMath implements Support {

	public static Long fatt( Number n )
	{
		long nl = ( n != null ) ? n.longValue() : 0L;
		if ( nl == 0 )
			return 1L;

		return nl * fatt( nl - 1 );
	}

	public static Long nCk( Number n, Number k )
	{
		long nl = ( n != null ) ? n.longValue() : 0L;
		long kl = ( k != null ) ? k.longValue() : 0L;

		return nPk( nl, kl ) * 1 / fatt( kl );
	}

	public static Long nPk( Number n, Number k )
	{
		long nl = ( n != null ) ? n.longValue() : 0L;
		long kl = ( k != null ) ? k.longValue() : 0L;

		return fatt( nl ) / fatt( nl - kl );
	}
}
