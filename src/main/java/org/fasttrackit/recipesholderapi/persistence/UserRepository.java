package org.fasttrackit.recipesholderapi.persistence;

import org.fasttrackit.recipesholderapi.domanin.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
