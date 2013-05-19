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

package net.marcomerli.dolly.memory.store;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import net.marcomerli.dolly.core.model.HierarchyTree;
import net.marcomerli.dolly.support.SupportString;
import net.marcomerli.dolly.system.model.Clone;

public final class StoreClone implements Store<Long, Clone> {

	private static final long serialVersionUID = 7031234511234126234L;
	private transient boolean locked = false;
	private transient boolean touched = false;

	private HierarchyTree<Long, Clone> clones;

	public StoreClone() {

		clones = new HierarchyTree<Long, Clone>();
	}

	@SuppressWarnings( "rawtypes" )
	public static StoreClone render( String toString )
	{
		StoreClone store = new StoreClone();
		if ( !toString.equals( new HierarchyTree().toString() ) )
			store.clones = TreeRender.render( toString );
		else
			store.clones = new HierarchyTree<Long, Clone>();

		return store;
	}

	public void clear()
	{
		clones.clear();
		touched = true;
	}

	public boolean contains( Long key )
	{
		return clones.contains( key ) ? true : false;
	}

	public Clone find( Long uid )
	{
		return clones.get( uid );
	}

	public boolean isLocked()
	{
		return locked;
	}

	public boolean isTouched()
	{
		return touched;
	}

	public Set<Long> keySet()
	{
		return clones.keySet();
	}

	public boolean merge( StoreClone storeClone )
	{
		boolean merge = clones.merge( storeClone.clones );
		if ( merge )
			touched = true;

		return merge;
	}

	public void remove( Long key )
	{
		clones.remove( key );
		touched = true;
	}

	public void setLocked( boolean locked )
	{
		this.locked = locked;
	}

	public void setRoot( Clone root )
	{
		clones.setRoot( root.uid(), root );
		touched = true;
	}

	public void setTouched( boolean touched )
	{
		this.touched = touched;
	}

	public int size()
	{
		return clones.size();
	}

	public void store( Long father, Clone clone )
	{
		clones.put( father, clone.uid(), clone );
		touched = true;
	}

	@Override
	public String toString()
	{
		return clones.toString();
	}

	public List<Clone> values()
	{
		return clones.values();
	}

	private static class TreeRender {

		private static final int LEFT = (int) '{';
		private static final int RIGHT = (int) '}';

		private static boolean isLeaf( String node )
		{
			boolean leaf = false;
			int index = node.indexOf( "{}" );
			if ( index == node.lastIndexOf( "{}" ) && index == node.length() - 2 )
				leaf = true;

			return leaf;
		}

		public static HierarchyTree<Long, Clone> render( String toString )
		{
			HierarchyTree<Long, Clone> tree = new HierarchyTree<Long, Clone>();
			Clone root = new Clone( SupportString.cut( toString, ':', '{', false ) );
			tree.setRoot( root.uid(), root );

			if ( !isLeaf( toString ) ) {
				String daugthers = SupportString.cut( toString, "{", "}" );
				if ( !isLeaf( daugthers ) ) {

					boolean level = false;
					if ( daugthers.contains( "," ) ) {
						for ( String s : daugthers.split( "\\," ) ) {
							if ( isLeaf( s ) )
								level = true;
							else {
								level = false;
								break;
							}
						}
					}

					if ( level ) {
						for ( String s : daugthers.split( "\\," ) ) {
							Clone c = new Clone( SupportString.cut( s, ':', '{', false ) );
							tree.put( root.uid(), c.uid(), c );
						}
					} else
						renderNode( splitNode( daugthers ), root.uid(), tree );

				} else {
					Clone c = new Clone( SupportString.cut( daugthers, ':', '{', false ) );
					tree.put( root.uid(), c.uid(), c );
				}
			}

			return tree;
		}

		private static void renderNode( List<String> nodes, Long father, HierarchyTree<Long, Clone> tree )
		{
			for ( String node : nodes ) {
				Clone clone = new Clone( SupportString.cut( node, ':', '{', false ) );
				tree.put( father, clone.uid(), clone );

				if ( !isLeaf( node ) ) {
					String daugthers = SupportString.cut( node, "{", "}" );
					if ( daugthers.contains( "," ) ) {
						for ( String daugther : daugthers.split( "\\," ) ) {
							daugther.trim();
							Clone c = new Clone( SupportString.cut( daugther, ':', '{', false ) );
							tree.put( clone.uid(), c.uid(), c );
						}

					} else {
						Clone c = new Clone( SupportString.cut( daugthers, ':', '{', false ) );
						tree.put( clone.uid(), c.uid(), c );
					}

					renderNode( splitNode( daugthers ), clone.uid(), tree );
				}
			}
		}

		private static List<String> splitNode( String node )
		{
			List<String> split = new LinkedList<String>();
			int level = 0;
			int first = 0;
			int last = 0;

			for ( int i = 0 ; i < node.length() ; i++ ) {
				if ( node.codePointAt( i ) == LEFT && node.codePointAt( i + 1 ) != RIGHT ) {
					level++;
					last = i;
				}

				if ( node.codePointAt( i ) == RIGHT && node.codePointAt( i - 1 ) != LEFT ) {
					level--;
					last = i;

					if ( level == 0 ) {
						String entry = node.substring( first, ++last );
						if ( entry.startsWith( "," ) )
							entry = entry.substring( 2 );

						split.add( entry );
						first = last;
					}
				}
			}

			return split;
		}

		private TreeRender() {}
	}
}
