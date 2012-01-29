/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package scalafx.stage

import javafx.{stage => jfxs}


object StageIncludes extends StageIncludes
trait StageIncludes {
  implicit def jfxStage2sfx(a: jfxs.Stage) = new Stage(a)
  implicit def jfxWindow2sfx(b: jfxs.Window) = new Window(b)
}
