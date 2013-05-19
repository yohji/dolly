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

import net.marcomerli.dolly.kernel.Kernel;
import net.marcomerli.dolly.memory.DollyMemory;
import net.marcomerli.dolly.memory.store.StoreClone;
import net.marcomerli.dolly.system.network.Message;

public class ActionResponseHierarchyMessage extends Action {

	protected Message message;

	public ActionResponseHierarchyMessage(Message message) {

		this.message = message;
	}

	@Override
	public void init() throws Exception
	{}

	@Override
	public void perform() throws Exception
	{
		// Merge hierarchy
		StoreClone clones = DollyMemory.clones();
		StoreClone merge = StoreClone.render( message.getContent() );
		clones.merge( merge );
		Kernel.release( clones );
	}

	@Override
	public void end() throws Exception
	{}
}
