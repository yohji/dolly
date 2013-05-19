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

package net.marcomerli.dolly.support;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import net.marcomerli.dolly.error.ErrorSupport;

import org.apache.commons.io.IOUtils;

public class SupportIO implements Support {

	public static File create( String file, byte[] bytes ) throws ErrorSupport
	{
		File f = new File( file );
		try {
			if ( !f.exists() )
				f.createNewFile();

			OutputStream outputStream = new FileOutputStream( f );
			outputStream.write( bytes );
			outputStream.flush();
			outputStream.close();
		}
		catch ( Exception e ) {
			throw new ErrorSupport( e );
		}

		return f;
	}

	public static byte[] read( InputStream inputStream ) throws ErrorSupport
	{
		Number size = null;
		try {
			size = inputStream.available();
		}
		catch ( Exception e ) {
			throw new ErrorSupport( e );
		}

		return read( inputStream, size );
	}

	public static byte[] read( InputStream inputStream, Number size ) throws ErrorSupport
	{
		byte[] buffer = new byte[size.intValue()];
		try {
			int read = 0;
			for ( int i = 0 ; ( read = inputStream.read() ) != -1 ; i++ )
				buffer[i] = (byte) read;
		}
		catch ( Exception e ) {
			throw new ErrorSupport( e );
		}
		finally {
			IOUtils.closeQuietly( inputStream );
		}

		return buffer;
	}

	public static boolean write( OutputStream outputStream, byte[] bytes ) throws ErrorSupport
	{
		boolean result = false;
		try {
			outputStream.write( bytes );
			outputStream.flush();
			result = true;
		}
		catch ( Exception e ) {
			throw new ErrorSupport( e );
		}
		finally {
			IOUtils.closeQuietly( outputStream );
		}

		return result;
	}

	private SupportIO() {}
}
