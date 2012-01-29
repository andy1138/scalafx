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
package ensemble.pages
import ensemble.Page
import java.net.URI
import java.io.BufferedReader
import java.io.InputStreamReader
import scalafx.Includes._
import ensemble.model.SampleInfo
import scalafx.scene.layout.VBox
import scalafx.scene.Node
import scalafx.scene.control.Label
import scalafx.scene.layout.StackPane
import ensemble.Sample
import scalafx.Includes._
import scalafx.scene.control.Button
import scalafx.scene.layout.BorderPane
import scalafx.scene.control.ToolBar
import scalafx.scene.control.ScrollPane
import scalafx.scene.control.Tab
import scalafx.scene.control.TabPane
import scalafx.scene.layout.GridPane
import scalafx.scene.text.Text
import javafx.scene.layout.Priority
import scalafx.scene.control.Separator
import javafx.scene.control.Hyperlink
import ensemble.SampleHelper
import javafx.scene.web.WebEngine
import scalafx.scene.image.ImageView
import scalafx.scene.image.Image
import scalafx.scene.shape.Rectangle
import ensemble.Ensemble
import javafx.scene.control.ContentDisplay
import javafx.scene.web.WebView
import scalafx.scene.paint.Color
import scalafx.scene.Group
import javafx.scene.paint.CycleMethod
import javafx.scene.effect.BlendMode
import scalafx.scene.paint.Stop
//import javafx.scene.web.WebView;
//import javafx.stage.FileChooser;
//
//import javax.swing.filechooser.FileSystemView;
//import java.io.*;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.net.URL;
//import java.util.HashMap;
//import java.util.Map;
//import javafx.application.ConditionalFeature;
//import javafx.application.Platform;
//import javafx.geometry.Pos;
//import javafx.scene.text.TextAlignment;

/**
 * SamplePage
 */
class SamplePage( name:String,  sourceFileUrl:String, pageToClone:SamplePage) extends Page(name) {
    var  engine:WebEngine = null
    var  webView:WebView = null
//    private SampleInfo sampleInfo;
//    private Class sampleClass;
//    private String rawCode;
//    private String htmlCode;

    
    var sampleInfo:SampleInfo = _
    var sampleClass:Class[_] = _
    
    def this( pageToClone:SamplePage) {
      this(pageToClone.name, "", pageToClone)
        sampleInfo = pageToClone.sampleInfo
        sampleClass = pageToClone.sampleClass
    }
    
    def this( name:String,  sourceFileUrl:String) {
      this( name, sourceFileUrl, null)
    	val unqualifiedClassName = sourceFileUrl.substring(sourceFileUrl.lastIndexOf('/')+1, sourceFileUrl.length()-6)
//    try {
        // load src into String
        val builder = scala.io.Source.fromFile(new URI(sourceFileUrl)).getLines.toList.foldLeft("") { (v,s) => s+v}
    
//        val builder = new StringBuilder
//        val uri = new URI(sourceFileUrl)
//        val in = uri.toURL().openStream
//        val reader = new BufferedReader(new InputStreamReader(in));
//        val line;
//        while((line = reader.readLine()) != null) {
//            builder.append(line);
//            builder.append('\n');
//        }
//        reader.close();
        // parse sample info
        sampleInfo = new SampleInfo(sourceFileUrl, unqualifiedClassName, builder.toString());
        // load class
        val sampleClass = getClass().getClassLoader().loadClass(sampleInfo.className);
//    } catch (ClassNotFoundException e) {
//        e.printStackTrace();
//    } catch (URISyntaxException e) {
//        e.printStackTrace();
//    } catch (IOException e) {
//        e.printStackTrace();
//    }
    // Add API back references
//    Ensemble2 ensemble2 = Ensemble2.getEnsemble2();
//    for (String apiClassPath : sampleInfo.getApiClasspaths()) {
//        String path = Pages.API_DOCS+'/'+apiClassPath.replace('.','/');
//        DocPage docPage = (DocPage) ensemble2.getPages().getPage(path);
//        if (docPage != null) {
//            docPage.getRelatedSamples().add(this);
//        }
//    }
    }
        

//    def this( pageToClone:SamplePage) {
//    	this(pageToClone.getName)
//        sampleInfo = pageToClone.sampleInfo;
//        sampleClass = pageToClone.sampleClass;
//    }

    def getSampleInfo: SampleInfo =  {
        sampleInfo
    }

