package om.tech.sample.componeants

import android.app.Activity
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.remember
import androidx.compose.ui.composed
import androidx.compose.ui.text.style.TextDecoration
import om.tech.sample.R
import om.tech.sample.utils.CommonUtil
import om.tech.sample.utils.Constants


@Composable
fun TopBar1(title: String = "") {
    var context=LocalContext.current as Activity
    TopAppBar(
        title = {
            Text(text = title,color= colorResource(id = R.color.white))
        },
        navigationIcon = {
            Row{
            IconButton(onClick = {context.finish() }) {
                Icon(
                    imageVector = Icons.Filled.ChevronLeft,
                    contentDescription ="Go Back", tint = Color.White
                )
            }
//            TextRegular(text = "Back")
            }
        },
        backgroundColor = colorResource(id = R.color.purple_500)
    )
}




@Composable
fun TopBar(title: String = "") {
    var context=LocalContext.current as Activity
    TopAppBar(
        title = {
            Row(modifier = Modifier.fillMaxWidth().padding(end=10.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = title,color= colorResource(id = R.color.white))
            Text(text = "Count: ${CommonUtil.getCount(context)}",color= colorResource(id = R.color.white))
            }
        },

        backgroundColor = colorResource(id = R.color.purple_500)
    )
}


@Composable
fun ShowError(error:String?, context: Context,type:Int=0,
              retryFun:() -> Unit={})
{
    var errorMsg=error
    if(error==null)
        errorMsg= stringResource(id = R.string.serverUnknownError)
    Column(modifier= Modifier
        .background(colorResource(id = R.color.white))
        .fillMaxSize()
        .padding(20.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
        errorMsg?.let {
            Text(text= it, color = colorResource(id = R.color.gray)) }
        Spacer(modifier = Modifier
            .height(20.dp)
        )
        when(type) {
            Constants.CLOSE_BUTTON ->
                SetButton(
                    scaffoldState = null,
                    lable = "Close",
                    Modifier.wrapContentWidth()
                ) { (context as Activity).finish() }
            Constants.RETRY_BUTTON -> {
                ButtonWrapContent(
                    scaffoldState = null,
                    label = "Retry",
                    painterResource = null,
                    false,
                    Modifier.wrapContentWidth(),
                    function = retryFun
                )
            }
        }
    }
}

fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
    clickable(indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}

@Composable()
fun Error(clickEvent:()->Unit)
{
    Row(modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(6))
        .background(colorResource(id = R.color.red_200))
        .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(Icons.Default.Error, contentDescription = "", tint = Color.White)
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = "Some Error Occured", fontSize = 12.sp, modifier = Modifier.weight(1f), color = Color.White)
        Text(text = "Retry", fontSize = 16.sp, textDecoration = TextDecoration.Underline, color = colorResource(id = R.color.blue_100), modifier = Modifier.clickable{
            clickEvent()
        })
    }
}

