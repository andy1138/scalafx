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

package ensemble.config
import scalafx.scene.layout.VBox
import ensemble.Pages
import scalafx.scene.layout.BorderPane
import scalafx.scene.control.Label
import javafx.geometry.Pos
import scalafx.scene.text.Text
import scalafx.scene.layout.HBox
import scalafx.stage.Window
import javafx.event.EventHandler
import javafx.scene.input.MouseEvent
import scalafx.scene.control.Button
import javafx.event.ActionEvent
import java.util.Properties
import scalafx.scene.control.Tab
import scalafx.scene.control.TabPane
import scalafx.scene.control.Control
import scalafx.geometry.Insets
import ensemble.Ensemble
import scalafx.scene.layout.GridPane
import scalafx.scene.control.TextField
import javafx.stage.FileChooser
import javafx.geometry.HPos
import javafx.geometry.VPos
import javafx.scene.layout.Priority
import java.io.File
import java.io.FileOutputStream
import scalafx.stage.Stage

import scalafx.Includes._


class ProxyDialog( owner:Stage, pages:Pages ) extends VBox {
    final val PROXY_ADDRESS = "ProxyAddress"
    final val PROXY_PORT = "ProxyPort"
    final val DOCS_LOCATION = "DocsLocation"
    /** Drag offsets for window dragging */
//    private double mouseDragOffsetX, mouseDragOffsetY;
//    private Button okBtn;
//    private Pages pages;
//    private final Window owner;
//    private final LocalDirPanel localDirPanel;
//    private final ProxyPanel proxyPanel;
//    private final Tab proxyTab;
//    private final Tab docsTab;
//    private final TabPane options;

        id = "ProxyDialog"
        spacing = 10
        maxSize = (430, Control.usePrefSize)
        // block mouse clicks
        onMouseClicked = new EventHandler[MouseEvent] {
          override def handle( event:MouseEvent  ) {
            event.consume
          }
        }



        final val explanation = new Text("An Internet connection is required to "
                + "access some samples and documentation. Either specify a web "
                + "proxy or a path to the documentation installed locally.")
        explanation.setWrappingWidth(400)

//        val explPane = new BorderPane {
//          center = explanation
//          margin = Insets(5, 5, 5, 5)
//        }
//        VBox.setMargin(explPane, Insets(5, 5, 5, 5))
        
//        new VBox {
//          content = Seq( new BorderPane {
//	          center = explanation
//	          margin = Insets(5, 5, 5, 5)
//	        })
//          margin = Insets(5, 5, 5, 5)
//        }
//        explPane.setCenter(explanation)
//        BorderPane.setMargin(explanation, Insets(5, 5, 5, 5))

        // create title
//        val title = new Label {
//          text = "Internet Access Needed"
//	        id = "title"
//	        minHeight = 22
//	        prefHeight = 22
//	        maxWidth = Double.MaxValue
//	        alignment = Pos.CENTER
//        }
//        children.add(title)

//    		val proxyPanel = new ProxyPanel
//        val proxyTab = new Tab {
//          text = "Set Proxy"
//        }
//        content.add( proxyPanel )

//        val localDirPanel = new LocalDirPanel
//        val docsTab = new Tab {
//          text = "Locate Docs Locally"
//          content = localDirPanel
//				}

        val options = new TabPane {
	        styleClass.add(TabPane.STYLE_CLASS_FLOATING)
	        tabClosingPolicy = TabPane.UNAVAILABLE
	        tabs = Seq(
	            new Tab {
	            	text = "Set Proxy"
          			content = new ProxyPanel
	            },
	            new Tab {
	            	text = "Locate Docs Locally"
          			content = new LocalDirPanel
	            }
            )
        }

        val cancelBtn = new Button {
          text = "Cancel"
	        id = "cancelButton"
        	onAction = {
            Ensemble.hideModalMessage
          }
	        minWidth = 74
	        prefWidth = 74
        }
//        HBox.setMargin(cancelBtn, Insets(0, 8, 0, 0))
        
