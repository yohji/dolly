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

package net.marcomerli.dolly.system.model;

import java.io.Serializable;

public class Clone implements Serializable {

	private static final long serialVersionUID = -743431776800583498L;
	private String address;
	private Integer port;
	private Long uid;

	public Clone() {

		uid = 0L;
		address = "0.0.0.0";
		port = 0;
	}

	public Clone(Long uid, String address, Integer port) {

		this.uid = uid;
		this.address = address;
		this.port = port;
	}

	public Clone(String clone) {

		String[] props = clone.split( "\\|" );

		this.uid = Long.valueOf( props[0] );
		this.address = props[1];
		this.port = Integer.valueOf( props[2] );
	}

	public String address()
	{
		return address;
	}

	@Override
	public boolean equals( Object clone )
	{
		Clone c = (Clone) clone;
		if ( c.uid.equals( uid ) && c.address.equals( address ) && c.port.equals( port ) )
			return true;

		return false;
	}

	public Integer port()
	{
		return port;
	}

	public Clone setAddress( String address )
	{
		this.address = address;
		return this;
	}

	public Clone setPort( Integer port )
	{
		this.port = port;
		return this;
	}

	public Clone setUid( Long uid )
	{
		this.uid = uid;
		return this;
	}

	public byte[] toBytes()
	{
		return toString().getBytes();
	}

	@Override
	public String toString()
	{
		return uid + "|" + address + "|" + port;
	}

	public Long uid()
	{
		return uid;
	}
}
