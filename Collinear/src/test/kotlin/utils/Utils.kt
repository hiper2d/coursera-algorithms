package utils

import edu.princeton.cs.algs4.StdDraw
import java.nio.file.Files
import java.nio.file.Paths
import java.util.regex.Pattern

import Point
import BruteCollinearPoints
import FastCollinearPoints
import edu.princeton.cs.algs4.StdOut
import java.util.*

private val WHITESPACE_PATTERN = Pattern.compile("\\p{javaWhitespace}+")

class Utils {
    fun createScannerForInputFile(file: String): Scanner {
        val path = Paths.get(javaClass.classLoader.getResource(file)!!.toURI())
        val scanner = Scanner(Files.newBufferedReader(path))
        scanner.useDelimiter(WHITESPACE_PATTERN)
        return scanner
    }
}

fun <T: Comparable<T>> exch(a: Array<T>, i: Int, j: Int) {
    val temp = a[i]
    a[i] = a[j]
    a[j] = temp
}


fun <T: Comparable<T>> less(a: Array<T>, i: Int, j: Int) = a[i] < a[j]

fun <T: Comparable<T>> shuffle(a: Array<T>) {
    val rand = Random()
    for (i in 0..a.lastIndex) {
        val exchIndex = rand.nextInt(i + 1)
        if (i != exchIndex) {
            exch(a, i, exchIndex)
        }
    }
}

fun readPointsFromFileToList(filename: String): List<Point> {
    val scanner = Utils().createScannerForInputFile(filename)
    val n = scanner.nextInt()
    val points = mutableListOf<Point>()
    (1..n).forEach {
        val x = scanner.nextInt()
        val y = scanner.nextInt()
        points.add(Point(x, y))
    }
    return points.toList()
}

fun drawBruteSegmentsFromFile(filename: String) {
    val points = drawPonts(filename)
    val collinear = BruteCollinearPoints(points.toTypedArray())
    for (segment in collinear.segments()) {
        StdOut.println(segment)
        segment.draw()
    }
    StdDraw.show()
}

fun drawFastSegmentsFromFile(filename: String) {
    val points = drawPonts(filename)
    val collinear = FastCollinearPoints(points.toTypedArray())
    for (segment in collinear.segments()) {
        StdOut.println(segment)
        segment.draw()
    }
    StdDraw.show()
}

private fun drawPonts(filename: String): List<Point> {
    StdDraw.enableDoubleBuffering()
    StdDraw.setXscale(0.0, 32768.0)
    StdDraw.setYscale(0.0, 32768.0)
    val points = readPointsFromFileToList(filename)
    for (p in points) {
        p.draw()
    }
    StdDraw.show()
    return points
}