package com.fcastel.raytracer.utils

import com.fcastel.raytracer.node.SceneNode
import com.fcastel.raytracer.RenderParameters
import com.fcastel.raytracer.primitive._
import com.fcastel.raytracer.node.GeometryNode
import com.fcastel.raytracer.algebra._
import com.fcastel.raytracer.Light

// use this class to parse whichever scripting language/file into
// a tree of SceneNodes and return its root && render params

class Parser(filename : String){
	def getScene() : SceneNode = {
		val scene = new SceneNode("Root Node")
		val z = 0
		val cube = new Cube(30)
		val GN = new GeometryNode("Cube Node", cube)
		scene.addChild(GN)
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