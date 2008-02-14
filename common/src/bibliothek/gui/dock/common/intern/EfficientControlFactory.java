/*
 * Bibliothek - DockingFrames
 * Library built on Java/Swing, allows the user to "drag and drop"
 * panels containing any Swing-Component the developer likes to add.
 * 
 * Copyright (C) 2007 Benjamin Sigg
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 * 
 * Benjamin Sigg
 * benjamin_sigg@gmx.ch
 * CH - Switzerland
 */
package bibliothek.gui.dock.common.intern;

import javax.swing.JFrame;

import bibliothek.gui.DockController;
import bibliothek.gui.Dockable;
import bibliothek.gui.dock.FlapDockStation;
import bibliothek.gui.dock.ScreenDockStation;
import bibliothek.gui.dock.SplitDockStation;
import bibliothek.gui.dock.action.ListeningDockAction;
import bibliothek.gui.dock.common.CWorkingArea;

/**
 * A factory that uses the most efficient elements, can only be used in
 * environments where global events can be observed.
 * @author Benjamin Sigg
 */
public class EfficientControlFactory implements CControlFactory {
    public DockController createController() {
        return new DockController();
    }

    public FlapDockStation createFlapDockStation() {
        return new FlapDockStation();
    }

    public ScreenDockStation createScreenDockStation( JFrame owner ) {
        return new ScreenDockStation( owner );
    }
    
    public SplitDockStation createSplitDockStation(){
    	 return new SplitDockStation(){
             @Override
             protected ListeningDockAction createFullScreenAction() {
                 return null;
             }
             @Override
             public void setFrontDockable( Dockable dockable ) {
                 // ignore
             }
         };
    }
    
    public CWorkingArea createWorkingArea( String id ) {
        return new CWorkingArea( id, false );
    }
}