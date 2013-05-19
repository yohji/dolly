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

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import net.marcomerli.dolly.core.model.MultiMap;

public final class StoreHost implements Store<String, Integer> {

	private static final long serialVersionUID = 7031623478596234697L;
	private transient boolean locked = false;
	private transient boolean touched = false;

	private MultiMap<String, Integer> hosts;

	public StoreHost() {

		hosts = new MultiMap<String, Integer>();
	}

	public void clear()
	{
		hosts.clear();
		touched = true;
	}

	public boolean contains( String address )
	{
		return hosts.containsKey( address ) ? true : false;
	}

	public boolean hasHosts()
	{
		return ( hosts.size() > 0 ) ? true : false;
	}

	public boolean isLocked()
	{
		return locked;
	}

	public boolean isTouched()
	{
		return touched;
	}

	public Set<String> keySet()
	{
		return hosts.keySet();
	}

	public void merge( StoreHost store )
	{
		for ( String address : store.keySet() ) {
			for ( Integer port : store.values( address ) )
				store( address, port );
		}

		touched = true;
	}

	public void remove( String key )
	{
		hosts.remove( key );
		touched = true;
	}

	public void remove( String address, Integer port )
	{
		hosts.remove( address, port );
		touched = true;
	}

	public void setLocked( boolean locked )
	{
		this.locked = locked;
	}

	public void setTouched( boolean touched )
	{
		this.touched = touched;
	}

	public int size()
	{
		return hosts.size();
	}

	public void store( String address, Integer port )
	{
		hosts.put( address, port );
		touched = true;
	}

	public void storeAll( String address, Collection<Integer> ports )
	{
		hosts.putAll( address, ports );
		touched = true;
	}

	@Override
	public String toString()
	{
		return hosts.toString();
	}

	public List<Integer> values()
	{
		return new LinkedList<Integer>( hosts.values() );
	}

	public List<Integer> values( String address )
	{
		return new LinkedList<Integer>( hosts.get( address ) );
	}
}
