package thirdwayv.calculator.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import thirdwayv.calculator.databinding.ActivityMainBinding
import thirdwayv.calculator.pojo.Operation
import thirdwayv.calculator.utils.Calculator

class MainActivity : AppCompatActivity() ,IMain{
    private lateinit var mainBinding: ActivityMainBinding
    private val calculator: Calculator = Calculator()
    private var firstOperand: Double = 0.0
    private var secondOperand: Double = 0.0
    private  var operatorChar: Char = ' '
    private var operationList = arrayListOf<Operation>()
    private var undoList = arrayListOf<Operation>()
    private lateinit var historyAdapter: HistoryAdapter
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        doCalc(false)
        initRecyclerView()
    }

    private fun doCalc(isRemove:Boolean){
        mainBinding.includedOperandsLayout.addOP.setOnClickListener{
            operatorChar = '+'
        }
        mainBinding.includedOperandsLayout.minusOP.setOnClickListener{
            operatorChar = '-'
        }
        mainBinding.includedOperandsLayout.devideOP.setOnClickListener{
            operatorChar = '/'
        }
        mainBinding.includedOperandsLayout.multiplyOP.setOnClickListener{
            operatorChar = '*'
        }

        mainBinding.includedOperandsLayout.equalOP.setOnClickListener{
            if (mainBinding.inputET.text.trim().isNotEmpty() && operatorChar.toString().trim().isNotEmpty()){
                secondOperand = mainBinding.inputET.text.toString().toDouble()
                getResult(isRemove)
            }else if (operatorChar.toString().trim().isEmpty()){
                Toast.makeText(this, "select operator first", Toast.LENGTH_SHORT).show()
            } else{
                Toast.makeText(this, "write num", Toast.LENGTH_SHORT).show()
            }
        }
        mainBinding.includedOperandsLayout.undoOP.setOnClickListener{
            if (operationList.size>0){
                operatorChar = Calculator.reverseOperator(operationList.get(0).operation)
                secondOperand = operationList.get(0).lastNum
                getResult(true)
                equalClicked()
                undoList.add(0, operationList.get(0))
                operationList.removeAt(0)
                historyAdapter.notifyDataSetChanged()
            }
        }

        mainBinding.includedOperandsLayout.redoOP.setOnClickListener{
            Log.d(TAG, "doCalc: redoOP " + undoList.size)
            if (undoList.size>0){
                operatorChar = undoList.get(0).operation
                secondOperand = undoList.get(0).lastNum
                getResult(true)
                equalClicked()
                operationList.add(0, undoList.get(0))
                undoList.removeAt(0)
                historyAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun getResult(isRemove: Boolean){
        if (operatorChar.toString().equals("+")){
            firstOperand = Calculator.add(firstOperand, secondOperand)
            if(!isRemove) {operationList.add(0, Operation('+', secondOperand))}
        }else if (operatorChar.toString().equals("-")){
            firstOperand = Calculator.sub(firstOperand, secondOperand)
            if(!isRemove) {operationList.add(0, Operation('-', secondOperand))}
        }else if (operatorChar.toString().equals("*")){
            firstOperand = Calculator.mul(firstOperand, secondOperand)
            if(!isRemove) {operationList.add(0, Operation('*', secondOperand))}
        }else if (operatorChar.toString().equals("/")){
            firstOperand = Calculator.divide(firstOperand, secondOperand)
            if(!isRemove) {operationList.add(0, Operation('/', secondOperand))}
        }
        equalClicked()
    }

    private fun equalClicked(){
        operatorChar =' '
        mainBinding.resultTV.visibility = View.VISIBLE
        mainBinding.resultTV.text = "Result= ${firstOperand.toString()}"
        mainBinding.inputET.text.clear()
        historyAdapter.notifyDataSetChanged()
    }

    private fun initRecyclerView(){
        historyAdapter = HistoryAdapter(operationList, this)
        mainBinding.historyRecyclerView.setHasFixedSize(true)
        mainBinding.historyRecyclerView.layoutManager = GridLayoutManager(this, 3)
        mainBinding.historyRecyclerView.adapter = historyAdapter
    }

    override fun removeTask(operation: Operation) {
        operatorChar = Calculator.reverseOperator(operation.operation)
        secondOperand = operation.lastNum
        getResult(true)
        equalClicked()
    }
}