package com.rag.rag4j.template.adapter.in.app;

import com.rag.rag4j.common.documents.port.WebRestInputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;

@WebRestInputPort
@RequestMapping("/temp")
@RequiredArgsConstructor
public class TempController {

}