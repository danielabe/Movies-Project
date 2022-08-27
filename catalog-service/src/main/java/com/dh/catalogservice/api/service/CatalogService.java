package com.dh.catalogservice.api.service;

import com.dh.catalogservice.api.client.MoviesServiceClient;
import com.dh.catalogservice.domain.model.Catalog;
import com.dh.catalogservice.domain.model.Movie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class CatalogService {
    private final MoviesServiceClient serviceClient;

    public CatalogService(MoviesServiceClient serviceClient) {
        this.serviceClient = serviceClient;
    }

    public ResponseEntity<Catalog> getMoviesByGenre(@PathVariable String genre) {

        ResponseEntity<List<Movie>> moviesResponse = serviceClient.getMoviesByGenre(genre);
        List<Movie> movies = moviesResponse.getBody();

        Catalog catalog = new Catalog(genre, movies);

        System.out.println("Puerto utilizado: " + moviesResponse.getHeaders().get("port"));
        return  ResponseEntity.ok().body(catalog);
    }
}
