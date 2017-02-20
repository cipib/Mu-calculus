// Mu-calculus grammar
grammar MuGrammar;
//formula : (form NL)+;
form : Var | Prop | nform | and | or | diamond | box | mu | nu ;
Var : 'X'|'Y'|'Z' ;
Prop : [P-S] ;
nform : Not form ;
Not : 'N' ;
and: And lpar form comma form rpar ;
lpar: '(' ;
comma: ' , ' ;
rpar: ')' ;
And : 'and' ;
or : Or lpar form comma form rpar ;
Or : 'or' ;
diamond : Dia form ;
Dia : '<a>' ;
box: Box form ;
Box : '[a]' ;
mu : Mu Var Dot form ;
Mu : 'm' ;
nu : Nu Var Dot form ;
Nu : 'n' ;
Dot : '.' ;
NL : [\n]+ ;
WS : [\n\t\r]+ -> skip ;


