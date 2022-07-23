package com.example.coffeemachine.ui.views


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.coffeemachine.R
import com.example.coffeemachine.ui.adapters.Contract
import com.example.coffeemachine.core.entities.OptionForBuyingCoffee
import com.example.coffeemachine.core.entities.Resources
import com.example.coffeemachine.core.entities.Response
import com.example.coffeemachine.core.interactors.BuyCoffeeInteractor
import com.example.coffeemachine.core.interactors.FillResourcesInteractor
import com.example.coffeemachine.core.interactors.ShowInfoInteractor
import com.example.coffeemachine.core.interactors.TakeMoneyInteractor
import com.example.coffeemachine.data.repositories.FakeActionRepositoryImplementation
import com.example.coffeemachine.ui.adapters.Presenter

class MainActivity : AppCompatActivity(), Contract.View {

    private val presenter = Presenter(
        BuyCoffeeInteractor(FakeActionRepositoryImplementation()),
        TakeMoneyInteractor(FakeActionRepositoryImplementation()),
        FillResourcesInteractor(FakeActionRepositoryImplementation()),
        ShowInfoInteractor(FakeActionRepositoryImplementation())
    )

    private var waterFill: EditText? = null
    private var milkFill: EditText? = null
    private var coffeeBeansFill: EditText? = null
    private var cupsFill: EditText? = null
    private var buttonFill: Button? = null
    private var buttonTakeMoney: Button? = null
    private var buttonEspresso: Button? = null
    private var buttonLatte: Button? = null
    private var buttonCappuccino: Button? = null
    private var buttonInfo: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.attach(this)

        waterFill = findViewById(R.id.water_fill)
        milkFill = findViewById(R.id.milk_fill)
        coffeeBeansFill = findViewById(R.id.coffee_beans_fill)
        cupsFill = findViewById(R.id.cups_fill)

        buttonFill = findViewById(R.id.button_fill)
        buttonTakeMoney = findViewById(R.id.button_takeMoney)



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
                presenter.fillResources(res)
            }
        }

        buttonTakeMoney!!.setOnClickListener {
            presenter.takeMoney()
        }

        buttonEspresso?.setOnClickListener {
            val obj = OptionForBuyingCoffee("1")
            presenter.buyCoffee(obj)
        }

        buttonLatte?.setOnClickListener {
            val obj = OptionForBuyingCoffee("2")
            presenter.buyCoffee(obj)
        }

        buttonCappuccino?.setOnClickListener {
            val obj = OptionForBuyingCoffee("3")
            presenter.buyCoffee(obj)
        }

        buttonInfo?.setOnClickListener {
            presenter.remaining()
        }
    }

    override fun showData(response: Response) {
        val textView = findViewById<TextView>(R.id.infoField)
        textView.text = response.message
    }
}