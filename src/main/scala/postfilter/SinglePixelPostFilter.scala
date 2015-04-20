package com.fcastel.raytracer.postfilter

import java.awt.image.BufferedImage

import com.fcastel.raytracer.utils.Colour

abstract class SinglePixelPostFilter() extends PostFilter(){
	def apply(image: BufferedImage): Unit = {
		for(x <- 0 to image.getWidth()-1; y <- 0 to image.getHeight()-1){
			val baseColour = new Colour(image.getRGB(x, y))
			image.setRGB(x, y, applyToSinglePixel(baseColour).getRGB())
		}
	}

	def applyToSinglePixel(colour: Colour): Colour
}