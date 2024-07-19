package com.kanban.kanban.services.ContactFormServices;

import com.kanban.kanban.model.ContactFormDto;
import com.kanban.kanban.model.ContactFormEntity;
import com.kanban.kanban.repository.ContactFormRepository;
import org.springframework.beans.factory.annotation.Autowired;

public interface ContactFormSerivce {

    public ContactFormEntity saveContactFormEntity(ContactFormDto contactFormDto);

}
