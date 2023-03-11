package com.example.totdo.List_recyclerview

import android.icu.text.CaseMap
import android.widget.ImageView

class Model{
    var title :String = ""
    var decription:String =""
    //var imge:ImageView? = null

    constructor(title: String, decription: String) {
        this.title = title
        this.decription = decription
       // this.imge = imge
    }
}
