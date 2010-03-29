% cube.gml
%
% OUTPUTS: cube0.ppm cube1.ppm cube2.ppm cube3.ppm cube4.ppm cube5.ppm
%
% test cube geometry and basic texturing
%

%%%%%%%%%% include util.ins %%%%%%%%%%

{ /x } /pop		% pop a stack item
{ /x x x } /dup		% duplicate a stack item

% pop n items off the stack
{ /n
  { /self /i
    i 1 lessi
    { }
    { /x i 1 subi self self apply }
    if
  } /loop
  n loop loop apply
} /popn

% dot product
% ... v2 v1  dot  ==> ... r
{ /v1 /v2
  v1 getx v2 getx mulf
  v1 gety v2 gety mulf addf
  v1 getz v2 getz mulf addf
} /dot

% integer absolute value
{ /i i 0 lessi { i negi } { i } if } /absi

% floating-point absolute value
{ /f f 0.0 lessf { f negf } { f } if } /absf

% normalize
% ... v1  normalize  ==> ... v2
{ /v
  1.0 v v dot apply sqrt divf /s	% s = sqrt(1.0/v dot v)
  s v getx mulf				% push s*x
  s v gety mulf				% push s*y
  s v getz mulf				% push s*z
  point					% make normalized vector
} /normalize

% addp
{ /v2 /v1
  v1 getx v2 getx addf
  v1 gety v2 gety addf
  v1 getz v2 getz addf point
} /addp

% subp
{ /v2 /v1
  v1 getx v2 getx subf
  v1 gety v2 gety subf
  v1 getz v2 getz subf point
} /subp

% mulp
{ /v2 /v1
  v1 getx v2 getx mulf
  v1 gety v2 gety mulf
  v1 getz v2 getz mulf point
} /mulp

% negp
{ /v
  v getx negf
  v gety negf
  v getz negf point
} /negp


% A simple pseudo-random number generator (from Graphics Gems II; p. 137)
% We have to do the computation in FP, since it overflows in integer arithmetic.
{ real 25173.0 mulf 13849.0 addf 65536.0 divf frac 65536.0 mulf floor } /random

% A random number in [0..1].
%
% seed  randomf  ==> f seed
{ 
  random apply /seed
  seed real 65535.0 divf seed
} /randomf

{
  randomf apply /seed /red
  seed randomf apply /seed /green
  seed randomf apply /seed /blue  
  red green blue point
  seed
} /randomcolor
  
  
%%%%%%%%%% util.ins %%%%%%%%%%

%%%%%%%%%% include colors.ins %%%%%%%%%%

0.0  0.0  0.0  point /black
1.0  1.0  1.0  point /white
1.0  0.0  0.0  point /red
0.0  1.0  0.0  point /green
0.0  0.0  1.0  point /blue
1.0  0.0  1.0  point /magenta
1.0  1.0  0.0  point /yellow
0.0  1.0  1.0  point /cyan

% ... <level>  grey  ==>  <color>
{ clampf /level level level level point } /grey

%%%%%%%%%% colors.ins %%%%%%%%%%


{
  randomcolor apply /seed /color
  seed randomf apply /seed
  0.5 lessf {0.5} {0.99} if /reflect
  { /v /u /face
    color
    1.0 reflect 1.0
    } cube -0.5 -0.5 -0.5 translate
  seed
} /siep0

1.0 3.0 divf /tiers
{
  siep0 apply /seed /colcube
  colcube
  colcube tiers tiers 1.0 scale
  colcube tiers 1.0 tiers scale union
  colcube 1.0 tiers tiers scale union
  difference
  seed
} /siep1

{/siep /n /size /seed
  seed size 3.0 divf n 1 subi siep siep apply
} /rec

{ /siep /n /size /seed
 seed size n siep rec apply /seed size negf 0.0 0.0 translate
 seed size n siep rec apply /seed union
 seed size n siep rec apply /seed  size 0.0 0.0 translate union
 seed
}
/bar

{/siep /n /size /seed
 seed size n siep rec apply /seed size negf 0.0 0.0 translate
  seed size n siep rec apply /seed size 0.0 0.0 translate union
  seed
} /middle

{/siep /n /size /seed
 seed size n siep bar apply /seed 0.0 size 0.0 translate
 seed size n siep middle apply /seed union
 seed size n siep bar apply /seed 0.0 size negf 0.0 translate union
 seed
} /front

{/siep /n /size /seed
 seed size n siep middle apply /seed 0.0 size 0.0 translate
 seed size n siep middle apply /seed 0.0 size negf 0.0 translate union
 seed
}
/center

{ /self /n /size /seed
 n 0 eqi
 {seed siep0 apply /x size 3.0 mulf uscale seed}
%%%% Front
 {n 1 eqi
   {seed siep1 apply /seed size 3.0 mulf uscale seed}
   {seed size n self front apply /seed 0.0 0.0 size negf translate
   seed size n self center apply /seed 90.0 rotatez union
   seed size n self front apply /seed 0.0 0.0 size translate union
   seed
   }
 if
 }
 if
} /siep

{ /file /box
  0.6 0.6 0.6 point
  [0.0 0.0 3.0 point 1.0 1.0 1.0 point pointlight
  0.0 0.0 1.0 point 0.8 0.8 0.8 point pointlight]
  box
%  -0.0 -0.0 3.1 translate
  -0.0 -0.0 3.0 translate
  1
  75.0
  300 300
  file
  render
} /doit

% render front view
{

} /aov

16 1.0 1 siep siep apply /seed aov apply
"siep-small1.ppm" doit apply

seed 1.0 2 siep siep apply /seed aov apply
"siep-small2.ppm" doit apply

seed 1.0 3 siep siep apply /seed aov apply
"siep-small3.ppm" doit apply

seed 1.0 4 siep siep apply /seed aov apply
"siep-small4.ppm" doit apply






