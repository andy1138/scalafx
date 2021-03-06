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

package scalafx.beans.property

import javafx.scene.paint.Color
import javafx.scene.paint.Paint

import org.scalatest.matchers.ShouldMatchers._
import org.scalatest.{BeforeAndAfterEach, FlatSpec}
import javafx.beans.{property => jfxbp}
import scalafx.Includes._

import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith

@RunWith(classOf[JUnitRunner])
class ReadOnlyObjectPropertySpec extends FlatSpec with BeforeAndAfterEach {
  val bean = new Object()
  var readOnlyObjectProperty: jfxbp.ReadOnlyObjectProperty[Paint] = null
  var objectProperty1: jfxbp.ObjectProperty[Paint] = null
  var objectProperty2: jfxbp.ObjectProperty[Paint] = null
  var sfxObjectProperty: ObjectProperty[Paint] = null
  var booleanProperty: jfxbp.BooleanProperty = null

  override def beforeEach() {
    readOnlyObjectProperty = new ReadOnlyObjectProperty[Paint](bean, "Test Read-only Object", Color.BLACK)
    objectProperty1 = new ObjectProperty[Paint](bean, "Test Object 1")
    objectProperty2 = new ObjectProperty[Paint](bean, "Test Object 2")
    sfxObjectProperty = new ObjectProperty[Paint](bean, "SFX Test Object")
    booleanProperty = new BooleanProperty(bean, "Test Boolean")
  }

  "An Object Property" should "start with the value we gave it" in {
    readOnlyObjectProperty.value should equal (Color.BLACK)
  }

  it should "return its value using apply" in {
    readOnlyObjectProperty() should equal (Color.BLACK)
  }

  it should "know its name" in {
    readOnlyObjectProperty.name should equal ("Test Read-only Object")
  }

  it should "know its bean" in {
    readOnlyObjectProperty.bean should equal (bean)
  }

  it should "be bindable to another Object Property" in {
    objectProperty1 <== readOnlyObjectProperty
    objectProperty1() should equal (Color.BLACK)
    objectProperty1.unbind()
  }
  
  it should "support bindable infix equality with a property" in {
    booleanProperty <== readOnlyObjectProperty === objectProperty1
    objectProperty1() = Color.WHITE
    booleanProperty() should equal (false)
    objectProperty1() = Color.BLACK
    booleanProperty() should equal (true)
    booleanProperty.unbind()
  }

  it should "support bindable infix equality with an sfx property" in {
    booleanProperty <== readOnlyObjectProperty === sfxObjectProperty
    sfxObjectProperty() = Color.WHITE
    booleanProperty() should equal (false)
    sfxObjectProperty() = Color.BLACK
    booleanProperty() should equal (true)
    booleanProperty.unbind()
  }
  
  it should "support bindable infix equality with a constant" in {
    booleanProperty <== readOnlyObjectProperty === Color.WHITE
    booleanProperty() should equal (false)
    booleanProperty <== readOnlyObjectProperty === Color.BLACK
    booleanProperty() should equal (true)
    booleanProperty.unbind()
  }

  it should "support bindable infix inequality with a property" in {
    booleanProperty <== readOnlyObjectProperty =!= objectProperty1
    objectProperty1() = Color.WHITE
    booleanProperty() should equal (true)
    objectProperty1() = Color.BLACK
    booleanProperty() should equal (false)
    booleanProperty.unbind()
  }

  it should "support bindable infix inequality with an sfx property" in {
    booleanProperty <== readOnlyObjectProperty =!= sfxObjectProperty
    sfxObjectProperty() = Color.WHITE
    booleanProperty() should equal (true)
    sfxObjectProperty() = Color.BLACK
    booleanProperty() should equal (false)
    booleanProperty.unbind()
  }

  it should "support bindable infix inequality with a constant" in {
    booleanProperty <== readOnlyObjectProperty =!= Color.WHITE
    booleanProperty() should equal (true)
    booleanProperty <== readOnlyObjectProperty =!= Color.BLACK
    booleanProperty() should equal (false)
    booleanProperty.unbind()
  }
}
