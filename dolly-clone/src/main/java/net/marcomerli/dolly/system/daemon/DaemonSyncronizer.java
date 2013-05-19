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

package net.marcomerli.dolly.system.daemon;

import net.marcomerli.dolly.memory.persistence.Persistence;
import net.marcomerli.dolly.system.Daemon;
import net.marcomerli.dolly.system.service.ServiceSyncronizer;

public class DaemonSyncronizer extends Daemon implements Persistence {

	private static final long serialVersionUID = -2347569346952365923L;

	@Override
	public void init() throws Exception
	{}

	@Override
	public void task() throws Exception
	{
		ServiceSyncronizer.sync();
	}

	@Override
	public void kill() throws Exception
	{}
}
