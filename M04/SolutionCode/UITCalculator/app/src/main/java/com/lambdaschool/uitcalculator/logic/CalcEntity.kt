package com.lambdaschool.uitcalculator.logic

import android.util.Log
import com.lambdaschool.uitcalculator.CalcState
import com.lambdaschool.uitcalculator.MathOp
import java.math.BigDecimal

/**
 * Calculator Business Logic
 */
object CalcEntity {

    fun pressDigit(digitPressed: Int, state: CalcState): CalcState {
        if (state.op == MathOp.NONE) {
            if (state.dot) {
                var temp = simplifyDigits(true, state.mem1)
                if (state.dotLength > 2) {
                    Log.d("MSTR", "cccccccc")
                    temp = temp.padEnd(state.dotLength, '0')
                }
                temp += digitPressed.toString()
                state.dotLength = temp.length
                state.mem1 = temp.toBigDecimal()
            } else {
                state.dotLength = 0
                state.mem1 = (state.mem1 * 10.toBigDecimal()) + digitPressed.toBigDecimal()
            }
        } else {
            if (state.dot) {
                val temp = simplifyDigits(true, state.mem2) + digitPressed.toString()
                state.dotLength = temp.length
                state.mem2 = temp.toBigDecimal()
            } else {
                state.dotLength = 0
                state.mem2 = ((state.mem2
                    ?: 0.toBigDecimal()) * 10.toBigDecimal()) + digitPressed.toBigDecimal()
            }
        }
        return state
    }

    fun clearDigit(state: CalcState): CalcState {
        //TODO: ensure this works when deleting ZEROS
        if (state.op == MathOp.NONE) {
            state.mem1 = remove1Digit(simplifyDigits(false, state.mem1))
        } else {
            state.mem2 = remove1Digit(simplifyDigits(false, state.mem2))
        }
        return state
    }

    private fun simplifyDigits(isDot: Boolean, num: BigDecimal?): String {
        if (num == null) {
            return ""
        }
        if (isDot) {
            var temp = num.toString().removeSuffix(".0")
            if (!temp.contains(".")) {
                temp = "$temp."
            }
            return temp
        } else {
            return num.toString().removeSuffix(".0")
        }
    }

    private fun remove1Digit(numString: String): BigDecimal {
        if (numString.length <= 1) {
            return 0.toBigDecimal()
        }
        return (numString.substring(0, numString.length - 1)).toBigDecimal()
    }

    fun pressDot(state: CalcState): CalcState {
        state.dot = true
        return state
    }

    fun pressOp(newop: MathOp, state: CalcState): CalcState {
        if (state.mem2 == null) {
            state.op = newop
            state.dot = false
            state.dotLength = 0
        }
        return state
    }

    fun pressEquals(state: CalcState): CalcState {
        if (state.op != MathOp.NONE) {
            val mem1 = state.mem1
            val mem2 = state.mem2 ?: 0.toBigDecimal()
            var result = 0.toBigDecimal()
            when (state.op) {
                MathOp.DIVIDE -> {
                    try {
                        result = mem1 / mem2
                    } catch (e: ArithmeticException) {
                    }
                }
                MathOp.MULTIPLY -> result = mem1 * mem2
                MathOp.SUBTRACT -> result = mem1 - mem2
                MathOp.ADD -> result = mem1 + mem2
                MathOp.NONE -> {
                }
            }
            state.mem1 = result
            state.mem2 = null
            state.op = MathOp.NONE
            state.dotLength = 0
        }
        return state
    }
}