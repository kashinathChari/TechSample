package om.tech.sample.utils

data class StatusResources<T>(val status: Status, val resourceData:T?=null, val message:String?=null) {
    companion object{
        fun <T> onSuccess(data:T?): StatusResources<T> {
            return StatusResources(Status.SUCCESS,data,null)
        }

        fun <T> onError(msg:String,data:T?): StatusResources<T> {
            return StatusResources(Status.ERROR,data,msg)
        }
        fun <T> onExemption(msg:String, data:T?): StatusResources<T> {
            return StatusResources(Status.EXEMPTION,data,msg)
        }
        fun <T> onLoading(data:T? =null): StatusResources<T> {
            return StatusResources(Status.LOADING,null)
        }
        fun <T> onEmpty(data:T?): StatusResources<T> {
            return StatusResources(Status.EMPTY,data,null)
        }
    }

    enum class Status{
        SUCCESS,
        ERROR,
        LOADING,
        EXEMPTION,
        EMPTY
    }
}