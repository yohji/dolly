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

import net.marcomerli.dolly.memory.DollyMemory;
import net.marcomerli.dolly.system.model.Clone;
import net.marcomerli.dolly.system.network.Message;
import net.marcomerli.dolly.system.network.Protocol;

public class ActionComposeProxyMessage extends Action implements Protocol {

	protected Clone proxy;
	protected Message message;

	public ActionComposeProxyMessage(Clone proxy, Message message) {

		this.proxy = proxy;
		this.message = message;
	}

	@Override
	public void init() throws Exception
	{}

	@Override
	public void perform() throws Exception
	{
		Message msg = new Message( DollyMemory.configuration().uid(), PROXY );
		final byte[] bProxy = proxy.toBytes();
		final byte[] bMessage = message.toBytes();
		System.out.println( new String( bMessage ) );
		byte[] buffer = new byte[bProxy.length + bMessage.length + 1];

		int i = 0;
		for ( ; i < bProxy.length ; i++ )
			buffer[i] = bProxy[i];

		buffer[++i] = DIV;

		for ( ; i < bMessage.length ; i++ )
			buffer[i] = bMessage[i];

		msg.setContent( buffer );
		result = msg;
	}

	@Override
	public void end() throws Exception
	{}
}
