package com.example.backend_academic_monitoring.Repository;

import com.example.backend_academic_monitoring.DTO.LastMessageDTO;
import com.example.backend_academic_monitoring.Entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<MessageEntity, Integer> {
    List<MessageEntity> findAllByChatIdOrderByDateDesc(String chatId);

    @Query(value = """
            SELECT m.id, content, date, sender, receiver, chat_id as chatId, i.uuid as uuid, is_seen as isSeen,
                    u.id as userId, username, p.name,lastname
                    FROM messages m join acad_user u on (m.sender = u.username or m.receiver = u.username)
                          join person p on u.id = p.acad_user_id
                          left join image i on u.image_id = i.id
                    WHERE (chat_id, date) IN (
                         SELECT chat_id, MAX(date)
                         FROM messages am
                         where receiver = :username or sender like :username
                         GROUP BY am.chat_id
                    ) and username != :username  order by date desc""", nativeQuery = true)
    List<LastMessageDTO> findLastMessages(String username);

    @Query(value = """
            SELECT m.id, content, date, sender, receiver, chat_id as chatId, i.uuid as uuid, is_seen as isSeen,
                                                       u.id as userId, username, p.name,lastname
                                                FROM messages m join acad_user u on (m.sender = u.username or m.receiver = u.username)
                                                                join person p on u.id = p.acad_user_id
                                                                left join image i on u.image_id = i.id
                                                WHERE chat_id = :chatId and date = (
                                                    SELECT MAX(date)
                                                    FROM messages am
                                                    where chat_id = :chatId
                                                ) and u.username != :username order by date desc;""", nativeQuery = true)
    LastMessageDTO getNotification(String chatId, String username);
}
