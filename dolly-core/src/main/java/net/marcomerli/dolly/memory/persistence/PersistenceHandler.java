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

package net.marcomerli.dolly.memory.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import net.marcomerli.dolly.error.ErrorPersistence;

public class PersistenceHandler {

	public static Serializable load( String resource, Class<? extends Serializable> type ) throws ErrorPersistence
	{
		Serializable serializable = null;
		try {
			serializable = read( resource );
			if ( serializable == null ) {
				serializable = (Serializable) type.newInstance();
				write( resource, serializable );
			}
		}
		catch ( Exception e ) {
			throw new ErrorPersistence( e );
		}

		return serializable;
	}

	public static Serializable read( String resource )
	{
		Serializable serializable = null;
		try {
			ObjectInputStream in = new ObjectInputStream( new FileInputStream( resource ) );
			serializable = (Serializable) in.readObject();
			in.close();
		}
		catch ( Exception e ) {}

		return serializable;
	}

	public static void write( String resource, Serializable instance ) throws ErrorPersistence
	{
		try {
			ObjectOutputStream o = new ObjectOutputStream( new FileOutputStream( resource ) );
			o.writeObject( instance );
			o.close();
		}
		catch ( Exception e ) {
			throw new ErrorPersistence( e );
		}
	}

	public static boolean delete( String resource ) throws ErrorPersistence
	{
		boolean result = false;
		try {
			File file = new File( resource );
			result = file.delete();
		}
		catch ( Exception e ) {
			throw new ErrorPersistence( e );
		}

		return result;
	}

	private PersistenceHandler() {}
}
