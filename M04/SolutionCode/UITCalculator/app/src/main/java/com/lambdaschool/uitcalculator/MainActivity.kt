package com.lambdaschool.uitcalculator

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.lambdaschool.uitcalculator.logic.CalcEntity
import kotlinx.android.synthetic.main.activity_main.*
import java.math.BigDecimal

fun Double.toBigDecimal(): BigDecimal = BigDecimal.valueOf(this)
operator fun BigDecimal.plus(a: BigDecimal) = this.add(a)
operator fun BigDecimal.minus(a: BigDecimal) = this.subtract(a)
operator fun BigDecimal.times(a: BigDecimal) = this.multiply(a)
operator fun BigDecimal.div(a: BigDecimal) = this.divide(a)

enum class MathOp(mp: String) {
    NONE(""), DIVIDE("DIV"), MULTIPLY("MULT"), SUBTRACT("SUB"), ADD("ADD")
}

data class CalcState(
    var mem1: BigDecimal = 0.toBigDecimal(),
    var mem2: BigDecimal? = null,
    var op: MathOp = MathOp.NONE,
    var dot: Boolean = false,
    var dotLength: Int = 0 //especially useful for trailing zeroes to the right of the decimal
) {
    fun reset() {
        mem1 = 0.toBigDecimal()
        mem2 = null
        op = MathOp.NONE
        dot = false
        dotLength = 0
    }
}

class MainActivity : AppCompatActivity() {

    private var state = CalcState()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUI()

        val a = 17.0.toBigDecimal()
        val b = 3.2.toBigDecimal()
        val c = a * b
        Log.d("MSTR", "a=$a, b=$b")
        Log.d("MSTR", "c=$c")
    }

    private fun initUI() {
        updateDisplay()

        clearEntry.setOnClickListener {
            state.reset()
            updateDisplay()
        }

        // Perform repeated calculations (5 * 10 = 50, pressing equals again should return 500, etc.)

        clearDigit.setOnClickListener {
            state = CalcEntity.clearDigit(state); updateDisplay()
        }

        digit0.setOnClickListener {
            state = CalcEntity.pressDigit(0, state); updateDisplay()
        }
        digit1.setOnClickListener {
            state = CalcEntity.pressDigit(1, state); updateDisplay()
        }
        digit2.setOnClickListener {
            state = CalcEntity.pressDigit(2, state); updateDisplay()
        }
        digit3.setOnClickListener {
            state = CalcEntity.pressDigit(3, state); updateDisplay()
        }
        digit4.setOnClickListener {
            state = CalcEntity.pressDigit(4, state); updateDisplay()
        }
        digit5.setOnClickListener {
            state = CalcEntity.pressDigit(5, state); updateDisplay()
        }
        digit6.setOnClickListener {
            state = CalcEntity.pressDigit(6, state); updateDisplay()
        }
        digit7.setOnClickListener {
            state = CalcEntity.pressDigit(7, state); updateDisplay()
        }
        digit8.setOnClickListener {
            state = CalcEntity.pressDigit(8, state); updateDisplay()
        }
        digit9.setOnClickListener {
            state = CalcEntity.pressDigit(9, state); updateDisplay()
        }

        // Decimal Point
        btnDOT.setOnClickListener {
            state = CalcEntity.pressDot(state); updateDisplay()
        }
        // Equals
        btnEQUAL.setOnClickListener {
            //set state.dot=false
            state = CalcEntity.pressEquals(state); updateDisplay()
        }

        // Operations
        btnDIV.setOnClickListener {
            state = CalcEntity.pressOp(MathOp.DIVIDE, state); updateDisplay()
        }
        btnMULT.setOnClickListener {
            state = CalcEntity.pressOp(MathOp.MULTIPLY, state); updateDisplay()
        }
        btnSUB.setOnClickListener {
            state = CalcEntity.pressOp(MathOp.SUBTRACT, state); updateDisplay()
        }
        btnADD.setOnClickListener {
            state = CalcEntity.pressOp(MathOp.ADD, state); updateDisplay()
        }
    }

    private fun updateDisplay() {
        val mem1 = state.mem1
        val mem2 = state.mem2
        val op = state.op
        val ansLength = state.dotLength

        mem1Text.text = "$mem1"
        mem2Text.text = "$mem2"
        opText.text = "$op"
        ansLengthText.text = "$ansLength"

        if (op == MathOp.NONE || (op != MathOp.NONE && mem2 == null)) {
            calcDisplay.text = prepFinalAnswer(mem1, ansLength)
        } else if (mem2 != null) {
            calcDisplay.text = prepFinalAnswer(mem2, ansLength)
        }
    }

    private fun prepFinalAnswer(mem: BigDecimal, ansLength: Int): String {
        return if (ansLength > mem.toString().length) {
            Log.d("MSTR", "aaa")
            mem.toString().padEnd(ansLength, '0')
        } else {
            Log.d("MSTR", "bbb")
            mem.toString()
        }
    }
}
