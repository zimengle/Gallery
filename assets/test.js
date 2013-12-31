var Utils = {};
Utils.MyClass1 = function (id, member) {
    this.id = id;
    this.member = member;
    this.sayHello = function(){
    	alert("hello"+member);
    }
}
var myobject = { MyClass1: new Utils.MyClass1("5678999", "text") };
console.info(myobject.toString());