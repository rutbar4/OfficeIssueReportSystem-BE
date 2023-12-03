package com.sourcery.oirs.service;

import com.sourcery.oirs.database.entity.PictureEntity;
import com.sourcery.oirs.database.repository.PictureRepository;
import com.sourcery.oirs.exceptions.IssueNotFoundException;
import com.sourcery.oirs.model.Issue;
import com.sourcery.oirs.model.Picture;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<String> getPictureLinksByIssueId(UUID issueId) {
        return pictureRepository.getPictureLinksByIssueId(issueId);
    }
    public void addPicture(String url, UUID issueId, UUID userId) {

        pictureRepository.addPicture(
                PictureEntity.builder()
                        .id(UUID.randomUUID())
                        .url(url)
                        .issueId(issueId)
                        .userId(userId)
                        .build()
        );
    }

    public void deletePicture(UUID id, String link) {
        pictureRepository.deletePicture(id,link);
    }
}
