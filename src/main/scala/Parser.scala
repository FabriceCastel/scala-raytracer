package raytracer.Parser

import raytracer.SceneNode._
import raytracer.RenderParameters._

// use this class to parse whichever scripting language into
// a tree of SceneNodes and return its root && render params

class Parser(filename : String){
	def getScene() : SceneNode = {
		val scene = new SceneNode("super magical node")
		scene
	}

	def getRenderParameters() : RenderParameters = {
		val params = new RenderParameters(600, 600)
		params
	}
}