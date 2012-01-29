
package ensemble

import ensemble.controls.WindowResizeButton
import ensemble.controls.BreadcrumbBar
import ensemble.controls.SearchBox
import ensemble.controls.WindowButtons
import ensemble.config.ProxyDialog
import scalafx.application.JFXApp
import scalafx.stage.Stage
import scalafx.scene.layout.StackPane
import scalafx.scene.layout.BorderPane
import scalafx.application.Platform
import scalafx.scene.Scene
import scalafx.scene.image.ImageView
import scalafx.scene.image.Image
import scalafx.scene.layout.BorderPane
import scalafx.scene.control.Button
import scalafx.scene.control.ToolBar
import scalafx.scene.layout.HBox
import scalafx.geometry.Insets
import scalafx.scene.control.Control
import scalafx.scene.layout.Region
import scalafx.scene.layout.Pane
import scalafx.scene.layout.GridPane
import scalafx.scene.control.ToggleButton
import scalafx.scene.control.SplitPane
import scalafx.scene.control.ToggleGroup
import scalafx.scene.control.TreeView
import scalafx.scene.Node
import javafx.stage.StageStyle
import javafx.scene.DepthTest
import javafx.application.ConditionalFeature
import javafx.scene.layout.Priority
import javafx.scene.input.MouseEvent
import javafx.event.EventHandler
import javafx.event.ActionEvent
import javafx.animation.TimelineBuilder
import javafx.animation.KeyFrame
import javafx.util.Duration
import javafx.animation.KeyValue
import javafx.animation.Interpolator
import scalafx.scene.shape.Circle
import scalafx.Includes._
import javafx.beans.InvalidationListener
import javafx.beans.Observable
import javafx.beans.value.ChangeListener
import javafx.beans.value.ObservableValue
import javafx.scene.control.TreeItem

/**
 * Ensemble Main Application
 */
object Ensemble extends JFXApp  {
//    static {
//        // Enable using system proxy if set
//        System.setProperty("java.net.useSystemProxies", "true");
//    }
    final val DEFAULT_DOCS_URL = "http://download.oracle.com/javafx/2.0/api/"
    
    var changingPage = false
    
    /**
     * Start the application
     * 
     * @param stage The main application stage
     */
//    def start(stage:Stage ) {
//        ensemble2 = this;
        stage = new Stage {
        	title = "Ensemble"
        	initStyle = StageStyle.UNDECORATED
    		}
    
        // set default docs location
//        docsUrl = System.getProperty("docs.url") != null ? System.getProperty("docs.url") : DEFAULT_DOCS_URL; 
        // create root stack pane that we use to be able to overlay proxy dialog
        val layerPane = new StackPane
        // check if applet
        // create window resize button
        val windowResizeButton = new WindowResizeButton(stage, 1020,700)
        
        // create root
        

        val root = new BorderPane( new javafx.scene.layout.BorderPane {
        			override def layoutChildren {
                super.layoutChildren
                windowResizeButton.autosize
                windowResizeButton.setLayoutX(getWidth - windowResizeButton.getLayoutBounds.getWidth)
                windowResizeButton.setLayoutY(getHeight - windowResizeButton.getLayoutBounds.getHeight)
        			}
        		}) {

        	styleClass.add("application") 
        	id = "root"
        }
        layerPane.depthTest = DepthTest.DISABLE
        layerPane.children.add(root)
        // create scene
        val is3dSupported = Platform.isSupported(ConditionalFeature.SCENE3D)
        
        val scene = new Scene(layerPane, 1020, 700, is3dSupported)
        if (is3dSupported) {
            //RT-13234
//            scene.camera = new PerspectiveCamera()
        }
        scene.getStylesheets.addAll(this.getClass.getResource("ensemble2.css").toExternalForm())
        
        // create modal dimmer, to dim screen when showing modal dialogs
        val modalDimmer = new StackPane {
          id = "ModalDimmer"
	        onMouseClicked = new EventHandler[MouseEvent] {
	          override def handle( event:MouseEvent  ) {
	            event.consume
              hideModalMessage
	          }
	        }
          visible = false
        }
        layerPane.children.add(modalDimmer)
        
        
        
        
        
