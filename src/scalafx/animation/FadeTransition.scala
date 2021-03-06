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

package scalafx.animation

import javafx.{animation => jfxa}
import scalafx.Includes._
import scalafx.util.SFXDelegate
import scalafx.scene.Node
import javafx.util.Duration

object FadeTransition extends AnimationStatics {
  implicit def sfxFadeTransition2jfx(v: FadeTransition) = v.delegate
}

class FadeTransition(override val delegate:jfxa.FadeTransition = new jfxa.FadeTransition()) extends Transition(delegate) with SFXDelegate[jfxa.FadeTransition] {
  def byValue = delegate.byValueProperty
  def byValue_=(by: Double) {
    byValue() = by
  }

  def duration = delegate.durationProperty
  def duration_=(d: Duration) {
    duration() = d
  }

  def fromValue = delegate.fromValueProperty
  def fromValue_=(from: Double) {
    fromValue() = from
  }

  def node = delegate.nodeProperty
  def node_=(n: Node) {
    node() = n
  }

  def toValue = delegate.toValueProperty
  def toValue_=(to: Double) {
    toValue() = to
  }
}