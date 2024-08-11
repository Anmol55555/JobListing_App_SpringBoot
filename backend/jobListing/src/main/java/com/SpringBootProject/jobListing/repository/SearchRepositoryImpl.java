package com.SpringBootProject.jobListing.repository;

import com.SpringBootProject.jobListing.models.Post;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.bson.Document;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class SearchRepositoryImpl implements SearchRepository
{
    @Autowired
    MongoClient client;


    @Autowired
    MongoConverter converter;


    @Override
    public List<Post> findByText(String text) {
        final List<Post> posts = new ArrayList<>();

        MongoDatabase database = client.getDatabase("Database_SpringBoot_Project");
        MongoCollection<Document> collection = database.getCollection("JobPost");

        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                                new Document("text",
                                new Document("query", text)
                                        .append("path", Arrays.asList("techs", "desc", "profile")))),
                                new Document("$sort",
                                new Document("exp", 1L)),
                                new Document("$limit", 5L)));

        // Iterate for each documnet got as a ssearch result and convert it into Post type and append it into posts
        result.forEach(doc -> posts.add(converter.read(Post.class, doc)));

        return posts;
    }
}