        // create main toolbar
        val toolBar = new ToolBar {
        	id = "mainToolBar"
        
	        val logo = new ImageView { image = new Image(this, "images/logo.png") }
	        
	        HBox.setMargin(logo, Insets(0, 0, 0, 5) )
	        items.add(logo)
	        
	        val spacer = new Region
	        HBox.setHgrow(spacer, Priority.ALWAYS)
	        items.add(spacer)
	        
	        val highlightsButton = new Button {
	        	id = "highlightsButton"
	        	minSize = (120, 66)
	        	prefSize = (120, 66)
	        	onAction =  {
            	goToPage(Pages.HIGHLIGHTS)
	          }
	        }
	        items.add(highlightsButton)
	        
	        val newButton = new Button {
	        	id = "newButton"
	        	minSize = (120,66)
	        	prefSize = (120,66)
	        	onAction = {
            	goToPage(Pages.NEW)
	          }
	      	}
	        items.add(newButton)
	        
	        val spacer2 = new Region
	        HBox.setHgrow(spacer2, Priority.ALWAYS)
	        items.add(spacer2)
	        
	        val searchTest = new ImageView { image = new Image(this, "images/search-text.png") }
	
	        items.add(searchTest)
	        
	        val searchBox = new SearchBox
	        HBox.setMargin(searchBox, scalafx.geometry.Insets(0,5,0,0))
	        items.add(searchBox)
	        prefHeight = 66
	        minHeight = 66
	        maxHeight = 66
	        
	        // add close min max
	        val windowButtons = new WindowButtons(stage)
	        items.add(windowButtons)
	        // add window header double clicking
	        onMouseClicked = new EventHandler[MouseEvent] {
	          override def handle( event:MouseEvent  ) {
		          if (event.getClickCount() == 2) {
		              windowButtons.toogleMaximized
		          }
	          }
	        }
	        // add window dragging
	        var mouseDragOffsetX:Double = 0
	        var mouseDragOffsetY:Double = 0
	        onMousePressed = new EventHandler[MouseEvent] {
	          override def handle( event:MouseEvent  ) {
		          mouseDragOffsetX = event.getSceneX
		          mouseDragOffsetY = event.getSceneY
	          }
	        }
	
	        onMouseDragged = new EventHandler[MouseEvent] {
	          override def handle( event:MouseEvent  ) {
	            if(!windowButtons.isMaximized) {
	              stage.setX(event.getScreenX-mouseDragOffsetX);
	              stage.setY(event.getScreenY-mouseDragOffsetY);
	          	}
	          }
	        }
	        
        }
        
        GridPane.setConstraints(toolBar, 0, 0)
        
        
        
        
        
        
        // create page tree toolbar
        
        def xToolBar = new javafx.scene.control.ToolBar {
                super.requestLayout
                // keep the height of pageToolBar in sync with pageTreeToolBar so they always match
                if (pageToolBar != null && getHeight != (pageToolBar.prefHeight = -1)) {
                    pageToolBar.setPrefHeight(getHeight())
                }         
        }
        
        val pageTreeToolBar = new ToolBar() {
//            @Override public void requestLayout() {
//                super.requestLayout();
//                // keep the height of pageToolBar in sync with pageTreeToolBar so they always match
//                if (pageToolBar != null && getHeight() != pageToolBar.prefHeight(-1)) {
//                    pageToolBar.setPrefHeight(getHeight());
//                }
//            }
        	id = "page-tree-toolbar"
        	minHeight = 29
        	maxWidth = Double.MaxValue
        }
        
        val pageButtonGroup = new ToggleGroup
        
        val allButton = new ToggleButton {
	        text = "All"
	        toggleGroup = pageButtonGroup
	        selected = true 
        }
        
        val samplesButton = new ToggleButton {
	        text = "Samples"
	        toggleGroup = pageButtonGroup
        }
        
        val docsButton = new ToggleButton {
	        text = "Document"
	        toggleGroup = pageButtonGroup
        }
        
        val treeButtonNotifyListener = new InvalidationListener() {
            def invalidated( ov:Observable) {
                if(allButton.isSelected()) {
                    pageTree.setRoot(pages.root.asInstanceOf[javafx.scene.control.TreeItem[Any]]);
                } else if(samplesButton.isSelected()) {
                    pageTree.setRoot(pages.samples.asInstanceOf[javafx.scene.control.TreeItem[Any]]);
                } else if(docsButton.isSelected()) {
                    pageTree.setRoot(pages.docs.asInstanceOf[javafx.scene.control.TreeItem[Any]]);
                }
            }
        };
        
