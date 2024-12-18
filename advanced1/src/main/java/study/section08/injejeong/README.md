# 생산자 소비자 문제1
## 개념
- 멀티 스레드 프로그래밍에서 발생할 수 있는 동시성 문제
- 여러 스레드가 동시에 데이터를 생산하거나 소비할 때 발생
- 생산자(producer): 데이터를 생성하여 버퍼에 저장
  - 버퍼가 가득 차있을 경우 대기
- 소비자(consumer): 버퍼에서 데이터를 꺼내 처리
  - 버퍼가 비어있을 경우 대기
- 버퍼(buffer): 생산된 데이터를 임시적으로 저장하는 메모리 공간
  - 버퍼를 사용해 데이터를 잠시 저장하면 데이터의 손실 방지 가능
  - 생산자와 소비자가 독립적으로 활동 가능, 생산자가 데이터를 쌓아두면 소비자는 나중에 그 데이터들을 처리 가능

## 해결 방법: `wait()`, `notify()`
- `synchronized` 블록 내에서 호출 가능
- `wait()`: 현재 락을 가진 스레드가 락을 반납하고 다른 스레드가 `notify()`를 호출하기 전까지 대기(`WAITING`)
- `notify()`: 대기중인 스레드 중 임의의 스레드 하나를 깨움
- `notifyAll()`: 대기중인 모든 스레드 깨움, 필요한 스레드가 깨어나지 않을 경우를 고려할 때 더 안전할 수 있음
- 한계:
  - 서로의 락을 기다리는 무한 대기 상태에 빠질 수 있음
  - `notify()`는 임의의 스레드 하나를 깨우기 때문에, 중요한 스레드가 계속 선택되지 않는 기아 문제 발생 가능

```java
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.logging.Logger;

class BoundedQueue {
    private static final Logger log = Logger.getLogger(BoundedQueue.class.getName());
    private final Queue<String> queue = new ArrayDeque<>();
    private final int max;

    public BoundedQueue(int max) {
        this.max = max;
    }

    public synchronized void put(String data) {
        while (queue.size() == max) {
            log.info("큐가 가득 참, 생산자 대기");
            try {
                wait();
                log.info("생산자 깨어남");
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        }
        queue.offer(data);
        log.info("데이터 추가: " + data);
        notifyAll();
    }

    public synchronized void take() {
        while (queue.isEmpty()) {
            log.info("큐가 비어있음, 소비자 대기");
            try {
                wait();
                log.info("소비자 깨어남");
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        }
        String data = queue.poll();
        log.info("데이터 꺼냄: " + data);
        notifyAll();
        return data;
    }
    
    @Override
    public String toString() {
        return queue.toString();
    }
}
```