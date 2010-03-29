% Math test 
% $Id: Tests.java,v 1.2 2008/04/21 17:53:11 dienst Exp $

"Math test" println

"Exponential numbers: 1E3=" 1E3  println

"10+1= " print 10 1 addi  println
"10+(-11)= " print 10 -11 addi  println
"10.0+1.1= " print 10.0 1.1 addf  println
"10.0+(-11.1)= " print 10.0 -11.1 addf  println

"acos(0.5)=" print 0.5 acos  println
"acos(-0.5)=" print -0.5 acos  println

"asin(0.5)=" print 0.5 asin  println
"asin(-0.5)=" print -0.5 asin  println

"clampf(-0.01)=" print -0.01 clampf  println
"clampf(0.01)=" print 0.01 clampf  println
"clampf(1.31)=" print 1.31 clampf  println

"cos(60)=" print 60.0 cos  println
"cos(0)=" print 0.0 cos  println
"cos(-60)=" print -60.0 cos  println

" 17 divi  4 = " print 17 4 divi  println
" 17 modi  4 = " print  17  4 modi  println
"-17 divi  4 = " print -17 4 divi  println
"-17 modi  4 = " print  -17  4 modi  println
"-17 divi -4 = " print -17 -4 divi  println
"-17 modi -4 = " print  -17  -4 modi  println
" 17 divi -4 = " print 17 -4 divi  println
" 17 modi -4 = " print  17  -4 modi  println

"  8 divi  2 = " print 8 2 divi  println
" -8 divi  2 = " print -8 2 divi  println
" -8 divi -2 = " print -8 -2 divi  println
"  8 divi -2 = " print 8 -2 divi  println

"5 divf 2 = " print 5.0 2.0 divf  println
"-5 divf 2 = " print -5.0 2.0 divf  println
"5 divf -2 = " print 5.0 -2.0 divf  println
"-5 divf -2 = " print -5.0 -2.0 divf  println

"5 eqi 5 = " print 5 5 eqi  println
"5 eqi -5 = " print 5 -5 eqi  println

"5 eqf 5 = " print 5.0 5.0 eqf  println
"5 eqf -5 = " print 5.0 -5.0 eqf  println

"floor(2.1)=" print 2.1 floor  println
"floor(-2.1)=" print -2.1 floor  println

"frac(2.1)=" print 2.1 frac  println
"frac(-2.1)=" print -2.1 frac  println

"4 lessi 5=" print 4 5 lessi  println
"-4 lessi -5=" print -4 -5 lessi  println
"4 lessi 4=" print 4 4 lessi  println 

"4.1 lessf 5.1=" print 4.1 5.1 lessf  println
"-4.1 lessf -5.1=" print -5.1 -4.1 lessf  println
"4.1 lessf 4.1=" print 4.1 4.1 lessf  println 


"10 muli 3 = " print 10 3 muli  println
"-10 muli 3 = " print -10 3 muli  println
"10 muli -3 = " print 10 -3 muli  println
"-10 muli -3 = " print -10 -3 muli  println
"10 muli 0 = " print 10 0 muli  println

"10 mulf 3.5 = " print 10.0 3.5 mulf  println
"-10 mulf 3.5 = " print -10.0 3.5 mulf  println
"10 mulf -3.5 = " print 10.0 -3.5 mulf  println
"-10 mulf -3.5 = " print -10.0 -3.5 mulf  println
"10 mulf 0 = " print 10.0 0.0 mulf  println

"negi 3 = " print 3 negi  println
"negi -3 = " print -3 negi  println
"negi 0 = " print 0 negi  println
"negf 3 = " print 3.0 negf  println
"negf -3 = " print -3.0 negf  println
"negf 0 = " print 0.0 negf  println

"real 4 = " print 4 real  println
"real -4 = " print -4 real  println 

"sin(60)=" print 60.0 sin  println
"sin(0)=" print 0.0 sin  println
"sin(-60)=" print -60.0 sin  println
"sin(390)=" print 390.0 sin  println

"sqrt(9)=" print 9.0 sqrt  println
"sqrt(0)=" print 0.0 sqrt  println

"10 subi 3 = " print 10 3 subi  println
"-10 subi 3 = " print -10 3 subi  println
"10 subi -3 = " print 10 -3 subi  println
"-10 subi -3 = " print -10 -3 subi  println
"10 subi 0 = " print 10 0 subi  println

"10 subf 3.5 = " print 10.0 3.5 subf  println
"-10 subf 3.5 = " print -10.0 3.5 subf  println
"10 subf -3.5 = " print 10.0 -3.5 subf  println
"-10 subf -3.5 = " print -10.0 -3.5 subf  println
"10 subf 0 = " print 10.0 0.0 subf  println
%union intersect difference render  
