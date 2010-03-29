% TODO camera position
%        CENTER 2 -2 2 
%        VIEWDIR -1 1 -1
%        UPDIR 1 1 1

				% a sphere
{ /v /u /face			  % bind arguments
  0.1 0.1 0.1 point		  % surface color
  0.1 0.99 10.0			  % kd ks n
} sphere /s

1.0 2.0 sqrt divf /sq2

s sq2 uscale
 -0.5 -0.5  0.5 translate 
s sq2 uscale
  0.5  0.5  0.5 translate
s sq2 uscale
 -0.5  0.5 -0.5 translate
s sq2 uscale
  0.5 -0.5 -0.5 translate
union
union
union
45.0 rotatey 
-45.0 rotatex 
%30.0 rotatez 
0.0 0.0 -10.0 translate
/scene

				% directional light
-3.0 -3.0 -3.0 point		  % direction
0.0  0. 1.0 point light /l1	  % directional light

3.0 -3.0 -3.0 point		  % direction
0.0  1.0 0.0 point light /l2	  % directional light

-3.0 3.0 -3.0 point		  % direction
1.0  0.0 0.0 point light /l3	  % directional light

				% render
0.4 0.4 0.4 point		  % ambient light
[ l1 l2 l3 ]				  % lights
scene				  % scene to render
2				  % tracing depth

90.0				  % field of view
300 300				  % image wid and height
"wadabasin"			  % output file
render


