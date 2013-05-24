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

package net.marcomerli.dolly.kernel;

import java.util.Random;

import net.marcomerli.dolly.ai.Ai;
import net.marcomerli.dolly.core.Lockable;
import net.marcomerli.dolly.core.Runnable;
import net.marcomerli.dolly.error.ErrorAi;
import net.marcomerli.dolly.error.ErrorKernel;

import org.apache.log4j.Logger;

public class Kernel extends Thread implements Runnable {

	private static final Logger logger = Logger.getLogger( Kernel.class );
	private transient boolean running = false;
	
	protected Ai ai;
	protected Long frequency = 500L;
	
	public Kernel( Ai ai ) throws ErrorAi {
		
		this.ai = ai;
		
		ai.setup();
		ai.behaviour();
	}
	
	@Override
	public final void run()
	{
		logger.info( "Starting Kernel" );
		
		running = true;
		while ( running ) {
			try {
				ai.vivificate();
				sleep( frequency );
			}
			catch ( Exception e ) {
				logger.error( "", e );
			}
		}
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
	public final Long frequency()
	{
		return frequency;
	}
	
	@Override
	public final void setFrequency( Number milliSeconds )
	{
		this.frequency = milliSeconds.longValue();
	}
	
	public static Long time()
	{
		return new Long( System.currentTimeMillis() );
	}

	public static Random random()
	{
		return new Random();
	}

	public static void pause( Number sec ) throws ErrorKernel
	{
		try {
			Thread.sleep( sec.longValue() * 1000 );
		}
		catch ( Exception e ) {
			throw new ErrorKernel( e );
		}
	}

	public static void lock( Lockable lockable )
	{
		if ( !lockable.isLocked() )
			lockable.setLocked( true );
	}

	public static void release( Lockable lockable )
	{
		if ( lockable.isLocked() )
			lockable.setLocked( false );
	}

	public static void waitFor( Lockable lockable ) throws ErrorKernel
	{
		try {
			Random rnd = random();
			while ( lockable.isLocked() )
				Thread.sleep( 1 + rnd.nextInt( 3 ) );
		}
		catch ( InterruptedException e ) {
			logger.warn( e + " for " + lockable.getClass().getName() );
		}
		catch ( Exception e ) {
			throw new ErrorKernel( e );
		}
	}
}
