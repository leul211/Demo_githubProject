package com.albums.api.albums_api.controller;

import java.util.HashMap;
import java.util.Map;

import javax.management.relation.InvalidRelationIdException;

import com.albums.api.albums_api.exceptions.InvalidRequestException;
import com.albums.api.albums_api.model.Albums;
import com.albums.api.albums_api.service.AlbumService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AlbumController {

    @Autowired 
    AlbumService service;

    @GetMapping("/")
    public String greet(){
        return "Welcome to Albums API";
    }

    @GetMapping(value = "/albums", produces = "application/json")
    public Page<Albums> getList(Pageable pageable){
      return service.getAlbumsList(pageable);   
    }

    @PostMapping(value = "/albums", consumes="application/json",produces = "application/json" )
    public Albums addAlbum(@RequestBody Albums albums){
        return service.addAlbum(albums);
    }

    @GetMapping(value = "/albums/{id}",produces = "application/json")
    public Albums byId(@PathVariable(value = "id") long id){
        return service.getAlbumById(id);
    }

    //TODO 
    /**
     * Implement the put and delete operations
     * add that to the 
     */
    /*
    @PutMapping(value = "/albums/{id}", produces = "application/json")
    public ResponseEntity<Albums> updateById(@PathVariable(value = "id") long id, @RequestBody Albums albumsDetails)
     throws InvalidRelationIdException{
        //Check if the id is correct
        //to find if Albums id is in DB
        Albums albums = service.getAlbumById(id);

        albums.setUser_id(albumsDetails.getUser_id());
        albums.setTitle(albumsDetails.getTitle());
        final Albums updateAlbums = service.addAlbum(albums);
        return ResponseEntity.ok(updateAlbums);
    }*/

    @PutMapping(value = "/albums/{id}", produces = "application/json")
    public Albums updatedAlbums(@PathVariable(value = "id") long id, @RequestBody Albums albums){

        Albums albumsById = service.getAlbumById(id);
        albumsById.setUser_id(albums.getUser_id());
        albumsById.setTitle(albums.getTitle());
        service.addAlbum(albumsById);
        return albumsById;
    }

    @DeleteMapping(value = "/albums/{id}")
    public HashMap<Long,String> removeAlbums(@PathVariable(value = "id") long id){
        Albums albumsToRemove = service.getAlbumById(id);
        service.delete(albumsToRemove);
        HashMap<Long,String> response = new HashMap<>();
        response.put(id, " is removed from the database");
        return response;

    }


    /*
    //delete albums
    @DeleteMapping(value = "albums/{id}", produces = "application/json")
    public Map<Long,String> removeAlbums(@PathVariable long id) throws InvalidRequestException{

        Albums albums = service.getAlbumById(id);
        service.delete(albums);
        Map<Long,String> response = new HashMap<>();
        response.put(id, "Album Deleted");
        return response;
    }*/
}