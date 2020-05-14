package edu.student.whackanandroid

import android.content.res.Resources
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.timerTask

class MainActivity : AppCompatActivity() {

    //declare variables
    var y:Float = 0.00F
    var x:Float = 0.00F
    var random = Random()
    var score:Int = 0
    var timer = Timer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //bind objects
        var btnControl = findViewById<Button>(R.id.btnControl)
        var GameBackGround = findViewById<ConstraintLayout>(R.id.GameCanvas)
        var btnImgButton = findViewById<ImageButton>(R.id.imgMole)
        //set objects offscreen
        btnImgButton.setTranslationX(-300F)
        btnImgButton.setTranslationY(-300F)
        //start invisible
        btnImgButton.visibility = View.INVISIBLE
        btnImgButton.bringToFront()

        //
        btnControl.setOnClickListener{
            if (btnControl.text == "Start"){
                Toast.makeText (this, "Tap Android To Score¡",Toast.LENGTH_LONG) .show ()
                btnControl.text = "Stop"
                score = 0
                txtScore.text = "Score: " + score.toString()
                btnImgButton.setTranslationX(-300F)
                btnImgButton.setTranslationY(-300F)
                timer = Timer()
                timer.schedule(timerTask {ChangeImage()},3000)
                ChangeImage()
                btnImgButton.visibility = View.VISIBLE
            }else{
                btnImgButton.visibility = View.INVISIBLE
                btnControl.text = "Start"
                btnImgButton.setTranslationX(-300F)
                btnImgButton.setTranslationY(-300F)
                timer.cancel()
            }

        }

        btnImgButton.setOnClickListener{
            score += 100
            if (score == 1000){
                timer.cancel()
                txtScore.text = "You Have Won!"
                score = 0
                btnControl.text = "Start"
                btnImgButton.setTranslationX(-300F)
                btnImgButton.setTranslationY(-300F)
            }else {
                txtScore.text = "Score: " + score.toString()
            }

        }

        GameBackGround.setOnClickListener{
            println("Click")
            score -= 100
            txtScore.text = "Score: " + score.toString()
            if (score == 0 || score == -100){
                btnImgButton.visibility = View.INVISIBLE
                Toast.makeText (this, "Game Over", Toast.LENGTH_LONG) .show ()
                score = 0
                txtScore.text = "Score: " + score.toString()
                btnImgButton.setTranslationX(-300F)
                btnImgButton.setTranslationY(-300F)
                timer.cancel()
                btnControl.text = "Start"

            }
        }
    }

    fun ChangeImage(){

        y = ((Math.random () * getScreenHeight()) + 50) .toFloat ()
        x = ((Math.random () * getScreenWidth()) + 50) .toFloat ()
        imgMole.setTranslationX(x)
        imgMole.setTranslationY(y)
        timer.schedule(timerTask {ChangeImage()},1000)
    }
    fun getScreenWidth(): Float {
        return Resources.getSystem().getDisplayMetrics().widthPixels / 1.4F

    }

    fun getScreenHeight(): Float {
        return Resources.getSystem().getDisplayMetrics().heightPixels / 1.4F
    }
}
