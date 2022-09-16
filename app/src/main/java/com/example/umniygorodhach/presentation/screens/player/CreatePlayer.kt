package com.example.umniygorodhach.presentation.screens.player

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.umniygorodhach.R
import com.example.umniygorodhach.data.close.dao.player.PlayerEntity
import com.example.umniygorodhach.presentation.navigation.LocalNavController
import com.example.umniygorodhach.presentation.utils.AppScreen
import com.example.umniygorodhach.presentation.utils.singletonViewModel

@Composable
fun CreatePlayer(
    playerViewModel: PlayerViewModel = singletonViewModel()
) {
    val navController = LocalNavController.current



    val lastname = rememberSaveable { mutableStateOf("") }
    val firstname = rememberSaveable { mutableStateOf("") }
    val middlename = rememberSaveable { mutableStateOf("") }
    val age = rememberSaveable { mutableStateOf("") }


    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = rememberScaffoldState(snackbarHostState = playerViewModel.snackbarHostState)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(25.dp)

        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier

                    .fillMaxWidth()
            ) {

                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    value = lastname.value,
                    onValueChange = {
                        lastname.value = it
                    },
                    placeholder = { Text(text = stringResource(R.string.lastname)) },
                    singleLine = true,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = MaterialTheme.colors.background,
                        focusedBorderColor = MaterialTheme.colors.onSurface

                    ),
                    modifier = Modifier
                        .fillMaxWidth()

                )


            }
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = firstname.value,
                onValueChange = {
                    firstname.value = it
                },
                placeholder = { Text(text = stringResource(R.string.firstname)) },
                singleLine = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = MaterialTheme.colors.background,
                    focusedBorderColor = MaterialTheme.colors.onSurface

                ),
                modifier = Modifier
                    .fillMaxWidth()

            )
            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value = middlename.value,
                onValueChange = {
                    middlename.value = it
                },
                placeholder = { Text(text = stringResource(R.string.middlename)) },
                singleLine = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = MaterialTheme.colors.background,
                    focusedBorderColor = MaterialTheme.colors.onSurface

                ),
                modifier = Modifier
                    .fillMaxWidth()

            )
            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value = age.value,
                onValueChange = {
                    age.value = it
                },
                placeholder = { Text(text = stringResource(R.string.age)) },
                singleLine = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = MaterialTheme.colors.background,
                    focusedBorderColor = MaterialTheme.colors.onSurface

                ),
                modifier = Modifier
                    .fillMaxWidth(),


                )

            Spacer(Modifier.height(8.dp))

            Button(
                onClick = {
                    val player = PlayerEntity(
                        id = (lastname.value+firstname.value+middlename.value+age.value).hashCode(),
                        lastname = lastname.value,
                        firstname = firstname.value,
                        middlename = middlename.value,
                        age = age.value.toInt()
                    )
                    playerViewModel.createPlayer(player) { isSuccess ->
                        if (isSuccess) {
                            playerViewModel.onRefresh()
                            navController.popBackStack()

                        }
                    }
                },
                shape = RoundedCornerShape(5.dp)

            ) {
                Text(text = "Создать участника")
            }
        }




    }
}