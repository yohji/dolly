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

package net.marcomerli.dolly.system;

import java.io.Serializable;
import java.util.List;

import net.marcomerli.dolly.core.Lockable;
import net.marcomerli.dolly.core.Runnable;
import net.marcomerli.dolly.error.Error;
import net.marcomerli.dolly.error.ErrorDaemon;
import net.marcomerli.dolly.kernel.Kernel;
import net.marcomerli.dolly.memory.Memory;
import net.marcomerli.dolly.memory.store.StoreDaemon;
import net.marcomerli.dolly.support.SupportRandom;

import org.apache.log4j.Logger;

public abstract class Daemon implements Runnable, Lockable, Serializable {

	private static final long serialVersionUID = -6634452104136969247L;
	private static final Logger logger = Logger.getLogger( Daemon.class );

	protected transient boolean running = false;
	protected transient boolean locked = false;

	protected String name;
	protected Integer pid;
	protected Long time = 0L;
	protected Long frequency = 1000L;

	@Override
	public final void run()
	{
		try {
			while ( running ) {
				task();
				
				locked = true;
				Kernel.sleep( frequency );
				locked = false;
			}
		}
		catch ( Exception e ) {
			logger.error( "", e );
		}
	}

	public String name()
	{
		return name;
	}

	public Integer pid()
	{
		return pid;
	}

	public Long uptime()
	{
		return Kernel.time() - time;
	}

	@Override
	public final Long frequency()
	{
		return frequency;
	}

	@Override
	public final void setFrequency( Number cycleTime )
	{
		this.frequency = cycleTime.longValue();
	}

	@Override
	public final boolean isRunning()
	{
		return running;
	}

	@Override
	public final void setRunning( boolean status )
	{
		running = status;
	}

	@Override
	public final boolean isLocked()
	{
		return locked;
	}

	@Override
	public final void setLocked( boolean status )
	{
		locked = status;
	}

	@Override
	public String toString()
	{
		return pid + " " + name;
	}

	public abstract void init() throws Exception;

	public abstract void task() throws Exception;

	public abstract void kill() throws Exception;

	public static Daemon start( Class<? extends Daemon> daemon ) throws ErrorDaemon
	{
		Daemon d = null;
		try {
			String n = daemon.getName();
			d = (Daemon) Daemon.class.getClassLoader().loadClass( n ).newInstance();
		}
		catch ( Exception e ) {
			throw new ErrorDaemon( e );
		}

		return start( d );
	}

	public static Daemon start( Daemon daemon ) throws ErrorDaemon
	{
		daemon.pid = SupportRandom.nextPid();
		daemon.name = daemon.getClass().getName();

		try {
			StoreDaemon daemonStore = Memory.daemons();
			daemonStore.store( daemon.pid(), daemon );
			Thread thread = daemonStore.thread( daemon.pid() );

			thread.setDaemon( true );
			daemon.init();
			daemon.setRunning( true );
			thread.start();

			daemon.time = Kernel.time();
			Kernel.release( daemonStore );
		}
		catch ( Error e ) {
			throw new ErrorDaemon( e );
		}
		catch ( Exception e ) {
			throw new ErrorDaemon( e );
		}

		logger.info( "Daemon started " + daemon.pid() + " " + daemon.name() );
		return daemon;
	}

	public static Daemon stop( Integer pid ) throws ErrorDaemon
	{
		Daemon daemon = null;
		try {
			StoreDaemon daemonStore = Memory.daemons();
			Thread thread = daemonStore.thread( pid );
			daemon = daemonStore.values( pid );

			daemon.setRunning( false );
			Kernel.waitFor( daemon );
			daemon.kill();
			thread.interrupt();

			daemon.time = 0L;
			Kernel.release( daemonStore );
		}
		catch ( Error e ) {
			throw new ErrorDaemon( e );
		}
		catch ( Exception e ) {
			throw new ErrorDaemon( e );
		}

		logger.info( "Daemon  stopped " + pid );
		return daemon;
	}

	public static void stopAll( Class<? extends Daemon> daemon ) throws ErrorDaemon
	{
		try {
			StoreDaemon daemonStore = Memory.daemons();
			List<Daemon> daemons = daemonStore.values( daemon );
			Kernel.release( daemonStore );

			for ( Daemon d : daemons )
				stop( d.pid() );
		}
		catch ( Error e ) {
			throw new ErrorDaemon( e );
		}
	}
}
