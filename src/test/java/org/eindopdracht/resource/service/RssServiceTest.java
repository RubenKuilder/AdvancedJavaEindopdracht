package org.eindopdracht.resource.service;

import org.eindopdracht.resource.dto.RssFeedDTO;
import org.eindopdracht.resource.model.RssFeed;
import org.eindopdracht.resource.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringJUnitWebConfig(classes = org.eindopdracht.configuration.DatabaseConfigTest.class)
@ContextConfiguration(classes = org.eindopdracht.configuration.DatabaseConfigTest.class)
@Transactional
class RssServiceTest
{
    @Autowired
    private RssFeedService service;

    @Test
    @Transactional
    void getRssFeeds()
    {
        List<RssFeedDTO>  list = service.getRssFeeds();

        assertEquals(2, list.size());
    }

    @Test
    @Transactional
    void getRssFeed()
    {
        RssFeedDTO rss = service.getRssFeed(1);

        assertEquals("Madlyaza", rss.getUser().getName());
    }

    @Test
    @Transactional
    void createRss() throws ParseException
    {
        RssFeed feed = new RssFeed();
        feed.setLink("test");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date startDateTime = sdf.parse("10-01-2022 15:40:10");
        Date endDateTime = sdf.parse("10-01-2022 15:50:10");
        feed.setStartDateTime(startDateTime);
        feed.setEndDateTime(endDateTime);

        User user = new User();
        user.setName("test22");
        user.setApproved(true);
        user.setEmail("true");
        user.setPassword("true");
        user.setProfileImagePath("true");

        feed.setUser(user);

        RssFeedDTO dto = service.create(feed);

        assertEquals("test22", dto.getUser().getName());
    }

    @Test
    @Transactional
    void deleteRss()
    {
        service.delete(1);
    }

    @Test
    @Transactional
    void updateRss()
    {
        RssFeed feed = new RssFeed();
        feed.setLink("test");
        feed.setEndDateTime(new Date(01, 01, 2022, 00, 00, 00));
        feed.setStartDateTime(new Date(01, 01, 2022, 00, 00, 00));

        User user = new User();
        user.setName("test33");
        user.setApproved(true);
        user.setEmail("true");
        user.setPassword("true");
        user.setProfileImagePath("true");
        feed.setUser(user);
        RssFeedDTO dto = service.update(feed, 1);

        assertEquals("test33", dto.getUser().getName());
    }
}