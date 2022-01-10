package org.AdvancedJavaEindopdracht.resource.repository;

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

    public List<RssFeed> getRssFeeds(){
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<RssFeed> query = cb.createQuery(RssFeed.class);
        return manager.createQuery(query.select(query.from(RssFeed.class))).getResultList();
    }

    public RssFeed getRssFeed(int id){
        return manager.find(RssFeed.class, id);
    }

    public RssFeed postRssFeed(RssFeed rssFeed){
        manager.persist(rssFeed);
        return manager.find(RssFeed.class, rssFeed.getId());
    }

    public RssFeed putRssFeed(RssFeed rssFeed, int id){
        RssFeed update = manager.find(RssFeed.class, id);
        update.setUser(rssFeed.getUser());
        update.setLink(rssFeed.getLink());
        update.setEndDate(rssFeed.getEndDate());
        update.setStartDate(rssFeed.getStartDate());
        return update;
    }

    public void deleteRssFeed(int id){
        RssFeed feed = manager.find(RssFeed.class, id);
        manager.remove(feed);
    }
}
