package com.workmanager.repository;

import com.workmanager.model.Project;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findProjectByProjectCode(String code);
}
