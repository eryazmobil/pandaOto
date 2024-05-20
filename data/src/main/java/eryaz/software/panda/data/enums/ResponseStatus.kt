package eryaz.software.panda.data.enums

enum class ResponseStatus(private val success: Boolean?) {
    OK(true),
    FAILED(false),
    NULL_DATA(null);
}