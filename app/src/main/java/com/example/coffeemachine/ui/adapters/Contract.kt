package com.example.coffeemachine.ui.adapters

import com.example.coffeemachine.core.entities.Response

interface Contract  {
    interface View {
        fun showData(response: Response)
    }

    interface Presenter {
        fun attach(view: View)

        fun detach()
    }
}