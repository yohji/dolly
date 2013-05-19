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
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.URL;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import net.marcomerli.dolly.error.ErrorSupport;

import org.apache.commons.io.IOUtils;

public class SupportNetwork implements Support {

	private static final int TIMEOUT = 1000;

	public enum PortRange
	{
		ALL_PORTS ( 1, 65535 ), 
		WELL_KNOWN_PORTS ( 1, 1023 ), 
		REGISTERED_PORTS ( 1024, 49151 ), 
		DYNAMIC_PORTS ( 49152, 65535 );

		Integer from, to;
		PortRange(Integer from, Integer to) {

			this.from = from;
			this.to = to;
		}
	}

	public static File download( String url, String dir ) throws ErrorSupport
	{
		File file = null;
		try {
			String n = url.substring( url.lastIndexOf( "/" ), url.length() );
			file = new File( dir + File.separator + n );
			file.delete();
			file.createNewFile();

			URL u = new URL( url );
			byte[] bytes = IOUtils.toByteArray( u.openStream() );
			IOUtils.write( bytes, new FileOutputStream( file ) );
		}
		catch ( Exception e ) {
			throw new ErrorSupport( e );
		}

		return file;
	}

	public static String localAddress() throws ErrorSupport
	{
		String address = null;
		try {
			Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();
			while ( e.hasMoreElements() ) {
				for ( InterfaceAddress a : e.nextElement().getInterfaceAddresses() ) {
					address = a.getAddress().getHostAddress();
					if ( address.indexOf( ":" ) == -1 && !address.equals( "127.0.0.1" ) )
						return address;
				}
			}
		}
		catch ( Exception e ) {
			throw new ErrorSupport( e );
		}

		return address;
	}

	public static Boolean ping( String address ) throws ErrorSupport
	{
		Boolean isAlive = false;
		try {
			InetAddress inetAddress = InetAddress.getByName( address );
			isAlive = inetAddress.isReachable( TIMEOUT );
		}
		catch ( Exception e ) {
			throw new ErrorSupport( e );
		}

		return isAlive;
	}

	public static List<Integer> scan( String address ) throws ErrorSupport
	{
		return scan( address, PortRange.ALL_PORTS );
	}

	public static List<Integer> scan( String address, PortRange range ) throws ErrorSupport
	{
		return scan( address, range.from, range.to );
	}

	public static List<Integer> scan( String address, Integer from, Integer to ) throws ErrorSupport
	{
		List<Integer> scan = new LinkedList<Integer>();

		try {
			for ( Integer p = from ; p <= to ; p++ ) {
				if ( scan( address, p ) )
					scan.add( p );
			}
		}
		catch ( Exception e ) {
			throw new ErrorSupport( e );
		}

		return scan;
	}

	public static Boolean scan( String address, Integer port ) throws ErrorSupport
	{
		try {
			SecurityManager sm = System.getSecurityManager();				
			if ( sm != null )
				sm.checkConnect( address, port );

			try {
				Socket socket = new Socket( address, port );
				if ( socket.isConnected() && socket.isBound() )
					return true;
			}
			catch ( ConnectException e ) {
				return false;
			}
		}
		catch ( Exception e ) {
			throw new ErrorSupport( e );
		}
		
		return false;
	}

	private SupportNetwork() {}
}
