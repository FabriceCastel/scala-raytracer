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
		val trans = new Matrix4D(1, 0, 0, 0,
								0, 1, 0, 0,
								0, 0, 1, 0,
								0, 0, 0, 2)
		val joint = new JointNode("J", trans)
		val cube = new Cube(30)
		val GN = new GeometryNode("Cube Node", cube)
		scene.addChild(joint.addChild(GN))
	}

	def getRenderParameters() : RenderParameters = {
		val params = new RenderParameters()
		params
	}

	def getLights(): List[Light] = {
		List(new Light(new Colour(0xffffffff), new Point3D(1000, -400, -700)),
			new Light(new Colour(0xffffbb99), new Point3D(-1000, 500, 600)))
	}
}