    override def  createView: Node = {
        // check if 3d sample and on supported platform
        //System.out.println("sampleClass.getSuperclass() == Sample3D.class = " + (sampleClass.getSuperclass() == Sample3D.class));
        //System.out.println("Platform.isSupported(ConditionalFeature.SCENE3D) = " + Platform.isSupported(ConditionalFeature.SCENE3D));
//        if (sampleClass.getSuperclass() == Sample3D.class && !Platform.isSupported(ConditionalFeature.SCENE3D)) {
//            Label error =  new Label("JavaFX 3D is currently not supported on your configuration.");
//            error.setStyle("-fx-text-fill: orangered; -fx-font-size: 1.4em;");
//            error.setWrapText(true);
//            error.setAlignment(Pos.CENTER);
//            error.setTextAlignment(TextAlignment.CENTER);
//            return error;
//        }
        //  load the code
        val htmlCode = loadCode
//        try {
            // create main grid
            //final FlowSafeVBox main = new FlowSafeVBox();
            val main = new VBox {
              spacing = 8
          		styleClass.add("sample-page")
          		
//          		content.add( new Label {
//          				text = name
//        					styleClass.add("page-header")
//          			}
//          		)
          		
        		}
            // create header
            val header = new Label {
              text = name
              styleClass.add("page-header")
            }
            
            main.content.add(header)
            
            // create sample area
            val sampleArea = new StackPane()
            VBox.setVgrow(sampleArea, Priority.SOMETIMES)
            main.content.add(sampleArea)
            
            // create sample
            val sample = sampleClass.newInstance.asInstanceOf[Sample]
            sampleArea.content.add(sample)
            // create sample controls
            val sampleControls = sample.getControls
            if(sampleControls != null) {
                val subHeader = new Label {
                	text = "Play with these:"
                  styleClass.add("page-subheader")
                }
                main.children.add(subHeader)
                main.children.add(sampleControls)
            }
            // create code view
            webView = getWebView
            webView.setPrefWidth(300)
            engine.loadContent(htmlCode)
            
            val codeToolBar = new ToolBar {
            	id = "code-tool-bar"
            }
            
            val saveProjectButton = new Button {
              text = "Save Netbeans Project..."
            	onAction = {
//                File initialDir = FileSystemView.getFileSystemView().getDefaultDirectory();
//                ///System.out.println("initialDir = " + initialDir);
//                FileChooser fileChooser = new FileChooser();
//                fileChooser.setTitle("Save Netbeans Project As:");
//                fileChooser.setInitialDirectory(initialDir);
//                File result = fileChooser.showSaveDialog(saveProjectButton.getScene().getWindow());
//                if (result != null) SampleProjectBuilder.createSampleProject(result, sampleInfo.getSourceFileUrl(), sampleInfo.getResourceUrls());
            }
            }
            val copyCodeButton = new Button {
              text = "Copy Source"
          		onAction = {
//                    val clipboardContent = new HashMap[DataFormat, Object]();
//                    clipboardContent.put(DataFormat.PLAIN_TEXT, rawCode);
//                    clipboardContent.put(DataFormat.HTML, htmlCode);
//                    Clipboard.getSystemClipboard().setContent(clipboardContent);
                }
            }
            codeToolBar.items.addAll(saveProjectButton,copyCodeButton)
            
            val codeTabPane = new BorderPane {
            	top = codeToolBar
        			center = webView
            }

            // create border pane for main content and sidebar
            val borderPane = new BorderPane {
            	right = createSideBar(sample)
        			center =main
            }
            
            val scrollPane = new ScrollPane {
	            styleClass.add("noborder-scroll-pane");
	            content = borderPane
	            fitToWidth = true
	            fitToHeight = true
	            minWidth = 685
            }
            
            // create tab pane
            val tabPane = new SamplePageView(sample)
            tabPane.setId("source-tabs")
            
            val sampleTab = new Tab {
	            text = "Sample"
	            content = scrollPane
	            closable = false
            }
            
            val sourceTab = new Tab {
              text = "Source Code"
              content = codeTabPane
              closable = false
            }
//            tabPane.getSelectionModel().selectedItemProperty().addListener(new InvalidationListener() {
//                @Override public void invalidated(Observable ov) {
//                    if (tabPane.getSelectionModel().getSelectedItem() == sampleTab) {
//                        sample.play();
//                    } else {
//                        sample.stop();
//                    }
//                }
//            });
            tabPane.tabs.addAll(sampleTab,sourceTab)
            tabPane
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new Text("Failed to create sample because of ["+e.getMessage()+"]");
//        }
    }

