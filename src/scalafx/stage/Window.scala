package scalafx.stage

import javafx.{stage => jfxs}
import scalafx.util.SFXDelegate
import scalafx.application.JFXApp
import javafx.{event => jfxe}

object Window {
  implicit def sfxWindow2jfx(v: Window) = v.delegate
}

class Window(override val delegate:jfxs.Window = JFXApp.STAGE) extends SFXDelegate[jfxs.Window] {
 
  def eventDispatcher = delegate.eventDispatcherProperty
  def eventDispatcher_=(v: jfxe.EventDispatcher) {
    eventDispatcher.set(v)
  }

  def focused = delegate.focusedProperty
  
  def height = delegate.heightProperty
  
  def onCloseRequest = delegate.onCloseRequestProperty
  def onCloseRequest_=(aeh: jfxe.EventHandler[jfxs.WindowEvent]) {
    onCloseRequest.set(aeh)
  }

  def onHidden = delegate.onHiddenProperty
  def onHidden_=(aeh: jfxe.EventHandler[jfxs.WindowEvent]) {
    onHidden.set(aeh)
  }
  
  
  def onHiding = delegate.onHidingProperty
  def onHiding_=(aeh: jfxe.EventHandler[jfxs.WindowEvent]) {
    onHiding.set(aeh)
  }
  
  def onShowing = delegate.onShowingProperty
  def onShowing_=(aeh: jfxe.EventHandler[jfxs.WindowEvent]) {
    onShowing.set(aeh)
  }
  
//  onShown
  def onShown = delegate.onShownProperty
  def onShown_=(aeh: jfxe.EventHandler[jfxs.WindowEvent]) {
    onShown.set(aeh)
  }

  def opacity = delegate.opacityProperty
  def opacity_=(v: Double) {
    opacity.set(v)
  }
  
//  scene
  def scene = delegate.sceneProperty
//  showing
  def showing = delegate.showingProperty
//  width
  def width = delegate.widthProperty
  
  
//  x
//  y
  
  
  def x = delegate.xProperty
  def y = delegate.yProperty


}