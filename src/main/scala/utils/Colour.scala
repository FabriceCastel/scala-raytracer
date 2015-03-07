package com.fcastel.raytracer.utils

import java.awt.{Color}

class Colour(hex: Int) extends Color(hex){
	def this(a: Int, r: Int, g: Int, b: Int) = {
		this(((a & 255) << 24) + ((r & 255) << 16) + ((g & 255) << 8) + (b &  255))
	}

	def this(r: Int, g: Int, b: Int) = {
		this(255, r, g, b)
	}

	// ignore alpha for the time being
	def +(c: Colour) = {
		val r = Math.min(255, this.getRed() + c.getRed())
		val g = Math.min(255, this.getGreen() + c.getGreen())
		val b = Math.min(255, this.getBlue() + c.getBlue())
		new Colour(255, r, g, b)
	}

	def *(s: Double) = {
		require(s >= 0)
		val r = Math.min(255, this.getRed() * s).toInt
		val g = Math.min(255, this.getGreen() * s).toInt
		val b = Math.min(255, this.getBlue() * s).toInt
		new Colour(255, r, g, b)
	}
}