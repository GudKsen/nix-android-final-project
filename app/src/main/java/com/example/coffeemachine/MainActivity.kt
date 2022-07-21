package com.example.coffeemachine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    private lateinit var controller: Controller
    private lateinit var model: Model

    private var waterFill: EditText? = null
    private var milkFill: EditText? = null
    private var coffeeBeansFill: EditText? = null
    private var cupsFill: EditText? = null
    private var buttonFill: Button? = null
    private var buttonTakeMoney: Button? = null
    private var infoField: TextView? = null
    private var buttonEspresso: Button? = null
    private var buttonLatte: Button? = null
    private var buttonCappuccino: Button? = null
    private var buttonInfo: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        model = Model()
        controller = Controller(model)
        controller.attachView(MainActivity())

        waterFill = findViewById(R.id.water_fill)
        milkFill = findViewById(R.id.milk_fill)
        coffeeBeansFill = findViewById(R.id.coffee_beans_fill)
        cupsFill = findViewById(R.id.cups_fill)

        buttonFill = findViewById(R.id.button_fill)
        buttonTakeMoney = findViewById(R.id.button_takeMoney)

        infoField = findViewById(R.id.infoField)

        buttonEspresso = findViewById(R.id.button_espresso)
        buttonLatte = findViewById(R.id.button_latte)
        buttonCappuccino = findViewById(R.id.button_cappuccino)
        buttonInfo = findViewById(R.id.button_info)

        buttonFill?.setOnClickListener {
            if (waterFill?.text?.toString()?.trim()?.equals("")!!) {
                Toast.makeText(this, "Enter water", Toast.LENGTH_LONG).show()
            }

            else if (milkFill?.text?.toString()?.trim()?.equals("")!!) {
                Toast.makeText(this, "Enter milk", Toast.LENGTH_LONG).show()
            }

            else if (coffeeBeansFill?.text?.toString()?.trim()?.equals("")!!) {
                Toast.makeText(this, "Enter coffee beans", Toast.LENGTH_LONG).show()
            }

            else if (cupsFill?.text?.toString()?.trim()?.equals("")!!) {
                Toast.makeText(this, "Enter cups", Toast.LENGTH_LONG).show()
            }

            else {
                val waterStr = waterFill!!.text.toString().trim()
                val milkStr = milkFill!!.text.toString().trim()
                val coffeeBeansStr = coffeeBeansFill!!.text.toString().trim()
                val cupsStr = cupsFill!!.text.toString().trim()

                val res = Resources(
                    water = waterStr.toInt(),
                    milk = milkStr.toInt(),
                    coffeeBeans = coffeeBeansStr.toInt(),
                    cups = cupsStr.toInt()
                )
                infoField?.text = controller.fillResources(res)
            }
        }

        buttonTakeMoney!!.setOnClickListener {
            infoField?.text = controller.takeMoney()
        }

        buttonEspresso?.setOnClickListener {
            val obj = OptionForBuyingCoffee("1")
            infoField?.text = controller.buyCoffee(obj)
        }

        buttonLatte?.setOnClickListener {
            val obj = OptionForBuyingCoffee("2")
            infoField?.text = controller.buyCoffee(obj)
        }

        buttonCappuccino?.setOnClickListener {
            val obj = OptionForBuyingCoffee("3")
            infoField?.text = controller.buyCoffee(obj)
        }

        buttonInfo?.setOnClickListener {
            infoField?.text = controller.remaining()
        }
    }
}
