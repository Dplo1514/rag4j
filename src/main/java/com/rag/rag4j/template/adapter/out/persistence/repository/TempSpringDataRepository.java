package com.rag.rag4j.template.adapter.out.persistence.repository;

import com.rag.rag4j.template.adapter.out.persistence.entity.TempEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TempSpringDataRepository extends JpaRepository<Long, TempEntity> {

}
