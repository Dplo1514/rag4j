# Rag4j Project Local 환경 구성

> 1. 해당 README.md 문서는 Rag4j Application의 Local 개발 환경 구성에 관하여 서술합니다.
> 2. 해당 문서를 바탕으로 개발 환경 구성 진행 후 Rag4j application의 정상 구동이 불가능할 때에 하위 기재된 Main Tainer에게 연락을 부탁드립니다.  
> * Main Tainer
>    1. 임인혁 : dladlsgur3334@gmail.com
---


## 0.주의사항(선행작업)
- 환경 설정을 진행하는 개발자의 local 환경에는 아래의 세가지 package가 존재해야한다.
    - docker
    - docker compose
    - docker demon OR docker Desktop

## 1. Docker Compose Start
```bash
# 1. /etc 경로에서 실행
cd etc
# 2. docker compose container set 실행
docker compose up -d
```