        val okBtn = new Button {
          text = "Save"
	        id = "saveButton"
	        defaultButton = true
	        disable = true
        	onAction = new EventHandler[ActionEvent] {
            override def handle( actionEvent:ActionEvent  ) {
              val settings = new Properties
//              if (options.getSelectionModel().getSelectedItem() == proxyTab) {
//                  settings.put(PROXY_ADDRESS, proxyPanel.getHostNameBox.getText())
//                  settings.put(PROXY_PORT, proxyPanel.getPortBox.getText())
//                  //proxy for internet
//                  setWebProxy(proxyPanel.getHostNameBox.getText, proxyPanel.getPortBox.getText)
//              } else {
////                  settings.put(DOCS_LOCATION, localDirPanel.getDocsUrl)
////                  retrieveLocalDocs(localDirPanel.getDocsUrl());
//              }
              // hide dialog
              Ensemble.hideModalMessage
              // try fetching docs
              getDocsInBackground(false, new Runnable() {
                def run() {
                  // save settings to file if we were successful getting docs with the settings
//                  try {
                    val ensembleSettings = new File(System.getProperty("user.home"),".ensemble-settings")
                    val fout = new FileOutputStream(ensembleSettings)
                    settings.save(fout, "JavaFX 2.0 - Ensemble Settings")
                    fout.flush()
                    fout.close()
//                  } catch (Exception e) {
//                    e.printStackTrace()
//                  }
                }
              })
            }
	        }
	        minWidth = 74
	        prefWidth = 74
        }

//        val bottomBar = new HBox {
//          spacing = 0
//          alignment = Pos.BASELINE_RIGHT
//          content = Seq (
//              cancelBtn, 
//              okBtn            
//          )
//        }
//        VBox.setMargin(bottomBar, Insets(20, 5, 5, 5))
        
//        val vBottomBar = new VBox {
//          content = Seq ( new HBox {
//	          spacing = 0
//	          alignment = Pos.BASELINE_RIGHT
//	          content = Seq (
//	            cancelBtn, 
//	            okBtn            
//	          )
//	        })
//          margin = Insets(20, 5, 5, 5)
//        }

        content = Seq (
          new Label {
	          text = "Internet Access Needed"
		        id = "title"
		        minHeight = 22
		        prefHeight = 22
		        maxWidth = Double.MaxValue
		        alignment = Pos.CENTER
	        },
          new VBox {
	          content = Seq( new BorderPane {
		          center = explanation
		          margin = Insets(5, 5, 5, 5)
		        })
	          margin = Insets(5, 5, 5, 5)
	        }, 
          options, 
          new VBox {
	          content = Seq ( new HBox {
		          spacing = 0
		          alignment = Pos.BASELINE_RIGHT
		          content = Seq (
		              new HBox {
		                margin = Insets(0, 8, 0, 0)
		                content = Seq( cancelBtn )
		              }, 
		            okBtn            
		          )
		        })
	          margin = Insets(20, 5, 5, 5)
	        }
            )
//        children.addAll(
//            title,
//            explPane, 
//            options, 
//            vBottomBar
//            )

    def getDocsInBackground(showProxyDialogOnFail: Boolean, callBackOnSuccess: Runnable) {
//        val task = new FetchDocListTask(Ensemble.getDocsUrl)
//        task.stateProperty().addListener(new ChangeListener<Worker.State>() {
//            public void changed(ObservableValue<? extends Worker.State> ov, Worker.State t, Worker.State newState) {
//                if (newState == Worker.State.SUCCEEDED) {
//                    // extract all the docs pages from the all classes page
//                    DocsHelper.extractDocsPagesFromAllClassesPage(
//                            (CategoryPage)Ensemble2.getEnsemble2().getPages().getDocs(),
//                            task.getValue(), 
//                            Ensemble2.getEnsemble2().getDocsUrl());
//                    // update docs pages cross links to samples
//                    DocsHelper.syncDocPagesAndSamplePages(
//                            (CategoryPage)Ensemble2.getEnsemble2().getPages().getSamples());
//                    if (callBackOnSuccess != null) callBackOnSuccess.run();
//                } else if (newState == Worker.State.FAILED) {
//                    if (showProxyDialogOnFail) {
//                        Ensemble2.getEnsemble2().showProxyDialog();
//                    }
//                }
//            }
//        });
//        new Thread(task).start();
    }
    
//    /**
//     * Load proxy or documentation location settings from properties
//     */
//    public void loadSettings() {
//        // check if we have saved setings
//        File ensembleSettings = new File(System.getProperty("user.home"),".ensemble-settings");
//        if (ensembleSettings.exists() && ensembleSettings.isFile()) {
//            final Properties settings = new Properties();
//            try {
//                settings.load(new FileInputStream(ensembleSettings));
//                if (settings.containsKey(DOCS_LOCATION)) {
//                    String location = settings.getProperty(DOCS_LOCATION);
//                    options.getSelectionModel().select(docsTab);
//                    localDirPanel.setDocsUrl(location);
//                    retrieveLocalDocs(location);
//                } else if (settings.containsKey(PROXY_ADDRESS) && settings.containsKey(PROXY_PORT)) {
//                    String address = settings.getProperty(PROXY_ADDRESS);
//                    String port = settings.getProperty(PROXY_PORT);
//                    proxyPanel.hostNameBox.setText(address);
//                    proxyPanel.portBox.setText(port);
//                    options.getSelectionModel().select(proxyTab);
//                    setWebProxy(address, port);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }

