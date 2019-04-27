package com.oskarro.justcode.repositories;

import com.oskarro.justcode.domains.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Long> {

}
