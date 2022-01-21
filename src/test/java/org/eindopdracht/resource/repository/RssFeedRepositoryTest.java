package org.eindopdracht.resource.repository;

import org.eindopdracht.resource.model.RssFeed;
import org.eindopdracht.resource.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = org.eindopdracht.configuration.DatabaseConfigTest.class)
@SpringJUnitWebConfig(classes = org.eindopdracht.configuration.DatabaseConfigTest.class)
@Transactional
public class RssFeedRepositoryTest {

    @Autowired
    private RssFeedRepository repository;

    private User user;
    private RssFeed feed;

    @BeforeEach
    void setUp() {
        this.user = new User();
        user.setName("test");
        user.setApproved(true);
        user.setEmail("true");
        user.setPassword("true");
        user.setProfileImagePath("true");
        user.setRole("ROLE_USER");
        user.setEnabled(false);

        this.feed = new RssFeed();
        this.feed.setUser(this.user);
        this.feed.setLink("test");
        this.feed.setStartDateTime(new Date(2010, 3, 5));
        this.feed.setEndDateTime(new Date(2010, 3, 5));
    }

    @Test
    @DisplayName("Testing if getRssFeeds() returns a list of users")
    public void testMethodGetRssFeeds() {
        List<RssFeed> feeds = repository.getRssFeeds();
        assertNotNull(feeds);
    }

    @Test
    @DisplayName("Testing if getRssFeed retrieves the right user")
    public void testMethodGetRssFeed() {
        RssFeed feed = repository.getRssFeed(1);
        assertEquals("Madlyaza", feed.getUser().getName());
    }

    @Test
    @DisplayName("Testing if postRssFeed has posted the right user")
    public void testMethodPostRssFeed() {
        RssFeed postfeed = repository.postRssFeed(this.feed);
        assertEquals("test", postfeed.getUser().getName());
    }

    @Test
    @DisplayName("Testing if putUserAvailability changed the entry")
    public void testPutRssFeed() {
        this.feed.getUser().setName("test2");
        repository.putRssFeed(this.feed, 1);
        RssFeed putfeed = repository.getRssFeed(1);
        assertEquals("test2", putfeed.getUser().getName());
    }

    @Test
    @DisplayName("Testing if deleteRssFeed works")
    public void testMethodDeleteRssFeed() {
        repository.deleteRssFeed(1);
    }
}
