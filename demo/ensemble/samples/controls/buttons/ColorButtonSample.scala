/*
 * Copyright (c) 2008, 2011 Oracle and/or its affiliates.
 * All rights reserved. Use is subject to license terms.
 *
 * This file is available and licensed under the following license:
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  - Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the distribution.
 *  - Neither the name of Oracle Corporation nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package ensemble.samples.controls.buttons


import ensemble.Sample
import scalafx.scene.layout.HBox
import scalafx.scene.control.Button
import scalafx.scene.layout.VBox


//import javafx.scene.control.Button;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;

/**
 * Buttons with different background colors.
 *
 * @see javafx.scene.control.Button
 * @related controls/buttons/GraphicButton
 * @related controls/buttons/HyperlinkSample
 */
class ColorButtonSample extends Sample {

  val hBox = new HBox {
  	spacing = 5
  }

  for( i <- 1 to 7) {
//  for(int i=0; i<7; i++) {
      val b = new Button {
      	text = "Color"
      	style = "-fx-base: rgb("+(10*i)+","+(20*i)+","+(10*i)+");"
      }
      hBox.children.add(b)
  }
  
  val hBox2 = new HBox {
  	spacing = 5
  	translateY = 30
  }
  
  val red = new Button {
  		text = "Red"
			style = "-fx-base: red;"
  }
  
  val orange = new Button {
	  text = "Orange"
	  style = "-fx-base: orange;"
  }
  
  val yellow = new Button {
    text = "Yellow"
    style = "-fx-base: yellow;"
  }
  
  val green = new Button {
   text = "Green"
   style ="-fx-base: green;"
  }
  
  val blue = new Button {
  	text = "Blue"
  	style = "-fx-base: rgb(30,170,255);"
  }
  
  val indigo = new Button {
  	text = "Indigo"
  	style = "-fx-base: blue;"
  }
  
  val violet = new Button {
  	text = "Violet"
  	style = "-fx-base: purple;"
  }
  
  hBox2.getChildren().add(red);
  hBox2.getChildren().add(orange);
  hBox2.getChildren().add(yellow);
  hBox2.getChildren().add(green);
  hBox2.getChildren().add(blue);
  hBox2.getChildren().add(indigo);
  hBox2.getChildren().add(violet);

  val vBox = new VBox {
    spacing = 20
    children.addAll(hBox,hBox2)
  }
  children.add(vBox)
}

