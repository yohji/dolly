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

package net.marcomerli.dolly.ai.action;

import java.util.List;

import net.marcomerli.dolly.kernel.Kernel;
import net.marcomerli.dolly.memory.DollyMemory;
import net.marcomerli.dolly.memory.store.StoreClone;
import net.marcomerli.dolly.system.model.Clone;
import net.marcomerli.dolly.system.network.Message;
import net.marcomerli.dolly.system.network.Protocol;
import net.marcomerli.dolly.system.service.ServiceCommunication;

public class ActionResponseBirthMessage extends Action implements Protocol {

	protected Message message;

	public ActionResponseBirthMessage(Message message) {

		this.message = message;
	}

	@Override
	public void init() throws Exception
	{}

	@Override
	public void perform() throws Exception
	{
		Clone daugther = new Clone( message.getContent() );

		// Store new daugther
		StoreClone store = DollyMemory.clones();
		store.store( DollyMemory.configuration().uid(), daugther );
		List<Clone> clones = store.values();
		String hierarchy = store.toString();
		Kernel.release( store );

		// Update hierarchy to all others clones
		clones.remove( DollyMemory.configuration().thisClone() );
		for ( Clone clone : clones ) {
			Message m = new Message( HIERARCHY );
			m.setContent( hierarchy.getBytes() );
			ServiceCommunication.append( clone, m );
		}
	}

	@Override
	public void end() throws Exception
	{}
}
