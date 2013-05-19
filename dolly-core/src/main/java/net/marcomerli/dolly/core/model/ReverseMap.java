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
import java.util.Map;
import java.util.Set;

public class ReverseMap<K, V> implements Map<K, V>, Serializable {

	private static final long serialVersionUID = 4756239456195629587L;
	private Map<K, V> main;
	private Map<V, K> reverse;

	public ReverseMap() {

		main = new LinkedHashMap<K, V>();
		reverse = new LinkedHashMap<V, K>();
	}

	public ReverseMap(Map<K, V> map) {

		main = map;
		for ( K k : main.keySet() )
			reverse.put( main.get( k ), k );
	}

	public void clear()
	{
		main.clear();
		reverse.clear();
	}

	public boolean containsKey( Object key )
	{
		return main.containsKey( key );
	}

	public boolean containsValue( Object value )
	{
		return main.containsValue( value );
	}

	public Set<Entry<K, V>> entrySet()
	{
		return main.entrySet();
	}

	public V get( Object key )
	{
		return main.get( key );
	}

	public K getKey( Object value )
	{
		return reverse.get( value );
	}

	public boolean isEmpty()
	{
		return main.isEmpty();
	}

	public Set<K> keySet()
	{
		return main.keySet();
	}

	public V put( K key, V value )
	{
		main.put( key, value );
		reverse.put( value, key );

		return value;
	}

	public void putAll( Map<? extends K, ? extends V> map )
	{
		for ( K k : map.keySet() ) {
			V v = main.get( k );
			main.put( k, v );
			reverse.put( v, k );
		}
	}

	public V remove( Object key )
	{
		V v = main.remove( key );
		reverse.remove( v );

		return v;
	}

	public int size()
	{
		return main.size();
	}

	@Override
	public String toString()
	{
		return main.toString();
	}

	public Collection<V> values()
	{
		return main.values();
	}
}
