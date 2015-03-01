package raytracer.Parser

import raytracer.node.SceneNode._
import raytracer.RenderParameters._
import raytracer.primitive.Mesh._
import raytracer.node.GeometryNode._
import raytracer.vecmath._

// use this class to parse whichever scripting language/file into
// a tree of SceneNodes and return its root && render params

class Parser(filename : String){
	def getScene() : SceneNode = {
		val scene = new SceneNode("Root Node")
		val z = 0
		val tri = new Mesh(List(new Point3D(0,0,z), new Point3D(0,40,z), new Point3D(40,0,z)), List(List(0,1,2)))
		val GN = new GeometryNode("Tri Node", tri)
		scene.addChild(GN)
	}

	def getRenderParameters() : RenderParameters = {
		val params = new RenderParameters()
		params
	}
}