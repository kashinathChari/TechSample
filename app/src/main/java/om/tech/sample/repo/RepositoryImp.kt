package om.tech.sample.repo

import android.app.Application
import android.util.Log
import om.tech.sample.R
import om.tech.sample.beans.DataBean
import om.tech.sample.networkService.RetroServices
import om.tech.sample.utils.Constants
import om.tech.sample.utils.StatusResources
import javax.inject.Inject

class RepositoryImp @Inject constructor(private val myApi: RetroServices, private val context:Application):
    Repository {



    override suspend fun getUserData(): StatusResources<DataBean> {
        lateinit var  statusResponse: StatusResources<DataBean>
        try {
            var response = myApi.getUsers()
            if (response.isSuccessful &&response.body() != null) {
                Log.i("Test--","Repo Data "+response.body()!!)
                var data=response.body()!!
                statusResponse= StatusResources.onSuccess(data)
            }
            else {
                statusResponse= StatusResources.onError(context.getString(R.string.serverUnknownError),
                    null)
            }
        }catch (ex:Exception){
            Log.d(Constants.TAG,"Exemption $ex")
            statusResponse= StatusResources.onError(context.getString(R.string.serverUnknownError), null)
        }
        return statusResponse
    }


}