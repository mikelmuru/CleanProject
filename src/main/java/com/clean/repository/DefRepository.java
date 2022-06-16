package com.clean.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clean.entity.DefClass;

public interface DefRepository extends JpaRepository<DefClass, Integer>{

}
