package om.tech.sample.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import om.tech.sample.beans.DataBean
import om.tech.sample.repo.Repository
import om.tech.sample.utils.StatusResources
import javax.inject.Inject

@HiltViewModel
class VM_MainScreen @Inject constructor(private val repo: Repository): ViewModel() {

    var response= mutableStateOf(StatusResources<DataBean>(StatusResources.Status.EMPTY))

    fun fetchUserData()
    {
        response.value=StatusResources.onLoading(null)
        viewModelScope.launch {
            response.value=repo.getUserData()
        }
    }

}