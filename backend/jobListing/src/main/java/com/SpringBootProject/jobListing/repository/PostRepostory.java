package com.SpringBootProject.jobListing.repository;

import com.SpringBootProject.jobListing.models.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepostory extends MongoRepository<Post, String>
{
    // MongoRepository provides CRUD options
    // Will have all the basics functions of MongoRepository like findAll(), save(), etc
}
