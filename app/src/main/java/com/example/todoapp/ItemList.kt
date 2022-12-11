package com.example.todoapp

class ItemList {
    var icons: Int ?= 0
    var category:String ? = null
    var title:String ? = null
    var task : String ? = null
    var date : String ? = null
    var status : String ?= null
    var detail : String ?= null

    constructor(
        icons: Int?,
        category: String?,
        title: String?,
        task: String?,
        date: String?,
        status: String?,
        detail: String?
    ) {
        this.icons = icons
        this.category = category
        this.title = title
        this.task = task
        this.date = date
        this.status = status
        this.detail = detail
    }
}