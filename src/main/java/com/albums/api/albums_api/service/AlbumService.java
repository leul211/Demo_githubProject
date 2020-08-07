package com.albums.api.albums_api.service;

import com.albums.api.albums_api.repository.AlbumsRepository;
import com.albums.api.albums_api.exceptions.DataNotFoundException;
import com.albums.api.albums_api.model.Albums;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AlbumService {
    
    @Autowired
    private AlbumsRepository repository;

    //this method can return list of ALBUMS SETS
    public Page<Albums> getAlbumsList(Pageable pageable){
        return repository.findAll(pageable);
    }

    public Albums addAlbum(Albums albums){
        return repository.save(albums);
    }

    public Albums getAlbumById(Long id){
        return repository.findById(id).orElseThrow(() -> new DataNotFoundException("Album with "+id+" not dound in database"));
    }


    public void delete(Albums albums){
        repository.delete(albums);
    }


}
