package org.fasttrackit.recipesholderapi.persistence;

import org.fasttrackit.recipesholderapi.domanin.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    Page<User>findByUserNameContaining(String partialUserName, Pageable pageable);

    Page<User> findAll(Pageable pageable);
}
