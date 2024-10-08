# 예외 처리 - 이론
## 예외 계층
- `Throwable`: 예외의 최상위 클래스, `Error`와 `Exception`의 두 가지 하위 클래스를 가짐
- `Error`: 직접적으로 처리하지 않아야 하며, 시스템 레벨의 심각한 문제
  - `OutOfMemoryError`: 메모리가 부족할 때 발생
  - `StackOverflowError`: 호출 스택이 너무 깊어졌을 때 발생
- `Exception`: 체크 예외, 컴파일 예외 
  - 컴파일 시점에 체크되는 예외로, 코드에서 명시적으로 처리해야함
  - 그렇지 않으면 컴파일 오류 발생
- `RuntimeException`: 언체크 예외, 런타임 예외
  - 런타임 시점에 발생하며, 컴파일 시점에 체크되지 않음
## 예외 처리의 기본 규칙
- 예외는 잡아서 처리하거나 던져야 함
- 예외 처리 시 지정 예외뿐만 아니라 그 예외의 하위들도 함께 처리 가능
  - 일반적인 예외 보다는 구체적인 예외 타입을 사용하여 처리
## 예외 처리를 위한 키워드
- `try`: 예외가 발생할 가능성이 있는 코드 블록을 감싸는 데 사용되며 예외 발생 시 `catch` 블록으로 전달
- `catch`: `try` 블록에서 발생한 예외를 처리
- `finally`: 예외가 발생하든 발생하지 않든 반드시 실행되는 코드 블록이며, 자원 해제 작업 수행 시 주로 사용
- `throw`: 예외를 명시적으로 발생시킬 때 사용
- `throws`: 발생시킨 예외를 메서드 밖으로 던질 때 사용
  - 체크 예외: 예외를 처리하지 않으려면 항상 `throws`로 던지는 예외를 선언해야 함
  - 언체크 예외: 예외를 처리하지 않아도 `throws` 생략 가능
```java
try {
    // 예외 발생, 정상 흐름
    System.out.println("try 블록 실행");
} catch (Exception e) {
    // 예외 처리, 예외 흐름
    System.out.println("예외 발생: " + e.getMessage());
} finally {
    // 항상 실행되는 코드, 마무리 흐름
    System.out.println("finally 블록 실행");
}
```
## 체크 예외
- 체크 예외를 처리하지 않으면 컴파일 에러가 발생하기 때문에 예외를 명시적으로 처리하도록 강제함
- 코드의 안정성을 높일 수 있음
- `try-catch` 블록을 사용하거나 메서드에 `throw`로 예외를 발생시켜야 함
- 예외를 처리할 수 없는 경우 `throws`로 예외를 밖으로 던지기 가능
```java
class MyCheckedException extends Exception {
    public MyCheckedException(String message) {
        super(message);
    }
}

public class CheckedExceptionExample {
    public static void main(String[] args) {
        try {
            // 체크 예외 발생
            checkedException();
        } catch (MyCheckedException e) {
            // 예외 처리
            System.out.println("예외 발생: " + e.getMessage());
        }
    }
    
    public static void checkedException() throws MyCheckedException {
        throw new MyCheckedException("체크 예외");
    }
}
```
## 언체크 예외
- 언체크 예외는 예외를 명시적으로 처리하지 않아도 되므로 처리하거나 무시할 수 있음
- 예외를 누락할 수 있음
```java
public class NullPointerExceptionExample {
    public static void main(String[] args) {
        String str = null;

        try {
            // NullPointerException 발생
            int length = str.length();
        } catch (NullPointerException e) {
            // 예외 처리
            System.out.println("NullPointerException 발생: " + e.getMessage());
        }
    }
}
```