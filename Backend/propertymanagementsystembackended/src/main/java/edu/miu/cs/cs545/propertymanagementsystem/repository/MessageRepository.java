package edu.miu.cs.cs545.propertymanagementsystem.repository;

import edu.miu.cs.cs545.propertymanagementsystem.model.Message;
import edu.miu.cs.cs545.propertymanagementsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query("select m from Message m join m.recipient r where r.user_id=:recipientId")
    List<Message> findByRecipientId(Long recipientId);
    @Query("select m from Message m join m.sender s where s.user_id=:senderId")
    List<Message> findBySenderId(Long senderId);
   @Query("select m from Message m join m.sender s join m.recipient r where " +
        "s=:sender and r=:recipient")
    List<Message> findBySenderAndRecipient(User sender, User recipient);
}
