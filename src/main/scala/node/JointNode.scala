package raytracer.node.JointNode

import javax.vecmath.{Matrix4d}

import raytracer.node.SceneNode._
import raytracer.node.FlattenedGeometryNode._

class JointNode(name: String, trans: Matrix4d) extends SceneNode(name){
	override def flatten(): List[FlattenedGeometryNode] = {
		var fl = children.foldLeft(List[FlattenedGeometryNode]())
			{(flatList, curNode) => flatList ::: curNode.flatten()}
		fl.map({node => node.applyTransform(trans)})
	}
}