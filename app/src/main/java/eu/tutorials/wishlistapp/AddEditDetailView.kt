package eu.tutorials.wishlistapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import eu.tutorials.wishlistapp.data.Wish
import kotlinx.coroutines.launch

@Composable
fun AddEditDetailView(
    id: Long,
    viewModel: WishViewModel,
    navController: NavController
) {
    val snackMessage= remember {
        mutableStateOf("")
    }
    val scope = rememberCoroutineScope()
    val scaffoldState= rememberScaffoldState()
    if (id!=0L){
        val wish=viewModel.getawishbyid(id).collectAsState(initial = Wish(0L,"",""))
        viewModel.wishliststate=wish.value.title
        viewModel.wishlistdescription=wish.value.description
    }
    else{
        viewModel.wishliststate=""
        viewModel.wishlistdescription=""
    }

    Scaffold(
        scaffoldState=scaffoldState,
        topBar = { AppBarView(title = if (id != 0L) stringResource(id = R.string.Update_Wish) else "Add Wish")
        {navController.navigateUp()}
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            WishTextField(
                label = "Title",
                value = viewModel.wishliststate,
                onValueChange = { viewModel.onwishllisttitlechange(it) }
            )
            Spacer(modifier = Modifier.height(10.dp))


            WishTextField(
                label = "Description",
                value = viewModel.wishlistdescription,
                onValueChange = {
                    viewModel.onwishllistdescriptionchange(it) }
            )
            Spacer(modifier = Modifier.height(10.dp))

            Button(onClick = {
                if(viewModel.wishliststate.isNotEmpty() && viewModel.wishlistdescription.isNotEmpty()){

                    if(id != 0L){
                        viewModel.updatewish(
                            Wish(
                                id=id,
                                title = viewModel.wishliststate.trim(),
                                description = viewModel.wishlistdescription.trim()
                            )
                        )
                    }
                    else{
                        viewModel.addwish(
                            Wish (
                            title = viewModel.wishliststate.trim(),
                            description = viewModel.wishlistdescription.trim()
                        )
                        )
                        snackMessage.value="Wish has been cretaed"
                    }
                }
                else{
                    snackMessage.value="Enter fields to create a Wish"
                }

                scope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(snackMessage.value)
                    navController.navigateUp()
                }
            }) {
                Text(text = if(id!=0L) stringResource(id = R.string.Update_Wish) else stringResource(
                    id = R.string.add_Wish
                ),
                 style = TextStyle(
                     fontSize = 18.sp
                 )
                    )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WishTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label, color = Color.Black) },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = colorResource(id = R.color.black),
            unfocusedBorderColor = colorResource(id = R.color.black),
            focusedLabelColor = colorResource(id = R.color.black),
            unfocusedLabelColor = colorResource(id = R.color.black),
            cursorColor = colorResource(id = R.color.white)
        )
    )
}
