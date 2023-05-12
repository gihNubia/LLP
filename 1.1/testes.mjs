// 0.9090764846396189
console.log(console.random());

// repete o que escreveu
// console.log(console.read());

let dobro = function(){
    return 2 * params[0];
};

// 10
console.log(dobro(5));

let cria_multiplicador = function(){
    const n = params[0];
    return function(){
        return n  * params[0];
    };
};

// 30
console.log(cria_multiplicador(5)(6));

let cachorro = {
    idade: 8,
    nome: "cachorro",
    latir: function(){
        console.log("au au");
    }
};

let print_cachorro = function(){
    console.log(params[0].nome + " tem " + params[0].idade + " anos.");
    params[0].latir();
};

// cachorro tem 8 anos.
// au au
print_cachorro(cachorro);

cachorro.idade = 9;
cachorro.nome = "cachorro velho";
cachorro.latir = function() {console.log("auuuu");};

// cachorro velho tem 9 anos.
// auuuu
print_cachorro(cachorro);

// undefined ~ null
console.log(cachorro.tamanho);

let cria_lista_aleatoria = function(){
    let i = 0;
    let list = [];
    while (i < params[0]){
        list[i] = console.random();
        i++;
    }

    return list;
};

let calcula_media = function(){
    let n = 0, soma = 0;
    for(let numero in params[0]){
        soma = soma + numero;
        n++;
    }

    return soma / n;
};

let listas = [
    cria_lista_aleatoria(1),
    cria_lista_aleatoria(5),
    cria_lista_aleatoria(10),
    cria_lista_aleatoria(50),
    cria_lista_aleatoria(100),
    cria_lista_aleatoria(500),
    cria_lista_aleatoria(1000)
];

// printa media de varias listas
// esperamos que quanto maior a lista, mais proximo de 0.5
for (let lista in listas){
    console.log(calcula_media(lista));
}

// undefined ~ null
console.log(listas[0][1]);

// true
console.log("ab" == "ab");

// false
console.log("ab" == "a");

let x = {oi: 1};
x.mundo = 2;
x["louco"] = 3;
x[false] = 4;
x[10] = 5;

for (let i in x) {
    console.log(i + ": " + x[i]);
}

for (let j in [1, 2, 3, 4]){
    console.log(j);
}