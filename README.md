###Overview

The Scala implementation will differ from the current C++ implementation as follows:

- Rather than using a SceneNode structure when computing scene intersections, the scene tree will be traversed & converted into a better-suited k-d tree by applying all geometric transformations to the tree leaves so as to make them organizable in a k-d tree


###Tasks

- Settle on a good linear algebra library to use (requires Matrix, Vector and Point elements, preferably with built-in methods for matrix inversion, dot/cross products, etc)

- Port SceneNode class from the C++ codebase

- Implement k-d tree structure to hold geometry nodes

- Select scripting language to be used in scene description (C++ version uses Lua) and implement parsing mechanism to constrct a SceneNode object from a given script (see scene_lua.cpp)

- Set up UI to allow for side-by-side scripting/rendering workflow (ie. a simple text editor and a render button)