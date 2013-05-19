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

package net.marcomerli.dolly.kernel.module;

import net.marcomerli.dolly.error.ErrorModule;

import org.apache.log4j.Logger;

public abstract class Module {

	private static final Logger logger = Logger.getLogger( Module.class );

	public static void execute( Class<? extends Module> module ) throws ErrorModule
	{
		try {
			String n = module.getName();
			Module m = (Module) Module.class.getClassLoader().loadClass( n ).newInstance();
			execute( m );
		}
		catch ( Exception e ) {
			throw new ErrorModule( e );
		}
	}

	public static void execute( Module instance ) throws ErrorModule
	{
		try {
			instance.init();
			instance.launch();

			logger.info( "Executed module " + instance.getClass().getName() );
		}
		catch ( Exception e ) {
			throw new ErrorModule( e );
		}
	}

	public abstract void init() throws Exception;

	public abstract void launch() throws Exception;
}
