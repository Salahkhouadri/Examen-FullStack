package com.cinexpress.videofriend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cinexpress.videofriend.models.DigitalPlatform;

@Repository
public interface DigitalPlatformRepository extends JpaRepository<DigitalPlatform, Long> {
}
