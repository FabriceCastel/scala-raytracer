package com.fcastel.raytracer.utils

import com.fcastel.raytracer.node.SceneNode
import com.fcastel.raytracer.RenderParameters
import com.fcastel.raytracer.primitive._
import com.fcastel.raytracer.node._
import com.fcastel.raytracer.algebra._
import com.fcastel.raytracer.Light

// use this class to parse whichever scripting language/file into
// a tree of SceneNodes and return its root && render params

class Parser(filename : String){
	def getScene() : SceneNode = {
		val scene = new SceneNode("Root Node")
		val trans = new Matrix4D()
		var joint = new JointNode("J", trans)
		for(x <- 0 to 10){
			val tri = new Triangle(List(
				new Point3D(r(), r(), r()),
				new Point3D(r(), r(), r()),
				new Point3D(r(), r(), r())))
			val GN = new GeometryNode("triangle #" + x, tri)
			joint = joint.addChild(GN)
		}
		scene.addChild(joint)
	}

	val siz = 120
	def r(): Double = Math.random()*siz - siz/2.0

	def getRenderParameters() : RenderParameters = {
		val params = new RenderParameters()
		params
	}

	def getLights(): List[Light] = {
		List(new Light(new Colour(0xffffffff), new Point3D(300, -400, -500)),
			new Light(new Colour(0xffffbb99), new Point3D(-200, 240, 400)))
	}
}