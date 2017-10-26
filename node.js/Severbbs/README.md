# 1. Server
- listen에서 누군가 요청이 들어오면 request, response를 받아서 함수로 전달
- [route](https://github.com/kps990515/ProgrammingStudy/tree/master/node.js/Severbbs/a_route)의 process에 전달하고 실행

```javascript
var http = require("http");
var route = require("./a_route");

var server = http.createServer(function(request,response){
	route.process(request,response);
});

server.listen(8090,function(){
	console.log("server's running...");
});
```
