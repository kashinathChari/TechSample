package om.tech.sample.activities

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import om.tech.sample.R
import om.tech.sample.activities.ui.theme.TechSampleTheme
import om.tech.sample.beans.UserBean
import om.tech.sample.componeants.TopBar1
import om.tech.sample.utils.Constants
import om.tech.sample.utils.LoadImage


class UserDetails : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TechSampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    UserDetailsContent()
                }
            }
        }
    }
}

@Composable
fun Greeting2(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    TechSampleTheme {
        Greeting2("Android")
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun UserDetailsContent(){
    val activity = LocalContext.current as Activity
    var user = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        activity.intent.getParcelableExtra("user",UserBean::class.java)
    } else {
        activity.intent.getParcelableExtra("user")
    }
    Scaffold(topBar = {TopBar1("User Details")}) {

        Column(modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white1))
            .padding(20.dp)) {
            Card(elevation = 8.dp, modifier = Modifier.fillMaxSize()) {
                Column(modifier = Modifier.padding(15.dp)) {
                    val image= LoadImage(context = activity, url =user?.avatar?:"" , defaultImg = Constants.DEFAULT_USER_IMAGE)
                    image.value?.let { img ->
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                        Image(
                            bitmap = img.asImageBitmap(),
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier
                                .width(150.dp)
                                .height(150.dp)
                                .clip(CircleShape)
                                .border(2.dp, Color.Gray, CircleShape)
                        )
                    }
                    }
                    Spacer( modifier=Modifier.height(20.dp))
                    Text(
                        text = "Name: ${user?.first_name} ${user?.last_name}",
                        fontSize = 16.sp, fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "EmailID: ${user?.email?:""}")
                }
            }
        }
    }
}