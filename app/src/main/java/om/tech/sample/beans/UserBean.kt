package om.tech.sample.beans

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class UserBean(
    val avatar: String,
    val email: String,
    val first_name: String,
    val id: Int,
    val last_name: String
) : Parcelable