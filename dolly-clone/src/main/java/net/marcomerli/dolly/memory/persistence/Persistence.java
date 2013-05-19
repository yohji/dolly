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

public interface Persistence {

	// Memory
	String MEMORY_LOCATION = "memory.ser";
	String MEMORY_DIRECTORY = ".dolly_mem";

	// Store
	String CREATOR = "creator.ser";
	String INFO_CONFIGURATION = "configuration.ser";
	String INFO_SYSTEM = "information.ser";
	String STORE_CLONE = "clone.ser";
	String STORE_MESSAGE_LISTENER = "message_list.ser";
	String STORE_MESSAGE_SENDER = "message_send.ser";
	String STORE_HOSTS = "hosts.ser";
	String MEMORY_SWAP = "swap.ser";

	// File
	String FILE_DOLLY = "dolly";
	String FILE_DOLLY_JAR = "dolly.jar";
	String FILE_DOLLY_ZIP = "dolly.zip";
}
