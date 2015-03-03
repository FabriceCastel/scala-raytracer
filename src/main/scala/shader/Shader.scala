package raytracer.shader.Shader

import raytracer.Colour._
import raytracer.AccelerationStructure._
import raytracer.vecmath._
import raytracer.Light._
import raytracer.Material._
import raytracer.BasicIntersection._
import raytracer.Ray._

abstract class Shader(){
	def apply(ray: Ray, inter: BasicIntersection, mat: Material)(scene: AccelerationStructure, lights: List[Light]): Colour
}