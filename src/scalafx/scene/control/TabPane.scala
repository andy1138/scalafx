/*
 * Copyright (c) 2012, ScalaFX Project
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

package scalafx.scene.control

import javafx.scene.{control => jfxsc}
import scalafx.util.SFXDelegate
import scalafx.Includes._
import javafx.scene.control.TabPane.TabClosingPolicy
//import scalafx.util.SFXDelegate
 import scala.collection.JavaConversions._
 import scala.collection.JavaConverters._

object TabPane {
  implicit def sfxTabPane2jfx(v: TabPane) = v.delegate
  
  def STYLE_CLASS_FLOATING = jfxsc.TabPane.STYLE_CLASS_FLOATING
  def UNAVAILABLE = jfxsc.TabPane.TabClosingPolicy.UNAVAILABLE
}

class TabPane(override val delegate:jfxsc.TabPane = new jfxsc.TabPane) extends Control(delegate) with SFXDelegate[jfxsc.TabPane] {

  def tabs = delegate.getTabs
  def tabs_=(c: Iterable[Tab]) {
    tabs.addAll( (c.map(_.delegate)).toList)
  }

  
  def tabClosingPolicy = delegate.getTabClosingPolicy
  def tabClosingPolicy_=(s:TabClosingPolicy) {
    delegate.setTabClosingPolicy(s)
  }
  

}