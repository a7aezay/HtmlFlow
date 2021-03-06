package htmlflow.test.views;

import htmlflow.DynamicHtml;
import htmlflow.HtmlTemplate;
import htmlflow.HtmlView;
import htmlflow.StaticHtml;
import htmlflow.test.model.Track;
import org.junit.Test;

import java.util.stream.Stream;

public class HtmlPartials {

    static HtmlView bbView = StaticHtml.view().div().text("Dummy bbView").__(); // div

    static HtmlView footerView(HtmlView banner) {
        StaticHtml view = StaticHtml.view();
        return view
                .div()
                    .of(__ -> view.addPartial(banner))
                    .p()
                        .text("Created with HtmFlow")
                    .__() // p
                .__(); //
    }


    /**
     * This unit test was only a sample for a paper.
     */
    @java.lang.SuppressWarnings("squid:S2699")
    @Test
    public void testPartials() {
        HtmlTemplate<Stream<Track>> tracksTemplate = (view , tracks, partials) -> view
        .div()
            .ul()
                .of(ul -> tracks.forEach (item -> ul
                    .li()
                        .text(item. getName ())
                    .__() // li
                ))
            .__ () // ul
            .of(__ -> view.addPartial(partials[0]))
        .__(); // div


        Stream<Track> tracks = Stream.of(new Track("Space Odyssey"), new Track("Bad"), new Track("Under Pressure"));
        DynamicHtml<Stream<Track>> tracksView = DynamicHtml.view(tracksTemplate);
        String html = tracksView.render(tracks, footerView(bbView));
        System.out.println(html);
    }
}
