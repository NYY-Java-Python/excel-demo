package com.gjing.excel.demo.repository;

import com.gjing.excel.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Gjing
 **/
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
