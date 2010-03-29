% Two arrays of blue and red speres stacked above each other
%   and mirror below them.
% A couple of lights is defined but not used.


{/u /v /face
  1.0 0.0 0.0 point
  1.0 0.0 1.0 
} sphere 
%2.0 uscale 
/rs

{/u /v /face
  0.0 0.0 1.0 point
  1.0 0.0 1.0
} sphere 
%2.0 uscale 
/bs

rs 0.0 0.0 0.0 translate
bs 1.0 0.0 0.0 translate
rs 1.0 0.0 1.0 translate
bs 0.0 0.0 1.0 translate
union union union
/block2

block2
block2 -2.0 0.0 0.0 translate
union
block2 -2.0 0.0 2.0 translate
union
block2 0.0 0.0 2.0 translate
union
2.0 0.0 0.0 translate
/block4

block4
block4 -4.0 0.0 0.0 translate
union
block4 -4.0 0.0 4.0 translate
union
block4 0.0 0.0 4.0 translate
union
4.0 0.0 0.0 translate
/block8


block8
block8 -8.0 0.0 0.0 translate
union
block8 -8.0 0.0 8.0 translate
union
block8 0.0 0.0 8.0 translate
union
8.0 0.0 0.0 translate
/block16

block16
block16 0.0 6.0 0.0 translate
union
/stack

                                % a mirror
{ /v /u /face
  1.0 1.0 1.0 point
  0.0 1.0 1.0
} plane /mirror


stack
  -30.0 rotatex
  -30.0 rotatey
  -2.0 -4.0 10.0 translate
mirror 
% -90.0 rotatex -45.0 rotatey
%  0.0 0.0 30.0 translate
   -10.0 rotatey
   0.0 -10.0 0.0 translate
union
/scene


                                % directional light
1.0 -1.0 0.0 point                % direction
1.0  1.0 1.0 point light /l       % directional light

6.0 -8.0 16.0 point           %position
0.0 1.0 1.0 point             %color
pointlight
/bl	%bottom light	

0.0 -8.0 0.0 point    % point from
6.0 -4.0 18.0 point   % point at 
0.0 1.0 1.0 point     %color
5.0                   %cutoff angle
1.0                   %exp
spotlight
/spl

                                % render
0.4 0.4 0.4 point                 % ambient light
[ l  
% spl bl
     ]                          % lights
scene                             % scene to render
3                                 % tracing depth
90.0                              % field of view
320 320                           % image wid and height
"bumps.ppm"                     % output file
render









