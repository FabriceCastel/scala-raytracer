package com.fcastel.raytracer.utils

import scala.io.Source

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
		var vertices = List[Point3D]()
		var faces = List[List[Int]]()
		for(line <- Source.fromFile("data/obj-models/teapot.obj").getLines()){
			val tok = line.split(" ")
			if(!(tok.length < 4)){
				if(tok(0).equals("v")){
					// println("ADDVEC: " + line)
					
					vertices = vertices ::: List(new Point3D(tok(1).toDouble,tok(2).toDouble,tok(3).toDouble))
				} else if(tok(0).equals("f")){
					// println("ADDFACE: " + line)
					val tok = line.split(" ")
					// println("         " + vertices(tok(2).toInt - 1))
					// println("         " + vertices(tok(1).toInt - 1))
					// println("         " + vertices(tok(3).toInt - 1))
					faces = List(tok(1).toInt - 1,tok(2).toInt - 1,tok(3).toInt - 1) :: faces
				}
			}
		}
		val m = new Mesh(vertices, faces)
		val gn = new GeometryNode("Teddybear", m)
		joint = joint.addChild(gn)
		// for(x <- 0 to 10){
		// 	val tri = new Triangle(List(
		// 		new Point3D(r(), r(), r()),
		// 		new Point3D(r(), r(), r()),
		// 		new Point3D(r(), r(), r())))
		// 	val GN = new GeometryNode("triangle #" + x, tri)
		// 	joint = joint.addChild(GN)
		// }
		scene.addChild(joint)
	}

	val siz = 120
	def r(): Double = Math.random()*siz - siz/2.0

	def getRenderParameters() : RenderParameters = {
		val params = new RenderParameters()
		params
	}

	def getLights(): List[Light] = {
		List(new Light(new Colour(0xffffffff), new Point3D(3000, 4000, 100)))
	}
}