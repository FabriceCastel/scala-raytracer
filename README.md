###Overview

Once you've installed sbt via ```brew install sbt```, use the ```sbt run``` command from the project root directory to compile & run.

The Scala implementation will differ from the current C++ implementation as follows:

- Rather than using a SceneNode structure when computing scene intersections, the scene tree will be traversed & converted into a better-suited ~~k-d tree~~ acceleration structure by applying all geometric transformations to the tree leaves so as to make them organizable in ~~a k-d tree~~ an acceleration structure

- The Shader class won't be a hacked together piece of donkey poop

- Calling the intersect() method on the scene should return a colour value rather than a cumbersome Intersection object

- Centralize consts and various render parameters in a Settings (or similar) class

- MasterTempo was only of use in the CS488 final project, it doesn't need to be brought over into the Scala version

- The interaction between Material & Shader isn't all that satisfactory, working out a better flow on that front would be beneficial

###Tasks

- ~~Settle on a good linear algebra library to use (requires Matrix, Vector and Point elements, preferably with built-in methods for matrix inversion, dot/cross products, etc)~~ ---> javax.vecmath <---

- ~~Port SceneNode class (scene.cpp) from the C++ codebase - the intersect() method will need a complete re-write worthy of its own separate task once this is done~~

- Implement ~~a k-d tree~~ an acceleration structure to hold flattened GeometryNode objects (refer to the first bullet point under 'overview' and https://www.cs.utexas.edu/~whunt/papers/hunt-pers.pdf)

- Select scripting language to be used in scene description (C++ version uses Lua) and implement parsing mechanism to constrct a SceneNode object from a given script (see scene_lua.cpp)

- Set up UI to allow for side-by-side scripting/rendering workflow (ie. a simple text editor and a render button) - to be revisited and improved on further down the line

- Port light class

- Port polyroots.cpp or find a good library alternative to use