package om.tech.sample.activities

import android.annotation.SuppressLint
import om.tech.sample.componeants.ShowError
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint
import om.tech.sample.R
import om.tech.sample.beans.UserBean
import om.tech.sample.componeants.ShowProgress
import om.tech.sample.componeants.TopBar
import om.tech.sample.componeants.TopBar1
import om.tech.sample.ui.theme.TechSampleTheme
import om.tech.sample.utils.CommonUtil
import om.tech.sample.utils.Constants
import om.tech.sample.utils.LoadImage
import om.tech.sample.utils.StatusResources
import om.tech.sample.viewModels.VM_MainScreen
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TechSampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                   MainScreenContent()
                }
            }
        }
        CommonUtil.saveCounter(this)
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TechSampleTheme {
        Greeting("Android")
    }
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreenContent()
{
    val vm = viewModel<VM_MainScreen>()
    val context= LocalContext.current
    Scaffold(topBar = { TopBar("User List") }) {
        Box {
            when (vm.response.value.status) {
                StatusResources.Status.EMPTY ->{
                    vm.fetchUserData()
                }
                StatusResources.Status.LOADING -> {
                    ShowProgress()
                }
                StatusResources.Status.SUCCESS -> {
                    var data=vm.response.value.resourceData
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .background(colorResource(id = R.color.white1))
                        .padding(10.dp)) {
                        data?.data?.let {userList ->
                            LazyColumn() {
                                items(userList){user ->
                                    ItemCard(user = user, context = context)
                                }
                            }
                        }
                    }
                }
                StatusResources.Status.ERROR -> {
                    ShowError(error = stringResource(id = R.string.serverUnknownError), context = context)
                }
                else -> { ShowError(error = stringResource(id = R.string.serverUnknownError), context = context)}
            }

        }
    }
}


@Composable
fun ItemCard(user:UserBean,context:Context)
{

    Card(elevation = 5.dp){
        Row(modifier= Modifier
            .clickable {
                context.startActivity(Intent(context.applicationContext,UserDetails::class.java).putExtra("user",user))
            }
          //.border(2.dp, color = colorResource(id = R.color.white))
            ){
            val image= LoadImage(context = context, url =user.avatar?:"" , defaultImg = Constants.DEFAULT_USER_IMAGE)
            image.value?.let { img ->
            Image(bitmap = img.asImageBitmap(),contentDescription = null,contentScale= ContentScale.FillBounds,
                modifier= Modifier
                    .width(100.dp)
                    .height(dimensionResource(id = R.dimen.homeBestCardSize)))}

            Column(modifier = Modifier
                .height(dimensionResource(id = R.dimen.homeBestCardSize))
                .weight(1f)
                .padding(10.dp), verticalArrangement = Arrangement.Top) {

                Row(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier=Modifier.weight(1f)) {
                        Text(
                            text = "${user.first_name} ${user.last_name}",
                            fontSize = 16.sp, fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = user.email)
                    }
                 
                }


            }
        }
    }
    Spacer(modifier =Modifier.height(10.dp))
}

