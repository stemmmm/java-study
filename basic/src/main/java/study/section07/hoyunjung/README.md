# JVM 메모리 구조

- JVM 메모리 구조는 크게 메서드, 힙, 스택 영역으로 이루어짐

> JVM 메모리 구조에 대한 더 자세한 내용은 [여기](https://velog.io/@stemmmm/jvm)를 참고하세요.

## 메서드 영역

- 프로그램을 실행하는데 필요한 공통 데이터를 관리하며, 프로그램의 모든 영역에서 공유됨
- 메서드 영역은 클래스 정보 영역, static 영역, 런타임 상수 풀로 구성됨
    - 클래스 정보 영역: 클래스, 필드, 메서드 등의 바이트 코드 저장
    - static 영역: `static` 필드, 메서드 저장
    - 런타임 상수 풀: 프로그램의 공통 리터럴 및 프로그램을 효율적으로 관리하기 위한 상수들 저장

## 힙 영역

- 참조 타입(클래스, 배열)의 인스턴스가 생성되는 영역
- Garbage collection이 발생해 사용하지 않는 메모리를 해제함

> Garbage collection에 대한 더 자세한 내용은 [여기](https://velog.io/@stemmmm/gc)를 참고하세요.

## 스택 영역

- 함수 호출 및 리턴 관련 정보가 저장되는 영역이며, 쓰레드 별로 생성됨
- 스택 자료구조 방식으로 동작하며, 함수 호출 시 스택 프레임이 `push`, 리턴 시 `pop` 됨
- 스택 프레임에는 지역 변수(파라미터), 리턴 주소 등 함수 실행을 위한 정보가 저장됨

## `main` 메서드가 `static`으로 선언된 이유?

- `main` 메서드는 자바 프로그램의 시작점임
- 따라서 JVM은 자바 프로그램을 실행하기 위해 특정 클래스의 `main` 메서드를 찾아 실행함
- 이때 `main` 메서드가 `static`이라면, JVM은 해당 클래스의 인스턴스를 생성하지 않도고 `main` 메서드를 호출할 수 있음
- 이를 통해 자바 프로그램을 간편하게 실행할 수 있음