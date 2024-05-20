package eryaz.software.panda.data.enums

enum class ReplenishStatusEnum(
    val status : Int, val replenishTypeName : String
) {
    HEPSI(0,"Hepsi"),
    DOLU(1,"Dolu"),
    IZLEMEDE(2,"İzlemede"),
    YUKLEME(3,"Yükleme"),
    ACILYUKLEME(4,"Acil Yükleme"),
    MAXADET0(5,"Max Adet Sıfır"),
    SIPARISONCELIKLIIKMAL(6,"Sipariş Öncelikli İkmal"),
    STOKONCELIKLIIKMAL(7,"Stok Öncelikli İkmal")
}