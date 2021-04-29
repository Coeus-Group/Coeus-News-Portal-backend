package com.coeusnews.model;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ArticleTest {


    @Test
    @DisplayName("Test article title GET")
    public void testTaskDescription() {
        ArticleLocation a2 = new ArticleLocation("1164", "0", "ITV Wales presenter's 'bully' ex-husband jailed for three years - BBC News", "entertainment", "Jonathan Wignall harassed and stalked Ruth Dodsworth over nine years, a court has heard.", " ","https://ichef.bbci.co.uk/news/1024/branded_news/119D2/production/_118064127_de27-10.jpg", "https://www.bbc.co.uk/news/uk-wales-56753460","2021-04-15T07:49:15Z", "2021-04-15T07:49:15Z", "published", " ", " ", " ", 0 );

        assertEquals("ITV Wales presenter's 'bully' ex-husband jailed for three years - BBC News", a2.getTitle(), "Article title was correct");
    }

    @Test
    @DisplayName("Test article category GET")
    public void testDefaultCompletedStatus() {
        ArticleCategory a4 = new ArticleCategory("1166", "0", "Foxtons to review boss's pay after shareholder revolt", "business", "Jonathan Wignall harassed and stalked Ruth Dodsworth over nine years, a court has heard.", " ","https://ichef.bbci.co.uk/news/1024/branded_news/119D2/production/_118064127_de27-10.jpg", "https://www.bbc.co.uk/news/uk-wales-56753460","2021-04-15T07:49:15Z", "2021-04-15T07:49:15Z", "published", " ", " "," ", 0 );
        assertEquals("business", a4.getCategory(), "Article Category was correct");
    }

    @Test
    @DisplayName("Test article location GET")
    public void testTaskId() {
      ArticleLocation a3 = new ArticleLocation("1165", "0", "I am sickened by Liverpool backing shameless breakaway - fans will not tolerate it", "sports", "Jonathan Wignall harassed and stalked Ruth Dodsworth over nine years, a court has heard.", " ","https://ichef.bbci.co.uk/news/1024/branded_news/119D2/production/_118064127_de27-10.jpg", "https://www.bbc.co.uk/news/uk-wales-56753460","2021-04-15T07:49:15Z", "2021-04-15T07:49:15Z", "published", "Liverpool", " ", " ", 0 );

        assertEquals("Liverpool", a3.getArticle_location(), "Article Location was correct");
    }
}
