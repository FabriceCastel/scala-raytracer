package raytracer.SceneNode

import java.awt.{Color}
import raytracer.RenderParameters._
import raytracer.ShadeableIntersection._

class SceneNode(val name: String, children: List[SceneNode]){
	def this(name: String){
		this(name, List())
	}

	def this(){
		this("UNNAMED NODE")
	}

	def intersect(params: RenderParameters): ShadeableIntersection = {
		val col = new Color(0xfff04ea9)
		val sinter = new ShadeableIntersection(0, true, () => {col})
		sinter
	}

	def addChild(node: SceneNode): SceneNode = {
		val updatedChildren = children :+ node
		val updatedNode = new SceneNode(name, children)
		updatedNode
	}
}