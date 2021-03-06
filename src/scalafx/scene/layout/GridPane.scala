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
import javafx.{geometry => jfxg}
import javafx.scene.{layout => jfxsl}
import scalafx.{scene => sfxs }
import scalafx.Includes._
import scalafx.util.SFXDelegate
import javafx.geometry.HPos
import javafx.geometry.VPos
import javafx.scene.layout.Priority

object GridPane {
  implicit def sfxGridPane2jfx(v: GridPane) = v.delegate
  // added object method (is this a good practice?)
  def setVAlignment(node: sfxs.Node, vp: jfxg.VPos) = javafx.scene.layout.GridPane.setValignment(node, vp)
  
  def setConstraints( node: sfxs.Node, columnIndex:Int, rowIndex:Int) {
  	javafx.scene.layout.GridPane.setConstraints(node,columnIndex, rowIndex)
  }
  def setConstraints( node: sfxs.Node, columnIndex:Int, rowIndex:Int, columnspan:Int, rowspan: Int, 
       halignment:HPos, valignment:VPos, hgrow:Priority, vgrow: Priority) {
  	jfxsl.GridPane.setConstraints(node, columnIndex, rowIndex, rowspan, columnspan, halignment, valignment, hgrow, vgrow)
  }
}

class GridPane(override val delegate:jfxsl.GridPane = new jfxsl.GridPane()) extends Pane with SFXDelegate[jfxsl.GridPane] {
  /**
   * Renamed from alignment to avoid a conflict with the pseudo-property for alignment on Node
   */
  def innerAlignment = delegate.alignmentProperty
  def innerAlignment_=(v: jfxg.Pos) {
    innerAlignment() = v
  }

  def gridLinesVisible = delegate.gridLinesVisibleProperty
  def gridLinesVisible_=(v: Boolean) {
    gridLinesVisible() = v
  }

  def hgap = delegate.hgapProperty
  def hgap_=(v: Double) {
    hgap() = v
  }

  def vgap = delegate.vgapProperty
  def vgap_=(v: Double) {
    vgap() = v
  }

  def columnConstraints = delegate.getColumnConstraints
  def columnConstraints_=(c: Iterable[ColumnConstraints]) {
    columnConstraints.setAll(c.map(_.delegate))
  }

  def rowConstraints = delegate.getRowConstraints
  def rowConstraints_=(c: Iterable[RowConstraints]) {
    rowConstraints.setAll(c.map(_.delegate))
  }
}
