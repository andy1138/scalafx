package scalafx.scene.image

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

import javafx.{scene => jfxs, geometry => jfxg}
import jfxs.{image => jfxsi}
import scalafx.Includes._
import scalafx.util.SFXDelegate
import scalafx.scene.Node

object ImageView {
  implicit def sfxImageView2jfx(iv: ImageView) = iv.delegate
}

class ImageView(override val delegate: jfxsi.ImageView = new jfxsi.ImageView()) extends Node(delegate) with SFXDelegate[jfxsi.ImageView] {

  def fitHeight = delegate.fitHeightProperty

  def fitHeight_=(v: Double) {
    fitHeight() = v
  }

  def fitWidth = delegate.fitWidthProperty

  def fitWidth_=(v: Double) {
    fitWidth() = v
  }

  def image = delegate.imageProperty

  def image_=(v: jfxsi.Image) {
    image() = v
  }

  def preserveRatio = delegate.preserveRatioProperty

  def preserveRatio_=(v: Boolean) {
    smooth() = v
  }

  def smooth = delegate.smoothProperty

  def smooth_=(v: Boolean) {
    smooth() = v
  }

  def viewport = delegate.viewportProperty

  def viewport_=(v: jfxg.Rectangle2D) {
    viewport() = v
  }

  def x = delegate.xProperty

  def x_=(v: Double) {
    x() = v
  }

  def y = delegate.yProperty

  def y_=(v: Double) {
    y() = v
  }

}