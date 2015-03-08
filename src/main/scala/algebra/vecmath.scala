package com.fcastel.raytracer.algebra

import javax.vecmath.{Vector3d}
import javax.vecmath.{Point3d}
import javax.vecmath.{Point2d}
import javax.vecmath.{Matrix4d}

// Note: the IDs in the constructor CANNOT be x, y, z because that would override
// the getter methods in the javax vecmath library
// TODO find a cleaner way to do this?

class Vector3D(a: Double, b: Double, c: Double) extends Vector3d(a, b, c){
	def -(other: Vector3D) = new Vector3D(x - other.x, y - other.y, z - other.z)
	def +(other: Vector3D) = new Vector3D(x + other.x, y + other.y, z + other.z)
	def +(other: Point3D) = new Point3D(x + other.x, y + other.y, z + other.z)
	def cross(other: Vector3D): Vector3D = {
		var P = new Vector3D(0,0,0)
		P.cross(this, other) // assigns val to P
		P // not sure this line is needed to return the newly assigned P
	}
	def *(s: Double): Vector3D = new Vector3D(x*s, y*s, z*s)
	def *(m4: Matrix4D): Vector3D = {
		new Vector3D(
			a*m4.m00 + b*m4.m10 + c*m4.m20,
			a*m4.m01 + b*m4.m11 + c*m4.m21,
			a*m4.m02 + b*m4.m12 + c*m4.m22)
	}
	def ==(v: Vector3D): Boolean = x == v.x && y == v.y && z == v.z
}

class Point3D(a: Double, b: Double, c: Double) extends Point3d(a, b, c){
	def -(other: Point3D) = new Vector3D(x - other.x, y - other.y, z - other.z)
	def +(other: Vector3D) = new Point3D(x + other.x, y + other.y, z + other.z)
	def -(other: Vector3D) = this + (other * -1)
	def *(m4: Matrix4D): Point3D = {
		new Point3D(
			a*m4.m00 + b*m4.m10 + c*m4.m20 + m4.m30,
			a*m4.m01 + b*m4.m11 + c*m4.m21 + m4.m31,
			a*m4.m02 + b*m4.m12 + c*m4.m22 + m4.m32)
	}
}

class Point2D(a: Double, b: Double) extends Point2d(a, b){
	def +(other: Point2D) = new Point2D(x + other.x, y + other.y)
	def *(s: Double) = new Point2D(x*s, y*s)
	def /(s: Double) = new Point2D(x/s, y/s)
}

class Matrix4D(
	m00: Double, m01: Double, m02: Double, m03: Double,
	m10: Double, m11: Double, m12: Double, m13: Double,
	m20: Double, m21: Double, m22: Double, m23: Double, 
	m30: Double, m31: Double, m32: Double, m33: Double) extends Matrix4d(
	m00, m01, m02, m03,
	m10, m11, m12, m13,
	m20, m21, m22, m23,
	m30, m31, m32, m33){

	def this(){
		this(1, 0, 0, 0,
			0, 1, 0, 0,
			0, 0, 1, 0,
			0, 0, 0, 1)
	}
}