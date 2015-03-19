package com.fcastel.raytracer.parser

import scala.io.Source

import com.fcastel.raytracer.node.GeometryNode
import com.fcastel.raytracer.primitive.Mesh
import com.fcastel.raytracer.algebra._

object ObjParser {
	def getGeometryNode(file: String): GeometryNode = {
		var vertices = List[Point3D]()
		var faces = List[List[Int]]()
		for(line <- Source.fromFile(file).getLines){
			val tok = line.split(" ")
			if(!(tok.length < 4)){
				tok(0) match {
					case "v" => vertices = vertices ::: List(new Point3D(tok(1).toDouble,tok(2).toDouble,tok(3).toDouble))
					case "f" => faces = List(tok(1).toInt - 1,tok(2).toInt - 1,tok(3).toInt - 1) :: faces
				}
			}
		}
		val mesh = new Mesh(vertices, faces)
		new GeometryNode(file, mesh)
	}
}