// esse codigo deveria fazer o que?

let f = function(){
    let n = params[0];
    return function(){
        console.log(n);
    };
};

let a = f(0);
a();
let b = f(1);
a();