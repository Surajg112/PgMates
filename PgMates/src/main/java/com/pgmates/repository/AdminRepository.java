package com.pgmates.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pgmates.models.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {

}
