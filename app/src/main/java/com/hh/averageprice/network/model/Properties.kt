package com.hh.averageprice.network.model

data class Properties (val properties : List<Item>)

data class  Item (val price : Int,val address :String)