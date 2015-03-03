package raytracer.shader.BlinnPhongShader

import raytracer.Colour._
import raytracer.shader.Shader._
import raytracer.vecmath._
import raytracer.Light._
import raytracer.AccelerationStructure._
import raytracer.Material._
import raytracer.BasicIntersection._
import raytracer.Ray._

class BlinnPhongShader() extends Shader(){
	def apply(ray: Ray, inter: BasicIntersection, mat: Material)(scene: AccelerationStructure, lights: List[Light]): Colour = {
		val ambient = new Colour(0xff252525)
		lights.foldLeft(ambient)((base, elem) => {
			base + shade(ray, inter, mat, scene, elem)
			})
	}

	private def shade(ray: Ray, inter: BasicIntersection, mat: Material, scene: AccelerationStructure, light: Light): Colour = {
		val eyeToPoint = inter.point - ray.p
		var pointToLight = light.pos - inter.point
		pointToLight.normalize()
		if((inter.normal dot eyeToPoint) > 0){
			new Colour(0xff000000)
		} else {
			val sdn = Math.max(pointToLight dot inter.normal, 0.0)
			val diffuse = mat.kd * sdn
			val cosAngInsidence = inter.normal dot pointToLight
			var halfAngle = (light.pos - inter.point) + (ray.p - inter.point)
			val halfAngleLen = halfAngle.length()
			halfAngle.normalize()
			halfAngle = halfAngle * (1.0/halfAngleLen)
			halfAngle.normalize()
			var blinnTerm = halfAngle dot inter.normal
			if(blinnTerm < 0 || cosAngInsidence == 0.0) blinnTerm = 0.0;
			else if(blinnTerm > 1) blinnTerm = 1.0;
			diffuse + mat.ks * Math.pow(blinnTerm, mat.shine)
		}
	}
}