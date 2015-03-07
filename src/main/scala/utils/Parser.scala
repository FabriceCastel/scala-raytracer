package com.fcastel.raytracer.utils

import com.fcastel.raytracer.node.SceneNode
import com.fcastel.raytracer.RenderParameters
import com.fcastel.raytracer.primitive.Mesh
import com.fcastel.raytracer.node.GeometryNode
import com.fcastel.raytracer.algebra._
import com.fcastel.raytracer.Light

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

	def getLights(): List[Light] = {
		List(new Light(new Colour(0xffffffff), new Point3D(120, 0, -20)))
	}
}