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
import net.marcomerli.dolly.support.SupportRandom;
import net.marcomerli.dolly.system.model.Clone;
import net.marcomerli.dolly.system.network.Message;
import net.marcomerli.dolly.system.network.Protocol;
import net.marcomerli.dolly.system.service.ServiceCommunication;

public class ActionResponseTestMessage extends Action implements Protocol {

	protected Message message;

	public ActionResponseTestMessage(Message message) {

		this.message = message;
	}

	@Override
	public void init() throws Exception
	{}

	@Override
	public void perform() throws Exception
	{
		// Fetch all clones
		StoreClone clones = DollyMemory.clones();
		List<Clone> allClones = clones.values();
		Kernel.release( clones );

		// Send a test message to all others clones
		allClones.remove( DollyMemory.configuration().thisClone() );
		for ( Clone clone : allClones ) {
			Message msg = new Message( TEST );
			msg.setContent( SupportRandom.nextString( 12 ).getBytes() );
			ServiceCommunication.append( clone, msg );
		}
	}

	@Override
	public void end() throws Exception
	{}
}
