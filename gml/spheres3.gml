% spheres3.gml
%
% spheres.gml + massive (but useless) computation
%

{ /self /i
  i 3 lessi
  { 1 }
  { i 1 subi self self apply
    i 2 subi self self apply
    addi
  } if
} /fib

				% a sphere
{ /v /u /face			  % bind arguments
  u v addf /dummy
  10 fib fib apply /dummy
  0.8 0.8 0.2 point		  % surface color
  1.0 0.0 1.0			  % kd ks n
} sphere /s

				% a matte white plane
{ /v /u /face
  u v addf /dummy
  10 fib fib apply /dummy
  1.0 1.0 1.0 point
  1.0 0.0 1.0
} plane /p

				% scene consisting of two spheres
s -1.2 0.0 3.0 translate 	  % sphere at (-1, 0, 3)
s  1.2 1.0 3.0 translate	  % sphere at (1, 1, 3)
p  0.0 -3.0 0.0 translate	  % plane at Y = -3
union union /scene		  % compose

				% directional light
1.0 -1.0 0.0 point		  % direction
1.0  1.0 1.0 point light /l	  % directional light

				% render
0.4 0.4 0.4 point		  % ambient light
[ l ]				  % lights
scene				  % scene to render
3				  % tracing depth
90.0				  % field of view
320 240				  % image wid and height
"spheres3.ppm"			  % output file
render

