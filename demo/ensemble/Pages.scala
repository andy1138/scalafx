package ensemble
import ensemble.pages.CategoryPage
import ensemble.pages.AllPagesPage
import ensemble.pages.SamplePage


object Pages {
  final val SAMPLES = "SAMPLES"
  final val API_DOCS = "API DOCUMENTATION"
  final val NEW = "NEW!"
  final val HIGHLIGHTS = "HIGHLIGHTS"
}

class Pages {
//    private AllPagesPage root;
//    private CategoryPage samples;
//  val samples_loc = new CategoryPage("", List[Page]())
//    private CategoryPage docs;
//    private CategoryPage newSamples;
//    var highlightedSamples: CategoryPage

//    // create all the pages
    val root = new AllPagesPage
    val samples = new CategoryPage(Pages.SAMPLES)
    val docs = new CategoryPage(Pages.API_DOCS)
    val newSamples = new CategoryPage(Pages.NEW);
    val highlightedSamples = new CategoryPage(Pages.HIGHLIGHTS);
    root.getChildren().addAll(highlightedSamples, newSamples);
    root.getChildren().add(highlightedSamples)
    root.getChildren().add(samples)
//    root.getChildren().add(docs);
//
    def parseSamples(){
        SampleHelper.getSamples(samples)
        println( ">>Pages: samples = " + samples.getChildren())
        // ADD PAGES TO HIGHLIGHTS CATEGORY
        highlightedSamples.getChildren().addAll(
            new SamplePage(getPage("SAMPLES/Controls/Buttons/Color Button"))
//                new SamplePage(getPage("SAMPLES/Web/Web View")),
//                new SamplePage(getPage("SAMPLES/Web/H T M L Editor")),
//                new SamplePage(getPage("SAMPLES/Graphics 3d/Cube")),
//                new SamplePage(getPage("SAMPLES/Graphics 3d/Cube System")),
//                new SamplePage(getPage("SAMPLES/Graphics 3d/Xylophone")),
//                new SamplePage(getPage("SAMPLES/Media/Advanced Media")),
//                new SamplePage(getPage("SAMPLES/Graphics/Digital Clock")),
//                new SamplePage(getPage("SAMPLES/Graphics/Display Shelf")),
//                new SamplePage(getPage("SAMPLES/Charts/Area/Adv Area Audio Chart")),
//                new SamplePage(getPage("SAMPLES/Charts/Bar/Adv Bar Audio Chart")),
//                new SamplePage(getPage("SAMPLES/Charts/Line/Advanced Stock Line Chart")),
//                new SamplePage(getPage("SAMPLES/Charts/Custom/Adv Candle Stick Chart")),
//                new SamplePage(getPage("SAMPLES/Charts/Scatter/Advanced Scatter Chart"))
        );
        // ADD PAGES TO NEW CATEGORY
        newSamples.getChildren().addAll(
//            new SamplePage(getPage("SAMPLES/Animation/Transitions/Fill Transition")),
//            new SamplePage(getPage("SAMPLES/Animation/Transitions/Pause Transition")),
//            new SamplePage(getPage("SAMPLES/Animation/Transitions/Stroke Transition")), 
//            new SamplePage(getPage("SAMPLES/Charts/Area/Advanced Area Chart")),
//            new SamplePage(getPage("SAMPLES/Charts/Area/Adv Area Audio Chart")),
//            new SamplePage(getPage("SAMPLES/Charts/Bar/Advanced Bar Chart")),
//            new SamplePage(getPage("SAMPLES/Charts/Bar/Adv Bar Audio Chart")),
//            new SamplePage(getPage("SAMPLES/Charts/Bar/Adv Horizontal Bar Chart")),
//            new SamplePage(getPage("SAMPLES/Charts/Bubble/Advanced Bubble Chart")),
//            new SamplePage(getPage("SAMPLES/Charts/Custom/Adv Candle Stick Chart")),
//            new SamplePage(getPage("SAMPLES/Charts/Custom/Adv Stacked Bar Chart")),
//            new SamplePage(getPage("SAMPLES/Charts/Line/Advanced Line Chart")),
//            new SamplePage(getPage("SAMPLES/Charts/Line/Advanced Stock Line Chart")),
//            new SamplePage(getPage("SAMPLES/Charts/Line/Adv Line Category Chart")),
//            new SamplePage(getPage("SAMPLES/Charts/Pie/Advanced Pie Chart")),
//            new SamplePage(getPage("SAMPLES/Charts/Scatter/Advanced Scatter Chart")),
//            new SamplePage(getPage("SAMPLES/Charts/Scatter/Adv Scatter Live Chart")),
//            new SamplePage(getPage("SAMPLES/Controls/Accordion")),
//            new SamplePage(getPage("SAMPLES/Controls/Buttons/Color Button")),
//            new SamplePage(getPage("SAMPLES/Controls/Buttons/Hyperlink")),
//            new SamplePage(getPage("SAMPLES/Controls/Buttons/Toggle Button")),
//            new SamplePage(getPage("SAMPLES/Controls/Choice Box")),
//            new SamplePage(getPage("SAMPLES/Controls/Progress Bar")),
//            new SamplePage(getPage("SAMPLES/Controls/Progress Indicator")),
//            new SamplePage(getPage("SAMPLES/Controls/Scroll Bar")),
//            new SamplePage(getPage("SAMPLES/Controls/Table/Table")),
//            new SamplePage(getPage("SAMPLES/Controls/Tabs/Tab")),
//            new SamplePage(getPage("SAMPLES/Controls/Text/Text Field")),
//            new SamplePage(getPage("SAMPLES/Controls/Tool Bar")),
//            new SamplePage(getPage("SAMPLES/Graphics/Colorful Circles")),
//            new SamplePage(getPage("SAMPLES/Graphics/Digital Clock")),
//            new SamplePage(getPage("SAMPLES/Graphics/Display Shelf")),
//            new SamplePage(getPage("SAMPLES/Graphics/Puzzle Pieces")),
//            new SamplePage(getPage("SAMPLES/Graphics 3d/Cube")),
//            new SamplePage(getPage("SAMPLES/Graphics 3d/Cube System")),
//            new SamplePage(getPage("SAMPLES/Graphics 3d/Xylophone")),
//            new SamplePage(getPage("SAMPLES/Graphics 3d/Audio Visualizer")),
//            new SamplePage(getPage("SAMPLES/Media/Audio Clip")),
//            new SamplePage(getPage("SAMPLES/Language/Beans/String Binding")),
//            new SamplePage(getPage("SAMPLES/Scenegraph/Events/Cursor")),
//            new SamplePage(getPage("SAMPLES/Scenegraph/Events/Key Stroke Motion")),
//            new SamplePage(getPage("SAMPLES/Scenegraph/Node/Custom Node")),
//            new SamplePage(getPage("SAMPLES/Scenegraph/Node/Node Properties")),
//            new SamplePage(getPage("SAMPLES/Scenegraph/Stage/Advanced Stage")),
//            new SamplePage(getPage("SAMPLES/Web/H T M L Editor")),
//            new SamplePage(getPage("SAMPLES/Web/Web View"))
        );
    }
//
    def getPage( name:String):SamplePage = {
      root.getChild(name).asInstanceOf[SamplePage]
    }
//
//    def getHighlighted:Page = {
//        return highlightedSamples
//    }
//
//    def getNew:Page = {
//        return newSamples
//    }
//
//    def samples:Page = {
//        samples_loc
//    }
//
//    def getDocs:Page = {
//        return docs
//    }
//
//    def getRoot:Page = {
//      root
//    }
}
