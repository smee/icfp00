"red"   /red
"green" /green
"blue"  /blue

1.0 0.0 0.0 point /red
0.0 1.0 0.0 point /green
0.0 0.0 1.0 point /blue

% function to produce checkered color on a 2-d plane
{ /x /y
  x floor y floor addi 2 modi
  0 eqi
    {red}
    {blue}
  if
} /chkcolor

{ /x /y
  x x mulf y y mulf addf 0.25 lessf   %green circle around origin
   {green}
   {x y chkcolor apply}               %red/blue checkered otherwise
  if
} /coloring


{ /u /v /face
    u v coloring apply
    1.0 0.0 1.0		                %coefficients
} plane /p

p
-45.0 rotatex
0.0 0.0 1.0 translate
2.0 uscale
/scene

1.0 1.0 1.0 point      %ambient light
[]               %no more light sources
scene
1		%tracing depth
90.0		%field of view
200 200         %size in pixels
"chkplane.ppm"
render


% 0.5 0.5 coloring apply
% 0.5 1.5 coloring apply
% 1.5 0.5 coloring apply
% 1.5 1.5 coloring apply
% "    "
% -0.5 -0.5 coloring apply
% 0.5 -0.5 coloring apply
% -0.5 0.5 coloring apply
% "    "
% 0.1 0.1 coloring apply
% -0.2 0.3 coloring apply
% 0.4 -0.1 coloring apply
% -0.2 -0.2 coloring apply


