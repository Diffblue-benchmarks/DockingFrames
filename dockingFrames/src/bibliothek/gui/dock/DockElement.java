/**
 * Bibliothek - DockingFrames
 * Library built on Java/Swing, allows the user to "drag and drop"
 * panels containing any Swing-Component the developer likes to add.
 * 
 * Copyright (C) 2007 Benjamin Sigg
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * Benjamin Sigg
 * benjamin_sigg@gmx.ch
 * 
 * Wunderklingerstr. 59
 * 8215 Hallau
 * CH - Switzerland
 */


package bibliothek.gui.dock;


/**
 * An element in the hierarchy of dockables and stations.
 * @author Benjamin Sigg
 */
public interface DockElement {
    /**
     * Returns <code>this</code> if <code>this</code> is an instance of 
     * {@link Dockable}. Otherwise <code>null</code> is returned.
     * @return <code>this</code> or <code>null</code>
     */
    public Dockable asDockable();

    /**
     * Returns <code>this</code> if <code>this</code> is an instance of 
     * {@link DockStation}. Otherwise <code>null</code> is returned.
     * @return <code>this</code> or <code>null</code>
     */
    public DockStation asDockStation();
    
    /**
     * Gets the unique name of the {@link DockFactory} which can read
     * and write elements of this type.
     * @return the id of the factory
     */
    public String getFactoryID();
}