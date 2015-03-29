package com.fcastel.raytracer.parser

import scala.io.Source

import com.fcastel.raytracer.node.GeometryNode
import com.fcastel.raytracer.primitive.Mesh
import com.fcastel.raytracer.primitive.Triangle
import com.fcastel.raytracer.algebra._
import com.fcastel.raytracer.material.ImageMaterial

object ObjParser {
	def getGeometryNode(file: String): GeometryNode = {
		var vertices = List[Point3D]()
		var triangles = List[Triangle]()
		var norms = List[Vector3D]()
		var tvert = List[Point2D]()
		for(line <- Source.fromFile(file).getLines){
			val tok = line.split("\\s+") // pattern matches any whitespace
			tok(0) match {
				case "v" => vertices = vertices ::: List(new Point3D(tok(1).toDouble,tok(2).toDouble,tok(3).toDouble))
				case "f" => {
					val triplet = 	List(tok(1), tok(2), tok(3))
					val ftr = triplet.map(e => e.split("/+").map(e => e.toInt - 1))
					if(ftr(1).length == 1){ // only geom verts
						triangles = new Triangle(vertices(ftr(0)(0)), vertices(ftr(1)(0)), vertices(ftr(2)(0))) :: triangles
					} else if(ftr(1).length == 2){ // only geom + normal verts
						triangles = new Triangle(vertices(ftr(0)(0)), vertices(ftr(1)(0)), vertices(ftr(2)(0))) :: triangles
					} else { // geom + normal + uv verts
						triangles = new Triangle(
							List(vertices(ftr(0)(0)), vertices(ftr(1)(0)), vertices(ftr(2)(0))),
							List(tvert(ftr(0)(1)), tvert(ftr(1)(1)), tvert(ftr(2)(1))),
							List(norms(ftr(0)(2)), norms(ftr(1)(2)), norms(ftr(2)(2)))) :: triangles
					}
				}
				case "vt" => {
					tvert = tvert ::: List(new Point2D(tok(1).toDouble, tok(2).toDouble))
				}
				case "vn" => norms = norms ::: List(new Vector3D(tok(1).toDouble,tok(2).toDouble,tok(3).toDouble))
				case default => Unit
			}
		}
		val mesh = new Mesh(triangles)
		val material = new ImageMaterial("data/textures/sphere.jpg")
		new GeometryNode(file, mesh, material)
	}
}