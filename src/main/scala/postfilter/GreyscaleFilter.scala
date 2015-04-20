package com.fcastel.raytracer.postfilter

import java.awt.image.BufferedImage

import com.fcastel.raytracer.utils.Colour

class GreyscaleFilter() extends SinglePixelPostFilter(){
	def applyToSinglePixel(colour: Colour): Colour = {
		val grey = (colour.getRed() + colour.getGreen() + colour.getBlue()) / 3
		new Colour(grey, grey, grey)
	}
}