package com.rag.rag4j.template.adapter.out.persistence;

import com.rag.rag4j.common.documents.adaptor.PersistenceOutputAdaptor;
import com.rag.rag4j.common.documents.port.PersistenceOutputPort;
import com.rag.rag4j.template.adapter.out.persistence.repository.TempRepository;
import com.rag.rag4j.template.application.port.out.TempOutputPort;
import java.util.List;

@PersistenceOutputAdaptor
public class TempPersistentAdaptor implements TempOutputPort {

    TempRepository repository;

}
