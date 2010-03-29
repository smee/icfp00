% fractal.gml
%
% Fractal thingie with spheres.
%

{ /v /u /face
  0.3 0.3 0.4 point
  1.0 0.1 1.0
} plane /p

{ /col
  { /v /u /face
    col
    0.1 0.99 1.0
  } sphere 0.9 uscale
} /mksphere

[
  0.5 0.7 0.9 point
  0.5 0.9 0.5 point
  0.6 0.6 0.7 point
  1.0 0.7 0.5 point
  0.9 1.0 0.6 point
  1.0 0.5 0.3 point
  1.0 0.8 0.9 point
  1.0 1.0 0.6 point
  1.0 1.0 1.0 point
] /colors

{ 1 addi colors length modi } /incrmod

{
  /self /col /depth /base
  depth 0 eqi
  { colors col get base apply 0.9 uscale }
  { 
    col incrmod apply /col
    colors col get base apply
    col incrmod apply /col
    base depth 1 subi col self self apply 2.5 0.0 0.0 translate union
    col incrmod apply /col
    base depth 1 subi col self self apply
    2.5 0.0 0.0 translate 60.0 rotatez union
    col incrmod apply /col
    base depth 1 subi col self self apply
    2.5 0.0 0.0 translate 120.0 rotatez union
    col incrmod apply /col
    base depth 1 subi col self self apply
    2.5 0.0 0.0 translate 180.0 rotatez union
    col incrmod apply /col
    base depth 1 subi col self self apply
    2.5 0.0 0.0 translate 240.0 rotatez union
    col incrmod apply /col
    base depth 1 subi col self self apply
    2.5 0.0 0.0 translate 300.0 rotatez union

    col incrmod apply /col
    base depth 1 subi col self self apply
    3.0 0.0 0.0 translate 90.0 rotatey union
    col incrmod apply /col
    base depth 1 subi col self self apply
    3.0 0.0 0.0 translate -90.0 rotatey union

    1.0 3.0 divf uscale
  }
  if
} /rec

{ 
 /angle
  mksphere 3 0 rec rec apply 30.0 rotatex 40.0 rotatey
  angle rotatey
  0.8 uscale 0.0 0.3 0.5 translate

  p 0.0 -1.0 0.0 translate union
  0.0 -0.2 0.3 translate
} /createscene

{
 /filename
 /angle

				% directional light
0.8 -1.0 0.4 point		  % direction
0.6  0.6 0.5 point light /l1	  % directional light

0.0 1.5 -0.4 point  % origin
0.4 0.5 0.6 point pointlight /l2


0.5 0.5 0.5 point		  % ambient light
[ l1 l2 ]				  % lights
angle createscene apply	  % scene to render
20      				  % tracing depth
80.0	       			  % field of view
300 400			      	  % image wid and height
filename		  % output file
render
} /rend

260.0 "fractal+260" rend apply
261.0 "fractal+261" rend apply
262.0 "fractal+262" rend apply
263.0 "fractal+263" rend apply
264.0 "fractal+264" rend apply
265.0 "fractal+265" rend apply
266.0 "fractal+266" rend apply
267.0 "fractal+267" rend apply
268.0 "fractal+268" rend apply
269.0 "fractal+269" rend apply

