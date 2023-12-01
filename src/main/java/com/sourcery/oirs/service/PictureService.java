package com.sourcery.oirs.service;

import com.sourcery.oirs.database.entity.PictureEntity;
import com.sourcery.oirs.database.repository.PictureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PictureService {

    private final PictureRepository pictureRepository;

    public void savePicture(String url, UUID issueId, UUID userId) {

        pictureRepository.insertPicture(
                PictureEntity.builder()
                        .id(UUID.randomUUID())
                        .url(url)
                        .issueId(issueId)
                        .userId(userId)
                        .build()
        );
    }
}
