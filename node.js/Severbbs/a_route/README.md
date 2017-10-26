# a.route/index

```javascript
var u = require("url");
var qs = require("querystring");
var bbs = require("../b_controller/bbs");
```

### process
- server.process(request,response)를 받아서 실행하는 함수
- 요청받은 url을 parse
- 받은 method방식을 소문자로 바꿔준 후 요청에 맞는 기능수행
- 여기서는 pathname(?전까지)이 bbs일 때만 설정

```javascript
exports.process = function(request, response){
	var url = u.parse(request.url);
	var method = request.method.toLowerCase();
	var cmds = url.pathname.split("/");
	if(cmds[1] == "bbs"){
```

#### Method
- Method==get이면 url의 쿼리를 parse하고 [b_controller](https://github.com/kps990515/ProgrammingStudy/tree/master/node.js/Severbbs/b_controller)의 read를 실행한다
- get이 아니면 데이터를 받아서 body에 넣고 다 받아오면
- method방식에 따라 b_controller의 함수를 실행시킨다
```javascript
		if(method == "get"){
			var query = qs.parse(url.query);
			bbs.read(request, response, query);
		}else{
			// get 이외의 method는 body 데이터를 가져온다
			var body = "";
			request.on("data", function(data){
				body += data;
			});
			// 데이터 로딩이 완료되면 각 method로 분기
			request.on("end", function(){
				var bbs_body = JSON.parse(body);
				if(method == "post"){
					bbs.create(request, response, bbs_body);
				}else if(method == "put"){
					bbs.update(request, response, bbs_body);
				}else if(method == "delete"){
					bbs.delete(request, response, bbs_body);
				}
			});
		}
	}else{

	}
};
```
