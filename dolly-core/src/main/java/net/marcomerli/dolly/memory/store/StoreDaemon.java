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

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.marcomerli.dolly.system.Daemon;

public final class StoreDaemon implements Store<Integer, Daemon> {

	private static final long serialVersionUID = 4640112561849172959L;
	private transient boolean locked = false;
	private transient boolean touched = false;

	private Map<Integer, Daemon> daemons;
	private Map<Integer, Thread> daemonsThread;

	public StoreDaemon() {

		daemons = new HashMap<Integer, Daemon>();
		daemonsThread = new HashMap<Integer, Thread>();
	}

	public List<Daemon> actives()
	{
		List<Daemon> result = new LinkedList<Daemon>();
		for ( Daemon daemon : daemons.values() ) {
			if ( isActive( daemon.pid() ) )
				result.add( daemon );
		}

		return result;
	}

	public void clear()
	{
		daemons.clear();
		daemonsThread.clear();

		touched = true;
	}

	public boolean contains( Integer pid )
	{
		return daemons.containsKey( pid ) ? true : false;
	}

	public boolean isActive( Integer pid )
	{
		return daemons.get( pid ).isRunning();
	}

	public boolean isLocked()
	{
		return locked;
	}

	public boolean isTouched()
	{
		return touched;
	}

	public Set<Integer> keySet()
	{
		return daemons.keySet();
	}

	public void remove( Integer key )
	{
		daemons.remove( key );
		daemonsThread.remove( key );

		touched = true;
	}

	public void setLocked( boolean locked )
	{
		this.locked = locked;
	}

	public void setThread( Integer pid, Thread thread )
	{
		daemonsThread.put( pid, thread );
		touched = true;
	}

	public void setTouched( boolean touched )
	{
		this.touched = touched;
	}

	public int size()
	{
		return daemons.size();
	}

	public void store( Integer pid, Daemon daemon )
	{
		daemons.put( pid, daemon );
		daemonsThread.put( pid, new Thread( daemon ) );

		touched = true;
	}

	public Thread thread( Integer pid )
	{
		return daemonsThread.get( pid );
	}

	@Override
	public String toString()
	{
		return daemons.keySet().toString();
	}

	public Long uptime( Integer pid )
	{
		Long uptime = daemons.get( pid ).uptime();
		if ( uptime == 0 )
			return uptime;

		return System.currentTimeMillis() - uptime;
	}

	public List<Daemon> values()
	{
		return new LinkedList<Daemon>( daemons.values() );
	}

	public List<Daemon> values( Class<? extends Daemon> daemonClass )
	{
		List<Daemon> result = new LinkedList<Daemon>();
		for ( Daemon daemon : daemons.values() ) {
			if ( daemon.getClass().getName().equals( daemonClass.getName() ) )
				result.add( daemon );
		}

		return result;
	}

	public Daemon values( Integer pid )
	{
		return daemons.get( pid );
	}
}
