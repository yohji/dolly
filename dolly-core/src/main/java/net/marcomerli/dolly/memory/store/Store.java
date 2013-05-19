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

package net.marcomerli.dolly.memory.store;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import net.marcomerli.dolly.core.Lockable;
import net.marcomerli.dolly.core.Touchable;

public interface Store<K, S> extends Serializable, Touchable, Lockable {

	void clear();

	boolean contains( K key );

	Set<K> keySet();

	void remove( K key );

	void store( K key, S serializable );

	String toString();

	List<S> values();
}