    def createSideBar( sample: Sample): Node =  {
        val sidebar = new GridPane {
	        styleClass.add("right-sidebar")
	        maxWidth = Double.MaxValue
	        maxHeight = Double.MaxValue
        }
        
        var sideRow = 0
        // create side bar content
        // description
        val discTitle = new Label {
          text = "Description"
      		styleClass.add("right-sidebar-title")
        }
        
        GridPane.setConstraints(discTitle, 0, sideRow);
        sideRow += 1
        sidebar.children.add(discTitle)
        
        val disc = new Text {
          text = sampleInfo.description
          wrappingWidth = 200
          styleClass.add("right-sidebar-body")
        }
        
        GridPane.setConstraints(disc, 0, sideRow)
        sideRow += 1

        sidebar.children.add(disc)
        // docs
        if (sampleInfo.apiClasspaths !=null && sampleInfo.apiClasspaths.length>0) {
            val separator = new Separator
            GridPane.setConstraints(separator, 0, sideRow)
            sideRow += 1

            sidebar.children.add(separator)
            
            val docsTitle = new Label {
              text = "API Documentation"
              styleClass.add("right-sidebar-title");
            }
            
            GridPane.setConstraints(docsTitle, 0, sideRow);
            sideRow += 1

            sidebar.children.add(docsTitle)
            sampleInfo.apiClasspaths foreach { docPath => 
//            for (String docPath:sampleInfo.apiClasspaths) {
                val  link = new Hyperlink(docPath);
//                link.setOnAction(new GoToPageEventHandler(Pages.API_DOCS+'/'+docPath.replace('.','/')));
                GridPane.setConstraints(link, 0, sideRow)
                sideRow += 1

                sidebar.children.add(link)
            }
        }
        // related
        if (sampleInfo.relatesSamplePaths!=null && sampleInfo.relatesSamplePaths.length>0) {
            val separator = new Separator
            GridPane.setConstraints(separator, 0, sideRow)
            sideRow += 1

            sidebar.children.add(separator)
            val relatedTitle = new Label {
              text = "Related"
              styleClass.add("right-sidebar-title")
            }
            GridPane.setConstraints(relatedTitle, 0, sideRow)
            sideRow += 1

            sidebar.children.add(relatedTitle)
            sampleInfo.relatesSamplePaths foreach { relatedPath =>
//            for (String relatedPath:sampleInfo.relatesSamplePaths) {
                val parts = relatedPath.split("/");
                val link = new Hyperlink(parts(parts.length-1) )
                //convert path
                var path = ""
                parts foreach { part =>  
//                for(String part:parts) {
                    path = path+'/'+ SampleHelper.formatName(part)
                }
//                link.setOnAction(new GoToPageEventHandler(Pages.SAMPLES+path));
                ///System.out.println("Pages.SAMPLES+path==>" + Pages.SAMPLES + path);
                GridPane.setConstraints(link, 0, sideRow);
                sideRow += 1
                sidebar.children.add(link)
            }
        }
        // sample extras
        val sampleExtras = sample.getSideBarExtraContent
        if (sampleExtras != null) {
            val separator = new Separator
            GridPane.setConstraints(separator, 0, sideRow)
            sideRow += 1

            sidebar.children.add(separator)
            val docsTitle = new Label {
              text = sample.getSideBarExtraContentTitle
              styleClass.add("right-sidebar-title")
            }
            GridPane.setConstraints(docsTitle, 0, sideRow)
            sideRow += 1

            sidebar.children.add(docsTitle)
            GridPane.setConstraints(sampleExtras, 0, sideRow)
            sideRow += 1

            sidebar.children.add(sampleExtras)
        }
        sidebar
    }

    def getIcon:Node =  {
        val url = sampleClass.getResource(sampleClass.getSimpleName()+".png")
        if (url != null) {
  	        val imageView = new ImageView { image = new Image(this, url.toString()) }
            return imageView
        } else {
  	        val imageView = new ImageView { image = new Image(this, "images/icon-overlay.png") }
            imageView.setMouseTransparent(true);
            
            import scala.collection.JavaConverters._
            val overlayHighlight = new Rectangle {
              x = -8
              y = -8
              width = 130
              height = 130
              fill = (new javafx.scene.paint.LinearGradient(0,0.5,0,1,true, 
                  CycleMethod.NO_CYCLE,  
                  (new javafx.scene.paint.Stop(0,Color.BLACK) :: new javafx.scene.paint.Stop(1,Color.web("#444444")) :: Nil).asJava  
                  ))
             	opacity = 0.8
            	mouseTransparent = true
            	blendMode = BlendMode.ADD
            }
            val background = new Rectangle {
              x = -8
              y = -8
              width = 130
              height = 130
            	fill = Color.web("#b9c0c5")
            }
            
            val group = new Group {
              children.add( background )
            }
            val clipRect = new Rectangle {
              width = 114
              height = 114
            }
            clipRect.setArcWidth(38);
            clipRect.setArcHeight(38);
            
            group.setClip(clipRect)
            val content = createIconContent
            if (content != null) {
                content.translateX = ( ((114-content.boundsInParent.getValue.getWidth)/2) - content.boundsInParent.getValue.getMinX  )
                content.setTranslateY( ((114-content.boundsInParent.getValue.getHeight)/2) - content.boundsInParent.getValue.getMinY)
                group.children.add(content)
            }
            group.getChildren().addAll(overlayHighlight,imageView);
            // Wrap in extra group as clip dosn't effect layout without it
            new Group(group)
        }
    }

