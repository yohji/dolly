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

import static org.junit.Assert.*;

import java.io.Serializable;

import net.marcomerli.dolly.test.TestCaseSupport;

import org.junit.Test;

public class PersistenceManagerTest extends TestCaseSupport {

	protected static final String SERIALIZATION_FILE = "test.ser";

	@Test
	public void testLoad() throws Exception
	{
		PersistenceHandler.load( SERIALIZATION_FILE, Klass.class );
		Klass klass = (Klass) PersistenceHandler.read( SERIALIZATION_FILE );

		assertNotNull( klass );
		assertNotNull( klass.key );
		assertNull( klass.index );
	}

	@Test
	public void testDelete() throws Exception
	{
		PersistenceHandler.delete( SERIALIZATION_FILE );
		Klass klass = (Klass) PersistenceHandler.read( SERIALIZATION_FILE );

		assertNull( klass );
	}
}

class Klass implements Serializable {

	private static final long serialVersionUID = -4663543629227807171L;
	public String key = "value";
	public transient Number index = 0;
}
