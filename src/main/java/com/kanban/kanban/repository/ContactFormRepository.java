package com.kanban.kanban.repository;

import com.kanban.kanban.model.ContactFormEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactFormRepository extends JpaRepository<ContactFormEntity, Long> {


}
