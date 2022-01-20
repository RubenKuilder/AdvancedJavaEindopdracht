package org.AdvancedJavaEindopdracht.resource.repository;

import org.AdvancedJavaEindopdracht.resource.exception.general.BadRequestException;
import org.AdvancedJavaEindopdracht.resource.exception.general.DataNotFoundException;
import org.AdvancedJavaEindopdracht.resource.model.Role;
import org.AdvancedJavaEindopdracht.resource.model.RssFeed;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class RssFeedRepository {
    @PersistenceContext
    private EntityManager manager;

    /**
     * Returns a list of all RSS feeds.
     *
     * @return      response entity with list of all RSS feeds
     */
    public List<RssFeed> getRssFeeds(){
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<RssFeed> query = cb.createQuery(RssFeed.class);
        return manager.createQuery(query.select(query.from(RssFeed.class))).getResultList();
    }

    /**
     * Find a single RSS feed and return it.
     *
     * @param id    id of the RSS feed to find
     * @return      response entity with a single RSS feed
     */
    public RssFeed getRssFeed(int id){
        try {
            return manager.find(RssFeed.class, id);
        }catch(Exception e){
            throw new DataNotFoundException();
        }
    }

    /**
     * Post a single RSS feed.
     *
     * @param rssFeed   RSS feed to post
     * @return          response entity with posted RSS feed
     */
    public RssFeed postRssFeed(RssFeed rssFeed){
        try {
            manager.persist(rssFeed);
            return manager.find(RssFeed.class, rssFeed.getId());
        }catch(Exception e){
            throw new BadRequestException();
        }
    }

    /**
     * Put a single RSS feed.
     * Updates all fields.
     *
     * @param id        id of the RSS feed to put
     * @param rssFeed   RSS feed to put
     * @return          response entity with put RSS feed
     */
    public RssFeed putRssFeed(RssFeed rssFeed, int id){
        RssFeed update = manager.find(RssFeed.class, id);
        update.setUser(rssFeed.getUser());
        update.setLink(rssFeed.getLink());
        update.setEndDateTime(rssFeed.getEndDateTime());
        update.setStartDateTime(rssFeed.getStartDateTime());
        return update;
    }

    /**
     * Delete a single RSS feed and return it.
     *
     * @param id    id of the RSS feed to delete
     * @return      response entity with deleted RSS feed
     */
    public RssFeed deleteRssFeed(int id){
        RssFeed feed = manager.find(RssFeed.class, id);
        manager.remove(feed);
        return feed;
    }
}