    def  createIconContent: Node = {
//        try {
            val createIconContent = sampleClass.getDeclaredMethod("createIconContent")
            createIconContent.invoke(sampleClass).asInstanceOf[Node]
//        } catch (NoSuchMethodException e) {
//            System.err.println("Sample ["+getName()+"] is missing a icon.");
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//        return null;
    }

    def createTile:Node = {
        val tile = new Button {
//          graphic = name.trim,icon
      		minSize = (140,140)
          prefSize = (140,140)
        	maxSize = (140,140)
        	contentDisplay = ContentDisplay.TOP
        	styleClass.clear
        	styleClass.add("sample-tile")
        	onAction =  {
              Ensemble.goToPage(SamplePage.this)
          }
        }
        return tile;
    }

    def getWebView: WebView = {
        if (engine == null) {
            webView = new WebView
            engine = webView.getEngine
        }
        webView
    }

//    private String shCoreJs;
//    private String shBrushJScript;
//    private String shCoreDefaultCss;


    def loadCode:String = {
//        // load syntax highlighter
//        if (shCoreJs == null) shCoreJs = Utils.loadFile(Ensemble2.class.getResource("syntaxhighlighter/shCore.js")) +";";
//        if (shBrushJScript == null) shBrushJScript = Utils.loadFile(Ensemble2.class.getResource("syntaxhighlighter/shBrushJava.js"));
//        if (shCoreDefaultCss == null) shCoreDefaultCss =
//                Utils.loadFile(Ensemble2.class.getResource("syntaxhighlighter/shCoreDefault.css")).replaceAll("!important","");
//        // load and convert source
//        String source = SampleProjectBuilder.loadAndConvertSampleCode(sampleInfo.getSourceFileUrl());
//        // store raw code
//        rawCode = source;
//        // escape < & >
//        source = source.replaceAll("&","&amp;");
//        source = source.replaceAll("<","&lt;");
//        source = source.replaceAll(">","&gt;");
//        source = source.replaceAll("\"","&quot;");
//        source = source.replaceAll("\'","&apos;");
//        // create content
//        StringBuilder html = new StringBuilder();
//        html.append("<html>\n");
//        html.append("    <head>\n");
//        html.append("    <script type=\"text/javascript\">\n");
//        html.append(shCoreJs);
//        html.append('\n');
//        html.append(shBrushJScript);
//        html.append("    </script>\n");
//        html.append("    <style>\n");
//        html.append(shCoreDefaultCss);
//        html.append('\n');
//        html.append("        .syntaxhighlighter {\n");
//        html.append("			overflow: visible;\n");
//        if (Utils.isMac()) {
//            html.append("			font: 12px Ayuthaya !important; line-height: 150% !important; \n");
//            html.append("		}\n");
//            html.append("		code { font: 12px Ayuthaya !important; line-height: 150% !important; } \n");
//        } else {
//            html.append("			font: 12px monospace !important; line-height: 150% !important; \n");
//            html.append("		}\n");
//            html.append("		code { font: 12px monospace !important; line-height: 150% !important; } \n");
//        }
//        html.append("		.syntaxhighlighter .preprocessor { color: #060 !important; }\n");
//        html.append("		.syntaxhighlighter .comments, .syntaxhighlighter .comments a  { color: #009300 !important; }\n");
//        html.append("		.syntaxhighlighter .string  { color: #555 !important; }\n");
//        html.append("		.syntaxhighlighter .value  { color: blue !important; }\n");
//        html.append("		.syntaxhighlighter .keyword  { color: #000080 !important; }\n");
//        html.append("    </style>\n");
//        html.append("    </head>\n");
//        html.append("<body>\n");
//        html.append("    <pre class=\"brush: java;gutter: false;toolbar: false;\">\n");
//        html.append(source);
//        html.append('\n');
//        html.append(
//                "    </pre>\n" +
//                        "    <script type=\"text/javascript\"> SyntaxHighlighter.all(); </script>\n" +
//                        "</body>\n" +
//                        "</html>\n");
//        htmlCode = html.toString();
      ""
    }

    class SamplePageView(sample:Sample ) extends TabPane {
        def stop {
          sample.stop
        }
    }
    
    
    override def toString:String = {
      "SamplePage[ name:"+ name+", sourceFileUrl"+sourceFileUrl+"]"
    }
}
