package ensemble


//import ensemble.pages.DocPage;
import javafx.scene.control.TreeItem
//import javafx.event.Event;
//import javafx.event.EventHandler;
//import javafx.scene.Node;

import javafx.collections.ObservableList;
import scalafx.scene.Node
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._


abstract class Page(val name:String) extends TreeItem[String](name) {

//    public void setName(String name){
//        setValue(name);
//    }
//
//    public String getName() {
//        return getValue();
//    }
//
//    public String getPath() {
//        if (getParent() == null) {
//            return getName();
//        } else {
//            String parentsPath = ((Page)getParent()).getPath();
//            if (parentsPath.equalsIgnoreCase("All")) {
//                return getName();
//            } else {
//                return  parentsPath + "/" + getName();
//            }
//        }
//    }
//
    def createView:Node
//
//    /** find a child with a '/' deliminated path */
    def  getChild( path: String):Page = {
      println(">>getChild - start " + path)
        var firstIndex = path.indexOf('/');
        var childName = if(firstIndex == -1)  path else path.substring(0,firstIndex)
        var anchor = "";
        if (childName.indexOf('#') != -1) {
            val parts = childName.split("#");
            childName = parts(0)
            anchor = if(parts.length == 2)  parts(1) else null
        }
        val c = getChildren()
        c.foreach { child =>
                println(">>getChild - loop " + c)

//        for (TreeItem child : getChildren()) {
            val page = child.asInstanceOf[Page]
        println(">>getChild - cmp " + page.name + "   " + childName)

            if(page.name.equals(childName)) {
                if(firstIndex == -1) {
//                    if (page.isInstanceOf[DocPage] == true ) {
//                        (page.asInstanceOf[DocPage]).setAnchor(anchor);
//                    }
                 println(">>getChild - return page" + page) 
                    return page;
                } else {
                  println(">>getChild - return getchild")
                    return page.getChild(path.substring(firstIndex+1));
                }
            }
        }
      println(">>getChild - return null")
        return null;
    }
//
//    override def toString: String = {
//        toString("")
//    }
//
//    def toString( indent:String ):String = {
//        String out = indent + "["+getName()+"] "+getClass().getSimpleName();
//        ObservableList<TreeItem<String>> childModel = getChildren();
//        if (childModel!=null) {
//            for (TreeItem child:childModel) {
//                out += "\n"+((Page)child).toString("    "+indent);
//            }
//        }
//        return out;
//    }
//
//    public static class GoToPageEventHandler implements EventHandler {
//        private String pagePath;
//
//        public GoToPageEventHandler(String pagePath) {
//            this.pagePath = pagePath;
//        }
//
//        public void handle(Event event) {
//            Ensemble2.getEnsemble2().goToPage(pagePath);
//        }
//    }
}
