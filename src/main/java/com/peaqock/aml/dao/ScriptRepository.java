package com.peaqock.aml.dao;

import com.peaqock.aml.domain.Script;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ScriptRepository extends JpaRepository<Script, UUID> {

}
