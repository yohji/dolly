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

package net.marcomerli.dolly.system.network;

/**
 * Protocol layout: <br />
 * <p>
 * [HEADER], [uid], [TYPE], [content]
 * </p>
 */
public interface Protocol {

	// Header
	byte HEADER = (byte) 0xFE;
	byte DIV = (byte) 0xFF;

	// Type
	/**
        * Content layout: <br />
        * <p>
        * ..., [thisClone]
        * </p>
        */
	byte BIRTH = (byte) 0xAA;

	/**
        * Content layout: <br />
        * <p>
        * ..., [storeClone]
        * </p>
        */
	byte HIERARCHY = (byte) 0xAB;

	/**
        * Content layout: <br />
        * <p>
        * ..., [upgrade], [DIV], [jar]
        * </p>
        */
	byte UPGRADE = (byte) 0xAE;

	/**
        * Content layout: <br />
        * <p>
        * ..., [fileName], [DIV], [bytes]
        * </p>
        */
	byte FILE = (byte) 0xAF;

	/**
        * Content layout: <br />
        * <p>
        * ..., [fileUid], [DIV], [messageBytes]
        * </p>
        */
	byte PROXY = (byte) 0xBA;

	byte TEST = (byte) 0xEF;
}
