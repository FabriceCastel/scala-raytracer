package com.fcastel.raytracer.parser

import scala.io.Source

import com.fcastel.raytracer.node.SceneNode
import com.fcastel.raytracer.RenderParameters
import com.fcastel.raytracer.primitive._
import com.fcastel.raytracer.node._
import com.fcastel.raytracer.algebra._
import com.fcastel.raytracer.Light
import com.fcastel.raytracer.utils._

// use this class to parse whichever scripting language/file into
// a tree of SceneNodes and return its root && render params

class Parser(filename : String){
	def getScene() : SceneNode = {
		val scene = new SceneNode("Root Node")
		val trans = new Matrix4D()
		var joint = new JointNode("J", trans)
		//val gn = ObjParser.getGeometryNode("data/obj-models/dodecahedron.obj")
		val mesh = new Mesh(
			List(new Point3D(-1, -1, 0), new Point3D(1, -1, 0), new Point3D(1, 1, 0), new Point3D(-1, 1, 0)),
			List(List(0,1,2) ,List(2,3,0)),
			List(new Point2D(1,0), new Point2D(0,0), new Point2D(0,1), new Point2D(1,1)))
		val gn = new GeometryNode("Test plane", mesh)
		joint = joint.addChild(gn)
		scene.addChild(joint)
	}

	val siz = 120
	def r(): Double = Math.random()*siz - siz/2.0

	def getRenderParameters() : RenderParameters = {
		val params = new RenderParameters()
		params
	}

	def getLights(): List[Light] = {
		List(new Light(new Colour(0xffffffff), new Point3D(300, 40, 100)))
	}
}