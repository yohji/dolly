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

import net.marcomerli.dolly.system.model.Clone;
import net.marcomerli.dolly.system.network.Message;
import net.marcomerli.dolly.system.network.Protocol;
import net.marcomerli.dolly.system.service.ServiceCommunication;

public class ActionResponseProxyMessage extends Action implements Protocol {

	protected Message message;

	public ActionResponseProxyMessage(Message message) {

		this.message = message;
	}

	@Override
	public void init() throws Exception
	{}

	@Override
	public void perform() throws Exception
	{
		byte[] content = message.content();
		byte[] buffer = new byte[255];

		int i = 0;
		for ( ; content[i] != DIV ; i++ )
			buffer[i] = content[i];
		i++;

		int size = message.content().length - i;
		byte[] bytes = new byte[size];
		for ( ; i < size ; i++ )
			bytes[i] = content[i];

		Clone recipient = new Clone( new String( buffer ) );
		Message send = Message.decode( bytes );
		ServiceCommunication.append( recipient, send );
	}

	@Override
	public void end() throws Exception
	{}
}
