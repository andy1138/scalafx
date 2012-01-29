
/*
 * Copyright (c) 2008, 2011 Oracle and/or its affiliates.
 * All rights reserved. Use is subject to license terms.
 *
 * This file is available and licensed under the following license:
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  - Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the distribution.
 *  - Neither the name of Oracle Corporation nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package ensemble.controls
import scalafx.scene.layout.VBox
import scalafx.stage.Stage
import scalafx.scene.control.Button
import javafx.event.EventHandler
import javafx.event.ActionEvent
import scalafx.application.Platform
import javafx.stage.Screen
import javafx.geometry.Rectangle2D

import scalafx.Includes._

/**
 * Vertical box with 3 small buttons for window close, minimize and maximize.
 */
class WindowButtons(stage:Stage) extends VBox {
    spacing = 4
    private var backupWindowBounds:Rectangle2D = null
    private var maximized = false;

    
    val closeBtn = new Button {
      id = "window-close"
      onAction = {
        Platform.exit
      }
    }
    val minBtn = new Button {
  		id = "window-min"
		  onAction = {
				stage.setIconified(true)
			}
    }


    val maxBtn = new Button {
      id = "window-max"
      onAction = {
        toogleMaximized
      }
    }
    

    content.addAll(closeBtn, minBtn, maxBtn);

    def toogleMaximized() {        
        val screen = Screen.getScreensForRectangle(stage.getX(), stage.getY(), 1, 1).get(0);
        if (maximized) {
            maximized = false
            if (backupWindowBounds != null) {
                stage.setX(backupWindowBounds.getMinX)
                stage.setY(backupWindowBounds.getMinY)
                stage.setWidth(backupWindowBounds.getWidth)
                stage.setHeight(backupWindowBounds.getHeight)
            }
        } else {
            maximized = true
            backupWindowBounds = new Rectangle2D(stage.getX, stage.getY, stage.getWidth, stage.getHeight)
            stage.setX(screen.getVisualBounds.getMinX)
            stage.setY(screen.getVisualBounds.getMinY)
            stage.setWidth(screen.getVisualBounds.getWidth)
            stage.setHeight(screen.getVisualBounds.getHeight)
        }
    }

    def isMaximized = maximized
}
