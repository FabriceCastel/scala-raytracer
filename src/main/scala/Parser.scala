package raytracer.Parser

import raytracer.node.SceneNode._
import raytracer.RenderParameters._
import raytracer.primitive.Sphere._
import raytracer.node.GeometryNode._

// use this class to parse whichever scripting language/file into
// a tree of SceneNodes and return its root && render params

class Parser(filename : String){
	def getScene() : SceneNode = {
		val scene = new SceneNode("Root Node")
		val sphere = new Sphere(20.0)
		val sphereGN = new GeometryNode("Sphere Node", sphere)
		scene.addChild(sphereGN)
	}

	def getRenderParameters() : RenderParameters = {
		val params = new RenderParameters()
		params
	}
}