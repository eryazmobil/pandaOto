package eryaz.software.panda.data.enums

enum class ActionType( val type:String) {
    CONTROL("Control"),
    PLACEMENT("Placement"),
    PICKING("Picking"),
    REPLENISH("Replenish"),
    REPLACEMENT("Replacement"),
    SHIPMENT("Shipment"),
    PACKAGE("Package"),
}

enum class ActivityType(val type :String){
    SHIPPING("Shipping"),
    REPLENISHMENT("Replenishment"),
    RECEIVING("Receiving")

}