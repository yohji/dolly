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
import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;

import net.marcomerli.dolly.kernel.Kernel;
import net.marcomerli.dolly.system.Os;

public class SupportRandom implements Support {

	private static char[] chars;
	static {
		chars = new char[62];
		int index = 0;

		for ( int i = 48 ; i < 58 ; i++ )
			chars[index++] = (char) i;
		for ( int i = 65 ; i < 91 ; i++ )
			chars[index++] = (char) i;
		for ( int i = 97 ; i < 123 ; i++ )
			chars[index++] = (char) i;
	}

	protected static File explorePath( File level, Random rnd )
	{
		File current = null;
		File[] content = level.listFiles();
		if ( content != null && content.length > 0 ) {
			current = content[rnd.nextInt( content.length )];
			if ( current.isDirectory() )
				current = explorePath( current, rnd );
			else
				return level;
		}

		return current;
	}

	public static String nextAddress()
	{
		Random random = Kernel.random();

		int[] numbers = new int[999];
		for ( int i = 0 ; i < 999 ; i++ )
			numbers[i] = random.nextInt( 9 );

		String buffer = new String();
		while ( buffer.split( "\\." ).length < 5 ) {
			String f = String.valueOf( numbers[random.nextInt( 999 )] );
			String s = String.valueOf( numbers[random.nextInt( 999 )] );
			String t = String.valueOf( numbers[random.nextInt( 999 )] );

			Integer value = new Integer( f + s + t );
			if ( buffer.equals( "" ) ) {
				if ( value > 0 && value < 240 && value != 127 )
					buffer += "." + value;
			} else {
				if ( value < 255 )
					buffer += "." + value;
			}
		}

		return buffer.substring( 1 );
	}

	public static byte[] nextBytes( Number length )
	{
		Random random = Kernel.random();
		length = Math.abs( length.intValue() );
		byte[] bytes = new byte[length.intValue()];

		for ( int index = 0 ; index < length.intValue() ; index++ )
			bytes[index] = (byte) random.nextInt( 256 );

		return bytes;
	}

	public static String nextPath()
	{
		String root = null;
		if ( Os.instance().isUnixLike() )
			root = "/";
		else
			root = "C:\\\\";

		return nextPath( root );
	}

	public static String nextPath( Number minDeep )
	{
		String s = null;
		if ( Os.instance().isUnixLike() )
			s = "/";
		else
			s = "\\\\";

		String path = null;
		while ( ( path = nextPath() ).split( s ).length < minDeep.intValue() + 1 );

		return path;
	}

	public static String nextPath( String root )
	{
		String path = null;
		Random rnd = Kernel.random();
		File rootPath = new File( root );

		try {
			path = explorePath( rootPath, rnd ).getAbsolutePath();
			if ( path.equals( rootPath.getAbsoluteFile() ) )
				return nextPath();
		}
		catch ( NullPointerException e ) {
			return nextPath();
		}

		return path;
	}

	public static Integer nextPid()
	{
		return new Integer( Kernel.random().nextInt( 65536 ) );
	}

	public static Integer nextPort()
	{
		int port = 0;
		try {
			port = 1024 + Kernel.random().nextInt( 64511 );

			Socket socket = new Socket( InetAddress.getLocalHost(), port );
			socket.close();
			nextPort();
		}
		catch ( Exception e ) {
			return port;
		}

		return 0;
	}

	public static String nextString( Number length )
	{
		String result = new String();
		Random random = Kernel.random();

		length = Math.abs( length.intValue() );
		for ( int index = 0 ; index < length.intValue() ; index++ )
			result += chars[Math.abs( random.nextInt( 52 ) )];

		return result;
	}

	public static Long nextUid()
	{
		Random random = Kernel.random();

		int[] numbers = new int[999];
		for ( int i = 0 ; i < 999 ; i++ )
			numbers[i] = random.nextInt( 9 ) + 1;

		StringBuffer buffer = new StringBuffer();
		while ( buffer.length() < 18 )
			buffer.append( numbers[random.nextInt( 999 )] );

		return new Long( buffer.toString() );
	}

	private SupportRandom() {}
}
