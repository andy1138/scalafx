package scalafx.imaginej

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

import javafx.scene.text.{TextAlignment, FontWeight}

import scalafx.application.JFXApp
import scalafx.stage.Stage
import scalafx.scene.Scene
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle
import scalafx.scene.layout.HBox
import scalafx.geometry.Insets
import scalafx.scene.control.Label
import scalafx.scene.image.ImageView._
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.text.Font
import scalafx.event.EventIncludes.eventClosureWrapper


/**
 * @author Luc Duponcheel <luc.duponcheel@gmail.com>
 *
 * based upon:
 *
 * http://docs.oracle.com/javafx/2.0/ui_controls/label.htm
 *
 */

object ScalaFX_Controls_01 extends JFXApp {
  val labelsImageView = new ImageView {
    image = new Image(this, "images/labels.jpg")
  }
  val searchLabel = new Label {
    text = "Search"
    graphic = labelsImageView
    font = Font.font("Arial", 30)
    textFill = Color.web("#0076a3")
    textAlignment = TextAlignment.JUSTIFY
  }
  val valuesLabel = new Label {
    text = "Values"
    font = Font.font("Cambria", 32)
    rotate = 270
    translateY = 50
  }
  val wrappedLabel = new Label {
    text = "A label that needs to be wrapped"
    wrapText = true
    translateY = 50
    prefWidth = 100
    onMouseEntered = {
      println("mouse entered")
      scaleX = 1.5
      scaleY = 1.5
    }
    onMouseExited = {
      println("mouse exited")
      scaleX = 1
      scaleY = 1
    }
  }
  val hBox = new HBox {
    spacing = 10
    content = List(
      searchLabel,
      valuesLabel,
      wrappedLabel
    )
  }
  stage = new Stage {
    title = "ScalaFX Controls 01"
    width = 400
    height = 180
    scene = new Scene {
      content = hBox
    }
  }
}

/*
    Label label1 = new Label("Search");
    label1.setGraphic(new ImageView(image));
    label1.setFont(new Font("Arial", 30));
    label1.setTextFill(Color.web("#0076a3"));
    label1.setTextAlignment(TextAlignment.JUSTIFY);


    Label label2 = new Label("Values");
    label2.setFont(Font.font("Cambria", 32));
    label2.setRotate(270);
    label2.setTranslateY(50);

    label3.setWrapText(true);
    label3.setTranslateY(50);
    label3.setPrefWidth(100);



*/