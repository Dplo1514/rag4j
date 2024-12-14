package com.rag.rag4j.prompt.adapter.in.app;

import com.rag.rag4j.common.adapter.in.dto.response.CommonBody;
import com.rag.rag4j.common.documents.port.WebRestInputPort;
import com.rag.rag4j.prompt.adapter.in.dto.request.InputRequest;
import com.rag.rag4j.prompt.application.port.in.UseCaseImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

// 사용자 입력을 받아 응답을 처리하는 컨트롤러 기능
@WebRestInputPort
@RequestMapping("/api/input")
@RequiredArgsConstructor
public class InputController {

    private final UseCaseImpl inputUseCase;

    @PostMapping("")
    public ResponseEntity<CommonBody<String>> processInput(@RequestBody InputRequest request) {
        // 입력으로부터 텍스트 확인
        String result = inputUseCase.process(request.getInputText());
        return ResponseEntity.ok(CommonBody.of(200, "Success", result));
    }
}