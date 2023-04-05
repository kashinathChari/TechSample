package om.tech.sample.repo

import om.tech.sample.beans.DataBean
import om.tech.sample.utils.StatusResources

interface Repository {
    suspend fun getUserData(): StatusResources<DataBean>



}