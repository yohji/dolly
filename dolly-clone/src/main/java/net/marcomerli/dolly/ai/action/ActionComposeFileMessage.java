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
import java.io.FileInputStream;
import java.io.InputStream;

import net.marcomerli.dolly.memory.DollyMemory;
import net.marcomerli.dolly.support.SupportIO;
import net.marcomerli.dolly.system.network.Message;
import net.marcomerli.dolly.system.network.Protocol;

public class ActionComposeFileMessage extends Action implements Protocol {

	protected String file;

	public ActionComposeFileMessage(String file) {

		this.file = file;
	}

	@Override
	public void init() throws Exception
	{}

	@Override
	public void perform() throws Exception
	{
		Message message = new Message( DollyMemory.configuration().uid(), FILE );
		byte[] name = file.substring( file.lastIndexOf( File.separator ) + 1 ).getBytes();

		File f = new File( file );
		InputStream in = new FileInputStream( f );
		int size = name.length + 1 + in.available();
		byte[] bytes = new byte[size];

		int i = 0;
		for ( ; i < name.length ; i++ )
			bytes[i] = name[i];

		bytes[++i] = DIV;

		byte[] read = SupportIO.read( in );
		for ( ; i < read.length ; i++ )
			bytes[i] = read[i];

		in.close();
		message.setContent( bytes );
		result = message;
	}
	
	@Override
	public void end() throws Exception
	{}
}
