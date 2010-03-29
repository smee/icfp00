

0.0  0.0  0.0  point /black
1.0  1.0  1.0  point /white
1.0  0.0  0.0  point /red
0.0  1.0  0.0  point /green
0.0  0.0  1.0  point /blue
1.0  0.0  1.0  point /magenta
1.0  1.0  0.0  point /yellow
0.0  1.0  1.0  point /cyan
0.6  0.2  0.2  point /wine
0.5  0.05  0.05  point /brown
0.2  0.2  0.2  point /darkgrey
0.5  0.5  0.5  point /grey


{/v /u /face
  white
  0.3 1.0 1.0
}/mirrorsf

mirrorsf cone /outer

mirrorsf cone /inner

{/v /u /face
  blue
  0.3 1.0 1.0
} cone /water

outer
inner 0.0 0.1 0.0 translate
difference
water 0.8 uscale
  0.0 0.1 0.0 translate
union
1.3 1.2 1.3 scale
/cup

mirrorsf cylinder 
0.1 2.1 0.1 scale
/leg

mirrorsf cone
1.0 0.4 1.0 scale
0.0 -0.4 0.0 translate
180.0 rotatex
/base

base
leg 0.0 0.3 0.0 translate
cup 0.0 2.2 0.0 translate
union
 union
/glass

{/v /u /face
  brown
  1.0 0.0 1.0  
} plane 
  -90.0 rotatex
  0.0 0.0 10.0 translate
/backstage

{/v /u /face
  brown
  1.0 0.0 1.0  
} plane 
/table

{/v /u /face
  white
  0.2 1.0 2.0  
} cube
  4.0 7.0 0.1 scale
  30.0 rotatex
  30.0 rotatey
  1.0 0.0 2.0 translate
/mirror

  glass
  table  
  backstage
    union union 
  0.0 -4.0 3.0 translate
  -30.0 rotatex
  -10.0 rotatey
/scene

0.0 5.0 -1.0 point
2.0 0.0 4.0 point
white
20.0
5.0
spotlight
/spot 

1.0 -1.0 1.0 point
darkgrey
light
/sun

0.5 0.5 0.5 point                 % ambient light
[ spot sun
]                          % lights
scene                             % scene to render
3                                 % tracing depth
90.0                              % field of view
320 240                           % image wid and height
"glass.ppm"                     % output file
render
