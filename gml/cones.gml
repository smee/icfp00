% Three cones in a row, with a slightly matte horozontal mirror under them.
% Two spotlights: from above the central cone (turned down currently),
%    and from the front, from under viewer (turned on).
% PROBLEM (may be with my 8-bit display)


{/u /v /face
  face 1 eqi
    {0.1 1.0 0.1 point}
    {1.0 0.1 0.1 point}
  if
  1.0 0.0 1.0
} cone /cP1

{/u /v /face
  face 1 eqi
    {0.1 1.0 0.1 point}
    {1.0 0.1 0.1 point}
  if
  1.0 0.0 2.0
} cone /cP2


{/u /v /face
  face 1 eqi
    {0.1 1.0 0.1 point}
    {1.0 0.1 0.1 point}
  if
  1.0 0.0 3.0
} cone /cP3


cP1 -3.0 0.0 0.0 translate
cP2
cP3  3.0 0.0 0.0 translate
union union 
/cones

{/u /v /face
  1.0 1.0 1.0 point
  0.2 1.0 1.0 
} plane /bottom

cones
2.0 uscale
  0.0 -3.0 15.0 translate
bottom 
  0.0 -5.0 0.0 translate
union
/scene

0.0  3.0 15.0 point    %from
0.0 -3.0 15.0 point    %at
1.0 1.0 1.0 point    %color
30.0      %cutoff angle
2.0       %exp
spotlight
/ul     %upper spotlight

0.0 0.0 -1.0 point   %from
0.0 -1.0 15.0 point  %at
1.0 1.0 1.0 point    %color
20.0   %angle
1.0   %exp
spotlight
/fl    %front light

0.5 0.5 0.5 point                 % ambient light
[ 
% ul  
  fl
]                          % lights
scene                             % scene to render
3                                 % tracing depth
90.0                              % field of view
320 240                           % image wid and height
"cones.ppm"                     % output file
render

