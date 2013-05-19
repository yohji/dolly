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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import net.marcomerli.dolly.memory.DollyMemory;
import net.marcomerli.dolly.memory.persistence.Persistence;
import net.marcomerli.dolly.memory.persistence.PersistenceHandler;

import org.apache.commons.io.FileUtils;

public class ModuleProcreate extends Module implements Persistence {

	private static final int BUFFER_SIZE = 512 * 512;
	private File staging;

	@Override
	public void init() throws Exception
	{
		// Prepare to procreation
		staging = new File( "staging" );
		if ( staging.exists() )
			FileUtils.deleteDirectory( staging );
		staging.mkdir();

		// Creator to export
		String path = staging.getAbsolutePath() + File.separator + Persistence.CREATOR;
		PersistenceHandler.write( path, DollyMemory.configuration().thisClone() );

		// Files for new procreated clone
		final File dolly = new File( FILE_DOLLY_JAR );
		FileUtils.copyFileToDirectory( dolly, staging );
	}

	@Override
	@SuppressWarnings( "resource" )
	public void launch() throws Exception
	{
		// Create new zip File
		File zip = new File( FILE_DOLLY_ZIP );
		if ( zip.exists() ) {
			zip.delete();
			zip.createNewFile();
		} else
			zip.createNewFile();

		FileOutputStream zipFile = new FileOutputStream( zip );
		ZipOutputStream out = new ZipOutputStream( new BufferedOutputStream( zipFile ) );

		// Export java library
		byte[] data = new byte[BUFFER_SIZE];
		FileInputStream fi = new FileInputStream( "staging" + File.separator + FILE_DOLLY_JAR );
		BufferedInputStream origin = new BufferedInputStream( fi, BUFFER_SIZE );
		ZipEntry entry = new ZipEntry( FILE_DOLLY_JAR );
		out.putNextEntry( entry );
		int count = 0;
		while ( ( count = origin.read( data, 0, BUFFER_SIZE ) ) != -1 )
			out.write( data, 0, count );

		// Export Creator
		data = new byte[BUFFER_SIZE];
		fi = new FileInputStream( "staging" + File.separator + Persistence.CREATOR );
		origin = new BufferedInputStream( fi, BUFFER_SIZE );
		entry = new ZipEntry( Persistence.CREATOR );
		out.putNextEntry( entry );
		count = 0;
		while ( ( count = origin.read( data, 0, BUFFER_SIZE ) ) != -1 )
			out.write( data, 0, count );
		
		// Close zip stream
		origin.close();
		out.close();

		// Clean
		FileUtils.deleteDirectory( staging );
	}
}
