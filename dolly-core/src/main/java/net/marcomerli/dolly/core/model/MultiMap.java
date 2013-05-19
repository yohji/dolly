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
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class MultiMap<K, V> implements Serializable {

	private static final long serialVersionUID = -6947032864410576212L;
	private Map<K, Collection<V>> map;

	public MultiMap() {

		map = new LinkedHashMap<K, Collection<V>>();
	}

	public MultiMap(Map<K, Collection<V>> map) {

		this.map = map;
	}

	public void clear()
	{
		map.clear();
	}

	public boolean containsKey( K key )
	{
		return map.containsKey( key );
	}

	public boolean containsValue( Collection<V> value )
	{
		boolean result = false;
		Collection<V> values = values();
		for ( V v : value ) {
			if ( values.contains( v ) )
				result = true;
			else {
				result = false;
				break;
			}
		}

		return result;
	}

	public boolean containsValue( K key, V value )
	{
		boolean result = false;
		if ( containsKey( key ) ) {
			if ( get( key ).contains( value ) )
				result = true;
		}

		return result;
	}

	public boolean containsValue( V value )
	{
		boolean result = false;
		if ( values().contains( value ) )
			result = true;

		return result;
	}

	public Collection<V> get( K key )
	{
		return map.get( key );
	}

	public boolean isEmpty()
	{
		return map.isEmpty();
	}

	public Set<K> keySet()
	{
		return map.keySet();
	}

	public Collection<V> put( K key, V value )
	{
		Collection<V> lookup = map.get( key );
		if ( lookup == null ) {
			lookup = new LinkedList<V>();
			lookup.add( value );
		} else
			lookup.add( value );

		map.put( key, lookup );
		return lookup;
	}

	public Collection<V> putAll( K key, Collection<V> value )
	{
		Collection<V> lookup = map.get( key );
		if ( lookup == null )
			map.put( key, value );
		else {
			for ( V v : value )
				lookup.add( v );
		}

		map.put( key, lookup );
		return lookup;
	}

	public Collection<V> remove( K key )
	{
		return map.remove( key );
	}

	public Collection<V> remove( K key, V value )
	{
		Collection<V> values = map.get( key );
		values.remove( value );
		if ( values.size() == 0 )
			map.remove( key );

		return values;
	}

	public int size()
	{
		return map.size();
	}

	public int size( K key )
	{
		int size = 0;
		if ( containsKey( key ) )
			size = get( key ).size();

		return size;
	}

	@Override
	public String toString()
	{
		return map.toString();
	}

	public Collection<V> values()
	{
		Collection<V> values = new LinkedList<V>();
		for ( K key : keySet() ) {
			for ( V value : get( key ) )
				values.add( value );
		}

		return values;
	}
}
