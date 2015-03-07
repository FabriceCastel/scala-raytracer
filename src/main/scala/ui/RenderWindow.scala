package com.fcastel.raytracer.ui

import swing._
import event._
import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import java.io.File

import com.fcastel.raytracer.RenderParameters

class RenderWindow(rp: RenderParameters, img: BufferedImage) {
	val renderPanel = new Panel {
		override def paint(g : Graphics2D){
			super.paint(g)
			g.drawImage(img, 0, 0, null)
		}
		preferredSize = new Dimension(rp.width, rp.height)
	}

	val frame = new MainFrame {
		title = "RenderWindow"
		contents = renderPanel
		centerOnScreen
	}

	frame.open

	def update(): Unit = {
		frame.repaint()
	}

	def alive(): Boolean = {
		frame.showing
	}
}