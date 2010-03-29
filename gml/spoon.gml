% Color definitions

0.0  0.0  0.0  point /black
1.0  1.0  1.0  point /white
1.0  0.0  0.0  point /red
0.0  1.0  0.0  point /green
0.0  0.0  1.0  point /blue
1.0  0.0  1.0  point /magenta
1.0  1.0  0.0  point /yellow
0.0  1.0  1.0  point /cyan
0.5  0.05  0.05  point /brown
0.2  0.2  0.2  point /darkgrey
0.5  0.5  0.5  point /grey

% Simple surface definitions

{/v /u /face
  white
  0.2 1.0 4.0  
} /white_surface

% end of top

white_surface sphere
20.0 10.0 10.0 scale
/topbase

white_surface cube
10.0 1.0 10.0 scale
0.0 9.0 -5.0 translate
/topcut

topbase
topcut
intersect
0.0 -10.0 0.0 translate
180.0 rotatex
/outertop

outertop
-0.1 0.1 0.0 translate
/innertop

outertop
innertop
difference
/top1

% middle of top

top1
0.5 1.0 1.0 scale
-1.0 0.0 0.0 translate
180.0 rotatey
/top2

% top

top1 top2 
union
/top

% arm

white_surface cylinder
-90.0 rotatex
15.0 1.0 1.5 scale
0.0 -0.3 0.0 translate
/midarmbase

white_surface cube
30.0 1.0 5.0 scale
-5.0 0.0 -2.5 translate
/midarmcut

midarmbase midarmcut intersect
-15.0 0.0 0.0 translate
/midarmouter

midarmouter
1.0 1.0 1.2 scale
-0.1 -0.1 0.1 translate
/midarminner

midarmouter
midarminner
difference
/midarm

midarm
-2.2 1.3 0.0 translate
/arm

% spoon altogether

top
arm
union
70.0 rotatez
-60.0 rotatey
0.1 uscale
0.0 3.0 0.0 translate
/spoon

% table

{/v /u /face
%  brown
grey
  1.0 0.0 1.0  
} plane 
/table


% the whole scene

{ /angle
  spoon
    angle rotatey
  table
    union
  -15.0 rotatex
  0.0 -2.0 2.5 translate
} /scene

% lights

1.0 -1.0 1.0 point
white
light
/sun

-2.0 2.0 -1.0 point
white
pointlight
/lt

{ /angle /fn
  0.5 0.5 0.5 point                 % ambient light
  [ lt
  ]                                 % lights
  angle scene apply                 % scene to render
  3                                 % tracing depth
  90.0                              % field of view
  320 240                           % image wid and height
  fn                                % output file
  render
} /rend

"spoon01.ppm" 0.0 rend apply
"spoon02.ppm" 20.0 rend apply
"spoon03.ppm" 40.0 rend apply
"spoon04.ppm" 60.0 rend apply
"spoon05.ppm" 80.0 rend apply
"spoon06.ppm" 100.0 rend apply
"spoon07.ppm" 120.0 rend apply
"spoon08.ppm" 140.0 rend apply
"spoon09.ppm" 160.0 rend apply
"spoon10.ppm" 180.0 rend apply
"spoon11.ppm" 200.0 rend apply
"spoon12.ppm" 220.0 rend apply
"spoon13.ppm" 240.0 rend apply
"spoon14.ppm" 260.0 rend apply
"spoon15.ppm" 280.0 rend apply
"spoon16.ppm" 300.0 rend apply
"spoon17.ppm" 320.0 rend apply
"spoon18.ppm" 340.0 rend apply
