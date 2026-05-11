package com.example.demo.repository;

import com.example.demo.Options;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Options, Long> {
}