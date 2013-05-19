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

import java.io.Serializable;
import java.util.LinkedList;

import net.marcomerli.dolly.core.Scrollable;

public class ScrollableList<E> extends LinkedList<E> implements Scrollable<E>, Serializable {

	private static final long serialVersionUID = -3416755083604383825L;
	private transient int index = -1;

	public E next()
	{
		index++;
		if ( index >= size() )
			index = 0;

		return get( index );
	}

	public E prev()
	{
		index--;
		if ( index < 0 )
			index = size() - 1;

		return get( index );
	}
}
