# b_controller/bbs
- a_route에서 response,request,query를 받아온다
- query의 조건문을 분리하여 query를 정제한다

### read일 때
- 여기서 search는 a_route의 query이다
- type이 all이면 query정제를 하지 않는다(전체검색)
- type이 no이면 기본값은 -1로 설정해놨다가 -> query의no를 int로 바꿔서 정제한다
- [c_dao](https://github.com/kps990515/ProgrammingStudy/tree/master/node.js/Severbbs/c_dao)에 query와 callback함수를 전달한다
- callback으로 결과값이 전달되면 result를 Json화해서 전송한다

```javascript
var dao = require("../c_dao/bbs")

exports.read = function(request, response, search){
	var query = {};
	if(search.type === "all"){
		query = {};
	}else if(search.type === "no"){
		query = {no : -1};
		query.no = parseInt(search.no);
	}

	dao.read(query, function(dataset){
		var result = {
			code : 200,
			msg : "정상처리",
			data : dataset
		};
		response.end(JSON.stringify(result));
	});
};
```

### creaete일 때
- read와 동일
- 여기서 bbs는 앱이나 웹에서 받아오는 정보
- c_dao에서 callback할때 날라오는 값은 code만

```javascript
exports.create = function(request, response, bbs){
	dao.create(bbs, function(result_code){
		var result = {
			code : result_code,
			msg : "입력완료"
		};

		response.end(JSON.stringify(result));
	});
};

exports.update = function(request, response, bbs){

};

exports.delete = function(request, response, bbs){

};
```
