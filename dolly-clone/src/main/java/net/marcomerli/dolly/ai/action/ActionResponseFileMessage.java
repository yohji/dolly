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

import java.io.File;

import net.marcomerli.dolly.support.SupportIO;
import net.marcomerli.dolly.system.network.Message;
import net.marcomerli.dolly.system.network.Protocol;

public class ActionResponseFileMessage extends Action implements Protocol {

	protected Message message;

	public ActionResponseFileMessage(Message message) {

		this.message = message;
	}

	@Override
	public void init() throws Exception
	{}

	@Override
	public void perform() throws Exception
	{
		byte[] content = message.content();
		byte[] n = new byte[255];

		int i = 0;
		for ( ; content[i] != DIV ; i++ )
			n[i] = content[i];
		i++;

		int size = message.content().length - i;
		byte[] bytes = new byte[size];
		for ( ; i < size ; i++ )
			bytes[i] = content[i];

		String name = new String( n );
		SupportIO.create( "tmp" + File.separator + name, bytes );
	}

	@Override
	public void end() throws Exception
	{}
}
