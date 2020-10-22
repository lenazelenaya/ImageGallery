package com.agileengine.gallery.repository;

import com.agileengine.gallery.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ImageRepository extends JpaRepository<Image, UUID>, JpaSpecificationExecutor<Image> {

    List<Image> findAllByPage(Integer page);

    Optional<Image> findById(String id);
}
