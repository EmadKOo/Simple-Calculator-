package thirdwayv.calculator.utils

class Calculator{
    companion object {
        fun add(firstOp: Double, secondOp: Double):Double{
            return firstOp + secondOp
        }

        fun sub(firstOp: Double, secondOp: Double):Double{
            return firstOp - secondOp
        }

        fun mul(firstOp: Double, secondOp: Double):Double{
            return firstOp * secondOp
        }

        fun divide(firstOp: Double, secondOp: Double):Double{
            return firstOp / secondOp
        }

        fun reverseOperator(mChar: Char):Char{
            if (mChar.equals('+'))
                return '-'
            else if (mChar.equals('-'))
                return '+'
            else if (mChar.equals('/'))
                return '*'
            else if (mChar.equals('*'))
                return '/'
            else return ' '
        }
    }
}