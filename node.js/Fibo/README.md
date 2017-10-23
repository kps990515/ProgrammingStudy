# Fibonacci

```JavaScript
var count = 10;

// 함수를 만들고
// 0~100개의 피보나치 수열을 출력

fibo(count);

function fibo(count){
    var first=1;
    var second=1;
    if(count==0){
        console.log("0보다 큰값 입력");
    }else if(count==1){
        console.log("1");
    }else if(count==2){
        console.log("1");
        console.log("1");
    }else{
        console.log("1");
        console.log("1");
        for(var i=3; i<=count; i++){
            var result = first+second;
            console.log(result);
            first=second;
            second=result;
        }
    }
}
```
