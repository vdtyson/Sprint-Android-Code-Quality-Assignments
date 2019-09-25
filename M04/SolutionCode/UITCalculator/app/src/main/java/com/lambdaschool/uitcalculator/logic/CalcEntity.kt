package com.lambdaschool.uitcalculator.logic

import com.lambdaschool.uitcalculator.CalcState
import com.lambdaschool.uitcalculator.MathOp

/**
 * Calculator Business Logic
 */
object CalcEntity {

    fun clearEntryCalc() = 0.0

    fun pressDigit(digitPressed: Int, state: CalcState): CalcState {
        if (state.op == MathOp.NONE) {
            if (state.dot) {
                state.mem1 = (simplifyDigits(true, state.mem1) + digitPressed.toString()).toDouble()
            } else {
                state.mem1 = (state.mem1 * 10.0) + digitPressed.toDouble()
            }
        } else {
            if (state.dot) {
                state.mem2 = (state.mem2.toString() + digitPressed.toString()).toDouble()
            } else {
                state.mem2 = ((state.mem2 ?: 0.0) * 10.0) + digitPressed.toDouble()
            }
        }
        return state
    }

    fun clearDigit(state: CalcState): CalcState {
        if (state.op == MathOp.NONE) {
            state.mem1 = remove1Digit(simplifyDigits(false, state.mem1))
        } else {
            state.mem2 = remove1Digit(simplifyDigits(false, state.mem2))
        }
        return state
    }

    private fun simplifyDigits(isDot: Boolean, num: Double?): String {
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

    private fun remove1Digit(numString: String): Double {
        if (numString.length <= 1) {
            return 0.0
        }
        return (numString.substring(0, numString.length - 1)).toDouble()
    }

    fun pressDot(state: CalcState): CalcState {
        state.dot = true
        return state
    }

    fun pressOp(newop: MathOp, state: CalcState): CalcState {
        if (state.mem2 != null) {
            state.op = newop
            state.dot = false
        }
        return state
    }

    fun pressEquals(state: CalcState): CalcState {
        if (state.op != MathOp.NONE) {
            val mem1 = state.mem1
            val mem2 = state.mem2 ?: 0.0
            var result: Double = 0.0
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
        }
        return state
    }
}