import java.awt.Dimension
import java.io.File
import javax.swing.*
import javax.imageio.ImageIO
import kotlin.system.exitProcess
import kotlin.math.roundToInt

fun newX(width: Int, lastX: Int): Int {
    var result = (Math.random() * width - width / 2)
        .roundToInt()
        .coerceAtLeast(40)
        .coerceAtMost(width / 2 - 40)

    while (result == lastX) {
        result = (Math.random() * width - width / 2)
            .roundToInt()
            .coerceAtLeast(40)
            .coerceAtMost(width / 2 - 40)
    }
    return result
}

fun newY(height: Int, lastY: Int): Int {
    var result = (Math.random() * height - height / 2)
        .roundToInt()
        .coerceAtLeast(40)
        .coerceAtMost(height / 2 - 40)
    while (result == lastY) {
        result = (Math.random() * height - height / 2)
            .roundToInt()
            .coerceAtLeast(40)
            .coerceAtMost(height / 2 - 40)
    }
    return result
}

fun rootDir(file: String = ""): String {
    return System.getProperty("user.dir") + "/$file"
}

fun currDir(file: String = ""): String {
    return rootDir() + "/src/main/kotlin/$file"
}

fun main() {
    // setting top bar thingy in mac os to the game name
    System.setProperty("apple.awt.application.name", "Mole Mash")

    val height = 640
    val width = 640
    var score = 0

    var lastX = -1 // increase "randomization" by preventing same coordinate twice in a row
    var lastY = -1

    val frame = JFrame("Score: $score") // needs to be here so score is defined

    val moleIMG = ImageIO.read(File(currDir("mole.png")))
        .getScaledInstance(width / 16, height / 16, 0)
    val mole = JButton(ImageIcon(moleIMG))
    val exitBtn = JButton("Exit")

    mole.setBounds(mole.x, mole.y, height / 16, width / 16)
    mole.size = Dimension(height / 16, width / 16)

    mole.addActionListener {
        println("Clicked mole")

        val newX = newX(width, lastX)
        val newY = newY(height, lastY)

        lastX = newX
        lastY = newY

        mole.setBounds(mole.x, mole.y, height / 16, width / 16)

        mole.setLocation(newX,newY)
        score++
        frame.title = "Score: $score"

        println("Set mole location to $newX, $newY") // debug
    }

    exitBtn.addActionListener { exitProcess(0) }

    frame.add(exitBtn)
    frame.add(mole)

    frame.setSize(height,width)
    frame.isVisible = true
    frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE;
}