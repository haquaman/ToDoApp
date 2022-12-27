package com.example.todoapp

class ItemList {
    var icons: Int ?= 0
    var category:String ? = null
    var title:String ? = null
    var task : String ? = null
    var date : String ? = null
    var status : String ?= null
    var detail : String ?= null
    var user : Int ?= 1
    var idT : Int ?=1

    constructor(
        icons: Int?,
        category: String?,
        title: String?,
        task: String?,
        date: String?,
        status: String?,
        detail: String?,
        user: Int?,
        idT : Int?
    ) {
        this.icons = icons
        this.category = category
        this.title = title
        this.task = task
        this.date = date
        this.status = status
        this.detail = detail
        this.user = user
        this.idT = idT
    }
}