        allButton.selectedProperty().addListener(treeButtonNotifyListener)
        samplesButton.selectedProperty().addListener(treeButtonNotifyListener)
        docsButton.selectedProperty().addListener(treeButtonNotifyListener)

        pageTreeToolBar.items.addAll(allButton, samplesButton, docsButton)
        // create page tree
        val pages = new Pages
        val proxyDialog = new ProxyDialog(stage, pages)
//        proxyDialog.loadSettings
//        proxyDialog.getDocsInBackground(true,null)
        pages.parseSamples
        
        val pageTree = new TreeView {
        	id = "page-tree"
    			maxSize = (Double.MaxValue, Double.MaxValue)
        root = pages.root.asInstanceOf[TreeItem[Any]]
    			showRoot = false
    			editable = false
    			selectionModel.setSelectionMode(javafx.scene.control.SelectionMode.SINGLE)
    			
//	        selection <== when (changingPage) then Color.GREEN otherwise Color.RED

    			
//  			import scalafx.beans.binding.BindingIncludes._
//  			import scalafx.collections.ObservableBuffer._
//    			onChange = {
//              if (!changingPage) {
//                  val selectedPage = selectionModel.getSelectedItem()  //(Page)
//                  if (selectedPage!=pages.root) goToPage(selectedPage.asInstanceOf[Page])
//              }
//        	  
//        	}
//    			
//    			selectionModel.selectedItemProperty().addListener(new ChangeListener[ObservableValue]() {
//            override def changed( observable: ObservableValue[Any],  oldValue:Object,  newValue:Object) {
//                if (!changingPage) {
//                    val selectedPage = selectionModel.getSelectedItem()  //(Page)
//                    if (selectedPage!=pages.root) goToPage(selectedPage.asInstanceOf[Page])
//                }
//            }
//        });
				}
        
        // create left split pane
        val leftSplitPane = new BorderPane {
        	top = pageTreeToolBar
    			center = pageTree
        }
        
        // create page toolbar
        val pageToolBar = new ToolBar {
        	id = "page-toolbar"
        	minHeight = 29
        	maxSize = (Double.MaxValue, Control.usePrefSize)
        
	        val backButton = new Button {
	        	graphic = new ImageView { image = new Image(this, "images/back.png") }
	        	onAction = {
            	back
	          }
	        	maxHeight = Double.MaxValue
	        }
	        
	        val forwardButton = new Button {
	        	graphic = new ImageView { image = new Image(this, "images/forward.png") }
	        	onAction = {
            	forward
	          }
	        	maxHeight = Double.MaxValue
	        }
	        
	        val reloadButton = new Button {
	        	graphic = new ImageView { image = new Image(this, "images/reload.png") }
	        	onAction = {
            	reload
	          }
	        	maxHeight = Double.MaxValue
	        }
        
        	items.addAll(backButton, forwardButton, reloadButton)
        
        	val breadcrumbBar = new BreadcrumbBar
        	items.add(breadcrumbBar)
        }
        
        val spacer3 = new Region
        HBox.setHgrow(spacer3, Priority.ALWAYS)
        
        val settingsButton = new Button {
        	id = "SettingsButton"
        	graphic = new ImageView { image = new Image(this, "images/settings.png") }
        	onAction = {
        	  showProxyDialog
        	}
        	maxHeight = Double.MaxValue
        }
        
        pageToolBar.items.addAll(spacer3, settingsButton)
        
        
        
        val xPageArea = new javafx.scene.layout.Pane {
            override def layoutChildren {
              getChildren.foreach { child => 
//                for (Node child:pageArea.getChildren()) {
                    child.resizeRelocate(0, 0, getWidth, getHeight)
                }
            }         
        }
        // create page area
        val pageArea = new Pane(xPageArea) {
        	id = "page-area"
//            @Override protected void layoutChildren() {
//                for (Node child:pageArea.getChildren()) {
//                    child.resizeRelocate(0, 0, pageArea.getWidth(), pageArea.getHeight());
//                }
//            }
        }
        // create right split pane
        val rightSplitPane = new BorderPane {
        	top = pageToolBar
        	center = pageArea
        }
        
