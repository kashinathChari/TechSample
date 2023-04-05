package om.tech.sample.componeants

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import om.tech.sample.R

@Composable
fun SetButton(scaffoldState: ScaffoldState?=null, lable:String,
              modifier:Modifier=Modifier.fillMaxWidth(),
              color:Color=colorResource(R.color.purple_500), textColor:Color=colorResource(R.color.black),
              fontSize:TextUnit=18.sp, contentPadding: PaddingValues = ButtonDefaults.ContentPadding, function: () -> Unit)
    {
        val context=LocalContext.current
        val scope= rememberCoroutineScope()
        Button(
            shape = RoundedCornerShape(5.dp),
            modifier = modifier,
//                .height(60.dp),
//                .padding(top=2.dp, bottom = 25.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = color,
                contentColor = textColor),
            contentPadding=contentPadding,
                onClick = function) {
          //  Box(modifier = Modifier.padding(top=5.dp,bottom=5.dp)) {
                Text(
                   lable,
                    modifier = Modifier.padding(all =5.dp), fontSize = fontSize, fontWeight = FontWeight.Bold
                )
           // }
        }
    }



@Composable
fun FlatConnerButton(scaffoldState: ScaffoldState?=null,lable:String,modifier:Modifier=Modifier.fillMaxWidth(),function: () -> Unit)
{
    val context=LocalContext.current
    val scope= rememberCoroutineScope()
    Button(
        shape = RoundedCornerShape(0.dp),
        modifier = modifier,
//                .height(60.dp),
//                .padding(top=2.dp, bottom = 25.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.purple_500),
            contentColor = Color.White),
        onClick = function) {
        //  Box(modifier = Modifier.padding(top=5.dp,bottom=5.dp)) {
        Text(
            lable,
            modifier = Modifier.padding(all =5.dp), fontSize = 18.sp
        )
        // }
    }
}





@Composable
fun ButtonWrapContent(
    scaffoldState: ScaffoldState?=null,
    label: String,
    painterResource: Painter?=null,
    disable:Boolean=false,
    modifier:Modifier=Modifier.fillMaxWidth(),
    function: () -> Unit,

    )
{
    val context=LocalContext.current
    val scope= rememberCoroutineScope()
    Button(
        enabled = !disable,
        shape = RoundedCornerShape(10.dp),
        modifier = modifier,
//                .height(60.dp),
//                .padding(top=2.dp, bottom = 25.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = if(disable)colorResource(R.color.gray) else colorResource(R.color.purple_500),
            contentColor = Color.White),

        onClick = function) {
        Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
            painterResource?.let {
                Image(painter = painterResource, contentDescription ="" ,modifier= Modifier
                    .width(20.dp)
                    .height(20.dp))
            }
            Text(
                text=label,
                modifier = Modifier.padding(all = 5.dp),
                fontSize = 20.sp,
                color= if(disable)colorResource(id = R.color.gray)else colorResource(id = R.color.black)
//                ,fontFamily = mulishBold

            )
        }
    }
}


