package org.example.rssamocatclone.repository.crudRepository;


import org.example.rssamocatclone.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCrudRepository extends JpaRepository<Users, Integer> {

}
