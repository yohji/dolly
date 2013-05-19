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

import java.net.InetAddress;

import net.marcomerli.dolly.system.Os;

import org.apache.log4j.Logger;

public class EnvironmentSystemExt extends EnvironmentSystem {

	private static final Logger logger = Logger.getLogger( EnvironmentSystemExt.class );
	private static final long serialVersionUID = -5239479123874012402L;

	public EnvironmentSystemExt() {

		try {
			hostName = InetAddress.getLocalHost().getCanonicalHostName();
		}
		catch ( Exception e ) {
			logger.error( "", e );
		}

		encoding = System.getProperty( "file.encoding" );
		javaHome = System.getProperty( "java.home" );
		javaVersion = System.getProperty( "java.specification.version" );
		lifeTime = System.currentTimeMillis();
		os = Os.instance();
		uptime = System.currentTimeMillis();
	}

	@Override
	public String encoding()
	{
		return encoding;
	}

	@Override
	public String hostName()
	{
		return hostName;
	}

	@Override
	public String javaHome()
	{
		return javaHome;
	}

	@Override
	public String javaVersion()
	{
		return javaVersion;
	}

	@Override
	public Long lifeTime()
	{
		return ( System.currentTimeMillis() - lifeTime ) / 1000;
	}

	@Override
	public Os os()
	{
		return os;
	}

	@Override
	public Long uptime()
	{
		if ( uptime == null ) {
			uptime = System.currentTimeMillis();
			return 0L;
		}

		return ( System.currentTimeMillis() - uptime ) / 1000;
	}
}
