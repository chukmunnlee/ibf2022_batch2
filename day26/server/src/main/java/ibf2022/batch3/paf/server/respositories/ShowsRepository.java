package ibf2022.batch3.paf.server.respositories;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class ShowsRepository {

    public static final String C_TVSHOWS = "tvshows";

    @Autowired
    private MongoTemplate mongoTemplate;

    // Write the native query
    // db.tvshows.find({ name: 'the name' })
    public List<Document> findTvShowByName(String title) {

        // Create the filter - the Criteria
        // { name: 'the name' }
        Criteria criterial = Criteria.where("name").is(title);

        // Create the query
        Query query = Query.query(criterial);

        // Execute the query
        return mongoTemplate.find(query, Document.class, C_TVSHOWS);
    }

    /*
     *db.tvshows.find({
        { "rating.average": { $gte: 6.5 } },
        { runtime: { $lte: 30 }}
     }) 
     */
    public List<Document> findShowtimeLessThan(int mins) {
        Criteria criteria = Criteria.where("rating.average").gte(6.5)
            .andOperator( Criteria.where("runtime").lte(mins));

        Query query = Query.query(criteria);

        return mongoTemplate.find(query, Document.class, C_TVSHOWS);
    }

    /*
     * db.tvshows
     *      .find({ status: "Ended"})
     *      .sort({ "rating.average": -1, name: 1 })
     */
    public List<Document> findMoviesByStatus(String status) {

        Criteria criteria = Criteria.where("status").is(status)
                .andOperator(Criteria.where("rating.average").not().isNullValue());

        Query query = Query.query(criteria)
            .with(
                Sort.by(Direction.DESC, "rating.average")
                    .and(Sort.by(Direction.ASC, "name"))
                );
        query.fields().include("name", "rating.average").exclude("_id");

        return mongoTemplate.find(query, Document.class, C_TVSHOWS);
    }
    
}
