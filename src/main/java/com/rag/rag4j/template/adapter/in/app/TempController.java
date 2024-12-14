package com.rag.rag4j.template.adapter.in.app;

import com.rag.rag4j.common.documents.port.WebRestInputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@WebRestInputPort
@RequestMapping("/temp")
@RequiredArgsConstructor
public class TempController {

    @GetMapping("/test")
    public ResponseEntity<Map<String, String>> test() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Hello, REST Docs!");
        return ResponseEntity.ok(response);
    }
}