    def setWebProxy( hostName:String, port:String) {
//        if (hostName != null) {
//            System.setProperty("http.proxyHost", hostName);
//            if (port != null) {
//                System.setProperty("http.proxyPort", port);
//            }
//        }
//        Ensemble2.getEnsemble2().setDocsUrl(Ensemble2.DEFAULT_DOCS_URL);
    }

//    public void retrieveLocalDocs(String docUrl) {
//        Ensemble2.getEnsemble2().setDocsUrl(docUrl);
//    }

    class ProxyPanel extends GridPane {
//        TextField hostNameBox;
//        TextField portBox;

        padding = Insets(8)
        hgap = 5.0F
        vgap = 5.0F

        var rowIndex = 0

        val label2 = new Label {
          text = "Host Name"
          id = "proxy-dialog-label"
        }
        GridPane.setConstraints(label2, 0, rowIndex)

        val label3 = new Label {
          text = "Port"
          id = "proxy-dialog-label"
        }
        GridPane.setConstraints(label3, 1, rowIndex)
        children.addAll(label2, label3)

        rowIndex += 1
        
        val hostNameBox = new TextField();
        hostNameBox.setPromptText("proxy.mycompany.com");
        hostNameBox.setPrefColumnCount(20);
        GridPane.setConstraints(hostNameBox, 0, rowIndex);

        val portBox = new TextField();
        portBox.setPromptText("8080");
        portBox.setPrefColumnCount(10);
        GridPane.setConstraints(portBox, 1, rowIndex);

//        ChangeListener<String> textListener = new ChangeListener<String>() {
//            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
//                okBtn.setDisable(
//                        hostNameBox.getText() == null || hostNameBox.getText().isEmpty()
//                        || portBox.getText() == null || portBox.getText().isEmpty());
//            }
//        };
//        hostNameBox.textProperty().addListener(textListener);
//        portBox.textProperty().addListener(textListener);

        children.addAll(hostNameBox, portBox)

        def getHostNameBox:TextField = {
          hostNameBox
        }

        def getPortBox:TextField = {
          portBox
        }
    }

     class LocalDirPanel extends GridPane {
        var docsUrl = ""
        var textField:TextField = new TextField {
          editable = false
        }

        padding = Insets(8)
        hgap = 5.0F
        vgap = 5.0F

        var rowIndex = 0
        
        val parentDirLabel = new Label {
          text = "Local javadoc index.html file"
      		id = "parent-dir-label"
        }
        GridPane.setConstraints(parentDirLabel, 0, rowIndex)
        children.add(parentDirLabel)

        rowIndex += 1

        GridPane.setConstraints(textField, 0, rowIndex, 1, 1, HPos.CENTER, VPos.CENTER,  Priority.ALWAYS, Priority.NEVER)

        val button = new Button {
          text = "Browse..."
        	id = "browseButton"
          minWidth = Control.usePrefSize
//        	onAction = new EventHandler[ActionEvent] {
//            override def handle( actionEvent:ActionEvent  ) {	          
//              val fileChooser = new FileChooser
//              fileChooser.setTitle("JavaFX 2.0 Javadoc location")
//              val filter = new FileChooser.ExtensionFilter("html", "*.html")
//              fileChooser.getExtensionFilters().add(filter)
//              val selectedFile = fileChooser.showOpenDialog(owner)
//              
//              okBtn.setDisable(selectedFile == null);
//              if (selectedFile != null) {
//                textField.text = (selectedFile.getAbsolutePath());
//                docsUrl = selectedFile.toURI().toString();
//                docsUrl = docsUrl.substring(0,docsUrl.lastIndexOf('/') + 1);
//              }
//            }
//          }
        }
        GridPane.setConstraints(button, 1, rowIndex);
        children.addAll(textField, button);

        def getDocsUrl {
             docsUrl
        }

        def setDocsUrl( docsUrl:String) {
//            this.docsUrl = docsUrl;
//            textField.setText(docsUrl+"index.html");
        }
    }
}
