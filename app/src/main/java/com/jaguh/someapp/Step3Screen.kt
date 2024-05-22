package com.jaguh.someapp

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.jaguh.someapp.navigation.Step2

@Composable
fun Step3Screen(navController: NavController) {
    val context = LocalContext.current
    var mSelectedService1 by remember { mutableStateOf("") }
    var mSelectedService2 by remember { mutableStateOf("") }
    var mSelectedService3 by remember { mutableStateOf("") }
    Column (
        Modifier
            .fillMaxSize()
            .padding(start = 10.dp, end = 10.dp, top = 16.dp)
    ){
        Column (
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ){
            Text(
                text = "Step 3/4",
                color = Color.Gray
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Service 1")
        OutlinedTextField(
            value = mSelectedService1,
            onValueChange = { mSelectedService1 = it},
            label = { Text("Service 1") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(36.dp))
        Text(text = "Service 2")
        OutlinedTextField(
            value = mSelectedService2,
            onValueChange = { mSelectedService2 = it},
            label = { Text(text = "Service 2")},
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(36.dp))
        Text(text = "Service 3")
        OutlinedTextField(
            value = mSelectedService3,
            onValueChange = { mSelectedService3 = it},
            label = { Text(text = "Service 3")},
            modifier = Modifier.fillMaxWidth()
        )


        Spacer(modifier = Modifier.height(100.dp))

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Button(
                onClick = {
                          navController.navigate(Step2.route)
                },
                modifier = Modifier,
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    Color.Gray
                )
            ) {
                Text(text = "Back")

            }


            Button(
                onClick = {
                if (mSelectedService1.isNotEmpty() && mSelectedService2.isNotEmpty() && mSelectedService3.isNotEmpty()){

                    } else {
                        Toast.makeText(context, "Empty fields", Toast.LENGTH_SHORT).show()
                    }

                },
                modifier = Modifier,
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    if (mSelectedService1.isEmpty() && mSelectedService2.isEmpty() && mSelectedService3.isEmpty()){Color.Gray } else {Color.Blue}
                )
            ) {
                Text(text = "Next")

            }
        }

    }
}