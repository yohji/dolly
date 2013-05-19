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

import net.marcomerli.dolly.system.network.Message;
import net.marcomerli.dolly.system.network.Protocol;

public class ActionResponseMessage extends Action implements Protocol {

	protected Message message;

	public ActionResponseMessage(Message message) {

		this.message = message;
	}

	@Override
	public void init() throws Exception
	{}

	@Override
	public void perform() throws Exception
	{
		if ( message != null ) {
			switch ( message.type() ) {

				case BIRTH:
					register( new ActionResponseBirthMessage( message ) );
					break;

				case HIERARCHY:
					register( new ActionResponseHierarchyMessage( message ) );
					break;

				case FILE:
					register( new ActionResponseFileMessage( message ) );
					break;

				case PROXY:
					register( new ActionResponseProxyMessage( message ) );
					break;

				case TEST:
					register( new ActionResponseTestMessage( message ) );
					break;
			}
		}
	}

	@Override
	public void end() throws Exception
	{}
}
