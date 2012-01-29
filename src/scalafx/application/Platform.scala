package scalafx.application

import javafx.application.{Platform => jPlatform}
import javafx.application.ConditionalFeature

object Platform {
  
  def exit {
    jPlatform.exit
  }
  
  def isFxApplicationThread: Boolean = jPlatform.isFxApplicationThread
  
  def isSupported(f:ConditionalFeature) = jPlatform.isSupported(f)
  
  def runLater( f:Runnable ) = jPlatform.runLater(f)
  
  

}