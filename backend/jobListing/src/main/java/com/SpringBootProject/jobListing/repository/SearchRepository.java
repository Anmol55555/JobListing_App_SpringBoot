package com.SpringBootProject.jobListing.repository;

import com.SpringBootProject.jobListing.models.Post;

import java.util.List;

public interface SearchRepository
{
    public List<Post> findByText(String text);
}
