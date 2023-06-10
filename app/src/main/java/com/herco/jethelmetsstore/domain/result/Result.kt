package com.herco.jethelmetsstore.domain.result

//sealed class Resource<out T> {
//    data class Success<out T>(val data: T) : Resource<T>()
//    data class Error(val message: String) : Resource<Nothing>()
//}

//sealed class Resource<T>(val data: T? = null, val message: String? = null, val error: Boolean = false) {
//    class Success<T>(data: T) : Resource<T>(data)
//    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
//}
sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val message: String) : Resource<Nothing>()
}