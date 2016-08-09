/**
 * 
 */



var SocketIdMap = function() {
	this.map = new Object();
};

SocketIdMap.prototype = {
	put : function(socketId, empId) {
		this.map[socketId] = empId;
	},
	get : function(socketId) {
		return this.map[socketId];
	},
	SocketIds : function() {
		var socketIds = {};
		for(var socket_id in this.map) {
			socketIds.push(socket_id);
		}
		return socketIds;
	},
	getSocketIdList : function(empId) {
		var socketIds = {};
		for(var socket_id in this.map) {
			if(this.map[socket_id] === empId) {
				socketIds.push(socket_id);
			}
		}
		return socketIds;
	},
	remove : function(socketId){    
		delete this.map[socketId];
	},
	EmpIds : function() {
		var empIds = {};
		for(var socket_id in this.map) {
			empIds.push(this.map[socket_id]);
		}
	}
};




Map = function(){
	this.map = new Object();
};   
Map.prototype = {   
		put : function(emp_id, value){   
			this.map[emp_id] = value;
		},   
		get : function(emp_id){   
			return this.map[emp_id];
		},
		containsEmp_id : function(emp_id){    
			return emp_id in this.map;
		},
		getemp_idList : function(value) {
			var emp_ids = new Arrays();
			
			for(var prop in this.map){
				if(this.map[prop] == value) {
					emp_ids.push(prop);
				}
			}			
			return emp_ids;
		},
		containsValue : function(value){    
			for(var prop in this.map){
				if(this.map[prop] == value) return true;
			}
			return false;
		},
		isEmpty : function(emp_id){    
			return (this.size() == 0);
		},
		clear : function(){   
			for(var prop in this.map){
				delete this.map[prop];
			}
		},
		remove : function(emp_id){    
			delete this.map[emp_id];
		},
		emp_ids : function(){   
			var emp_ids = new Array();   
			for(var prop in this.map){   
				emp_ids.push(prop);
			}   
			return emp_ids;
		},
		values : function(){   
			var values = new Array();   
			for(var prop in this.map){   
				values.push(this.map[prop]);
			}   
			return values;
		},
		size : function(){
			var count = 0;
			for (var prop in this.map) {
				count++;
			}
			return count;
		}
};