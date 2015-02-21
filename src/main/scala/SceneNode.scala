package raytracer.SceneNode

import java.awt.{Color}

import raytracer.RenderParameters._

class SceneNode(name: String){
	def intersect(params : RenderParameters) : Color = {
		// return an arbitraty colour for now, implement this later
		val col = new Color(0xff4f4f8f)
		return col
	}

	// return a new SceneNode consisting of this + added child
	def addChild(node : SceneNode) : SceneNode = {
		return this
	}
}