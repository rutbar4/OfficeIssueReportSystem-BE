package com.sourcery.oirs.database.repository;


import com.sourcery.oirs.model.Issue;
import com.sourcery.oirs.model.IssueDetailsResponseDto;
import com.sourcery.oirs.model.Office;
import com.sourcery.oirs.model.OfficeResponseDTO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Mapper
@Repository
public interface OfficeRepository {
    @Select("SELECT " +
            "o.ID as id, " +
            "o.OFFICE_NAME as name, " +
            "c.COUNTRY_NAME as country " +
            "FROM Office o " +
            "LEFT JOIN Country c ON o.COUNTRY_ID = c.ID")
    List<OfficeResponseDTO> findAllOffices();

}
