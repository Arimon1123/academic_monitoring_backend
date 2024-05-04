package com.example.backend_academic_monitoring.Repository;

import com.example.backend_academic_monitoring.Entity.AnnouncementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnouncementRepository extends JpaRepository<AnnouncementEntity, Integer> {
    @Query(value = """
                   Select * from announcement a where ( receivers ->> 'receiver' = :receiver or receivers ->> 'receiver' = 'ALL' )
                         and :gradeId in( Select cast(jsonb_array_elements_text(receivers -> 'grade') as int) as gradeIds from announcement b where a.id = b.id  group by gradeIds)
                         and cast(receivers->> 'shift' as int) = :shift and publishingdate <= now()
                         order by a.publishingdate desc ;
            """, nativeQuery = true)
    List<AnnouncementEntity> findAllByReceiver(String receiver, Integer gradeId, Integer shift);
}
