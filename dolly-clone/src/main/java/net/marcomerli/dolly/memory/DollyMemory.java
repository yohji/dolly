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

package net.marcomerli.dolly.memory;

import java.io.File;
import java.io.Serializable;

import net.marcomerli.dolly.error.Error;
import net.marcomerli.dolly.error.ErrorMemory;
import net.marcomerli.dolly.error.ErrorPersistence;
import net.marcomerli.dolly.error.ErrorSystem;
import net.marcomerli.dolly.kernel.Kernel;
import net.marcomerli.dolly.memory.environment.EnvironmentConfiguration;
import net.marcomerli.dolly.memory.environment.EnvironmentConfigurationExt;
import net.marcomerli.dolly.memory.environment.EnvironmentSystem;
import net.marcomerli.dolly.memory.environment.EnvironmentSystemExt;
import net.marcomerli.dolly.memory.persistence.Persistence;
import net.marcomerli.dolly.memory.persistence.PersistenceHandler;
import net.marcomerli.dolly.memory.store.StoreClone;
import net.marcomerli.dolly.memory.store.StoreHost;
import net.marcomerli.dolly.memory.store.StoreMessage;
import net.marcomerli.dolly.support.SupportOs;
import net.marcomerli.dolly.support.SupportRandom;
import net.marcomerli.dolly.system.model.Clone;

public class DollyMemory extends Memory implements Persistence {

	private static EnvironmentConfigurationExt configuration;
	private static EnvironmentSystem information;
	private static StoreClone cloneStore;
	private static StoreHost hosts;
	private static StoreMessage messageListener;
	private static StoreMessage messageSender;
	
	public static void initialize() throws ErrorPersistence, ErrorSystem
	{
		initialize( true );
	}

	public static void initialize( Boolean dynamic ) throws ErrorPersistence, ErrorSystem
	{
		location = (String) PersistenceHandler.read( MEMORY_LOCATION );

		// Not yet defined
		if ( location == null ) {
			if ( dynamic ) {
				while ( !SupportOs.isAccessible( location = SupportRandom.nextPath( 3 ) ) );
				location += File.separator + MEMORY_DIRECTORY + File.separator;
				new File( location ).mkdir();

			} else {
				File here = new File( "." );
				location = here.getAbsolutePath();
				location = location.substring( 0, location.length() - 1 );
				location += MEMORY_DIRECTORY + File.separator;
				new File( location ).mkdir();
			}

			PersistenceHandler.write( MEMORY_LOCATION, location );
		}
	}

	public static StoreClone clones() throws ErrorMemory
	{
		try {
			if ( cloneStore == null ) {
				Serializable serializable = PersistenceHandler.read( location + STORE_CLONE );
				if ( serializable != null )
					cloneStore = (StoreClone) serializable;
				else {
					cloneStore = new StoreClone();
					if ( configuration().creator() == null 
						|| configuration().creator().equals( new Clone() ) )
						cloneStore.setRoot( configuration().thisClone() );

					PersistenceHandler.write( location + STORE_CLONE, cloneStore );
				}

				Kernel.lock( cloneStore );
				return cloneStore;
			}

			Kernel.waitFor( cloneStore );
			Kernel.lock( cloneStore );
		}
		catch ( Error e ) {
			throw new ErrorMemory( e );
		}

		return cloneStore;
	}

	public static EnvironmentConfiguration configuration() throws ErrorMemory
	{
		try {
			if ( configuration == null ) {
				Serializable serializable = PersistenceHandler.read( location + INFO_CONFIGURATION );
				if ( serializable != null )
					configuration = (EnvironmentConfigurationExt) serializable;
				else {
					configuration = new EnvironmentConfigurationExt();
					configuration.setCreator( (Clone) PersistenceHandler.read( CREATOR ) );
					configuration.setMemory( location );

					PersistenceHandler.write( location + INFO_CONFIGURATION, configuration );
					PersistenceHandler.delete( CREATOR );
				}
			}
		}
		catch ( Error e ) {
			throw new ErrorMemory( e );
		}

		return configuration;
	}

	public static StoreHost hosts() throws ErrorMemory
	{
		try {
			if ( hosts == null ) {
				hosts = (StoreHost) PersistenceHandler.load( location + STORE_HOSTS, StoreHost.class );
				Kernel.lock( hosts );
				return hosts;
			}

			Kernel.waitFor( hosts );
			Kernel.lock( hosts );
		}
		catch ( Error e ) {
			throw new ErrorMemory( e );
		}

		return hosts;
	}

	public static StoreMessage messagesListener() throws ErrorMemory
	{
		try {
			if ( messageListener == null ) {
				messageListener = (StoreMessage) PersistenceHandler.load( location 
					+ STORE_MESSAGE_LISTENER, StoreMessage.class );
				Kernel.lock( messageListener );
				return messageListener;
			}

			Kernel.waitFor( messageListener );
			Kernel.lock( messageListener );
		}
		catch ( Error e ) {
			throw new ErrorMemory( e );
		}

		return messageListener;
	}

	public static StoreMessage messagesSender() throws ErrorMemory
	{
		try {
			if ( messageSender == null ) {
				messageSender = (StoreMessage) PersistenceHandler.load( location 
					+ STORE_MESSAGE_SENDER, StoreMessage.class );
				Kernel.lock( messageSender );
				return messageSender;
			}

			Kernel.waitFor( messageSender );
			Kernel.lock( messageSender );
		}
		catch ( Error e ) {
			throw new ErrorMemory( e );
		}

		return messageSender;
	}

	public static EnvironmentSystem system() throws ErrorMemory
	{
		try {
			if ( information == null ) {
				information = (EnvironmentSystemExt) PersistenceHandler.load( location 
					+ INFO_SYSTEM, EnvironmentSystemExt.class );
				information.uptime();
			}
		}
		catch ( Error e ) {
			throw new ErrorMemory( e );
		}

		return information;
	}
	
	public static boolean isLockedClones()
	{
		return ( cloneStore != null ) ? cloneStore.isLocked() : false;
	}

	public static boolean isLockedHosts()
	{
		return ( hosts != null ) ? hosts.isLocked() : false;
	}

	public static boolean isLockedMListener()
	{
		return ( messageListener != null ) ? messageListener.isLocked() : false;
	}

	public static boolean isLockedMSender()
	{
		return ( messageSender != null ) ? messageSender.isLocked() : false;
	}

	private DollyMemory() {}
}
