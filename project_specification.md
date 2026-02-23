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
- 현재 추가된 외부 의존성 없음 (최소 기능 프로젝트)

## 5. 설정 및 구조
- **환경 설정**: `src/main/resources/application.yml`
- **핵심 패키지**: `com.example.vibeapp`
- **애플리케이션 시작 클래스**: `VibeApp.java`
