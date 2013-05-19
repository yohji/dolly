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

package net.marcomerli.dolly.memory.environment;

import java.io.File;

import net.marcomerli.dolly.error.ErrorSupport;
import net.marcomerli.dolly.support.SupportNetwork;
import net.marcomerli.dolly.support.SupportRandom;
import net.marcomerli.dolly.system.model.Clone;

public class EnvironmentConfigurationExt extends EnvironmentConfiguration {

	private static final long serialVersionUID = -5815932822584058592L;

	public EnvironmentConfigurationExt() throws ErrorSupport {

		thisClone = new Clone();
		thisClone.setUid( SupportRandom.nextUid() );
		thisClone.setAddress( SupportNetwork.localAddress() );
		thisClone.setPort( SupportRandom.nextPort() );

		String path = new File( "." ).getAbsolutePath();
		home = path.substring( 0, path.length() - 2 );
		home += File.separator;
	}

	@Override
	public String address()
	{
		return thisClone.address();
	}

	public Clone creator()
	{
		return creator;
	}

	@Override
	public String home()
	{
		return home;
	}

	@Override
	public String memory()
	{
		return memory;
	}

	@Override
	public Integer port()
	{
		return thisClone.port();
	}

	public void setCreator( Clone creator )
	{
		super.creator = creator;
	}

	public void setMemory( String location )
	{
		memory = location;
	}

	@Override
	public Clone thisClone()
	{
		return thisClone;
	}

	@Override
	public Long uid()
	{
		return thisClone.uid();
	}
}
