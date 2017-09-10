# Stream

### 정의 - 자료의 입출력을 도와주는 중간 매개체

##### 스트림 전체 정의
- 스트림은 데이터를 읽고 기록하는 중간역할을 한다.  
스트림은 빨대다.  
빨대는 음료수를 마시는 중간역할을 한다.  
빨대는 입에 있는 음료수를 다시 내뱉는 중간역할을 한다.  
스트림은 단 방향 빨대이다. 음료수를 내뱉고 다시 마시려면 빨대가 2개 필요하다.  

##### 입력 스트림
- 음료수를 마실 때 빨대를 이용하여 음료수를 빨대에 모으고 빨대에 들어있는 음료수를 흡입한다.  
그러면 음료수가 입안으로 들어 올 것이다.

##### 출력 스트림
- 출력 스트림으로 데이터를 보낸다.   
출력 스트림에 보낸 데이터를 비워 버린다.   
그렇게 되면 출력 스트림에 존재하던 데이터가 모두 목표지점에 저장된다.

- 입 안에 있던 음료수를 빨대로 일단 보낸다.   
빨대에 들어있는 음료수를 불어 버린다.  
그렇게 되면 음료수는 다시 컵 안으로 들어가게 된다.

### 스트림의 종류

##### 1. 바이트 스트림
[!]
### Controller
- the controller, accepts input and converts it to commands for the model or view.
> controller는 입력을 받고 입력을 model이나 view가 받을 수 있는 명령으로 바꿔준다

- controller can send commands to the model to update the model's state (e.g., editing a document).  
It can also send commands to its associated view to change the view's presentation of the model
> controller는 model의 상태를 바꾸기 위해 명령을 보낼 수 있다  
> 또한 model을 표현하고 있는 view를 바꾸기 위해 명령을 보낸다

### 목표
##### 1. 동시개발
- MVC decouples the various components of an application,  
 developers are able to work in parallel on  
  different components without impacting or blocking one another.
> MVC는 어플리케이션을 여러 부분으로 분리하기 때문에  
개발자들이 서로 다른 부분을 평행적으로 충돌이나 방해 없이 개발할 수 있다.

##### 2. 코드 재사용성
- By creating components that are independent of one another,  
 developers are able to reuse components quickly and easily in other applications.
 > 코드를 역활마다 쪼개놓기 때문에 다른 어플을 개발할 때도  
같은 기능이 존재하면 코드를 재사용 할 수 있다.

참고사이트 : 위키피디아(https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller)

참고사이트 : 생활코딩(https://opentutorials.org/course/697/3828)
