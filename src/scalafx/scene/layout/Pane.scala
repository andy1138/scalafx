/*
 * Copyright (c) 2011, ScalaFX Project
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the ScalaFX Project nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE SCALAFX PROJECT OR ITS CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED
 * AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package scalafx.scene.layout

import collection.JavaConversions._
import javafx.scene.{layout => jfxsl}
import scalafx.scene.Node
import scalafx.util.SFXDelegate
import scalafx.geometry.Insets
import javafx.geometry.HPos
import javafx.geometry.VPos

import scalafx.Includes._

object Pane {
  implicit def sfxPane2jfx(v: Pane) = v.delegate
}


class Pane(override val delegate:jfxsl.Pane = new jfxsl.Pane()) extends Region with SFXDelegate[jfxsl.Pane] {
  def getChildren = delegate.getChildren
  def children = getChildren

  def content = getChildren
  def content_=(c: Iterable[Node]) {
    content.setAll(c.map(_.delegate))
  }
  
  def layoutInArea( child:Node,
                areaX:Double,
                areaY:Double,
                areaWidth:Double,
                areaHeight:Double,
                areaBaselineOffset:Double,
                 margin:Insets,
                  fillWidth:Boolean,
                fillHeight:Boolean,
                 halignment:HPos,
                 valignment:VPos) {
    delegate.layoutInArea( child,
                 areaX,
                 areaY,
                 areaWidth,
                 areaHeight,
                 areaBaselineOffset,
                 margin,
                 fillWidth, fillHeight,
                 halignment,
                 valignment)
  }
}
