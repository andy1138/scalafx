package ensemble.controls

import scalafx.scene.layout.Region
import scalafx.stage.Stage

class WindowResizeButton(stage:Stage, stageMinimumWidth:Double, stageMinimumHeight:Double) extends Region {
	id = "window-resize-button"
////	prefSize = (11,11) 
	prefWidth = 11
	prefHeight = 11
	
//	onMousePressed = {
//	  val dragOffsetX = (stage.x + stage.width) - screenX
//    dragOffsetY = (stage.getY() + stage.getHeight()) - e.getScreenY()
//    e.consume()
//	}
	
}