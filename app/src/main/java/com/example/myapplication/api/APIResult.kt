package com.example.myapplication.api.results

class APIResult<T> : ExceptionResult<T> {
    constructor(exception: Exception?) : super(exception!!)
    constructor(result: T) : super(result)

    override val isSuccess = null == exception
}

abstract class ExceptionResult<T> : Result<T> {
    var exception: Exception? = null
        private set

    protected constructor(exception: Exception) : super(null) {
        this.exception = exception
    }

    protected constructor(result: T?) : super(result)

    val exceptionMessage
        get() = if (null != exception) if (null != exception!!.localizedMessage) exception!!.localizedMessage!! else (if (null != exception!!.message) exception!!.message!! else exception.toString()) else "empty response"
}

abstract class Result<T> internal constructor(val result: T?) {
    open val isSuccess = null != result
}