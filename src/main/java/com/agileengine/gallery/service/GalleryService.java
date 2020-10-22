package com.agileengine.gallery.service;

import com.agileengine.gallery.dto.ImageDto;
import com.agileengine.gallery.helper.GalleryHelper;
import com.agileengine.gallery.model.Image;
import com.agileengine.gallery.repository.ImageRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
public class GalleryService {
    private final ImageRepository repository;

    public void updateData(String token) {
        Map<Integer, List> pageImages = GalleryHelper.getImages(token);

        for (Map.Entry<Integer, List> elem : pageImages.entrySet()) {
            for (Object o : elem.getValue()) {
                Map image = (Map) o;
                Image info = GalleryHelper.getInfo((String) image.get("id"), token);

                Image item = repository.findById((String) image.get("id")).orElse(new Image((String) image.get("id")));

                item.setAuthor(info.getAuthor());
                item.setCamera(info.getCamera());
                item.setCropped_picture(info.getCropped_picture());
                item.setFull_picture(info.getFull_picture());
                item.setTags(info.getTags());
                item.setPage(elem.getKey());

                repository.save(item);
            }
        }
    }

    public Optional<Image> getImageById(String id) {
        return repository.findById(id);
    }

    public List<ImageDto> getAll() {
        return repository.findAll().stream()
                .map(image -> new ImageDto(image.getId(), image.getCropped_picture()))
                .collect(Collectors.toList());
    }

    public List<ImageDto> getPage(Integer page) {
        return repository.findAllByPage(page).stream()
                .map(image -> new ImageDto(image.getId(), image.getCropped_picture()))
                .collect(Collectors.toList());
    }

    public List<ImageDto> searchImagesByTerm(String searchTerm) {
        return repository.findAll(GalleryHelper.containsText(searchTerm)).stream()
                .map(image -> new ImageDto(image.getId(), image.getCropped_picture()))
                .collect(Collectors.toList());
    }
}
