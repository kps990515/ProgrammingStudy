# c_dao/bbs
- 진짜 각각의 명령어들이 정의되어 있음
- 명령어들과 mongodb가 연결되어 실제 작동 부분

```javascript
var mongo = require("mongodb").MongoClient;
var dbname = "bbsdb";
var dburl = "mongodb://localhost:27017/"+dbname;
var table = "bbs";
```

### 명령어부분
- b_controller에서 입력된정보(bbs), callback함수를 받아온다
- mongodb와 연결한다
- 각각의 맞는 명령어들을 실행시켜준다
- 끝나면 callback함수에 값을 넘겨준다

#### create
- callback으로 성공하면 400, 실패시 200넘겨줌

```javascript
exports.create = function(bbs, callback){
	mongo.connect(dburl, function(error, db){
		db.collection(table).insert(bbs,function(error,inserted){
			if(error){
				callback(400);
			}else{
				callback(200);
			}
			db.close();
		});
	});
}
```

#### read
- 찾은 값들을 cursor에 집어넣고
- array로 만든다 - callback에 cursorarray를 넘겨준다

```javascript
exports.read = function(search, callback){
	mongo.connect(dburl, function(error, db){
		var cursor = db.collection(table).find(search);
		cursor.toArray(function(error,documents){
			if(error){

			}else{
				callback(documents);
			}
			db.close();
		});
	});
}
```

#### readOne
- 찾은 값들을 cursor에 집어넣고
- array로 만든다 - callback에 cursorarray를 넘겨준다

```javascript
exports.readOne = function(search, callback){
	mongo.connect(dburl, function(error, db){
		var query = {};
		if(search.type === "all"){
			query = {};
		}else if(search.type === "no"){
			query = {no : -1};
			query.no = search.no;
		}

		var cursor = db.collection(table).find(query);

		cursor.toArray(function(error,documents){
			if(error){

			}else{
				callback(documents);
			}
			db.close();
		});
	});
}
```

#### update
- _ id는 mongodb자체 id
- 이것을 -1으로 기본값으로 주고 -> bbs의 _ id값을 query값에 넣어준다(수정될 쿼리의 _ id);
- operator에 bbs에 전달된 값을 넣어주고(수정될 쿼리의 수정할 내용)
- operator의 _ id값을 지워준다(mongo에서 다시 할당해줄거라서)
- upsert : true로 설정해준다(수정할 데이터 없으면 insert로 자도변경)

```javascript
exports.update = function(bbs){
	mongo.connect(dburl, function(error, db){
		//1. 수정대상쿼리
		var query = {_id:-1};
		query._id = bbs._id;
		//2. 데이터 수정명령 - 실제 변경될 컬럼이름과 값
		var operator = bbs;
		delete operator._id;

		//3. 수정옵션 - upsert true 일때 데이터가 없으면 insert
		var option = {upsert:true};

		db.collection(table).update(query, operator, option, function(err, upserted){
			if(err){

			}else{
				// 정상처리
			}
			db.close();
		});
	});
}
```

#### delete
- bbs.no를 받아와서 삭제할 query의 no로 설정해주고
- 지운다

```javascript
exports.delete = function(bbs){
	mongo.connect(dburl, function(error, db){
		//1. 수정대상쿼리
		var query = {no:-1};
		query.no = bbs.no;

		db.collection(table).remove(query, function(err, removed){
			if(err){

			}else{
				// 정상 삭제
			}
			db.close();
		});
	});
}
