# 프로젝트 명세서: vibeapp

## 1. 프로젝트 개요
최소 기능 스프링부트 애플리케이션을 생성하는 프로젝트입니다.

## 2. 기술 스택 및 라이브러리 설정
- **JDK**: 25 이상
- **Language**: Java
- **Spring Boot**: 4.0.1 이상
- **Build Tool**: Gradle 9.3.0 이상 (Groovy DSL)
- **Configuration**: YAML 파일 (`application.yml`)

## 3. 프로젝트 메타데이터
- **Group**: `com.example`
- **Artifact**: `vibeapp`
- **Description**: 최소 기능 스프링부트 애플리케이션을 생성하는 프로젝트다.
- **Main Class Name**: `com.example.vibeapp.VibeApp`

## 4. 빌드 설정 (build.gradle)
### 4.1. 플러그인
- `org.springframework.boot` (버전 4.0.1 이상)
- `io.spring.dependency-management` (Spring Boot 버전에 맞춰 추가)
- `java`

### 4.2. 의존성 (Dependencies)
- `org.springframework.boot:spring-boot-starter-web` (웹 MVC 지원)
- `org.springframework.boot:spring-boot-starter-thymeleaf` (템플릿 엔진)
- `org.springframework.boot:spring-boot-starter-test` (테스트 지원)

## 5. 애플리케이션 구조 및 아키텍처 (도메인 주도 패키지 구조)
- **환경 설정**: `src/main/resources/application.yml`
- **시작 클래스**: `com.example.vibeapp.VibeApp`
- **핵심 모듈 구조**:
  - `com.example.vibeapp.home`: 메인 홈 화면 (`HomeController`)
  - `com.example.vibeapp.post`: 게시판 도메인 (`Post`, `PostController`, `PostService`, `PostRepository`)
- **뷰 템플릿(Thymeleaf) 경로**:
  - `src/main/resources/templates/home/` : 홈 뷰 리소스 (`home.html`)
  - `src/main/resources/templates/post/` : 게시판 뷰 리소스 (`posts.html`, `post_detail.html`, `post_new_form.html`, `post_edit_form.html`)

## 6. 구현 기능 (Features)
- **게시글 관리 (CRUD)**: 게시글 목록 조회, 상세 조회, 작성, 수정, 삭제 기능 구현
- **목록 페이징 (Pagination)**: 5개 단위 페이징 처리 및 UI 구현
- **임시 데이터 저장소**: `ArrayList`를 활용한 In-Memory 방식 레포지토리 운영 (DB 연동 전 단계)
- **UI 스타일링**: Tailwind CSS (CDN 연동)를 적용한 모던한 프론트엔드 스타일링
