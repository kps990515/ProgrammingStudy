// db/index.js
var mongo = require("mongodb").MongoClient;
var dbname = "bbsdb";
var dburl = "mongodb://localhost:27017/"+dbname;
var table = "bbs";
var page_count = 20;

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

exports.read = function(search, callback){
	mongo.connect(dburl, function(error, db){
        var sort = {
            no : -1 // 1 오름차순, -1 내림차순 
        };
        // skip - 카운트를 시작할 index의 위치
        // limit - 가져올 개수
        var start = (search.page-1)*page_count;
        // 사용하지 않는 검색컬럼은 삭제처리
        delete search.page;
        var cursor = db.collection(table).find(search).sort(sort)
                        .skip(start).limit(page_count);
		cursor.toArray(function(error,documents){
			if(error){

			}else{
				callback(documents);
			}
			db.close();
		});
	});
}

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