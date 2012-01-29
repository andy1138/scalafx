package scalafx



//             ________                                __                   ________   __    __
//           /   _____/\                             /  /\                /   _____/\/__/\ /  /\
//          /  /\_____\/  ________     _______      /  / /   ________    /  /\_____\/\  \ /  / /
//         /  /_/___    /   _____/\  /_____   /\   /  / /  /_____   /\  /  /_/__      \  /  / /
//        /______  /\  /  /\_____\/  \____/  / /  /  / /   \____/  / / /   ____/\      \/  / /
//        \_____/ / / /  / /       /  ___   / /  /  / /  /  ___   / / /  /\____\/      /  / /\
//       ______/ / / /  /_/___    /  /__/  / /  /  / /  /  /__/  / / /  / /           /  / /\ \
//     /________/ / /________/\  /________/ /  /__/ /  /________/ / /__/ /           /__/ /  \ \
//     \________\/  \________\/  \________\/   \__\/   \________\/  \__\/            \__\/ \__\/
//
//                                  ScalaFX Programming Library Examples
//

import javafx.scene.paint.Color

import scalafx.application.JFXApp
import scalafx.stage.Stage
import scalafx.scene.Scene
import scalafx.geometry.Insets
import scalafx.event.EventIncludes.eventClosureWrapper
import scalafx.beans.property.PropertyIncludes.jfxStringProperty2sfx
import scalafx.beans.property.PropertyIncludes.jfxDoubleProperty2sfx
import scalafx.scene.control.Button
import scalafx.scene.text.Text
import scalafx.scene.shape.Rectangle
import scalafx.beans.property.{ObjectProperty}
import scalafx.scene.layout.{StackPane, HBox, BorderPane}

/**
 * @author Luc Duponcheel <luc.dup...@gmail.com>
 */
object App11 extends JFXApp {

  val text: Text = new Text {
  }

  val rectangle = new Rectangle {
    fill = Color.LIGHTBLUE
  }

  val stackPane = new StackPane {
    content = List(rectangle, text)
  }

  val textProperty = new ObjectProperty[Text](new Object(), "text")
  textProperty onChange {
    (_, _, newValue) =>
      text.wrappingWidth = newValue.wrappingWidth()
      text.text = newValue.text()
      rectangle.width = newValue.boundsInLocal.getValue.getWidth() + 10
      rectangle.height = newValue.boundsInLocal.getValue.getHeight() + 10
  }

  textProperty() = new Text {
    wrappingWidth = 60
    text = "some text here to have an idea about what is happening"
  }

  val button1: Button = new Button {
    text = "text"
    onAction = {
      println(text())
      textProperty() = new Text {
        wrappingWidth = 40
        text = button1.text()
      }
    }
  }
  val button2: Button = new Button {
    text = "some larger text"
    onAction = {
      println(text())
      textProperty() = new Text {
        wrappingWidth = 60
        text = button2.text()
      }
    }
  }
  stage = new Stage {
    title = "App11"
    scene = new Scene {
      content = new BorderPane {
        top = new HBox {
          fill = Color.PINK
          spacing = 10
          padding = Insets(8, 6, 8, 6)
          content = List(
            button1,
            button2,
            stackPane)
        }
      }
    }
  }
}
