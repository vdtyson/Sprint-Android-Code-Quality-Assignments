package com.lambdaschool.uitcalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lambdaschool.uitcalculator.logic.CalcEntity
import kotlinx.android.synthetic.main.activity_main.*

enum class MathOp(mp: String) {
    NONE(""), DIVIDE("DIV"), MULTIPLY("MULT"), SUBTRACT("SUB"), ADD("ADD")
}

data class CalcState(
    var mem1: Double = 0.0,
    var mem2: Double? = null,
    var op: MathOp = MathOp.NONE,
    var dot: Boolean = false
)

class MainActivity : AppCompatActivity() {

    private var state = CalcState()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUI()
    }

    private fun initUI() {
        updateDisplay()

        clearEntry.setOnClickListener {
            state.mem1 = CalcEntity.clearEntryCalc()
            state.mem2 = null
            state.op = MathOp.NONE
            state.dot = false
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
        mem1Text.text = "${state.mem1}"
        mem2Text.text = "${state.mem2}"
        opText.text = "${state.op}"

        if (state.op == MathOp.NONE || state.mem2 == null) {
            calcDisplay.text = state.mem1.toString()
        } else {
            calcDisplay.text = state.mem2.toString()
        }
    }
}
