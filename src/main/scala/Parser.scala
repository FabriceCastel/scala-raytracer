package raytracer.Parser

import raytracer.node.SceneNode._
import raytracer.RenderParameters._

// use this class to parse whichever scripting language/file into
// a tree of SceneNodes and return its root && render params

class Parser(filename : String){
	def getScene() : SceneNode = {
		val scene = new SceneNode("super magical node")
		val gnode = new SceneNode("child magical node")
		val gnode2 = new SceneNode("other child magical node")
		scene.addChild(gnode).addChild(gnode2.addChild(gnode))
	}

	def getRenderParameters() : RenderParameters = {
		val params = new RenderParameters()
		params
	}
}