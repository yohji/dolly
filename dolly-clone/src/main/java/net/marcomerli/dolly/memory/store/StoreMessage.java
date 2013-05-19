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

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import net.marcomerli.dolly.core.Scrollable;
import net.marcomerli.dolly.core.model.MultiMap;
import net.marcomerli.dolly.error.ErrorMemory;
import net.marcomerli.dolly.kernel.Kernel;
import net.marcomerli.dolly.memory.DollyMemory;
import net.marcomerli.dolly.system.model.Clone;
import net.marcomerli.dolly.system.network.Message;

public final class StoreMessage implements Store<Clone, Message>, Scrollable<Clone> {

	private static final long serialVersionUID = 7031623478596234697L;
	private transient boolean touched = false;
	private transient boolean locked = false;
	private transient int index = -1;

	private MultiMap<Clone, Message> messages;

	public StoreMessage() {

		messages = new MultiMap<Clone, Message>();
	}

	public void clear()
	{
		messages.clear();
		touched = true;
	}

	public boolean contains( Clone clone )
	{
		return messages.containsKey( clone ) ? true : false;
	}

	public boolean hasMessages()
	{
		return ( messages.keySet().size() > 0 ) ? true : false;
	}

	public boolean hasMessages( Clone clone )
	{
		return ( messages.get( clone ).size() > 0 ) ? true : false;
	}

	public boolean isLocked()
	{
		return locked;
	}

	public boolean isTouched()
	{
		return touched;
	}

	public Set<Clone> keySet()
	{
		return messages.keySet();
	}

	public void merge( StoreMessage store )
	{
		for ( Clone clone : store.keySet() ) {
			for ( Message message : store.values( clone ) )
				store( clone, message );
		}

		touched = true;
	}

	public Clone next()
	{
		index += 1;
		if ( index >= size() )
			index = 0;

		return new LinkedList<Clone>( keySet() ).get( index );
	}

	public Clone prev()
	{
		index -= 1;
		if ( index < 0 )
			index = size() - 1;

		return new LinkedList<Clone>( keySet() ).get( index );
	}

	protected Clone readKey( Long uid )
	{
		for ( Clone clone : messages.keySet() ) {
			if ( clone.uid().equals( uid ) )
				return clone;
		}

		return null;
	}

	public void remove( Clone key )
	{
		messages.remove( key );
		touched = true;
	}

	public void remove( Long uid, Message message )
	{
		messages.remove( readKey( uid ), message );
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
		return messages.size();
	}

	public void store( Clone clone, Message message )
	{
		messages.put( clone, message );
		touched = true;
	}

	public void store( Message message ) throws ErrorMemory
	{
		Clone clone = null;
		StoreClone store = DollyMemory.clones();
		clone = store.find( message.uid() );
		Kernel.release( store );

		if ( clone == null ) {
			clone = new Clone();
			clone.setUid( message.uid() );
		}

		store( clone, message );
		touched = true;
	}

	@Override
	public String toString()
	{
		return messages.toString();
	}

	public List<Message> values()
	{
		return new LinkedList<Message>( messages.values() );
	}

	public List<Message> values( Clone key )
	{
		return new LinkedList<Message>( messages.get( key ) );
	}

	public List<Message> values( Long uid )
	{
		return new LinkedList<Message>( messages.get( readKey( uid ) ) );
	}
}
