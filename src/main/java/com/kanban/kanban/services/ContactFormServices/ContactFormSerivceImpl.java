package com.kanban.kanban.services.ContactFormServices;

import com.kanban.kanban.model.ContactFormDto;
import com.kanban.kanban.model.ContactFormEntity;
import com.kanban.kanban.repository.ContactFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactFormSerivceImpl implements ContactFormSerivce {
    @Autowired
    private ContactFormRepository contactFormRepository;

    @Override
    public ContactFormEntity saveContactFormEntity(ContactFormDto contactFormDto) {
        return contactFormRepository.save(new ContactFormEntity
                (
                contactFormDto.getFirstname()
                ,contactFormDto.getLastname()
                ,contactFormDto.getEmail()
                ,contactFormDto.getNumber()
                ,contactFormDto.getCountry()
                ,contactFormDto.getDescription()
                ));

    }


}
