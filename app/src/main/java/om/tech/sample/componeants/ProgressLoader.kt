package om.tech.sample.componeants

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import om.tech.sample.R
@Composable
fun ShowProgress(msg:String=""){
    Box(contentAlignment = Alignment.Center, modifier = Modifier
        .background(
            colorResource(id = R.color.white1)
        )
        .fillMaxSize()) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator(color= colorResource(id = R.color.gray))
            Spacer(modifier = Modifier.height(30.dp))
//            Text(text = msg, fontFamily = mulishMediumBold,color= colorResource(id = R.color.bgColor2))
            Text(text = msg, color= colorResource(id = R.color.gray), textAlign = TextAlign.Center)
        }
    }
}



@Composable
fun PaggingProgress()
{
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        CircularProgressIndicator(
            color= colorResource(id = R.color.gray),
            modifier = Modifier.align(
                Alignment.Center
            )
        )
    }
}