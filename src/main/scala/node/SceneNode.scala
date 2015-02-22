package raytracer.node.SceneNode

import java.awt.{Color}
import raytracer.RenderParameters._
import raytracer.ShadeableIntersection._
import raytracer.node.FlattenedGeometryNode._
import raytracer.Ray._

class SceneNode(val name: String, val children: List[SceneNode]){
	def this(name: String){
		this(name, List())
	}

	def this(){
		this("UNNAMED NODE")
	}

	def intersect(params: RenderParameters, ray: Ray): ShadeableIntersection = {
		val col = new Color(0xfff04ea9)
		val sinter = new ShadeableIntersection(0, true, () => {col})
		sinter
	}

	def addChild(node: SceneNode): SceneNode = {
		val updatedChildren = children :+ node
		val updatedNode = new SceneNode(name, updatedChildren)
		updatedNode
	}

	def flatten(): List[FlattenedGeometryNode] = {
		children.foldLeft(List[FlattenedGeometryNode]())
			{(flatList, curNode) => flatList ::: curNode.flatten()}
	}

	def print(): Unit = {
		printNode("")
	}

	protected def printNode(sp: String): Unit = {
		println(sp + "-" + name)
		children.foreach(c => c.printNode(sp + "  "))
	}
}