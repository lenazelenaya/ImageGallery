package com.agileengine.gallery.controller;


import com.agileengine.gallery.dto.ImageDto;
import com.agileengine.gallery.model.Image;
import com.agileengine.gallery.model.Response;
import com.agileengine.gallery.service.GalleryService;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/")
public class GalleryController {

    private final GalleryService service;
    private final Response response;

    public GalleryController(GalleryService service, Response response) {
        this.service = service;
        this.response = response;
    }

    @GetMapping("/images")
    public List<ImageDto> getAll(@RequestParam(required = false) Integer page) {

        update();

        if (service.getPage(page).isEmpty() && service.getAll().isEmpty()){
            throw new RuntimeException("Data could not be found.");
        }

        return page != null ? service.getPage(page) : service.getAll();
    }

    @GetMapping("/images/{id}")
    public Image get(@PathVariable String id) {

        update();

        return service.getImageById(id).orElseThrow(() -> new EntityNotFoundException(id));

    }

    @GetMapping("/search/{searchTerm}")
    public List<ImageDto> search(@PathVariable String searchTerm) {

        update();

        return service.searchImagesByTerm(searchTerm);
    }

    public void update() {
        if (response.isAuthorized()) {
            service.updateData(response.getToken());
        }
    }
}