        // create split pane
        val splitPane = new SplitPane {
        	id = "page-splitpane"
          maxSize = (Double.MaxValue, Double.MaxValue)
        	items.addAll(leftSplitPane, rightSplitPane)
        	dividerPosition = (0, 0.25)
        }
        GridPane.setConstraints(splitPane, 0, 1)

        root.top = toolBar
        root.center = splitPane
        // add window resize button so its on top
        windowResizeButton.managed = false
        root.children.add(windowResizeButton)
        // expand first level of the tree
//        for (TreeItem child: pages.getRoot().getChildren()) {
//            if (child == pages.getHighlighted() || child == pages.getNew()) continue;
//            child.setExpanded(true);
//            for (TreeItem child2: (ObservableList<TreeItem<String>>)child.getChildren()) {
//                child2.setExpanded(true);
//            }
//        }
        // goto initial page
        // default to all samples
        goToPage(pages.samples)
        // show stage
        stage.setScene(scene)
        stage.show
//    }
    
    
    /**
     * Show the given node as a floating dialog over the whole application, with 
     * the rest of the application dimmed out and blocked from mouse events.
     * 
     * @param message 
     */
    def showModalMessage( message:Node) {
        modalDimmer.children.add(message)
        modalDimmer.setOpacity(0)
        modalDimmer.setVisible(true)
        modalDimmer.setCache(true)
        
//        val op:javafx.beans.value.WritableValue[Any] = modalDimmer.opacityProperty().asInstanceOf[javafx.beans.value.WritableValue[Any]]
//        
//        TimelineBuilder.create().keyFrames(
//            new KeyFrame(Duration.seconds(1), 
//                new EventHandler[ActionEvent]() {
//                    def handle( t:ActionEvent) {
//                        modalDimmer.setCache(false);
//                    }
//                },
//                new javafx.animation.KeyValue(op, 1, Interpolator.EASE_BOTH)
//        )).build().play()
        

        val timeline = new scalafx.animation.Timeline

  			timeline.getKeyFrames.addAll(
//					new KeyFrame(new Duration(1000), scalafx.animation.KeyValue[Number](modalDimmer.opacityProperty, 1, Interpolator.EASE_BOTH)),
					new KeyFrame(new Duration(1000), {
					    println( "opacityProperty " + modalDimmer.opacity)
					    scalafx.animation.KeyValue[Number](modalDimmer.opacityProperty, 1, Interpolator.EASE_BOTH)
							}
					    ),
					new KeyFrame(new Duration(2000), {
						println( "opacityProperty " + modalDimmer.opacity)
						scalafx.animation.KeyValue[Number](modalDimmer.opacityProperty, 0, Interpolator.EASE_BOTH)
					}
							),
					new KeyFrame(new Duration(3000), {
						println( "opacityProperty " + modalDimmer.opacity)
						scalafx.animation.KeyValue[Number](modalDimmer.opacityProperty, 1, Interpolator.EASE_BOTH)
					}
							)

//					new KeyFrame(new Duration(1000), new EventHandler[ActionEvent]() { def handle( t:ActionEvent) {modalDimmer.setCache(false);} })
				)

        timeline.play        
        
    }
    
//    /**
//     * Hide any modal message that is shown
//     */
    def hideModalMessage {
        modalDimmer.setCache(true)
        modalDimmer.visible = false
        modalDimmer.children.clear

        val op:javafx.beans.value.WritableValue[Any] = modalDimmer.opacityProperty().asInstanceOf[javafx.beans.value.WritableValue[Any]]

        TimelineBuilder.create().keyFrames(
            new KeyFrame(Duration.seconds(1), 
                new EventHandler[ActionEvent]() {
                    def handle( t:ActionEvent) {
                        modalDimmer.setCache(false)
                        modalDimmer.setVisible(false)
                        modalDimmer.children.clear
                    }
                },
                new KeyValue(op, 0, Interpolator.EASE_BOTH)
        )).build().play()
        
        
    }
//    
//    /**
//     * Get the pages object that contains the tree of all avaliable pages
//     * 
//     * @return Pages containing tree of all pages
//     */
//    def getPages() = {
//        pages
//    }
//
//    /**
//     * Change to new page without swapping views, assumes that the current view 
//     * is already showing the new page
//     * 
//     * @param page The new page object
//     */
//    def updateCurrentPage( page: Page) {
//        goToPage(page, true,false,false)
//    }
//    
//    /**
//     * Take ensemble to the given page path, navigating there and adding 
//     * current page to history
//     * 
//     * @param pagePath The path for the new page to show
//     */
    def goToPage( pagePath: String) {
//        goToPage(pages.getPage(pagePath));
    }
//
//    /**
//     * Take ensemble to the given page path, navigating there and adding 
//     * current page to history.
//     * 
//     * @param pagePath  The path for the new page to show
//     * @param force     Reload page even if its the current page
//     */
    def goToPage( pagePath:String, force:Boolean) {
//        goToPage(pages.getPage(pagePath),true,force,true)
    }
//
//    /**
//     * Take ensemble to the given page object, navigating there and adding 
//     * current page to history.
//     * 
//     * @param page Page object to show
//     */
    def goToPage( page:Page) {
//        goToPage(page, true, false, true)
    }
//    
//    /**
//     * Take ensemble to the given page object, navigating there.
//     * 
//     * @param page          Page object to show
//     * @param addHistory    When true add current page to history before navigating
//     * @param force         When true reload page if page is current page
//     * @param swapViews     If view should be swapped to new page
//     */
//    def goToPage(Page page, boolean addHistory, boolean force, boolean swapViews) {
//        if(page==null) return;
//        if(!force && page == currentPage) {
//            return;
//        }
//        changingPage = true;
//        if (swapViews) {
//            Node view = page.createView();
//            if (view == null) view = new Region(); // todo temp workaround
//            // replace view in pageArea if new
//            if (force || view != currentPageView) {
//                for (Node child:pageArea.getChildren()){
//                    if (child instanceof SamplePage.SamplePageView) {
//                        ((SamplePage.SamplePageView)child).stop();
//                    }
//                }
//                pageArea.getChildren().setAll(view);
//                currentPageView = view;
//            }
//        }
//        // add page to history
//        if (addHistory && currentPage!=null) {
//            history.push(currentPage);
//            forwardHistory.clear();
//        }
//        currentPage = page;
//        currentPagePath = page.getPath();
//        // when in applet update location bar
//        // expand tree to selected node
//        Page p = page;
//        while (p!=null) {
//            p.setExpanded(true);
//            p = (Page) p.getParent();
//        }
//        // update tree selection
//        pageTree.getSelectionModel().select(page);
//        // update breadcrumb bar
//        breadcrumbBar.setPath(currentPagePath);
//        // done
//        changingPage = false;
//    }
//    
//    /**
//     * Check if current call stack was from back or forward button's action
//     * 
//     * @return True if current call was caused by action on back or forward button
//     */
//    public boolean isFromForwardOrBackButton() {
//        return fromForwardOrBackButton;
//    }
//    
//    /**
//     * Got to previous page in history
//     */
    def back {
//        fromForwardOrBackButton = true;
//        if (!history.isEmpty()) {      
//            Page prevPage = history.pop();
//            forwardHistory.push(currentPage);
//            goToPage(prevPage,false, false, true);
//        }
//        fromForwardOrBackButton = false;
    }
//    
//    /**
//     * Utility method for viewing the page history
//     */
//    private void printHistory() {
//        System.out.print("   HISTORY = ");
//        for (Page page:history) {
//            System.out.print(page.getName()+"->");
//        }
//        System.out.print("   ["+currentPage.getName()+"]");
//        for (Page page:forwardHistory) {
//            System.out.print(page.getName()+"->");
//        }
//        System.out.print("\n");
//    }
//
//    /**
//     * Go to next page in history if there is one
//     */
    def forward {
//        fromForwardOrBackButton = true;
//        if (!forwardHistory.isEmpty()) {
//            Page prevPage = forwardHistory.pop();
//            history.push(currentPage);
//            goToPage(prevPage,false, false, true);
//        }
//        fromForwardOrBackButton = false;
    }
//    
//    /**
//     * Reload the current page
//     */
    def reload {
//        goToPage(currentPage,false,true, true)
    }
//    
//    /**
//     * Show the dialog for setting proxy to the user
//     */
    def showProxyDialog {
        showModalMessage(proxyDialog)
    }
    
}
