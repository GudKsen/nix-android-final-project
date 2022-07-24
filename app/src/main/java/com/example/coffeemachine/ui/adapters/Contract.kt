package com.example.coffeemachine.ui.adapters

import com.example.coffeemachine.core.entities.Response

interface Contract  {
    interface View {
        fun showData(response: Response)
        fun showPriceEspresso(response: Response)
        fun showPriceLatte(response: Response)
        fun showPriceCappuccino(response: Response)
    }

    interface Presenter {
        fun attach(view: View)

        fun detach()
    }
}