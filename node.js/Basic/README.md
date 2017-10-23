# JavaScript Basic

### 1. 변수의 선언 : var 변수명

```JavaScript
var a =11;
var b = "hello";
```

### 2. 출력
```JavaScript
console.log(a);
console.log(b);
```

### 3. 반복문
```JavaScript
for(var i=0; i<10; i++){
	console.log(i);
}
```

### 4. 조건문
```JavaScript
if(a>10){
	console.log("a가 10보다 큽니다");
}else if(a<10){
	console.log("a가 10보다 작습니다");
}else{
	console.log("a가 10입니다");
}
```

### 5. 문자열 기본연산
```JavaScript
var s = "abc"+"def";
console.log(s);
```

### 6. 함수사용하기(파라미터의 타입이 없다)
```JavaScript
// 문장내에서 return 유무에 따라 return 타입이 결정
function sum(a,b){
    return a+b;
}

var sum = function(param1,param2){
    return param1+param2;
};
```

### 7. class 사용하기(함수 만드는 것과 동일)
```JavaScript
// function 클래스명(param1, param2) -> 클래스일 때 클래스명 맨 앞자는 대문자
function Num(param1, param2){
    var a=0; // private 선언된 변수
    this.b=10; // public 선언된 변수
    function sum(a,b){  // private선언된 함수
        return a+b;
    }
    this.sum = function(a,b){ // public 선언됨 함수
        return a+b;
    };

}
```

### 8. class 사용
```JavaScript
var num = new Num("hello", 157);
num.b=500;
num.sum(3,5);
```
