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

package net.marcomerli.dolly.ai;

import net.marcomerli.dolly.ai.step.StepPass;
import net.marcomerli.dolly.ai.task.TaskResponseMessage;
import net.marcomerli.dolly.error.ErrorAi;
import net.marcomerli.dolly.kernel.module.Module;
import net.marcomerli.dolly.kernel.module.ModuleBirth;
import net.marcomerli.dolly.kernel.module.ModuleSetup;
import net.marcomerli.dolly.memory.persistence.Persistence;
import net.marcomerli.dolly.memory.persistence.PersistenceHandler;
import net.marcomerli.dolly.system.Halt;

public class DollyAi extends Ai {
	
	@Override
	public void setup() throws ErrorAi
	{
		try {
			// Check for birth
			boolean birth = false;
			if ( PersistenceHandler.read( Persistence.CREATOR ) != null )
				birth = true;

			// System setup
			Module.execute( ModuleSetup.class );

			// Birth
			if ( birth )
				Module.execute( ModuleBirth.class );

			// Add shutdown hook to jvm
			Thread hook = new Thread( new Halt() );
			Runtime.getRuntime().addShutdownHook( hook );
		}
		catch ( Exception e ) {
			throw new ErrorAi( e );
		}
	}

	@Override
	public void behaviour() throws ErrorAi
	{
		// Setup workflow
		register( new StepPass( new TaskResponseMessage() ) );
	}
}
