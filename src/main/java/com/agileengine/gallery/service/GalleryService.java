package com.agileengine.gallery.service;

import com.agileengine.gallery.repository.ImageRepository;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.stereotype.Service;

@Service
public class GalleryService {
    private final ImageRepository pictureRepository;
    private final CloseableHttpClient closeableHttpClient;

    public GalleryService(ImageRepository pictureRepository, CloseableHttpClient closeableHttpClient) {
        this.pictureRepository = pictureRepository;
        this.closeableHttpClient = closeableHttpClient;
    }

}
