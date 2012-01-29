package scalafx.scene.control

import javafx.scene.{control => jfxsc}

import scalafx.Includes._
import scalafx.util.SFXDelegate
import scalafx.scene._

object ToolBar {
  implicit def sfxToolBar2jfx(v: ToolBar) = v.delegate
}

class ToolBar (override val delegate:jfxsc.ToolBar = new jfxsc.ToolBar) extends Control(delegate) with SFXDelegate[jfxsc.ToolBar] {

  def items = delegate.getItems()
}